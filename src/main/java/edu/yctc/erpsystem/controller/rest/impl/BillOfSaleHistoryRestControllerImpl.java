package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.BillOfSaleHistoryRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.BillOfSaleHistoryInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.BillOfSaleHistoryVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 出货单历史数据接口
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/invoice-history")
public class BillOfSaleHistoryRestControllerImpl implements BillOfSaleHistoryRestController {

    @Resource
    private BillOfSaleHistoryInterService billOfSaleHistoryService;

    @Override
    @GetMapping("getBillOfSaleHistory")
    public ResultDO<PageUtils<BillOfSaleHistoryVO>> getBillOfSaleHistory(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return billOfSaleHistoryService.getBillOfSaleHistory(params);
    }

    @Override
    @GetMapping("getBillOfSaleHistoryByConditions")
    public ResultDO<PageUtils<BillOfSaleHistoryVO>> getBillOfSaleHistoryByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return billOfSaleHistoryService.getBillOfSaleHistoryByConditions(params);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody BillOfSaleHistoryVO[] billOfSaleHistory) {
        if (null == billOfSaleHistory || 0 == billOfSaleHistory.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return billOfSaleHistoryService.exportExcel(billOfSaleHistory);
    }

    @Override
    @PostMapping("exportExcelByModel")
    public ResultDO<Void> exportExcelByModel(@RequestBody List<BillOfSaleHistoryVO> billOfSaleHistoryVO) {
        if (null == billOfSaleHistoryVO || 0 == billOfSaleHistoryVO.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return billOfSaleHistoryService.exportExcelByModel(billOfSaleHistoryVO);
    }

}
