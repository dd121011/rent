package com.scrats.rent.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2017/8/28 16:10.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Description:  配置拦截器链
     * Author: lol
     * Date: 2017/8/28 16:13
     * Params:
     * return:
     */
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/login","/userLogin");
//    }

    /**
     * 配置静态访问资源
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
////        registry.addResourceHandler("/my/**").addResourceLocations("file:E:/my/");
//        super.addResourceHandlers(registry);
//    }

    /**
     * 以前要访问一个页面需要先创建个Controller控制类，再写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了,直接访问http://localhost:8080/toLogin就跳转到login.jsp页面了
     * @param registry
     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/gomain").setViewName("mainFrame");
//        super.addViewControllers(registry);
//    }
}
