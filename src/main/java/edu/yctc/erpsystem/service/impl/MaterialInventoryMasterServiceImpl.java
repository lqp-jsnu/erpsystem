package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.bo.MaterialExportBO;
import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.MaterialInventoryMasterDetailModel;
import edu.yctc.erpsystem.excel.MaterialInventoryMasterModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.MaterialInventoryMasterInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialInventoryMasterVO;
import edu.yctc.erpsystem.vo.MaterialInventoryVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 材料库存逻辑接口实现
 *
 * @author wjd
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialInventoryMasterService")
public class MaterialInventoryMasterServiceImpl implements MaterialInventoryMasterInterService {

    // 过滤参数
    /** 规格 */
    private static final String SPEC = "spec";
    /** 品名 */
    private static final String ITEM_NAME = "itemName";

    private static final String IS_MATERIAL_EXPORT = "否";


    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private MaterialInventoryDAO materialInventoryDAO;

    @Resource
    private MaterialInventoryMasterDAO materialInventoryMasterDAO;

    @Resource
    private MaterialExportDAO materialExportDAO;

    @Resource
    private MaterialExportDetailDAO materialExportDetailDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Override
    public ResultDO<PageUtils<MaterialInventoryMasterVO>> getMaterialRepertory(Map<String, Object> params){
        return CallbackUtils.getCallback("getMaterialRepertory", params.toString(), () -> {
            List<MaterialInventoryMasterVO> result = new ArrayList<>();

            // 得到所有材料库存信息
            List<MaterialInventoryMasterDO> materialInventoryMasterList = materialInventoryMasterDAO.getMaterialInventoryMaster(params);
            if (materialInventoryMasterList == null) {
                logger.error("getMaterialRepertory exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == materialInventoryMasterList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有盘点数据
            List<MaterialInventoryVO> materialInventoryList = materialInventoryDAO.getMaterialInventory(null);
            // 获得所有客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrder(null);
            // 获得所有制造流程单信息
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlave(null);
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMaster(null);
            // 获得所有材料原始信息
            List<OriginalProductDO> originalProductList = originalProductDAO.getOriginalProduct(null);

            // 获得所有材料原始信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialInventoryMasterList.stream().map(MaterialInventoryMasterDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            DecimalFormat df = new DecimalFormat("0.00");
            for (MaterialInventoryMasterDO tempMaterialInventoryMaster : materialInventoryMasterList) {
                MaterialInventoryMasterVO temp = new MaterialInventoryMasterVO();

                List<MaterialInventoryVO> tempMaterialInventoryList = materialInventoryList.stream().filter(item -> tempMaterialInventoryMaster.getId().equals(item.getMaterialInventoryMasterId())).collect(Collectors.toList());

                float totalNumber = 0;
                temp.setId(tempMaterialInventoryMaster.getId());
                for (MaterialInventoryVO tempMaterialInventoryVO : tempMaterialInventoryList) {
                    totalNumber = totalNumber + Float.parseFloat(tempMaterialInventoryVO.getLeftAmount());
                }
                temp.setLeftAmount(df.format(totalNumber));

                if (tempMaterialInventoryList.size() != 0) {
                    temp.setCode(tempMaterialInventoryList.get(0).getCode());
                }

                temp.setItemName(materialInfoMasterMap.get(tempMaterialInventoryMaster.getMaterialInfoMasterId()).getItemName());
                temp.setSpec(materialInfoMasterMap.get(tempMaterialInventoryMaster.getMaterialInfoMasterId()).getSpec());

                final int[] number = {0};
                originalProductList.stream().filter(originalProductItem -> tempMaterialInventoryMaster.getMaterialInfoMasterId().equals(originalProductItem.getMaterialInfoMasterId())).forEach(originalProductItem ->
                        customerOrderList.stream().filter(customerOrderItem -> originalProductItem.getId().equals(customerOrderItem.getOriginalProductId())).forEach(customerOrderItem ->
                                manufactureProcessMasterList.stream().filter(manufactureProcessMasterItem -> customerOrderItem.getId().equals(manufactureProcessMasterItem.getCustomerOrderId())).forEach(manufactureProcessMasterItem ->
                                        number[0] += manufactureProcessSlaveList.stream().filter(manufactureProcessSlaveItem -> manufactureProcessMasterItem.getId().equals(manufactureProcessSlaveItem.getManufactureProcessMasterId())
                                                && IS_MATERIAL_EXPORT.equals(manufactureProcessSlaveItem.getIsMaterialExport())).count()
                                )));
                temp.setWaitNumber(number[0]);

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialInventoryMasterDAO.count(params), result));
        });
    }


    @Override
    public ResultDO<PageUtils<MaterialInventoryMasterVO>> getMaterialRepertoryByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialRepertoryByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialInventoryMasterVO>> resultVO = getMaterialRepertory(params);
            if (resultVO == null) {
                logger.error("getMaterialRepertoryByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialInventoryMasterVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(MaterialExportBO materialExportParam) {
        MaterialExportDO materialExportDO = materialExportParam.getMaterialExportDO();
        List<String> materialInventoryIds = materialExportParam.getMaterialInventoryIds();
        if (null == materialExportDO || null == materialInventoryIds || StringUtils.isBlank(materialExportDO.getDate().toString()) || StringUtils.isBlank(materialExportDO.getManufactureProcessSlaveId())
                || StringUtils.isBlank(materialExportDO.getMaterialInventoryMasterId()) || StringUtils.isBlank(materialExportDO.getNumber())
                || StringUtils.isBlank(materialExportDO.getIssueNumber()) || 0 == materialInventoryIds.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("insert", materialExportDO.toString(), () -> {
            manufactureProcessSlaveDAO.updateIsMaterialExport(materialExportDO.getManufactureProcessSlaveId(), "是");
            materialExportDAO.insert(materialExportDO);

            List<MaterialInventoryDO> materialInventoryList = new ArrayList<>();
            List<MaterialExportDetailDO> materialExportDetailList  = new ArrayList<>();
            List<MaterialInventoryDO> materialInventory = materialInventoryDAO.getMaterialInventoryByIds(materialInventoryIds);

            DecimalFormat df = new DecimalFormat("0.00");
            for (MaterialInventoryDO tempMaterialInventoryDO : materialInventory) {
                if (Float.parseFloat(tempMaterialInventoryDO.getLeftAmount()) < Float.parseFloat(materialExportDO.getIssueNumber())) {
                    MaterialExportDetailDO materialExportDetailDO = new MaterialExportDetailDO();
                    materialExportDetailDO.setAmount(tempMaterialInventoryDO.getLeftAmount());
                    materialExportDetailDO.setMaterialExportId(materialExportDO.getId());
                    materialExportDetailDO.setMaterialInventoryId(tempMaterialInventoryDO.getId());
                    materialExportDetailList.add(materialExportDetailDO);

                    MaterialInventoryDO tempMaterialInventory = new MaterialInventoryDO();
                    tempMaterialInventory.setLeftAmount("0.00");
                    tempMaterialInventory.setId(tempMaterialInventoryDO.getId());
                    materialInventoryList.add(tempMaterialInventory);

                    materialExportDO.setIssueNumber(df.format(Float.parseFloat(materialExportDO.getIssueNumber()) - Float.parseFloat(tempMaterialInventoryDO.getLeftAmount())));
                } else {
                    MaterialExportDetailDO materialExportDetailDO = new MaterialExportDetailDO();
                    materialExportDetailDO.setAmount(materialExportDO.getIssueNumber());
                    materialExportDetailDO.setMaterialExportId(materialExportDO.getId());
                    materialExportDetailDO.setMaterialInventoryId(tempMaterialInventoryDO.getId());
                    materialExportDetailList.add(materialExportDetailDO);

                    MaterialInventoryDO tempMaterialInventory = new MaterialInventoryDO();
                    tempMaterialInventory.setLeftAmount(df.format(Float.parseFloat(tempMaterialInventoryDO.getLeftAmount()) - Float.parseFloat(materialExportDO.getIssueNumber())));
                    tempMaterialInventory.setId(tempMaterialInventoryDO.getId());
                    materialInventoryList.add(tempMaterialInventory);

                    materialExportDO.setIssueNumber(df.format(0));
                }
            }

            materialInventoryDAO.updateAllLeftAmountByIds(materialInventoryList);
            materialExportDetailDAO.insertAll(materialExportDetailList);
        });
    }

    @Override
    public ResultDO<Void> exportExcel(MaterialInventoryMasterVO[] materialInventoryMasterVO) {
        if (null == materialInventoryMasterVO || 0 == materialInventoryMasterVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("MaterialInventoryMaster exportExcel", Arrays.toString(materialInventoryMasterVO), () -> {
            List<MaterialInventoryMasterModel> data = new ArrayList<>();

            int i = 1;
            for (MaterialInventoryMasterVO temp : materialInventoryMasterVO) {
                MaterialInventoryMasterModel item = new MaterialInventoryMasterModel();
                    item.setIndex(Integer.toString(i++));
                    item.setCode(temp.getCode());
                    item.setItemName(temp.getItemName());
                    item.setSpec(temp.getSpec());
                    item.setLeftAmount(temp.getLeftAmount());
                    data.add(item);
            }
            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(12);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(24.86*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(23.43*256));
            columnWidth.put(5,(int)(26.14*256));
            columnWidth.put(6,(int)(26.14*256));
            columnWidth.put(7,(int)(14.14*256));
            columnWidth.put(8,(int)(14.14*256));
            columnWidth.put(9,(int)(9.0*256));
            columnWidth.put(10,(int)(14.14*256));
            columnWidth.put(11,(int)(14.14*256));
            return MyExcel.write( ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_REPERTORY_FILE_NAME, MaterialInventoryMasterModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<Void> exportDetailExcel(List<MaterialInventoryMasterVO> materialInventoryMasterVO) {
        if (null == materialInventoryMasterVO || 0 == materialInventoryMasterVO.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("MaterialInventoryMaster exportDetailExcel", materialInventoryMasterVO.toString(), () -> {

            // 获得所有盘点数据
            List<MaterialInventoryVO> materialInventoryList = materialInventoryDAO.getMaterialInventory(null);

            int k = 1;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            List<MaterialInventoryMasterDetailModel> data = new ArrayList<>();
            for (MaterialInventoryMasterVO tempMaterialInventoryMasterVO : materialInventoryMasterVO) {

                List<MaterialInventoryVO> tempMaterialInventoryList = materialInventoryList.stream().filter(item -> tempMaterialInventoryMasterVO.getId().equals(item.getMaterialInventoryMasterId())).collect(Collectors.toList());
                for (MaterialInventoryVO tempMaterialInventory : tempMaterialInventoryList) {
                    MaterialInventoryMasterDetailModel item = new MaterialInventoryMasterDetailModel();

                    item.setIndex(Integer.toString(k++));
                    item.setCode(tempMaterialInventory.getCode());
                    item.setOrderNumber(tempMaterialInventory.getOrderNumber());
                    item.setInDate(format.format(tempMaterialInventory.getInDate()));
                    item.setInStorageDate(format.format(tempMaterialInventory.getInStorageDate()));
                    item.setLeftAmount(tempMaterialInventory.getLeftAmount());
                    item.setInAmount(tempMaterialInventory.getInAmount());
                    item.setUnitPrice(tempMaterialInventory.getUnitPrice());
                    item.setJine(tempMaterialInventory.getJine());
                    item.setHouseName(tempMaterialInventory.getHouseName());
                    item.setItemName(tempMaterialInventory.getItemName());
                    item.setSpec(tempMaterialInventory.getSpec());
                    item.setUnit(tempMaterialInventory.getUnit());

                    data.add(item);
                }
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(12);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(24.86*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(23.43*256));
            columnWidth.put(5,(int)(26.14*256));
            columnWidth.put(6,(int)(26.14*256));
            columnWidth.put(7,(int)(14.14*256));
            columnWidth.put(8,(int)(14.14*256));
            columnWidth.put(9,(int)(9.0*256));
            columnWidth.put(10,(int)(14.14*256));
            columnWidth.put(11,(int)(14.14*256));
            columnWidth.put(12,(int)(15.14*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_REPERTORY_DETAIL_FILE_NAME, MaterialInventoryMasterDetailModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
