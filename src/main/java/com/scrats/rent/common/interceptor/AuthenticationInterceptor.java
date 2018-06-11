package com.scrats.rent.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.entity.User;
import com.scrats.rent.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
        if (StringUtils.isEmpty(token)) {
            throw new NotAuthorizedException("非法请求, 请登陆");
        }
        httpServletRequest.setAttribute("tokenId", token);
        APIRequest apiRequest = JSON.parseObject(json,APIRequest.class);
        if(null == apiRequest){
            apiRequest = new APIRequest();
        }
        User user = (User) redisService.get(token);
        apiRequest.setUser(user);
        if(httpServletRequest.getMethod().equalsIgnoreCase("GET")){
            String page = httpServletRequest.getParameter("page");
            if(!StringUtils.isEmpty(page)){
                apiRequest.setPage(Integer.parseInt(page));
            }
            String rows = httpServletRequest.getParameter("page");
            if(!StringUtils.isEmpty(rows)){
                apiRequest.setPage(Integer.parseInt(rows));
            }
            apiRequest.setSearchText(httpServletRequest.getParameter("searchText"));
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
