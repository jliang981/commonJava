package com.step.jliang.web.controller;

import com.step.jliang.service.FirstService;
import com.step.jliang.service.ScopeDemoService;
import com.step.jliang.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jliang
 */
@RestController("/")
public class Index {

    @Autowired
    FirstService firstService;
    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/hello")
    public Map hello() {
        ScopeDemoService scopeDemoService = SpringContextUtil.getBean(ScopeDemoService.class);
        FirstService bean = applicationContext.getBean(FirstService.class);
        System.out.println(bean);
        Map<String, Object> map = new HashMap<>();
        map.put("hello", "jliang68");
        map.put("count", scopeDemoService.getCount());
        return map;
    }

    @RequestMapping("/login2")
    public Map login() {
        Map<String, Object> map = new HashMap<>();
        map.put("hello", "请先登录呦");
        return map;
    }

}
