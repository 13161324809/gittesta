package com.wash.car.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.system.SysRole;
import com.wash.car.service.system.ISysRoleService;
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
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author washcar
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/role")
@Api(tags = "系统管理 - 权限")
public class SysRoleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISysRoleService iSysRoleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "role")
    @ResponseBody
    @ApiOperation(value="权限列表")
    public ResultUtils list(SysRole sysRole, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SysRole> q = new QueryWrapper<SysRole>();
            q.setEntity(sysRole);
            q.orderByDesc("id");
            IPage<SysRole> list = iSysRoleService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询角色信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "role")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SysRole> q = new QueryWrapper<SysRole>();
            q.eq("id", id);

            SysRole sysRole = iSysRoleService.getOne(q);
            resultUtils.put("data",sysRole);
        }catch (Exception e){
            logger.info("查询角色信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @AuthPassport(menu = "role")
    @ResponseBody
    public ResultUtils update(SysRole sysRole, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysRole.setUpdateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysRole.setUpdateTime(new Date());
            iSysRoleService.updateById(sysRole);
        }catch (Exception e){
            logger.info("修改权限信息方法异常",e);
            return ResultUtils.error(999999,"修改失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    @AuthPassport(menu = "role")
    @ResponseBody
    public ResultUtils insert(SysRole sysRole, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysRole.setCreateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysRole.setCreateTime(new Date());
            iSysRoleService.save(sysRole);
        }catch (Exception e){
            logger.info("新增权限信息方法异常",e);
            return ResultUtils.error(999999,"新增失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @AuthPassport(menu = "role")
    @ResponseBody
    public ResultUtils delete(SysRole sysRole, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysRole.setUpdateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysRole.setUpdateTime(new Date());
            sysRole.setIsDelete(Constant.IS_DELETE_1);
            iSysRoleService.updateById(sysRole);
        }catch (Exception e){
            logger.info("删除权限信息方法异常",e);
            return ResultUtils.error(999999,"删除失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

}
