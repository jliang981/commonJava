package com.step.jliang.ref;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author haoliang
 * @Date 2019-05-07
 **/
public abstract class GenericTypeCapture<T> extends TypeCapture<T> {

    private final Type runtimeType;

    protected GenericTypeCapture() {
        this.runtimeType = capture();
        checkState(
                !(runtimeType instanceof TypeVariable),
                "Cannot construct a TypeToken for a type variable.\n"
                        + "You probably meant to call new TypeToken<%s>(getClass()) "
                        + "that can resolve the type variable for you.\n"
                        + "If you do need to create a TypeToken of a type variable, "
                        + "please use TypeToken.of() instead.",
                runtimeType);
    }

    /** Returns the represented type. */
    public final Type getType() {
        return runtimeType;
    }
}
