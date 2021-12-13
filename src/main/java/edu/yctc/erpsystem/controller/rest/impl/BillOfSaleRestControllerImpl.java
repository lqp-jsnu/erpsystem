package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.BillOfSaleRestController;
import edu.yctc.erpsystem.entity.BillOfSaleDO;
import edu.yctc.erpsystem.entity.BillOfSaleHistoryDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.BillOfSaleInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.BillOfSaleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 出货单信息数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/delivery-order")
public class BillOfSaleRestControllerImpl implements BillOfSaleRestController {

    @Resource
    private BillOfSaleInterService billOfSaleService;

    @Override
    @GetMapping("getBillOfSale")
    public ResultDO<PageUtils<BillOfSaleVO>> getBillOfSale(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return billOfSaleService.getBillOfSale(params);

    }

    @Override
    @GetMapping("getBillOfSaleByConditions")
    public ResultDO<PageUtils<BillOfSaleVO>> getBillOfSaleByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return billOfSaleService.getBillOfSaleByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody BillOfSaleHistoryDO billOfSaleHistoryDO) {
        if (StringUtils.isBlank(billOfSaleHistoryDO.getInvoiceTitleId()) || StringUtils.isBlank(billOfSaleHistoryDO.getNumber())
                || StringUtils.isBlank(billOfSaleHistoryDO.getDate().toString()) || StringUtils.isBlank(billOfSaleHistoryDO.getBillOfSaleId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return billOfSaleService.insert(billOfSaleHistoryDO);
    }

    @Override
    @PostMapping("updateRemarkById")
    public  ResultDO<Void> updateRemarkById(@RequestBody BillOfSaleDO billOfSaleDO) {
        if (StringUtils.isBlank(billOfSaleDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return billOfSaleService.updateRemarkById(billOfSaleDO);
    }

    @Override
    @PostMapping("exportExcel")
    public   ResultDO<Void> exportExcel(@RequestBody BillOfSaleVO[] billOfSalesVO) {
        if (null == billOfSalesVO || 0 == billOfSalesVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return billOfSaleService.exportExcel(billOfSalesVO);

    }

    @Override
    @PostMapping("importExcel")
    public ResultDO<Void> importExcel() {
        return billOfSaleService.importExcel();
    }

}
