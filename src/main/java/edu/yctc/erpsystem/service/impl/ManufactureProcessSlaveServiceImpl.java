package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.service.ManufactureProcessSlaveInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManufactureProcessSlaveVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 制造流程单逻辑接口实现
 *
 * @author zzy
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("manufactureProcessSlaveService")
public class ManufactureProcessSlaveServiceImpl implements ManufactureProcessSlaveInterService {

    private static final String IS_MATERIAL_EXPORT = "否";
    private static final String JOB_TICKET_NUMBER = "jobTicketNumber";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private MaterialInventoryMasterDAO materialInventoryMasterDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Override
    public ResultDO<PageUtils<ManufactureProcessSlaveVO>> getManufactureProcessSlave(Map<String, Object> params) {
        return CallbackUtils.getCallback("getManufactureProcessSlave", params.toString(), () -> {
            List<ManufactureProcessSlaveVO> result = new ArrayList<>();
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveDO = manufactureProcessSlaveDAO.getManufactureProcessSlave(params);
            if (manufactureProcessSlaveDO == null) {
                logger.error("getManufactureProcessSlave exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == manufactureProcessSlaveDO.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 得到所有制造流程单主表
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(manufactureProcessSlaveDO.stream().map(ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect(Collectors.toList()));
            Map<String, ManufactureProcessMasterDO> manufactureProcessMasterMap = manufactureProcessMasterList.stream().collect(Collectors.toMap(ManufactureProcessMasterDO::getId, manufactureProcessMaster -> manufactureProcessMaster));

            // 得到客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(manufactureProcessMasterList.stream().map(ManufactureProcessMasterDO::getCustomerOrderId).collect(Collectors.toList()));
            Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap(CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 得到所有客户管理信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map(CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap(CustomerDO::getId, customer -> customer));

            // 得到所有成品原始信息
            List<OriginalProductDO> originalProductList = originalProductDAO.getOriginalProductByIds(customerOrderList.stream().map(CustomerOrderDO::getOriginalProductId).collect(Collectors.toList()));
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap(OriginalProductDO::getId, originalProduct -> originalProduct));

            for (ManufactureProcessSlaveDO temp : manufactureProcessSlaveDO) {
                ManufactureProcessSlaveVO manufactureProcessSlaveVO = new ManufactureProcessSlaveVO();

                String tempCustomerOrderId = manufactureProcessMasterMap.get(temp.getManufactureProcessMasterId()).getCustomerOrderId();
                String tempCustomerId = customerOrderMap.get(tempCustomerOrderId).getCustomerId();
                String tempOriginalProductId = customerOrderMap.get(tempCustomerOrderId).getOriginalProductId();

                manufactureProcessSlaveVO.setId(temp.getId());
                manufactureProcessSlaveVO.setJobTicketNumber(temp.getJobTicketNumber());
                manufactureProcessSlaveVO.setEveryOrderAmount(customerOrderMap.get(tempCustomerOrderId).getEveryOrderAmount());
                manufactureProcessSlaveVO.setEveryAmount(temp.getEveryAmount());
                manufactureProcessSlaveVO.setCode(customerMap.get(tempCustomerId).getCode());
                manufactureProcessSlaveVO.setItemName(originalProductMap.get(tempOriginalProductId).getItemName());
                manufactureProcessSlaveVO.setSpec(originalProductMap.get(tempOriginalProductId).getSpec());

                result.add(manufactureProcessSlaveVO);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(manufactureProcessSlaveDAO.count(params), result));
        });
   }

    @Override
    public ResultDO<PageUtils<ManufactureProcessSlaveVO>> getManufactureProcessSlaveByMaterialInventoryMasterId(Map<String, Object> params) {
        String materialInventoryMasterId = (String) params.get("materialInventoryMasterId");
        if (StringUtils.isBlank(materialInventoryMasterId)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        // 获得所有数据
        Integer offset = (Integer) params.get("offset");
        Integer limit = (Integer) params.get("limit");
        params.remove("offset");
        params.remove("limit");

        return CallbackUtils.getCallback("getManufactureProcessSlaveByMaterialInventoryMasterId", params.toString(), () -> {
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveDO = new ArrayList<>();

            // 获得所有客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrder(null);
            // 获得所有制造流程单信息
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlave(null);
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMaster(null);
            // 获得所有材料原始信息
            List<OriginalProductDO> originalProductList = originalProductDAO.getOriginalProduct(null);

            MaterialInventoryMasterDO materialInventoryMasterDO = materialInventoryMasterDAO.getMaterialInventoryMasterById(materialInventoryMasterId);
            originalProductList.stream().filter(originalProductItem -> materialInventoryMasterDO.getMaterialInfoMasterId().equals(originalProductItem.getMaterialInfoMasterId())).forEach(originalProductItem ->
                    customerOrderList.stream().filter(customerOrderItem -> originalProductItem.getId().equals(customerOrderItem.getOriginalProductId())).forEach(customerOrderItem ->
                            manufactureProcessMasterList.stream().filter(manufactureProcessMasterItem -> customerOrderItem.getId().equals(manufactureProcessMasterItem.getCustomerOrderId())).forEach(manufactureProcessMasterItem ->
                                    manufactureProcessSlaveDO.addAll(manufactureProcessSlaveList.stream().filter(manufactureProcessSlaveItem -> manufactureProcessMasterItem.getId().equals(manufactureProcessSlaveItem.getManufactureProcessMasterId())
                                            && IS_MATERIAL_EXPORT.equals(manufactureProcessSlaveItem.getIsMaterialExport())).collect(Collectors.toList()))
                            )));

            List<ManufactureProcessSlaveVO> result = new ArrayList<>();

            // 如果没有则直接返回
            if (0 == manufactureProcessSlaveDO.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 得到所有制造流程单主表
            Map<String, ManufactureProcessMasterDO> manufactureProcessMasterMap = manufactureProcessMasterList.stream().collect(Collectors.toMap(ManufactureProcessMasterDO::getId, manufactureProcessMaster -> manufactureProcessMaster));

            // 得到客户订单信息
            Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap(CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 得到所有客户管理信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map(CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap(CustomerDO::getId, customer -> customer));

            // 得到所有成品原始信息
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap(OriginalProductDO::getId, originalProduct -> originalProduct));

            for (ManufactureProcessSlaveDO temp : manufactureProcessSlaveDO) {
                ManufactureProcessSlaveVO manufactureProcessSlaveVO = new ManufactureProcessSlaveVO();

                String tempCustomerOrderId = manufactureProcessMasterMap.get(temp.getManufactureProcessMasterId()).getCustomerOrderId();
                String tempCustomerId = customerOrderMap.get(tempCustomerOrderId).getCustomerId();
                String tempOriginalProductId = customerOrderMap.get(tempCustomerOrderId).getOriginalProductId();

                manufactureProcessSlaveVO.setId(temp.getId());
                manufactureProcessSlaveVO.setJobTicketNumber(temp.getJobTicketNumber());
                manufactureProcessSlaveVO.setEveryOrderAmount(customerOrderMap.get(tempCustomerOrderId).getEveryOrderAmount());
                manufactureProcessSlaveVO.setEveryAmount(temp.getEveryAmount());
                manufactureProcessSlaveVO.setCode(customerMap.get(tempCustomerId).getCode());
                manufactureProcessSlaveVO.setItemName(originalProductMap.get(tempOriginalProductId).getItemName());
                manufactureProcessSlaveVO.setSpec(originalProductMap.get(tempOriginalProductId).getSpec());

                result.add(manufactureProcessSlaveVO);
            }

            // 模糊查询jobTicketNumber
            String jobTicketNumber = params.get(JOB_TICKET_NUMBER).toString().toLowerCase();
            result = result.stream().filter(item -> null != item.getJobTicketNumber() && item.getJobTicketNumber().toLowerCase().contains(jobTicketNumber)).collect(Collectors.toList());

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> changeIsZeroProductInHouseWhenInsert(ManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("changeIsZeroProductInHouseWhenInsert", manufactureProcessSlaveVO.toString(), () -> manufactureProcessSlaveDAO.changeIsZeroProductInHouseByIdWhenInsert(manufactureProcessSlaveVO.getId()));
    }

    @Override
    public ResultDO<Void> changeIsZeroProductInHouseWhenDelete(ManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("changeIsZeroProductInHouseWhenDelete", manufactureProcessSlaveVO.toString(), () -> manufactureProcessSlaveDAO.changeIsZeroProductInHouseByIdWhenDelete(manufactureProcessSlaveVO.getId()));
    }

    @Override
    public ResultDO<Void> changeIsInHouseWhenInsert(ManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("changeIsInHouseWhenInsert", manufactureProcessSlaveVO.toString(), () -> manufactureProcessSlaveDAO.changeIsInHouseByIdWhenInsert(manufactureProcessSlaveVO.getId()));
    }

    @Override
    public ResultDO<Void> changeIsInHouseWhenDelete(ManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("changeIsInHouseWhenDelete", manufactureProcessSlaveVO.toString(), () -> manufactureProcessSlaveDAO.changeIsInHouseByIdWhenDelete(manufactureProcessSlaveVO.getId()));

    }

}
