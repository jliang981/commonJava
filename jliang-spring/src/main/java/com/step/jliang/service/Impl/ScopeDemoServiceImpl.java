package com.step.jliang.service.Impl;

import com.step.jliang.service.ScopeDemoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author jliang
 */
@Component("scopeDemoService")
@Scope("prototype")
public class ScopeDemoServiceImpl implements ScopeDemoService {

    private int i = 1;

    @Override
    public int getCount() {
        return i++;
    }

}
