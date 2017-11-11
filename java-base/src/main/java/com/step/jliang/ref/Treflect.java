package com.step.jliang.ref;

import com.step.jliang.beanDO.Task;
import com.step.jliang.enumerated.TaskType;

import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 * Created by jliang on 17/6/28.
 */
public class Treflect {

    /**
     * 使用反射时，不用管这个属性是不是私有的，以及是否有相应的get方法，那么都可以获取到这个
     * 字段的值。
     *
     * @param args
     */
    public static void main(String[] args) {
        Task task = new Task("1", TaskType.CODING, LocalDate.now());
        try {
            Field idf = task.getClass().getDeclaredField("id");
            idf.setAccessible(true);
            Object id = idf.get(task);
            System.out.println(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
