package com.step.jliang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jliang
 */
@SpringBootTest(classes = BaseTest.AutoSpringConfig.class)
@RunWith(SpringRunner.class)
public class BaseTest {

    @ComponentScan(basePackages = {"com.step.jliang"})
    @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
            DataSourceTransactionManagerAutoConfiguration.class,
            ErrorMvcAutoConfiguration.class})
    public static class AutoSpringConfig {

    }

    @Test
    public void test() {

    }
}
