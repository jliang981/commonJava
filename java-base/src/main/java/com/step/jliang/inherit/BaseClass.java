package com.step.jliang.inherit;

/**
 * @author jliang
 */
public abstract class BaseClass {

    BaseClass() {
        System.out.println("baseClass");
        // com.step.jliang.inherit.AClass
        System.out.println(this.getClass().getName());
        System.out.println(BaseClass.this.getClass().getName());
    }

}
