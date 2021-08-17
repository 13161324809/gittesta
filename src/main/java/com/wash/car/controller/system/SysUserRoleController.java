package com.wash.car.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.system.SysUserRole;
import com.wash.car.service.system.ISysUserRoleService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author washcar
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/userRole")
@Api(tags = "系统管理 - 用户权限")
public class SysUserRoleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISysUserRoleService iSysUserRoleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "sysUser")
    @ResponseBody
    @ApiOperation(value="用户权限列表")
    public ResultUtils list(SysUserRole SysUserRole, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<SysUserRole> q = new QueryWrapper<SysUserRole>();
            q.setEntity(SysUserRole);
            q.orderByDesc("id");
            IPage<SysUserRole> list = iSysUserRoleService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询角色信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @AuthPassport(menu = "sysUser")
    @ResponseBody
    public ResultUtils update(String userId,String roleId) {
        try{
            if(StringUtils.isNotBlank(roleId)){
                QueryWrapper<SysUserRole> q = new QueryWrapper<SysUserRole>();
                q.eq("user_id", userId);
                iSysUserRoleService.remove(q);

                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);
                iSysUserRoleService.save(sysUserRole);
            }else{
                return ResultUtils.error(999999,"请选择关联角色！");
            }
        }catch (Exception e){
            logger.info("关联角色信息方法异常",e);
            return ResultUtils.error(999999,"关联角色失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

}
