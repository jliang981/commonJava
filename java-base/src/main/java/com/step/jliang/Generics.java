package com.step.jliang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jliang on 17/4/21.
 * 测试泛型的类
 */
public class Generics {

    public void oldStyleMethod(List list){
        list.add(new Cat());
    }

    @Test
    public void checkedList(){
        List<Dog> dogList = new ArrayList<>();
        oldStyleMethod(dogList);
        //  如果没有强制类型转换，也不会出错。将从这个list中去除cat对象。
        System.out.println(dogList.get(0));
        List<Dog> checkedDogList = Collections.checkedList(dogList, Dog.class);
        //  这里就会有问题，不能向list中添加cat对象
        // oldStyleMethod(checkedDogList);
        System.out.println("down");
    }

    public void zhuanxing(){
        //  这里将会出错。
        // List<Pet> pets = new ArrayList<Dog>();
    }

    @Test
    public void addCovariance(){
        List<? extends Pet> pets = new ArrayList<Dog>();
        //pets.add(new Pet());
        //pets.add(new Object());
    }
}

class Pet{
    //int like;
    void getLike(){

    }
}

class Dog extends Pet{

}
class Cat extends Pet{

}

interface weight{
    int getWeight();
}

class pig<T extends Pet & weight>{
    T item;
    pig(T obj){
        item = obj;
    }

    public T getItem() {
        return item;
    }
}