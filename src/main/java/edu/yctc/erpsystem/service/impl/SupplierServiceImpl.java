package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.SupplierDAO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.SupplierDO;
import edu.yctc.erpsystem.excel.SupplierModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.SupplierInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 供应商逻辑接口实现
 *
 * @author lqp
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("supplierService")
public class SupplierServiceImpl implements SupplierInterService {

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private SupplierDAO supplierDAO;

    @Override
    public ResultDO<PageUtils<SupplierDO>> getSuppliers(Map<String, Object> params) {
        return CallbackUtils.getCallback("getSupplier", params.toString(), () -> {
            List<SupplierDO> result = supplierDAO.getSupplier(params);
            if (result == null) {
                logger.error("getSupplier exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(supplierDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<Void> insert(SupplierDO supplierDO) {
        if (StringUtils.isBlank(supplierDO.getCode()) || StringUtils.isBlank(supplierDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("Supplier", supplierDO.toString(), () -> supplierDAO.insert(supplierDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("Supplier", id, () -> supplierDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateSupplier(SupplierDO supplierDO) {
        if (StringUtils.isBlank(supplierDO.getCode()) || StringUtils.isBlank(supplierDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateSupplier", supplierDO.toString(), () -> supplierDAO.updateSupplier(supplierDO));
    }

    @Override
    public ResultDO<Void> exportExcel(SupplierDO[] supplierDO) {
        if (null == supplierDO  || 0 == supplierDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("Supplier exportExcel", Arrays.toString(supplierDO), () -> {
            List<SupplierModel> data = new ArrayList<>();

            int i = 1;
            for (SupplierDO temp : supplierDO) {
                SupplierModel item = new SupplierModel();

                item.setIndex(Integer.toString(i++));
                item.setCode(temp.getCode());
                item.setName(temp.getName());
                item.setProductCategory(temp.getProductCategory());
                item.setContact(temp.getContact());
                item.setMobileTelephone(temp.getMobileTelephone());
                item.setFixedTelephone(temp.getFixedTelephone());
                item.setFax(temp.getFax());
                item.setEmail(temp.getEmail());
                item.setAddress(temp.getAddress());
                item.setDeliveryTime(temp.getDeliveryTime());
                item.setPayment(temp.getPayment());
                item.setRemark(temp.getRemark());

                data.add(item);
            }

            Map<Integer,Integer> columnWidth = new HashMap<>(13);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(30.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(18.86*256));
            columnWidth.put(6,(int)(21.86*256));
            columnWidth.put(7,(int)(21.86*256));
            columnWidth.put(8,(int)(21.86*256));
            columnWidth.put(9,(int)(24.86*256));
            columnWidth.put(10,(int)(13.0*256));
            columnWidth.put(11,(int)(10.14*256));
            columnWidth.put(12,(int)(30.71*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + "供应商信息.xlsx", SupplierModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
