package com.step.jliang;

import org.junit.Test;

/**
 * @author jliang
 */
public class RandomTest {

    @Test
    public void test() {
        boolean flag = false;
        for (int time = 0; !flag; time++) {
            for (int i = 0; i <= 404; i++) {
                if (Math.ceil(Math.random() * 11765) <= 8750) {
                    System.out.println(i);
                    System.out.println(time);
                    if (time == 0) {

                    }
                    flag = true;
                    break;
                }
            }
        }
    }
}
