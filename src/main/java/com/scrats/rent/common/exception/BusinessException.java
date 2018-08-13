package com.scrats.rent.common.exception;

import lombok.Data;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:  业务逻辑层异常
 * @User: lol.
 * @Date: 2018/1/11 10:40.
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
