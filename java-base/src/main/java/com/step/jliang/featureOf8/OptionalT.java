package com.step.jliang.featureOf8;

import org.junit.Test;

import java.util.Optional;

public class OptionalT {

    @Test
    public void init() {
        Optional<Object> o = Optional.of(null);

    }

    /**
     * 下面是很常用的一套链式操作，
     * 构造对象，转换，过滤，判断
     */
    @Test
    public void custom() {
//        Optional.ofNullable()
//                .flatMap()
//                .filter()
//                .orElse()

        //CompletableFuture
    }
}
