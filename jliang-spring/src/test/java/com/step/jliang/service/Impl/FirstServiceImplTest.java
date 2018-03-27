package com.step.jliang.service.Impl;

import com.step.jliang.BaseTest;
import com.step.jliang.module.ChaiDog;
import com.step.jliang.service.FirstService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FirstServiceImpl Tester.
 *
 * @author <haoliang>
 * @version 1.0
 * @since <pre>一月 13, 2018</pre>
 */
public class FirstServiceImplTest extends BaseTest {

    @Autowired
    FirstService firstService;

    /**
     * Method: getOne(String name)
     */
    @Test
    public void testGetOne() throws Exception {
        ChaiDog jia = firstService.getOne("jia");
        System.out.println(jia);
        System.out.println(firstService.getOne("jia"));
        System.out.println(firstService.getOne("jia2"));
        firstService.save(new ChaiDog("jia", 22));
        System.out.println(firstService.getOne("jia"));

        firstService.delete("jiajia");
        System.out.println(firstService.getOne("jiajia"));
    }

    /**
     * Method: save(ChaiDog dog)
     */
    @Test
    public void testSave() throws Exception {

    }

    /**
     * Method: delete(String name)
     */
    @Test
    public void testDelete() throws Exception {

    }


} 
