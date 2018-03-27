package com.step.jliang.web.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author jliang
 */
@Configuration
@PropertySource(value = "classpath:test.properties", ignoreResourceNotFound = true)
public class ElConfig {

    @Value("you and me")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100}")
    private int randomNum;

    @Value("#{scopeDemoService.getCount()}")
    private int fromAnthorBean;

    @Value("http://www.baidu.com")
    private Resource testUrl;

    @Value("") // 获取properties中的内容，使用的是$符号
    private String author;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputRes() {
        System.out.println(normal);
        System.out.println(osName);
        System.out.println(randomNum);
        System.out.println(fromAnthorBean);
        System.out.println(author);
        try {
            System.out.println(IOUtils.toString(testUrl.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
