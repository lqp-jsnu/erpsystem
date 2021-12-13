package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.MaterialInventoryModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.MaterialInventoryInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MainMaterialExpiryDateVO;
import edu.yctc.erpsystem.vo.MainMaterialNumberVO;
import edu.yctc.erpsystem.vo.MaterialInventoryVO;
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
 * 材料盘点逻辑接口实现
 *
 * @author lqp
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialInventoryService")
public class MaterialInventoryServiceImpl implements MaterialInventoryInterService {

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 采购单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 仓库名称 */
    private static final String HOUSE_NAME = "houseName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";

    /** 供应商id */
    private static final String SUPPLIER_ID = "supplierId";
    /** 仓库id */
    private static final String WAREHOUSE_ID = "warehouseId";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private MaterialInventoryDAO materialInventoryDAO;

    @Resource
    private MaterialInventoryMasterDAO materialInventoryMasterDAO;

    @Resource
    private MaterialExpiryDateSetDAO materialExpiryDateSetDAO;

    @Resource
    private MaterialNumberSetDAO materialNumberSetDAO;

    @Override
    public ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventory(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialInventory", params.toString(), () -> new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialInventoryDAO.count(params), materialInventoryDAO.getMaterialInventory( params ))));
    }

    @Override
    public ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialInventoryByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialInventoryVO>> resultVO = getMaterialInventory(params);
            if (resultVO == null) {
                logger.error("getMaterialInventoryByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialInventoryVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(HOUSE_NAME)) {
                result = result.stream().filter(item -> null != item.getHouseName() && item.getHouseName().toLowerCase().contains(params.get(HOUSE_NAME).toString().toLowerCase())).collect(Collectors.toList());
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
    public ResultDO<Void> exportExcel(MaterialInventoryVO[] materialInventories) {
        if (null == materialInventories || 0 == materialInventories.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("MaterialInventory exportExcel", Arrays.toString(materialInventories), () -> {
            List<MaterialInventoryModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (MaterialInventoryVO temp : materialInventories) {
                MaterialInventoryModel item = new MaterialInventoryModel();
                item.setIndex(Integer.toString(i++));
                item.setCode(temp.getCode());
                item.setOrderNum(temp.getOrderNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setInDate(format.format(temp.getInDate()));
                item.setInStorageDate(format.format(temp.getInStorageDate()));
                item.setLeftAmount(temp.getLeftAmount());
                item.setUnitPrice(temp.getUnitPrice());
                item.setUnit(temp.getUnit());
                item.setJine(temp.getJine());
                item.setHouseName(temp.getHouseName());
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
            return MyExcel.write( ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_INVENTORY_FILE_NAME, MaterialInventoryModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryBySearchSuppId(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialInventoryBySearchSuppId", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            List<MaterialInventoryVO> result = materialInventoryDAO.getMaterialInventory(params);

            if (result == null) {
                logger.error("getMaterialInventoryBySearchSupCode exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有则直接返回
            if (0 == result.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 模糊查询spec
            String spec = params.get(SPEC).toString().toLowerCase();
            result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(spec)).collect(Collectors.toList());

            // 模糊查询supplierId
            String supplierId = params.get(SUPPLIER_ID).toString();
            result = result.stream().filter(item -> null != item.getSupplierId() && item.getSupplierId().equals(supplierId)).collect(Collectors.toList());

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<PageUtils<MainMaterialExpiryDateVO>> getMainMaterialExpiryDate(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMainMaterialExpiryDate", params.toString(), () -> {
            List<MainMaterialExpiryDateVO> mainMaterialExpiryDateViewList = new ArrayList<>();

            // 用来获得材料保质期设置所有信息参数
            Map<String, Object> materialExpiryDateParams = new HashMap<>(0);
            // 得到材料库存信息
            List<MaterialInventoryVO> materialInventoryList = materialInventoryDAO.getMaterialInventory(params);
            if (materialInventoryList == null) {
                logger.error("getMaterialInventories exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (materialInventoryList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, mainMaterialExpiryDateViewList));
            }

            // 得到所有材料保质期设置信息
            List<MaterialExpiryDateSetDO> materialExpiryDateSetList = materialExpiryDateSetDAO.getMaterialDateSet(materialExpiryDateParams);

            for (MaterialInventoryVO tempMaterialInventory : materialInventoryList) {
                MainMaterialExpiryDateVO temp = new MainMaterialExpiryDateVO();

                temp.setItemName(tempMaterialInventory.getItemName());
                temp.setSpec(tempMaterialInventory.getSpec());
                temp.setMaterialInfoMasterId(tempMaterialInventory.getMaterialInfoMasterId());
                temp.setStorageDate(tempMaterialInventory.getInStorageDate());
                temp.setId(tempMaterialInventory.getId());

                mainMaterialExpiryDateViewList.add(temp);
            }

            // 获取当前日期
            Date todayDate = new Date();
            // 剩余天数
            Integer leaveDate;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.format(todayDate);
            if (0 == materialExpiryDateSetList.size()) {
                for(MainMaterialExpiryDateVO tempMainMaterialExpiryDate : mainMaterialExpiryDateViewList) {
                    tempMainMaterialExpiryDate.setResult("未设置预警值");
                }
            } else {
                for (MainMaterialExpiryDateVO tempMainMaterialExpiryDate : mainMaterialExpiryDateViewList) {
                    for (MaterialExpiryDateSetDO tempMaterialExpiryDateSet : materialExpiryDateSetList) {
                        if (tempMainMaterialExpiryDate.getMaterialInfoMasterId().equals(tempMaterialExpiryDateSet.getMaterialInfoMasterId())) {
                            long days = (todayDate.getTime() - tempMainMaterialExpiryDate.getStorageDate().getTime() + 1000000) / (60 * 60 * 24 * 1000);
                            if (days <= tempMaterialExpiryDateSet.getExpiryDate()) {
                                leaveDate = tempMaterialExpiryDateSet.getExpiryDate() - (int) days;
                                tempMainMaterialExpiryDate.setDate("还有" + leaveDate + "天过期");
                                tempMainMaterialExpiryDate.setResult("正常");
                                tempMainMaterialExpiryDate.setLeaveDate(leaveDate);
                            } else {
                                leaveDate = (int) days - tempMaterialExpiryDateSet.getExpiryDate();
                                tempMainMaterialExpiryDate.setResult("产品已过期");
                                tempMainMaterialExpiryDate.setDate("已过期" + leaveDate + "天");
                                tempMainMaterialExpiryDate.setLeaveDate(leaveDate);
                            }
                            break;
                        } else {
                            tempMainMaterialExpiryDate.setResult("未设置预警值");
                        }
                    }
                }
            }


            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,new PageUtils<>(materialInventoryDAO.count(params), mainMaterialExpiryDateViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<MainMaterialNumberVO>> getMainMaterialNumber(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMainMaterialNumber", params.toString(), () -> {
            List<MainMaterialNumberVO> mainMaterialNumberViewList = new ArrayList<>();

            // 用来获得材料数量设置所有信息参数
            Map<String, Object> maNumberParams = new HashMap<>(0);
            // 得到材料库存master信息
            List<MaterialInventoryMasterDO> materialInventoryMasterList = materialInventoryMasterDAO.getMaterialInventoryMaster(params);
            if ( materialInventoryMasterList == null) {
                logger.error("getMaterialInventories exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (materialInventoryMasterList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, mainMaterialNumberViewList));
            }
            //得到所有材料数量设置信息
            List<MaterialNumberSetDO> materialNumberSetList = materialNumberSetDAO.getMaterialNumberSet(maNumberParams);

            // 得到所有原材料信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialInventoryMasterList.stream().map(MaterialInventoryMasterDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            // 用来记录库存数量
            float leftAmount = 0;
            DecimalFormat df = new DecimalFormat("0.00");
            for (MaterialInventoryMasterDO tempMaterialInventoryMaster:materialInventoryMasterList) {
                List<MaterialInventoryDO> materialInventoryList = materialInventoryDAO.getMaterialInventoryByMasterId(tempMaterialInventoryMaster.getId());
                for (MaterialInventoryDO tempMaterialInventory:materialInventoryList) {
                    leftAmount += Float.parseFloat(tempMaterialInventory.getLeftAmount());
                }
                MainMaterialNumberVO temp=new MainMaterialNumberVO();

                temp.setSpec(materialInfoMasterMap.get(tempMaterialInventoryMaster.getMaterialInfoMasterId()).getSpec());
                temp.setItemName(materialInfoMasterMap.get(tempMaterialInventoryMaster.getMaterialInfoMasterId()).getItemName());
                temp.setMaterialInfoMasterId(tempMaterialInventoryMaster.getMaterialInfoMasterId());
                temp.setLeftAmount(df.format(leftAmount));
                temp.setId(tempMaterialInventoryMaster.getId());

                mainMaterialNumberViewList.add(temp);
                leftAmount = 0;
            }

            if (0 == materialNumberSetList.size()) {
                for (MainMaterialNumberVO tempMainMaterialNumber : mainMaterialNumberViewList) {
                    tempMainMaterialNumber.setResult("未设置预警值");
                }
            } else {
                for (MainMaterialNumberVO tempMainMaterialNumber:mainMaterialNumberViewList) {
                    for (MaterialNumberSetDO tempMaterialNumberSet:materialNumberSetList) {
                        if (tempMainMaterialNumber.getMaterialInfoMasterId().equals(tempMaterialNumberSet.getMaterialInfoMasterId())) {
                            if (Float.parseFloat(tempMainMaterialNumber.getLeftAmount())<Float.parseFloat(tempMaterialNumberSet.getMinNumber())) {
                                tempMainMaterialNumber.setResult("低于预警值");
                            } else if (Float.parseFloat(tempMainMaterialNumber.getLeftAmount())> Float.parseFloat(tempMaterialNumberSet.getMaxNumber())) {
                                tempMainMaterialNumber.setResult("高于预警值");
                            } else {
                                tempMainMaterialNumber.setResult("正常");
                            }
                            break;
                        } else {
                            tempMainMaterialNumber.setResult("未设置预警值");
                        }
                    }
                }
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,new PageUtils<>(materialInventoryMasterDAO.count(params), mainMaterialNumberViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryBySearchWarehouse(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialInventoryBySearchWarehouse", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            List<MaterialInventoryVO> result = materialInventoryDAO.getMaterialInventory(params);

            if (result == null) {
                logger.error("getMaterialInventoryBySearchSupCode exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有则直接返回
            if (0 == result.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 模糊查询spec
            String spec = (params.get(SPEC)).toString().toLowerCase();
            result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(spec)).collect(Collectors.toList());

            // warehouseId
            String warehouseId = params.get(WAREHOUSE_ID).toString();
            result = result.stream().filter(item -> null != item.getWarehouseId() && item.getWarehouseId().equals(warehouseId)).collect(Collectors.toList());

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

}
