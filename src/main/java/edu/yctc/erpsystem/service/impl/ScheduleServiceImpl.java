package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.ScheduleModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.ScheduleInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ScheduleVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 未完结排程表
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleInterService {

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";
    /** 工作传票号码*/
    private static final String JOB_TICKET_NUMBER = "jobTicketNumber";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ScheduleDAO scheduleDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private OriginalProductDAO originalproductDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private MaterialExportDAO materialExportDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Override
    public ResultDO<PageUtils<ScheduleVO>> getSchedule(Map<String, Object> params) {
        return CallbackUtils.getCallback("getSchedule", params.toString(), () -> {
            List<ScheduleVO> result = new ArrayList<>();

            // 得到所有未完结排程信息
            List<ScheduleDO> scheduleList = scheduleDAO.getSchedule(params);
            if (scheduleList == null) {
                logger.error("getOpenSchedule exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == scheduleList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            List<MaterialExportDO> materialExportList = materialExportDAO.getMaterialExportByIds(scheduleList.stream().map(ScheduleDO::getMaterialExportId).collect(Collectors.toList()));
            Map<String, MaterialExportDO> materialExportMap = materialExportList.stream().collect(Collectors.toMap(MaterialExportDO::getId, materialExport -> materialExport));

            // 制造流程单
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(materialExportList.stream().map(MaterialExportDO::getManufactureProcessSlaveId).collect(Collectors.toList()));
            Map<String, ManufactureProcessSlaveDO> manufactureProcessSlaveMap = manufactureProcessSlaveList.stream().collect(Collectors.toMap( ManufactureProcessSlaveDO::getId, manufactureProcessSlave -> manufactureProcessSlave));

            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(manufactureProcessSlaveList.stream().map(ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect(Collectors.toList()));
            Map<String, ManufactureProcessMasterDO> manufactureProcessMasterMap = manufactureProcessMasterList.stream().collect(Collectors.toMap( ManufactureProcessMasterDO::getId, manufactureProcessMaster -> manufactureProcessMaster));

            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(manufactureProcessMasterList.stream().map(ManufactureProcessMasterDO::getCustomerOrderId).collect(Collectors.toList()));
            Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap(CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 获得所有客户信息管理
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map(CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap( CustomerDO::getId, customer -> customer));

            // 得到所有成品原始信息
            List<OriginalProductDO> originalProductList = originalproductDAO.getOriginalProductByIds(customerOrderList.stream().map(CustomerOrderDO::getOriginalProductId).collect(Collectors.toList()));
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap( OriginalProductDO::getId, originalProduct-> originalProduct));

            for (ScheduleDO tempSchedule : scheduleList) {
                ScheduleVO temp = new ScheduleVO();

                String manufactureProcessSlaveId = materialExportMap.get(tempSchedule.getMaterialExportId()).getManufactureProcessSlaveId();
                String manufactureProcessMasterId = manufactureProcessSlaveMap.get(manufactureProcessSlaveId).getManufactureProcessMasterId();
                String customerOrderId = manufactureProcessMasterMap.get(manufactureProcessMasterId).getCustomerOrderId();
                String customerId = customerOrderMap.get(customerOrderId).getCustomerId();
                String originalProductId = customerOrderMap.get(customerOrderId).getOriginalProductId();

                temp.setCode(customerMap.get(customerId).getCode());
                temp.setJobTicketNumber(manufactureProcessSlaveMap.get(manufactureProcessSlaveId).getJobTicketNumber());
                temp.setEveryOrderAmount(manufactureProcessSlaveMap.get(manufactureProcessSlaveId).getEveryOrderAmount());
                temp.setId(tempSchedule.getId());
                temp.setCustomerId(customerMap.get(customerId).getId());
                temp.setJobTicketNumber(manufactureProcessSlaveMap.get(manufactureProcessSlaveId).getJobTicketNumber());
                temp.setItemName(originalProductMap.get(originalProductId).getItemName());
                temp.setSpec(originalProductMap.get(originalProductId).getSpec());
                temp.setDeliveryDate(tempSchedule.getDeliveryDate());
                temp.setRemark(originalProductMap.get(originalProductId).getRemark());
                temp.setQie(tempSchedule.getQie());
                temp.setHan(tempSchedule.getHan());
                temp.setRao(tempSchedule.getRao());
                temp.setTu(tempSchedule.getTu());
                temp.setChai(tempSchedule.getChai());
                temp.setIsFinish(tempSchedule.getIsFinish());
                temp.setCreateTime(tempSchedule.getCreateTime());
                temp.setOriginalProductId(originalProductId);
                temp.setMaterialExportId(tempSchedule.getMaterialExportId());
                temp.setManufactureProcessSlaveId(manufactureProcessSlaveId);
                temp.setId(tempSchedule.getId());
                temp.setCustomerOrderId(customerOrderId);

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(scheduleDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<ScheduleVO>> getScheduleByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getScheduleByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<ScheduleVO>> resultVO = getSchedule(params);
            if (resultVO == null) {
                logger.error("getOpenScheduleByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<ScheduleVO> result = resultVO.getModule().getRows();


            // 过滤
            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(JOB_TICKET_NUMBER)) {
                result = result.stream().filter(item -> null != item.getJobTicketNumber() && item.getJobTicketNumber().toLowerCase().contains(params.get(JOB_TICKET_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> updateSchedule(ScheduleDO scheduleDO) {
        if (StringUtils.isBlank(scheduleDO.getId()) || StringUtils.isBlank(scheduleDO.getQie()) || StringUtils.isBlank(scheduleDO.getRao())
                || StringUtils.isBlank(scheduleDO.getHan()) || StringUtils.isBlank(scheduleDO.getTu()) || StringUtils.isBlank(scheduleDO.getChai())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateSchedule", scheduleDO.toString(), () -> scheduleDAO.updateSchedule(scheduleDO));
    }

    @Override
    public ResultDO<Void> updateIsFinish(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateIsFinish", id, () -> scheduleDAO.updateIsFinish(id));
    }

    @Override
    public ResultDO<Void> exportExcel(ScheduleVO[] schedules) {
        if (null == schedules || 0 == schedules.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("Schedule exportExcel", Arrays.toString(schedules), () -> {
            List<ScheduleModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
            int i = 1;
            for (ScheduleVO temp : schedules) {
                ScheduleModel item = new ScheduleModel();
                item.setIndex( Integer.toString( i++ ) );
                item.setCode( temp.getCode() );
                item.setJobTicketNumber( temp.getJobTicketNumber() );
                item.setItemName( temp.getItemName() );
                item.setSpec( temp.getSpec() );
                item.setEveryOrderAmount( temp.getEveryOrderAmount() );
                if (temp.getDeliveryDate() == null) {
                    item.setDeliveryDate(" ");
                } else {
                    item.setDeliveryDate(format.format(temp.getDeliveryDate()));
                }
                item.setRemark( temp.getRemark() );
                item.setQie( temp.getQie() );
                item.setHan( temp.getHan() );
                item.setRao( temp.getRao() );
                item.setTu( temp.getTu() );
                item.setChai( temp.getChai() );
                item.setIsFinish( temp.getIsFinish() );
                if (temp.getCreateTime() == null) {
                    item.setCreateTime(" ");
                } else {
                    item.setCreateTime(format.format(temp.getCreateTime()));
                }
                data.add( item );
            }

            // 设置列宽
            Map<Integer, Integer> columnWidth = new HashMap<>( 15 );
            columnWidth.put( 0, (int) (4.57 * 256) );
            columnWidth.put( 1, (int) (10.14* 256) );
            columnWidth.put( 2, (int) (24.86 * 256) );
            columnWidth.put( 3, (int) (10.14 * 256) );
            columnWidth.put( 4, (int) (24.86 * 256) );
            columnWidth.put( 5, (int) (9.0 * 256) );
            columnWidth.put( 6, (int) (24.86 * 256) );
            columnWidth.put( 7, (int) (10.14* 256) );
            columnWidth.put( 8, (int) (8.71 * 256) );
            columnWidth.put( 9, (int) (8.71 * 256) );
            columnWidth.put( 10, (int) (8.71 * 256) );
            columnWidth.put( 11, (int) (8.71 * 256) );
            columnWidth.put( 12, (int) (8.71 * 256) );
            columnWidth.put( 13, (int) (8.71 * 256) );
            columnWidth.put( 14, (int) (24.86 * 256) );

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.OPEN_SCHEDULE_FILE_NAME, ScheduleModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
