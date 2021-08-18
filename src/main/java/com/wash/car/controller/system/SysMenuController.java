package com.wash.car.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.DTO.system.SysMenuDTO;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.system.SysMenu;
import com.wash.car.service.system.ISysMenuService;
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
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author washcar
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "系统管理 - 菜单")
public class SysMenuController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISysMenuService iSysMenuService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "system")
    @ResponseBody
    @ApiOperation(value="菜单列表")
    public ResultUtils list(SysMenu sysMenu, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            List<SysMenuDTO> list = iSysMenuService.findList();
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询菜单信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "system")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SysMenu> q = new QueryWrapper<SysMenu>();
            q.eq("id", id);

            SysMenu sysMenu = iSysMenuService.getOne(q);
            resultUtils.put("data",sysMenu);
        }catch (Exception e){
            logger.info("查询菜单信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @AuthPassport(menu = "system")
    @ResponseBody
    public ResultUtils update(@RequestBody SysMenu sysMenu, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysMenu.setUpdateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysMenu.setUpdateTime(new Date());
            iSysMenuService.updateById(sysMenu);
        }catch (Exception e){
            logger.info("修改菜单信息方法异常",e);
            return ResultUtils.error(999999,"修改失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    @AuthPassport(menu = "system")
    @ResponseBody
    public ResultUtils insert(SysMenu sysMenu, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysMenu.setCreateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysMenu.setCreateTime(new Date());

            iSysMenuService.save(sysMenu);
        }catch (Exception e){
            logger.info("新增菜单信息方法异常",e);
            return ResultUtils.error(999999,"新增失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @AuthPassport(menu = "system")
    @ResponseBody
    public ResultUtils delete(SysMenu sysMenu, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            sysMenu.setUpdateBy(session.getAttribute(Constant.SESSION_USER_ID)+"");
            sysMenu.setUpdateTime(new Date());
            sysMenu.setIsDelete(Constant.IS_DELETE_1);
            iSysMenuService.updateById(sysMenu);
        }catch (Exception e){
            logger.info("删除菜单信息方法异常",e);
            return ResultUtils.error(999999,"删除失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

}
