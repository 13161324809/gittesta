package com.wash.car.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.OrderComment;
import com.wash.car.service.IOrderCommentService;
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
 * 订单评价 前端控制器
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/orderComment")
@Api(tags = "订单评价")
public class OrderCommentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IOrderCommentService iOrderCommentService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "orderComment")
    @ResponseBody
    @ApiOperation(value="订单评价列表")
    public ResultUtils list(OrderComment OrderComment, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<OrderComment> q = new QueryWrapper<OrderComment>();
            q.setEntity(OrderComment);
            q.orderByDesc("id");
            IPage<OrderComment> list = iOrderCommentService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询订单评价信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "orderComment")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<OrderComment> q = new QueryWrapper<OrderComment>();
            q.eq("id", id);

            OrderComment OrderComment = iOrderCommentService.getOne(q);
            resultUtils.put("data",OrderComment);
        }catch (Exception e){
            logger.info("查询订单评价信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

}
