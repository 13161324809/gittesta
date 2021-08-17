package com.wash.car.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.BankAccount;
import com.wash.car.service.IBankAccountService;
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
 * 银行卡 前端控制器
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/bankAccount")
@Api(tags = "银行卡")
public class BankAccountController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IBankAccountService iBankAccountService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "bankAccount")
    @ResponseBody
    @ApiOperation(value="银行卡列表")
    public ResultUtils list(BankAccount BankAccount, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<BankAccount> q = new QueryWrapper<BankAccount>();
            q.setEntity(BankAccount);
            q.orderByDesc("id");
            IPage<BankAccount> list = iBankAccountService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询银行卡信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "bankAccount")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<BankAccount> q = new QueryWrapper<BankAccount>();
            q.eq("id", id);

            BankAccount BankAccount = iBankAccountService.getOne(q);
            resultUtils.put("data",BankAccount);
        }catch (Exception e){
            logger.info("查询银行卡信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

}
