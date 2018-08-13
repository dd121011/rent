package com.scrats.rent.common.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/11 10:35.
 */
@Data
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

}
