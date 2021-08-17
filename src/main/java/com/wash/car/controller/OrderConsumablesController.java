package com.wash.car.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.OrderConsumables;
import com.wash.car.service.IOrderConsumablesService;
import com.wash.car.util.Constant;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * <p>
 * 耗材订单 前端控制器
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/orderConsumables")
@Api(tags = "耗材订单")
public class OrderConsumablesController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IOrderConsumablesService iOrderConsumablesService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "orderConsumables")
    @ResponseBody
    @ApiOperation(value="耗材订单列表")
    public ResultUtils list(OrderConsumables OrderConsumables, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<OrderConsumables> q = new QueryWrapper<OrderConsumables>();
            q.setEntity(OrderConsumables);
            q.orderByDesc("id");
            IPage<OrderConsumables> list = iOrderConsumablesService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询耗材订单信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "orderConsumables")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<OrderConsumables> q = new QueryWrapper<OrderConsumables>();
            q.eq("id", id);

            OrderConsumables OrderConsumables = iOrderConsumablesService.getOne(q);
            resultUtils.put("data",OrderConsumables);
        }catch (Exception e){
            logger.info("查询耗材订单信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @AuthPassport(menu = "orderConsumables")
    @ResponseBody
    public ResultUtils update(OrderConsumables OrderConsumables, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            OrderConsumables.setUpdateBy(Integer.valueOf(session.getAttribute(Constant.SESSION_USER_ID)+""));
            OrderConsumables.setUpdateTime(LocalDateTime.now());

            iOrderConsumablesService.updateById(OrderConsumables);
        }catch (Exception e){
            logger.info("修改耗材订单信息方法异常",e);
            return ResultUtils.error(999999,"修改失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

}
