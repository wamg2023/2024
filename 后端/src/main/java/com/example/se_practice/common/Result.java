package com.example.se_practice.common;
/*
* 封装统一的数据返回类型
* code为200表示成功，为400表示失败
* msg为消息描述
* data为任意类型的数据
* */

public class Result {
    private static final String SUCCESS="200";
    private static final String ERROR ="400";
    private String code;
    private String msg;
    private Object data;

    public static Result Success(String msg) {
        Result result= new Result();
        result.setMsg(msg);
        result.setCode(SUCCESS);
        return result;
    }
    public static Result Success(String msg,Object data) {
        Result result= new Result();
        result.setMsg(msg);
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }
    public static Result Error(String msg) {
        Result result= new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
