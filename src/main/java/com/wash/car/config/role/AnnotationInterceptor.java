package com.wash.car.config.role;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wash.car.util.Constant;
import com.wash.car.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义拦截器的 通过获取请求接口自定义的注解判定是否需要登录返回对应的信息
 */
@Configuration
public class AnnotationInterceptor implements WebMvcConfigurer {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AsyncHandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {

                // 验证用户权限
                if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                    AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
                    if (authPassport != null) {
                        HttpSession session = request.getSession();

                        boolean isToken = authPassport.token();
                        if(isToken){
                            String token = request.getHeader("token");

                            //判断token不为空
                            if(StringUtils.isBlank(token)){
                                jsonWriter("10000","用户未登录不能访问",response);
                                return false;
                            }
                            // 解析token
                            Claims claim = jwtUtils.getClaimByToken(token);

                            if (null == claim) {
                                jsonWriter("10001","系统异常，解析token失败",response);
                                return false;
                            }

                            // 判断 token 是否过期
                            Date expiration = claim.getExpiration();
                            boolean tokenExpired = jwtUtils.isTokenExpired(expiration);
                            if (tokenExpired) {
                                jsonWriter("10002","用户登录超时",response);
                                return false;
                            }

                            // 从 token 中获取员工信息
                            String subject = claim.getSubject();

                            String userId = String.valueOf(session.getAttribute(Constant.SESSION_USER_ID));
                            // 去数据库中匹配 id 是否存在 (这里直接写死了)
                            if (StringUtils.isBlank(subject) || !subject.equals(userId)) {
                                jsonWriter("10002","用户登录超时",response);
                                return false;
                            }
                        }

                        String menu = authPassport.menu();

                        //需要校验菜单权限
                        if(StringUtils.isNotBlank(menu)){
                            Map<String,Object> menus = (Map<String, Object>) session.getAttribute(Constant.SESSION_USER_MENU);

                            if(null==menus || null==menus.get(menu)){
                                jsonWriter("10003","没有访问权限，请联系管理员开通！",response);
                                return false;
                            }
                        }
                    }
                }
                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                        Exception ex) throws Exception {
                if (ex != null) {
                    Enumeration<String> enu = request.getParameterNames();
                    Map<String, Object> param = new HashMap<String, Object>();
                    while (enu.hasMoreElements()) {
                        String paraName = (String) enu.nextElement();
                        param.put(paraName, request.getParameter(paraName));
                    }
                } else {
                    ex = new Exception();
                    afterCompletion(request, response, handler, ex);
                }
            }

            @Override
            public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
                                                       Object handler) throws Exception {
                afterConcurrentHandlingStarted(request, response, handler);
            }

        });//.addPathPatterns("/**");//拦截的请求路径地址内容
    }

    public void jsonWriter(String code,String msg,HttpServletResponse response) throws Exception{
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(json));
    }

}
