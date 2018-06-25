package com.scrats.rent.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.entity.Renter;
import com.scrats.rent.entity.User;
import com.scrats.rent.entity.WxSns;
import com.scrats.rent.service.RenterService;
import com.scrats.rent.service.WxSnsService;
import com.scrats.rent.util.IOUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/8 16:54.
 */
public class AuthenticationInterceptor  implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;
    @Autowired
    private RenterService renterService;
    @Autowired
    private WxSnsService wxSnsService;
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String requestPath = httpServletRequest.getRequestURI();
        logger.debug("Method: " + method.getName() + ", IgnoreSecurity: " + method.isAnnotationPresent(IgnoreSecurity.class));
        logger.debug("requestPath: " + requestPath);

        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return true;
        }

        String token = httpServletRequest.getHeader("tokenId");
        String json = IOUtil.getInputStreamAsText(httpServletRequest.getInputStream(),"UTF-8");
        logger.debug("token: " + token);
        if (StringUtils.isBlank(token)) {
            throw new NotAuthorizedException("非法请求, 请登陆");
        }
        APIRequest apiRequest = JSON.parseObject(json,APIRequest.class);
        if(null == apiRequest){
            apiRequest = new APIRequest();
        }
        User user = (User) redisService.get(token);
        if(null == user){
            throw new BusinessException("请求的tokenId无效, 请重新获取");
        }
        apiRequest.setUser(user);
        apiRequest.setTokenId(token);
        switch (user.getType()){
            //租客
            case "0":
                Renter renter = renterService.findBy("userId",user.getUserId());
                WxSns wxSns = wxSnsService.findBy("userId",user.getUserId());
                apiRequest.setRenterId(renter.getRenterId());
                if(null != wxSns){
                    apiRequest.setOpenId(wxSns.getOpenid());
                }
                break;
                //房东
            case "1":
                apiRequest.setLanlordId(user.getUserId());
                break;
                //管理员
            case "2":
                apiRequest.setAdminId(user.getUserId());
                break;
                //巡管员
            case "3":
                apiRequest.setGuardId(user.getUserId());
                break;
                //超级管理员
            case "4":
                apiRequest.setAdminId(user.getUserId());
                apiRequest.setAdministratorFlag(true);
                break;
            default:
                throw new BusinessException("数据有误,请联系系统管理员");
        }
        httpServletRequest.setAttribute("apiRequest", apiRequest);
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
