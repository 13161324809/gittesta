package com.wash.car.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.Order;
import com.wash.car.service.IOrderService;
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
 * 订单表 前端控制器
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单表")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IOrderService iOrderService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "order")
    @ResponseBody
    @ApiOperation(value="订单表列表")
    public ResultUtils list(Order Order, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<Order> q = new QueryWrapper<Order>();
            q.setEntity(Order);
            q.orderByDesc("id");
            IPage<Order> list = iOrderService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询订单表信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "order")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<Order> q = new QueryWrapper<Order>();
            q.eq("id", id);

            Order Order = iOrderService.getOne(q);
            resultUtils.put("data",Order);
        }catch (Exception e){
            logger.info("查询订单表信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

}
