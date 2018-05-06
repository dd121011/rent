package com.scrats.rent.common;

import java.io.Serializable;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2017/12/29 09:58.
 */
public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 3287794870377113472L;

    private static final int SUCCESS = 1;
    private static final int ERROR = 0;
    private static final String MESSAGE = "成功";

    private int code;
    private String msg;
    private T data;

    //构造方法
    public JsonResult() {
        code = SUCCESS;
        msg = MESSAGE;
    }
    public JsonResult (T data){
        code = SUCCESS;
        this.data = data;
    }
    public JsonResult (Throwable e){
        code = ERROR;
        this.msg = e.getMessage();
    }
    public JsonResult (String msg){
        code = ERROR;
        this.msg = msg;
    }
    //get AND set 方法
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "JsonResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
}
