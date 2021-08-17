package com.wash.car.config.role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 设置拦截器通过注解设定某种属性
 * @author kanghanyu
 * 自定义的注解内容设定对应的内容信息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthPassport {
    /**
     * 是否需要登录
     * @return
     */
    String menu() default "";

    /**
     * 验证token
     * @return
     */
    boolean token() default true;
}
