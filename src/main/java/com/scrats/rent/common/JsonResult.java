package com.scrats.rent.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2017/12/29 09:58.
 */
@Data
public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 3287794870377113472L;

    private static final int SUCCESS = 1;
    private static final int ERROR = 0;
    private static final String MESSAGE = "成功";

    private int code;
    private String message;
    private T data;
    private int count;

    //构造方法
    public JsonResult() {
        this.code = SUCCESS;
        this.message = MESSAGE;
    }

    public JsonResult (T data){
        this.code = SUCCESS;
        this.data = data;
    }

    public JsonResult(T data, int count){
        this.code = SUCCESS;
        this.data = data;
        this.count = count;

    }

    public JsonResult (Throwable e){
        this.code = ERROR;
        this.message = e.getMessage();
    }

    public JsonResult (String msg){
        this.code = ERROR;
        this.message = msg;
    }

}
