package com.scrats.rent.common.exception;

import lombok.Data;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/11 10:46.
 */
@Data
public class NotAuthorizedException extends RuntimeException {

    private String message;

    public NotAuthorizedException(String message) {
        this.message = message;
    }
}
