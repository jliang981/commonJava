package com.step.jliang.innerClass;

/**
 * Created by jliang on 17/5/6.
 */

interface ClassInterface {
    void howdy();
    class Test implements ClassInterface {
        public void howdy() {
            System.out.println("Howdy!");
        }
        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}

class ClassImpl implements ClassInterface {
    @Override
    public void howdy() {

    }

    public static void main(String[] args) {
        ClassInterface c = new ClassImpl();


        //t.howdy();
    }
}