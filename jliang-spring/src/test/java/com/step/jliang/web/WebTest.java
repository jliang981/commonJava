package com.step.jliang.web;

import com.step.jliang.BaseTest;
import com.step.jliang.web.config.ElConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jliang
 */
public class WebTest extends BaseTest {
    @Autowired
    private ElConfig elConfig;

    @Test
    public void testEl() {
        elConfig.outputRes();
    }
}
