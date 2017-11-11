package com.step.jliang;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by jliang on 17/4/18.
 */
class TestFanXing
{
    public static void main (String[] args) throws java.lang.Exception
    {
        System.out.println("hi");
        TwoTuple<String,Integer> t1 = f();
        //  这里会报一条类型未检查警告
        TwoTuple<String,Byte> t2 = f2();
        //  这里不会报警告，
        TwoTuple t3 = f2();
        //  这里可以正常输出b，尽管这里b异常超出了Byte的范围
        System.out.println(t2.b);
        //  下面回报异常
       // System.out.println(t2.b.getClass());
        System.out.println(t3.b.getClass());
        Integer[] a = (Integer[]) new Object[3];
    }

    @Test
    public void assertClassType(){
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.println(c1 == c2);
    }

    static <A,B> TwoTuple<A,B> tuple(A a,B b){
        return new TwoTuple<>(a,b);
    }
    static TwoTuple<String,Integer> f(){
        return new TwoTuple<>("abc",11234);
    }
    static TwoTuple f2(){
        return new TwoTuple<>("abc",11234);
    }
}
class TwoTuple<A,B> {
    public final A a;
    public final B b;
    public TwoTuple(A a1,B b1){
        a=a1;
        b=b1;
    }

}
