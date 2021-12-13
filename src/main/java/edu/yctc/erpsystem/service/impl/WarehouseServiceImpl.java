package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.UserDAO;
import edu.yctc.erpsystem.dao.WarehouseDAO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.UserDO;
import edu.yctc.erpsystem.entity.WarehouseDO;
import edu.yctc.erpsystem.service.WarehouseInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.WarehouseVO;
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
 * 仓库管理逻辑接口实现
 *
 * @author qiang
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseInterService {

    // 过滤参数
    /** 管理员姓名 */
    private static final String HOUSE_MANAGER = "houseManager";

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");
    @Resource
    private WarehouseDAO warehouseDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public ResultDO<PageUtils<WarehouseVO>> getWarehouse(Map<String, Object> params) {
        return CallbackUtils.getCallback("getWarehouse", params.toString(), () -> {
            List<WarehouseVO> wareHouseViewList = new ArrayList<>();

            // 获得成品数量设置信息
            List<WarehouseDO> wareHouseList = warehouseDAO.getWarehouse(params);
            if (wareHouseList == null) {
                logger.error("getWarehouse exception, db error, params={}",params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            if (wareHouseList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, wareHouseViewList));
            }

            // 获得原始成品信息
            List<UserDO> userList = userDAO.getUserByIds(wareHouseList.stream().map(WarehouseDO::getSyUserId).collect(Collectors.toList()));
            Map<String, UserDO> userMap = userList.stream().collect(Collectors.toMap(UserDO::getId, user -> user));

            for (WarehouseDO tempWareHouse : wareHouseList) {
                WarehouseVO temp = new WarehouseVO();

                temp.setHouseManager(userMap.get(tempWareHouse.getSyUserId()).getName());
                temp.setSyUserId(tempWareHouse.getSyUserId());
                temp.setName(tempWareHouse.getName());
                temp.setType(tempWareHouse.getType());
                temp.setIsZero(tempWareHouse.getIsZero());
                temp.setAddress(tempWareHouse.getAddress());
                temp.setUse(tempWareHouse.getUse());
                temp.setCreateTime(tempWareHouse.getCreateTime());
                temp.setUpdateTime(tempWareHouse.getUpdateTime());
                temp.setId(tempWareHouse.getId());

                wareHouseViewList.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(warehouseDAO.count(params), wareHouseViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<WarehouseVO>> getWarehouseByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getWarehouseByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<WarehouseVO>> resultVO = getWarehouse(params);
            if (resultVO == null) {
                logger.error("getWareHouseByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<WarehouseVO> wareHouseViewList = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(HOUSE_MANAGER)) {
                wareHouseViewList = wareHouseViewList.stream().filter(item -> null != item.getHouseManager() && item.getHouseManager().toLowerCase().contains(params.get(HOUSE_MANAGER).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(wareHouseViewList.size(), wareHouseViewList.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(WarehouseDO warehouseDO) {
        if (StringUtils.isBlank(warehouseDO.getName()) || StringUtils.isBlank(warehouseDO.getType()) || StringUtils.isBlank(warehouseDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("warehouse", warehouseDO.toString(), () -> warehouseDAO.insert(warehouseDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("warehouse", id, () -> warehouseDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateWarehouse(WarehouseDO warehouseDO) {
        if (StringUtils.isBlank(warehouseDO.getName()) || StringUtils.isBlank(warehouseDO.getType()) || StringUtils.isBlank(warehouseDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateWarehouse", warehouseDO.toString(), () -> warehouseDAO.updateWareHouse(warehouseDO));
    }

}
