package com.scrats.rent.common.exception;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:  业务逻辑层异常
 * @User: lol.
 * @Date: 2018/1/11 10:40.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
