package com.scrats.rent.common.exception;

import java.io.Serializable;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/11 10:35.
 */
public class ErrorInfo<T> implements Serializable {

    public static final int OK = 0;
    public static final int ERROR = 500;
    public static final int STATUS_CODE_BUSINESS_ERROR = 10100;
    public static final int STATUS_CODE_METHODARGUMENTNOTVALID_ERROR = 10101;
    public static final int STATUS_CODE_NOT_AUTHORIZED = 10102;
    public static final int STATUS_CODE_SYSTEM_ERROR = 10103;

    private int code;
    private String message;
    private String url;
    private T data;

    public ErrorInfo() {
        super();
    }

    public ErrorInfo(String url) {
        this.url = url;
    }

    public static int getERROR() {
        return ERROR;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
