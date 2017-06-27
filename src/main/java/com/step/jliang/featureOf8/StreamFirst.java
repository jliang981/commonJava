package com.step.jliang.featureOf8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jliang on 17/5/19.
 * java8 新特性Stream学习
 */
public class StreamFirst {

    @Test
    /**
     * Filtered value: three
     Mapped value: THREE
     Filtered value: four
     Mapped value: FOUR
     说明并不是遍历很多遍，而是遍历一遍把所有的操作都做完
     */
    public void peek() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    @Test
    /**
     * optional提供编译期检查，可在编译器发现一些空指针问题
     */
    public void optional() {
        System.out.println(getLength(null));
        System.out.println(getLength("abc"));
    }

    @Test
    public void reduce() {
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
    }

    @Test
    public void exception() {
        Consumer<Integer> lambda = (num) -> System.out.println("num" + num/0);

        Consumer<Exception> error = (e) -> System.out.println(e.getMessage());

        try {
            lambda.accept(30);
        } catch (Exception e) {
            error.accept(e);
        }
    }

    /**
     * 注意这个用例中的skip和limit的效果。这里peek操作只会打印出前六个数据的结果。
     * 如果只有limit（4），则只会打印前四个结果，
     * 如果使用skip（2），会把所有的结果都打印出来
     * count可以返回数量
     */
    @Test
    public void mixture() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null).
                distinct().mapToInt(num -> num * 2).
                peek(System.out::println).skip(2).limit(4).sum());
    }


    private int getLength(String text) {
        return Optional.ofNullable(text).map(String::length).orElse(-1);
    }
}

class Person {
    int no;
    String name;

    Person(int no, String name) {
        this.no = no;
        this.name = name;
    }

    String getName() {
        System.out.println(name);
        return name;
    }
}