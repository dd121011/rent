package com.scrats.rent.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/3 21:54.
 */
//@Configuration
public class FreemarkerConfiguration {

    @Autowired
    private Environment env;

    @Bean(value = "freemarkerConfiguration")
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfigurationFactoryBean()
    {
        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        String templateLoaderPath = env.getProperty("freeMarker.templateLoaderPath");
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath(templateLoaderPath);
        return freeMarkerConfigurationFactoryBean;
    }
}
