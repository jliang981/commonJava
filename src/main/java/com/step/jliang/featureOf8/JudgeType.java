package com.step.jliang.featureOf8;

/**
 * Created by jliang on 17/5/25.
 * java8，加强了类型推断
 */
public class JudgeType {
    public static void main(String[] args) {
        Value<String> value = new Value<>();
        //  如果使用jdk7的话，value.defaultVaule()就会报错。
        System.out.println(value.get("abc", value.defaultVaule()));
    }
}

class Value<T> {
    static <T> T defaultVaule() {
        return null;
    }

    T get(T value, T defaultValue) {
        if (value == null)
            return defaultValue;
        return value;
    }
}


