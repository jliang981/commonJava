package com.step.jliang.featureOf8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class LambdaHead {

    @Test
    public void useLocalVarible() {
        int portNumber = 13;
        Runnable r = () -> System.out.println(portNumber);
        // 如果下面在对portNumber操作，就会报错。
        //portNumber = 9;

        Function<String, String> f1 = String::new;
        f1.apply("");

    }

    @Test
    public void useSort() {
        List<Integer> integers = Arrays.asList(1, 3, 4, 2);
        integers.sort(Comparator.comparing(Integer::intValue).reversed());
        System.out.println(integers);

        String a = "hello world";
        System.out.println(a.split(""));
    }

    /**
     * 给定列表［1，2，3］和［3，4］，返回两个列表的组合数对。
     * 应该返回［（1，3），（1，4），（2，3），（2，4），（3，3），（3，4）］
     */
    @Test
    public void useFlatMap() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Integer> integers1 = Arrays.asList(3, 4);
        integers.stream().flatMap(i -> integers1.stream()
                .map(j -> new int[]{i, j})
        ).map(ints -> ints[0] + "," + ints[1])
                .forEach(
                        System.out::println
                );

    }

    @Test
    public void testCollector() {
        //Collectors.counting()
    }
}
