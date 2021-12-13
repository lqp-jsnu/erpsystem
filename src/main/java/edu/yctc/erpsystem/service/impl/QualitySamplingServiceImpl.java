package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.QualitySamplingModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.QualitySamplingInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.QualitySamplingVO;
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
 * 质量抽检逻辑接口实现
 *
 * @author zzy
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("qualitySamplingService")
public class QualitySamplingServiceImpl implements QualitySamplingInterService {

    //过滤参数
    /** 订单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 客户代码 */
    private static final String CODE = "code";
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 规格 */
    private static final String SPEC = "spec";
    /** 包装员 */
    private static final String PACKAGE_OPERATOR = "packageOperator";
    /** 检验员 */
    private static final String CHECK_OPERATOR = "checkOperator";
    /** 工作传票号 */
    private static final String JOB_TICKET_NUMBER = "jobTicketNumber";
    /** 成品料号 */
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 检验结果 */
    private static final String SAMPLE_RESULT = "sampleResult";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private QualitySamplingDAO qualitySamplingDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public ResultDO<PageUtils<QualitySamplingVO>> getQualitySampling(Map<String, Object> params) {
        return CallbackUtils.getCallback("getQualitySampling", params.toString(), () -> {
            List<QualitySamplingVO> result = new ArrayList<>();

            // 得到所有质量抽检信息
            List<QualitySamplingDO> qualitySamplingList = qualitySamplingDAO.getQualitySampling(params);
            if (qualitySamplingList == null) {
                logger.error("getQualitySampling exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == qualitySamplingList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 得到所有制造流程单副表
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(qualitySamplingList.stream().map(QualitySamplingDO::getManufactureProcessSlaveId).collect(Collectors.toList()));
            Map<String, ManufactureProcessSlaveDO> manufactureProcessSlaveMap = manufactureProcessSlaveList.stream().collect(Collectors.toMap(ManufactureProcessSlaveDO::getId, manufactureProcessSlave -> manufactureProcessSlave));

            // 得到所有制造流程单主表
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(manufactureProcessSlaveList.stream().map(ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect(Collectors.toList()));
            Map<String, ManufactureProcessMasterDO> manufactureProcessMasterMap = manufactureProcessMasterList.stream().collect(Collectors.toMap(ManufactureProcessMasterDO::getId, manufactureProcessMaster -> manufactureProcessMaster));

            // 得到客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(manufactureProcessMasterList.stream().map(ManufactureProcessMasterDO::getCustomerOrderId).collect(Collectors.toList()));
            Map<String,CustomerOrderDO> customerOrderMap =customerOrderList.stream().collect(Collectors.toMap(CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 得到所有客户管理信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map(CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap(CustomerDO::getId, customer -> customer));

            // 得到所有成品原始信息
            List<OriginalProductDO> originalProductList = originalProductDAO.getOriginalProductByIds(customerOrderList.stream().map(CustomerOrderDO::getOriginalProductId).collect(Collectors.toList()));
            Map<String,OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap(OriginalProductDO::getId, originalProduct -> originalProduct));

            // 得到所有检验员
            List<UserDO> checkOperatorList = userDAO.getUserByIds(qualitySamplingList.stream().map(QualitySamplingDO::getCheckOperator).collect(Collectors.toList()));
            Map<String, UserDO> checkOperatorMap = checkOperatorList.stream().collect(Collectors.toMap(UserDO::getId, checkOperator -> checkOperator));

            // 得到所有包装员
            List<UserDO> packageOperatorList = userDAO.getUserByIds(qualitySamplingList.stream().map(QualitySamplingDO::getPackageOperator).collect(Collectors.toList()));
            Map<String, UserDO> packageOperatorMap = packageOperatorList.stream().collect(Collectors.toMap(UserDO::getId, packageOperator -> packageOperator));

            // 得到所有录入者
            List<UserDO> enterList = userDAO.getUserByIds(qualitySamplingList.stream().map(QualitySamplingDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap(UserDO::getId, enter -> enter));

            // 得到所有审核者
            List<UserDO> checkerList = userDAO.getUserByIds(qualitySamplingList.stream().map(QualitySamplingDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap(UserDO::getId, checker -> checker));

            for (QualitySamplingDO tempQualitySampling : qualitySamplingList) {
                QualitySamplingVO temp = new QualitySamplingVO();

                temp.setId(tempQualitySampling.getId());
                temp.setPackageOperator(packageOperatorMap.get(tempQualitySampling.getPackageOperator()).getName());
                if (null != tempQualitySampling.getChecker()) {
                    temp.setChecker(checkerMap.get(tempQualitySampling.getChecker()).getName());
                }
                temp.setEnter(enterMap.get(tempQualitySampling.getUser()).getName());
                temp.setCheckOperator(checkOperatorMap.get(tempQualitySampling.getCheckOperator()).getName());

                temp.setPackageOperatorId(tempQualitySampling.getPackageOperator());
                temp.setCheckOperatorId(tempQualitySampling.getCheckOperator());
                temp.setCheckerId(tempQualitySampling.getChecker());
                temp.setUserId(tempQualitySampling.getUser());

                temp.setJobTicketNumber(manufactureProcessSlaveMap.get(tempQualitySampling.getManufactureProcessSlaveId()).getJobTicketNumber());

                String tempManufactureProcessMasterId = manufactureProcessSlaveMap.get(tempQualitySampling.getManufactureProcessSlaveId()).getManufactureProcessMasterId();
                String tempCustomerOrderId = manufactureProcessMasterMap.get(tempManufactureProcessMasterId).getCustomerOrderId();
                String tempCustomerId = customerOrderMap.get(tempCustomerOrderId).getCustomerId();
                String tempOriginalProductId = customerOrderMap.get(tempCustomerOrderId).getOriginalProductId();

                temp.setSpec(originalProductMap.get(tempOriginalProductId).getSpec());
                temp.setItemName(originalProductMap.get(tempOriginalProductId).getItemName());
                temp.setProductNumber(originalProductMap.get(tempOriginalProductId).getProductNumber());
                temp.setCode(customerMap.get(tempCustomerId).getCode());
                temp.setOrderNumber(customerOrderMap.get(tempCustomerOrderId).getOrderNumber());
                temp.setSampleNumber(tempQualitySampling.getSampleNumber());
                temp.setCheckFlag(tempQualitySampling.getCheckFlag());
                temp.setSampleResult(tempQualitySampling.getSampleResult());
                temp.setPassNumber(tempQualitySampling.getPassNumber());
                temp.setFailNumber(tempQualitySampling.getFailNumber());
                temp.setAql(tempQualitySampling.getAql());
                temp.setCreateTime(tempQualitySampling.getCreateTime());
                temp.setPackageNumber(tempQualitySampling.getPackageNumber());
                temp.setManufactureProcessSlaveId(tempQualitySampling.getManufactureProcessSlaveId());
                temp.setRemark(tempQualitySampling.getRemark());

                result.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(qualitySamplingDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<QualitySamplingVO>> getQualitySamplingByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getQualitySamplingByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<QualitySamplingVO>> resultVO = getQualitySampling(params);

            if (resultVO == null) {
                logger.error("getQualitySamplingByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<QualitySamplingVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(PACKAGE_OPERATOR)) {
                result = result.stream().filter(item -> null != item.getPackageOperator() && item.getPackageOperator().toLowerCase().contains(params.get(PACKAGE_OPERATOR).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CHECK_OPERATOR)) {
                result = result.stream().filter(item -> null != item.getCheckOperator() && item.getCheckOperator().toLowerCase().contains(params.get(CHECK_OPERATOR).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(JOB_TICKET_NUMBER)) {
                result = result.stream().filter(item -> null != item.getJobTicketNumber() && item.getJobTicketNumber().toLowerCase().contains(params.get(JOB_TICKET_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(PRODUCT_NUMBER)) {
                result = result.stream().filter(item -> null != item.getProductNumber() && item.getProductNumber().toLowerCase().contains(params.get(PRODUCT_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SAMPLE_RESULT)) {
                result = result.stream().filter(item -> null != item.getSampleResult() && item.getSampleResult().toLowerCase().contains(params.get(SAMPLE_RESULT).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(QualitySamplingDO qualitySamplingDO) {
        if (StringUtils.isBlank(qualitySamplingDO.getManufactureProcessSlaveId()) || StringUtils.isBlank(qualitySamplingDO.getPassNumber())
                || StringUtils.isBlank(qualitySamplingDO.getAql()) || StringUtils.isBlank(qualitySamplingDO.getSampleNumber())
                || StringUtils.isBlank(qualitySamplingDO.getFailNumber()) || StringUtils.isBlank(qualitySamplingDO.getPassNumber())
                || StringUtils.isBlank(qualitySamplingDO.getSampleResult()) || StringUtils.isBlank(qualitySamplingDO.getCheckOperator())
                || StringUtils.isBlank(qualitySamplingDO.getPackageOperator())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("QualitySampling", qualitySamplingDO.toString(), () -> qualitySamplingDAO.insert(qualitySamplingDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("QualitySampling", id, () -> qualitySamplingDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateQualitySampling(QualitySamplingDO qualitySamplingDO) {
        if (StringUtils.isBlank(qualitySamplingDO.getId()) || StringUtils.isBlank(qualitySamplingDO.getManufactureProcessSlaveId())
                || StringUtils.isBlank(qualitySamplingDO.getPackageNumber()) || StringUtils.isBlank(qualitySamplingDO.getCheckOperator())
                || StringUtils.isBlank(qualitySamplingDO.getPackageOperator()) || StringUtils.isBlank(qualitySamplingDO.getSampleNumber())
                || StringUtils.isBlank(qualitySamplingDO.getSampleResult()) || StringUtils.isBlank(qualitySamplingDO.getAql())
                || StringUtils.isBlank(qualitySamplingDO.getPassNumber()) || StringUtils.isBlank(qualitySamplingDO.getFailNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateQualitySampling", qualitySamplingDO.toString(), () -> qualitySamplingDAO.updateQualitySampling(qualitySamplingDO));
    }

    @Override
    public ResultDO<Void> updateCheckerById(QualitySamplingDO qualitySamplingDO) {
        if (StringUtils.isBlank(qualitySamplingDO.getId()) || StringUtils.isBlank(qualitySamplingDO.getUser()) || StringUtils.isBlank(qualitySamplingDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateCheckerById", qualitySamplingDO.toString(), () -> qualitySamplingDAO.updateCheckerById(qualitySamplingDO));
    }

    @Override
    public ResultDO<Void> exportExcel(QualitySamplingVO[] qualitySamplingVO) {
        if (null == qualitySamplingVO || 0 == qualitySamplingVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("QualitySampling exportExcel", Arrays.toString(qualitySamplingVO), () -> {
            List<QualitySamplingModel> data = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i=1;
            for(QualitySamplingVO temp: qualitySamplingVO){
                QualitySamplingModel item =new QualitySamplingModel();
                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setJobTicketNumber(temp.getJobTicketNumber());
                item.setProductNumber(temp.getProductNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setPackageNumber(temp.getPackageNumber());
                item.setPacker(temp.getPackageOperator());
                item.setSampleNumber(temp.getSampleNumber());
                item.setAql(temp.getAql());
                item.setPassNumber(temp.getPassNumber());
                item.setFailNumber(temp.getFailNumber());
                item.setCheckOperator(temp.getCheckOperator());
                item.setSampleResult(temp.getSampleResult());
                item.setEnter(temp.getEnter());
                item.setChecker(temp.getChecker());
                item.setCheckFlag(temp.getCheckFlag());
                item.setCreateTime(format.format(temp.getCreateTime()));
                data.add(item);
            }
            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(20);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(9.0*256));
            columnWidth.put(6,(int)(10.14*256));
            columnWidth.put(7,(int)(8.71*256));
            columnWidth.put(8,(int)(9.0*256));
            columnWidth.put(9,(int)(10.14*256));
            columnWidth.put(10,(int)(8.71*256));
            columnWidth.put(11,(int)(23.43*256));
            columnWidth.put(12,(int)(23.43*256));
            columnWidth.put(13,(int)(8.71*256));
            columnWidth.put(14,(int)(23.43*256));
            columnWidth.put(15,(int)(23.43*256));
            columnWidth.put(16,(int)(23.43*256));
            columnWidth.put(17,(int)(8.71*256));
            columnWidth.put(18,(int)(23.43*256));
            columnWidth.put(19,(int)(23.43*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.QUALITY_SAMPLING_FILE_NAME, QualitySamplingModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
