package com.wash.car.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义返回格式
 */
public class ResultUtils extends HashMap<String, Object> {

    public ResultUtils() {
        put("code", 200);
        put("msg", "success");
    }

    public static ResultUtils ok() {
        ResultUtils t = new ResultUtils();
        t.put("msg", "操作成功");
        return t;
    }

    public static ResultUtils ok(String msg) {
        ResultUtils t = new ResultUtils();
        t.put("msg", msg);
        return t;
    }

    public static ResultUtils ok(Map<String, Object> map) {
        ResultUtils t = new ResultUtils();
        t.putAll(map);
        return t;
    }

    public static ResultUtils error(int code, String msg) {

        ResultUtils t = new ResultUtils();
        t.put("code", code);
        t.put("msg", msg);
        return t;
    }

    public static ResultUtils error() {
        return error(999999, "系统异常，请稍后重试");
    }

    public ResultUtils put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
