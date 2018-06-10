package com.scrats.rent.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.scrats.rent.common.filter.CsrfFilter;
import com.scrats.rent.common.filter.XssFilter;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description: 配置过滤器链
 * @User: lol.
 * @Date: 2018/1/3 14:07.
 */
@Configuration
public class FilterConfig
{

    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        registration.setFilter(characterEncodingFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean csrfFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new CsrfFilter());
        //拦截规则
        //过滤应用程序中所有资源,当前应用程序根下的所有文件包括多级子目录下的所有文件，注意这里*前有“/”
        registration.addUrlPatterns("/*");
        //过滤指定的类型文件资源, 当前应用程序根目录下的所有html文件，注意：*.html前没有“/”,否则错误
//        registration.addUrlPatterns(".html");
        //过滤指定的目录下的所有文件,当前应用程序根目录下的folder_name子目录（可以是多级子目录）下所有文件
//        registration.addUrlPatterns("/folder_name/*");
        //过滤指定文件
//        registration.addUrlPatterns("/index.html");
        //添加默认参数
        registration.addInitParameter("paramName", "paramValue");
        //是否自动注册 false 取消Filter的自动注册
//        registration.setEnabled(false);
        //过滤器名称
        registration.setName("csrfFilter");
        //过滤器顺序
        registration.setOrder(2);

        return registration;
    }

    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(3);
        return registration;
    }

}
