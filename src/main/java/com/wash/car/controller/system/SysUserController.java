package com.wash.car.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.system.SysUser;
import com.wash.car.service.system.ISysUserService;
import com.wash.car.util.Constant;
import com.wash.car.util.PasswordUtil;
import com.wash.car.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author washcar
 * @since 2021-06-18
 */
@RestController
@RequestMapping("/sysUser")
@Api(tags = "系统管理 - 用户")
public class SysUserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISysUserService iSysUserService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "sysUser")
    @ResponseBody
    @ApiOperation(value="用户列表")
    public ResultUtils list(SysUser sysUser, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SysUser> q = new QueryWrapper<SysUser>();
            q.setEntity(sysUser);
            q.orderByDesc("id");
            IPage<SysUser> list = iSysUserService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询用户信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "sysUser")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SysUser> q = new QueryWrapper<SysUser>();
            q.eq("id", id);

            SysUser user = iSysUserService.getOne(q);
            resultUtils.put("data",user);
        }catch (Exception e){
            logger.info("查询用户信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @AuthPassport(menu = "sysUser")
    @ResponseBody
    public ResultUtils update(SysUser sysUser, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysUser.setUpdateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysUser.setUpdateTime(new Date());

            if(StringUtils.isNotBlank(sysUser.getPassword())){
                String encryptedPassword = PasswordUtil.encrypt(sysUser.getPassword());
                sysUser.setPassword(encryptedPassword);
            }

            iSysUserService.updateById(sysUser);
        }catch (Exception e){
            logger.info("修改用户信息方法异常",e);
            return ResultUtils.error(999999,"修改失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    @AuthPassport(menu = "sysUser")
    @ResponseBody
    public ResultUtils insert(SysUser sysUser, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysUser.setCreateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysUser.setCreateTime(new Date());

            if(StringUtils.isNotBlank(sysUser.getPassword())){
                String encryptedPassword = PasswordUtil.encrypt(sysUser.getPassword());
                sysUser.setPassword(encryptedPassword);
            }

            QueryWrapper<SysUser> q = new QueryWrapper<SysUser>();
            q.eq("user_name", sysUser.getUserName());

            SysUser user = iSysUserService.getOne(q);
            if(null != user){
                return ResultUtils.error(999999,"该用户名已存在");
            }


            iSysUserService.save(sysUser);
        }catch (Exception e){
            logger.info("新增用户信息方法异常",e);
            return ResultUtils.error(999999,"新增失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @AuthPassport(menu = "sysUser")
    @ResponseBody
    public ResultUtils delete(SysUser sysUser, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysUser.setUpdateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysUser.setUpdateTime(new Date());
            sysUser.setIsDelete(Constant.IS_DELETE_1);
            iSysUserService.updateById(sysUser);
        }catch (Exception e){
            logger.info("删除用户信息方法异常",e);
            return ResultUtils.error(999999,"删除失败，请稍后再试");
        }
        return ResultUtils.ok();
    }


}
