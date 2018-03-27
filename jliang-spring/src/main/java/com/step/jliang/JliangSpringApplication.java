package com.step.jliang;

/**
 * Hello world!
 *
 * @author jliang
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JliangSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JliangSpringApplication.class, args);
    }
}
