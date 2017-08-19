package com.step.jliang.effective.method;

import java.util.*;

/**
 * Created by jliang on 17/6/27.
 * 重载classify函数的目的，是为了判断集合的类型，如果是set则打印出set。
 * 但是对于方法重载的判定，是编译期开始的，也就是由定义的类型决定，因此main会打印出三个unknown。
 */
public class CollectionClassify {
    public static String classify(Set<?> set) {
        return "set";
    }

    public static String classify(List<?> list) {
        return "list";
    }

    public static String classify(Collection<?> collection) {
        return "unknown collection";
    }

    public static void main(String[] args) {
        Collection<?>[] collection = {
                new HashSet<String>(),
                new ArrayList<String>(),
                new HashMap<String, String>().values()
        };
        /**
         * 因为对于方法重载的判定是从编译期开始的，所以只会调用第三个函数。
         * 但是对于方法重写的判断是运行期开始的。
         */
        for (Collection<?> objects : collection) {
            System.out.println(classify(objects));
        }
    }
}
