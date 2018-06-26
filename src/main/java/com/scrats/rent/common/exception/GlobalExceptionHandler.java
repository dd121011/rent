package com.scrats.rent.common.exception;

import com.scrats.rent.constant.GlobalConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description: 全局异常处理类
 * @User: lol.
 * @Date: 2018/1/11 10:32.
 */
//声明该类中的@ExceptionHandler作用于全局
@ControllerAdvice
public class GlobalExceptionHandler implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @ExceptionHandler
    public Object businessExceptionHandler(Exception exception, HttpServletRequest req) {
        ErrorInfo response = new ErrorInfo(req.getRequestURL().toString());

        if (exception instanceof BusinessException) {
            int code = ((BusinessException) exception).getCode();
            response.setCode(code > 0 ? code : ErrorInfo.STATUS_CODE_BUSINESS_ERROR);
            response.setMessage(exception.getMessage());
        } else if (exception instanceof MethodArgumentNotValidException) {
            response.setCode(ErrorInfo.STATUS_CODE_METHODARGUMENTNOTVALID_ERROR);
            response.setMessage(exception.getMessage());
        } else if (exception instanceof NotAuthorizedException) {
            response.setCode(ErrorInfo.STATUS_CODE_NOT_AUTHORIZED);
            response.setMessage(exception.getMessage());
        } else {
            response.setCode(ErrorInfo.STATUS_CODE_SYSTEM_ERROR);
            String profile = applicationContext.getEnvironment().getProperty("spring.profiles.active");
            if (profile != GlobalConst.PROFILE_PRD) {
                response.setMessage(exception.toString());
            } else {
                response.setMessage("系统异常");
            }
            logger.error("「系统异常」", exception);
        }

        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");

        if("XMLHttpRequest".equalsIgnoreCase(xRequestedWith)){
            HttpStatus httpStatus = HttpStatus.OK;
            if (response.getCode() == ErrorInfo.STATUS_CODE_SYSTEM_ERROR) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<>(response, httpStatus);
        }
        if(contentTypeHeader != null && (contentTypeHeader.contains("application/json") || contentTypeHeader.contains("application/x-www-form-urlencoded"))){
            if(acceptHeader != null){
                HttpStatus httpStatus = HttpStatus.OK;
                if (response.getCode() == ErrorInfo.STATUS_CODE_SYSTEM_ERROR) {
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                }
                return new ResponseEntity<>(response, httpStatus);
            }

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", response.getMessage());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("code", response.getCode());
        modelAndView.setViewName("/error");
        return modelAndView;
    }
}
