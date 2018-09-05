package com.scrats.rent.common.filter;

import com.scrats.rent.util.CusAccessObjectUtil;
import com.scrats.rent.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/3 15:21.
 */
@Slf4j
public class CsrfFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("====>CsrfFilter启动<====");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestAdress = CusAccessObjectUtil.getIpAddress(request);
        String edcDomain = PropertiesUtil.getProperty("system.properties","edc.domain");
//        throw new NotAuthorizedException("非法请求");
        if(edcDomain.indexOf(requestAdress) > -1){
            filterChain.doFilter(request, response);
        }else{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("msg", "非法请求");
            modelAndView.addObject("url", request.getRequestURL());
            modelAndView.setViewName("error");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
