package com.wash.car.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.SystemMessage;
import com.wash.car.service.ISystemMessageService;
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
 * 系统通知 前端控制器
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/systemMessage")
@Api(tags = "系统通知")
public class SystemMessageController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISystemMessageService iSystemMessageService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "systemMessage")
    @ResponseBody
    @ApiOperation(value="系统通知列表")
    public ResultUtils list(SystemMessage SystemMessage, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SystemMessage> q = new QueryWrapper<SystemMessage>();
            q.setEntity(SystemMessage);
            q.orderByDesc("id");
            IPage<SystemMessage> list = iSystemMessageService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询系统通知信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "systemMessage")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SystemMessage> q = new QueryWrapper<SystemMessage>();
            q.eq("id", id);

            SystemMessage SystemMessage = iSystemMessageService.getOne(q);
            resultUtils.put("data",SystemMessage);
        }catch (Exception e){
            logger.info("查询系统通知信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

}
