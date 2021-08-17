package com.wash.car.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wash.car.DTO.system.SysMenuDTO;
import com.wash.car.DTO.system.SysRoleDTO;
import com.wash.car.DTO.system.SysUserDTO;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.system.SysUser;
import com.wash.car.service.system.ISysMenuService;
import com.wash.car.service.system.ISysRoleService;
import com.wash.car.service.system.ISysUserService;
import com.wash.car.util.Constant;
import com.wash.car.util.JwtUtils;
import com.wash.car.util.PasswordUtil;
import com.wash.car.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@Api(tags = "登录")
public class LoginController{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    ISysMenuService iSysMenuService;

    @Autowired
    ISysRoleService iSysRoleService;

    @Autowired
    JwtUtils jwtUtils;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @AuthPassport(token = false)
    @ResponseBody
    @ApiOperation(value="登录")
    public ResultUtils login(@RequestParam(value="username") String username, @RequestParam(value="password") String password, HttpServletRequest request) {
        HttpSession session = request.getSession();

        ResultUtils resultUtils = new ResultUtils();

        //多类型账号登陆
        QueryWrapper<SysUser> q = new QueryWrapper<SysUser>();
        q.eq("user_name", username);
        q.eq("is_delete",Constant.IS_DELETE_0);

        SysUser sysUser = iSysUserService.getOne(q);

        if(null == sysUser){
            return resultUtils.error(999999,"用户名不存在");
        }

        String encryptedPassword = PasswordUtil.encrypt(sysUser.getPassword());
        System.out.println(encryptedPassword);
        boolean bool = PasswordUtil.valid(password,sysUser.getPassword());

        if(bool){
            resultUtils = ResultUtils.ok("登录成功");
        }else{
            return ResultUtils.error(999999,"用户名或密码不正确");
        }

        String token = jwtUtils.generateToken(sysUser.getId()+"");

        try{
            List<SysRoleDTO> rolelist = iSysRoleService.findListByUserId(sysUser.getId());
            SysUserDTO sysUserDTO = new SysUserDTO();

            sysUserDTO.setId(sysUser.getId());
            sysUserDTO.setUserName(sysUser.getUserName());
            sysUserDTO.setChineseName(sysUser.getChineseName());
            sysUserDTO.setEnglishName(sysUser.getEnglishName());
            sysUserDTO.setPassword(sysUser.getPassword());
            sysUserDTO.setEmail(sysUser.getEmail());
            sysUserDTO.setPhone(sysUser.getPhone());
            sysUserDTO.setFax(sysUser.getFax());
            sysUserDTO.setQq(sysUser.getQq());
            sysUserDTO.setIsSystem(sysUser.getIsSystem());
            sysUserDTO.setPasswordInvalidDate(sysUser.getPasswordInvalidDate());
            sysUserDTO.setIsOnlineRetailers(sysUser.getIsOnlineRetailers());
            sysUserDTO.setIsDelete(sysUser.getIsDelete());
            sysUserDTO.setRoleList(rolelist);

            Map<String,Object> menuMap = new HashMap<String,Object>();

            for(SysRoleDTO role : rolelist){
                for(SysMenuDTO menu : role.getMenuList()){
                    menuMap.put(menu.getMenuCode(),menu.getMenuName());
                    for(SysMenuDTO childMenu : menu.getChildMenu()){
                        menuMap.put(childMenu.getMenuCode(),childMenu.getMenuName());
                    }
                }
            }

            session.setAttribute(Constant.SESSION_USER,sysUserDTO);
            session.setAttribute(Constant.SESSION_USER_ID,sysUserDTO.getId());
            session.setAttribute(Constant.SESSION_USER_NAME,sysUserDTO.getChineseName());
            session.setAttribute(Constant.SESSION_USER_MENU,menuMap);
            session.setAttribute(Constant.SESSION_TOKEN,token);

            resultUtils.put("userName",sysUser.getChineseName()!=null?sysUser.getChineseName():sysUser.getEnglishName());
        } catch(Exception e){
            logger.info("获取登录信息失败",e);
            return ResultUtils.error(999999,"系统异常，登录失败");
        }

        resultUtils.put("token",token);
        return resultUtils;
    }

    /**
     * 方法描述：注销 返回类型：String
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    @ApiOperation(value="登出")
    public ResultUtils logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.SESSION_USER);
        session.removeAttribute(Constant.SESSION_USER_ID);
        session.removeAttribute(Constant.SESSION_USER_NAME);
        session.removeAttribute(Constant.SESSION_TOKEN);
        return ResultUtils.ok();
    }

}
