package com.step.jliang.ref;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

/**
 * 泛型参数类型获取
 *
 * @author haoliang
 * @Date 2019-05-06
 **/
public class TestParameterType {

    public static void main(String[] args) {
        ArrayList<String> stringList = Lists.newArrayList();

        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println("intList type is " + intList.getClass());
        System.out.println("stringList type is " + stringList.getClass());
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));

        Type actualTypeArgument = ((ParameterizedType) stringList.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(actualTypeArgument);

        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {};
        System.out.println(typeToken.getType());

        GenericTypeCapture<Map<String, Long>> genericTypeCapture = new GenericTypeCapture<Map<String, Long>>() {
        };
        System.out.println(genericTypeCapture.getType());

        testGuava();
    }

    private static void testGuava() {
        TypeToken<Function<Integer, String>> funToken = new TypeToken<Function<Integer, String>>() {};

        TypeToken<?> funResultToken = funToken.resolveType(Function.class.getTypeParameters()[1]);
//        funResultToken 表示String的class
        System.out.println(funResultToken);
    }

}
