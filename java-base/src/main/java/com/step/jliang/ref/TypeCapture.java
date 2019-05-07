package com.step.jliang.ref;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author haoliang
 * @Date 2019-05-07
 **/
abstract class TypeCapture<T> {

    /** Returns the captured type. */
    final Type capture() {
        Type superclass = getClass().getGenericSuperclass();
        checkArgument(superclass instanceof ParameterizedType, "%s isn't parameterized", superclass);
        return ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }

}
