package com.step.jliang.featureOf8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jliang on 17/5/25.
 * Java 8扩展了注解可以使用的范围，现在我们几乎可以在所有的地方：局部变量、泛型、
 * 超类和接口实现、甚至是方法的Exception声明。Java 8 新增加了两个注解的程序元素
 * 类型ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER
 */
public class Annotations {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    public @interface NonEmpty {
    }

    public static class Holder<@NonEmpty T> extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        final Holder<String> holder = new @NonEmpty Holder<String>();
        @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
    }
}

