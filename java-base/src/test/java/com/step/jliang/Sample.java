package com.step.jliang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by jliang on 17/4/6.
 */
public class Sample {

    @Test
    public void base() {
        Long l = null;
        //  long l1 = l;
        Set<String> set = new HashSet<String>();
//        set.add(new Integer(999));
        set.add("abc");
        set.add("de");
        if (set instanceof Set) {
            Set<?> st = (Set<?>) set;
            Iterator i = st.iterator();
            while (i.hasNext()) {

                System.out.println(i.next());

            }

        }

        byte a = 100, b = 100;
        a += b;
        System.out.println(a);
//        a = a + b;
        System.out.println(l);
    }

    @Test
    public void printLong() {
        Map<String, String> map = new HashMap<>();
        Set<String> keyset = map.keySet();
        map.put("a", "123");
        for (String s : keyset) {
            System.out.println(s);
        }
    }

    @Test
    public void assertClassType() {
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.println(c1 == c2);
    }
}
