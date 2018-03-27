package com.step.jliang.dao;

import com.step.jliang.BaseTest;
import com.step.jliang.module.ChaiDog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RedisDao Tester.
 *
 * @author <haoliang>
 * @version 1.0
 * @since <pre>一月 13, 2018</pre>
 */
public class RedisDaoTest extends BaseTest {

    @Autowired
    RedisDao redisDao;

    /**
     * Method: save(String name, String value)
     */
    @Test
    public void testSaveForNameValue() throws Exception {
        try {
            redisDao.save("jia", "happy");
            System.out.println(redisDao.getString("jia"));

            ChaiDog dog = new ChaiDog("jia2", 22);
            redisDao.save(dog);
            System.out.println(redisDao.getDog(dog));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


} 
