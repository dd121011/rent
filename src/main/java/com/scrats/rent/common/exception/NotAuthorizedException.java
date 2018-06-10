package com.scrats.rent.common.exception;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/11 10:46.
 */
public class NotAuthorizedException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotAuthorizedException(String message) {
        this.message = message;
    }
}
