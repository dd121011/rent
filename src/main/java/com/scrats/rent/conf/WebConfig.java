package com.scrats.rent.conf;

import com.scrats.rent.common.APIRequestResolver;
import com.scrats.rent.common.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2017/8/28 16:10.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    //关键，将拦截器作为bean写入配置中
    @Bean
    public AuthenticationInterceptor getAuthenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Bean
    public APIRequestResolver APIRequestResolver() {
        return new APIRequestResolver();
    }

    /**
     * Description:  配置拦截器链
     * Author: lol
     * Date: 2017/8/28 16:13
     * Params:
     * return:
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthenticationInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/error");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(APIRequestResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(fastJsonHttpMessageConverterEx());
        super.configureMessageConverters(converters);
    }

    @Bean
    public FastJsonHttpMessageConverterEx fastJsonHttpMessageConverterEx() {
        return new FastJsonHttpMessageConverterEx();
    }


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
//        super.addViewControllers(registry);
//    }
}
