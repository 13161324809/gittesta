package com.wash.car.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.system.SysRoleMenu;
import com.wash.car.service.system.ISysRoleMenuService;
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

import java.util.ArrayList;
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
@RequestMapping("/roleMenu")
@Api(tags = "系统管理 - 权限菜单")
public class SysRoleMenuController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISysRoleMenuService iSysRoleMenuService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "role")
    @ResponseBody
    @ApiOperation(value="权限菜单列表")
    public ResultUtils list(SysRoleMenu SysRoleMenu, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SysRoleMenu> q = new QueryWrapper<SysRoleMenu>();
            q.setEntity(SysRoleMenu);
            q.orderByDesc("id");
            IPage<SysRoleMenu> list = iSysRoleMenuService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询菜单信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @AuthPassport(menu = "role")
    @ResponseBody
    public ResultUtils update(String menuId, String roleId) {
        try{
            if(StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(roleId)){
                QueryWrapper<SysRoleMenu> q = new QueryWrapper<SysRoleMenu>();
                q.eq("role_id", roleId);
                iSysRoleMenuService.remove(q);

                String[] menus = menuId.split(",");

                List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
                for(String menu : menus){
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setRoleId(roleId);
                    sysRoleMenu.setMenuId(menu);
                    list.add(sysRoleMenu);
                }

                iSysRoleMenuService.saveBatch(list);
            }else{
                return ResultUtils.error(999999,"请选择关联角色与菜单！");
            }
        }catch (Exception e){
            logger.info("关联角色信息方法异常",e);
            return ResultUtils.error(999999,"关联角色失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

}
