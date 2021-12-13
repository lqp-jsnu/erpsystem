package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.CustomerDAO;
import edu.yctc.erpsystem.entity.CustomerDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.excel.CustomerModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.CustomerInterService;
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
 * 客户信息逻辑接口实现
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("customerService")
public class CustomerServiceImpl implements CustomerInterService {

    /** excel文件名 */
    private static final String EXCEL_FILE_NAME = "customer.xlsx";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private CustomerDAO customerDAO;

    @Override
    public ResultDO<PageUtils<CustomerDO>> getCustomer(Map<String, Object> params) {
        return CallbackUtils.getCallback("getCustomer", params.toString(), () -> {
            // 获得所有客户信息
            List<CustomerDO> customerList = customerDAO.getCustomer(params);
            if (customerList == null) {
                logger.error("getCustomer exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(customerDAO.count(params), customerList));
        });
    }

    @Override
    public ResultDO<Void> insert(CustomerDO customerDO) {
        if (StringUtils.isBlank(customerDO.getCode()) || StringUtils.isBlank(customerDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("Customer", customerDO.toString(), () -> customerDAO.insert(customerDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("Customer", id, () -> customerDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateCustomer(CustomerDO customerDO) {
        if (StringUtils.isBlank(customerDO.getCode()) || StringUtils.isBlank(customerDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateCustomer", customerDO.toString(), () -> customerDAO.updateCustomer(customerDO));
    }

    @Override
    public ResultDO<Void> exportExcel(CustomerDO[] customerDO) {
        if (null == customerDO || 0 == customerDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("Customer exportExcel", Arrays.toString(customerDO), () -> {
            List<CustomerModel> data = new ArrayList<>();

            int i = 1;
            for (CustomerDO temp : customerDO) {
                CustomerModel item = new CustomerModel();
                item.setIndex(Integer.toString(i++));
                item.setCode(temp.getCode());
                item.setName(temp.getName());
                item.setAddress(temp.getAddress());
                item.setSupplierCode(temp.getSupplierCode());
                item.setContact(temp.getContact());
                item.setMobileTelephone(temp.getMobileTelephone());
                item.setFixedTelephone(temp.getFixedTelephone());
                item.setFax(temp.getFax());
                item.setEmail(temp.getEmail());
                item.setPayType(temp.getPayType());
                item.setDeliveryTime(temp.getDeliveryTime());
                item.setRemark(temp.getRemark());

                data.add(item);
            }
            // 设置excel格式
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

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.CUSTOMER_FILE_NAME, CustomerModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<Void> importExcel() {
        try {
            ResultDO<List<Object>> resultDO = MyExcel.read(ConstantHolder.FILE_SAVE_PATH + EXCEL_FILE_NAME, CustomerModel.class);

            if (resultDO.isSuccess()) {
                List<CustomerDO> customerList = new ArrayList<>();

                Date date = new Date();
                for (Object temp : resultDO.getModule()) {
                    CustomerDO customerDO = new CustomerDO();

                    CustomerModel customerModel = (CustomerModel)(temp);
                    if (StringUtils.isNumeric(customerModel.getIndex()) && !StringUtils.isBlank(customerModel.getCode()) && !StringUtils.isBlank(customerModel.getCode())) {
                        customerDO.setCode(customerModel.getCode());
                        customerDO.setName(customerModel.getName());
                        customerDO.setAddress(customerModel.getAddress());
                        customerDO.setSupplierCode(customerModel.getSupplierCode());
                        customerDO.setContact(customerModel.getContact());
                        customerDO.setMobileTelephone(customerModel.getMobileTelephone());
                        customerDO.setFixedTelephone(customerModel.getFixedTelephone());
                        customerDO.setFax(customerModel.getFax());
                        customerDO.setEmail(customerModel.getEmail());
                        customerDO.setPayType(customerModel.getPayType());
                        customerDO.setDeliveryTime(customerModel.getDeliveryTime());
                        customerDO.setRemark(customerModel.getRemark());
                        customerDO.setCreateTime(date);
                        customerDO.setUpdateTime(date);

                        customerList.add(customerDO);
                    }
                }

                return CallbackUtils.insertCallback("Customer importExcel", customerList.toString(), () -> customerDAO.insertAll(customerList));
            } else {
                return new ResultDO<>(false, ResultCode.FILE_IS_NOT_EXIST, ResultCode.MSG_FILE_IS_NOT_EXIST, null);
            }
        } catch (Exception e) {
            logger.error("Customer importExcel exception, system error, e={}", e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

}
