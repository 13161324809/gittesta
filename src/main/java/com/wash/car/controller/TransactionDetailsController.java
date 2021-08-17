package com.wash.car.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.TransactionDetails;
import com.wash.car.service.ITransactionDetailsService;
import com.wash.car.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 流水表 前端控制器
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/transactionDetails")
@Api(tags = "流水表")
public class TransactionDetailsController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ITransactionDetailsService iTransactionDetailsService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "transactionDetails")
    @ResponseBody
    @ApiOperation(value="流水表列表")
    public ResultUtils list(TransactionDetails TransactionDetails, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<TransactionDetails> q = new QueryWrapper<TransactionDetails>();
            q.setEntity(TransactionDetails);
            q.orderByDesc("id");
            IPage<TransactionDetails> list = iTransactionDetailsService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询流水表信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "transactionDetails")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<TransactionDetails> q = new QueryWrapper<TransactionDetails>();
            q.eq("id", id);

            TransactionDetails TransactionDetails = iTransactionDetailsService.getOne(q);
            resultUtils.put("data",TransactionDetails);
        }catch (Exception e){
            logger.info("查询流水表信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

}
