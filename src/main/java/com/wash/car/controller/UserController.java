package com.wash.car.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.User;
import com.wash.car.service.IUserService;
import com.wash.car.util.Constant;
import com.wash.car.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "user")
    @ResponseBody
    @ApiOperation(value="用户列表")
    public ResultUtils list(User User, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<User> q = new QueryWrapper<User>();
            q.setEntity(User);
            q.orderByDesc("id");
            IPage<User> list = iUserService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询用户信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "user")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<User> q = new QueryWrapper<User>();
            q.eq("id", id);

            User User = iUserService.getOne(q);
            resultUtils.put("data",User);
        }catch (Exception e){
            logger.info("查询用户信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @AuthPassport(menu = "user")
    @ResponseBody
    public ResultUtils update(@RequestBody User User, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            User.setUpdateBy(Integer.valueOf(session.getAttribute(Constant.SESSION_USER_ID)+""));
            User.setUpdateTime(LocalDateTime.now());

            iUserService.updateById(User);
        }catch (Exception e){
            logger.info("修改用户信息方法异常",e);
            return ResultUtils.error(999999,"修改失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    @AuthPassport(menu = "user")
    @ResponseBody
    public ResultUtils insert(User User, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            User.setCreateBy(Integer.valueOf(session.getAttribute(Constant.SESSION_USER_ID)+""));
            User.setCreateTime(LocalDateTime.now());

            iUserService.save(User);
        }catch (Exception e){
            logger.info("新增用户信息方法异常",e);
            return ResultUtils.error(999999,"新增失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @AuthPassport(menu = "user")
    @ResponseBody
    public ResultUtils delete(User User, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            User.setUpdateBy(Integer.valueOf(session.getAttribute(Constant.SESSION_USER_ID)+""));
            User.setUpdateTime(LocalDateTime.now());
            User.setIsDelete(Constant.IS_DELETE_1);
            iUserService.updateById(User);
        }catch (Exception e){
            logger.info("删除用户信息方法异常",e);
            return ResultUtils.error(999999,"删除失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

}
