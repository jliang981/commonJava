package com.step.jliang.featureOf8;

import com.step.jliang.beanDO.Task;
import com.step.jliang.enumerated.TaskType;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jliang on 17/6/12.
 */
public class StreamSecond {

    private static List<Task> tasks;

    @Before
    public void before() {
        Task task1 = new Task("Read Version Control with Git book", TaskType.READING, LocalDate.of(2015, Month.JULY, 1)).addTag("git").addTag("reading").addTag("books");

        Task task2 = new Task("Read Java 8 Lambdas book", TaskType.READING, LocalDate.of(2015, Month.JULY, 2)).addTag("java8").addTag("reading").addTag("books");

        Task task3 = new Task("Write a mobile application to store my tasks", TaskType.CODING, LocalDate.of(2015, Month.JULY, 3)).addTag("coding").addTag("mobile");

        Task task4 = new Task("Write a blog on Java 8 Streams", TaskType.WRITING, LocalDate.of(2015, Month.JULY, 4)).addTag("blogging").addTag("writing").addTag("streams");

        Task task5 = new Task("Read Domain Driven Design book", TaskType.READING, LocalDate.of(2015, Month.JULY, 5)).addTag("ddd").addTag("books").addTag("reading");

        tasks = Arrays.asList(task1, task2, task3, task4, task5);
    }

    /**
     * 过滤所有的任务来找到任务类型为READING的任务。
     * 将过滤后的结果按照createdOn域进行排序。
     * 获得每个任务的标题。
     * 将结果的标题收集到一个列表中。
     */
    @Test
    public void base1() {
        tasks.stream().filter(task -> task.getType() == TaskType.READING).
                //sorted(Comparator.comparing(Task::getCreatedOn)) 可以使用comparator的辅助方法。
                        sorted((task1, task2) -> task1.getCreatedOn().compareTo(task2.getCreatedOn())).
                map(Task::getTitle).collect(Collectors.toList()).
                forEach((title) -> System.out.println(title));
    }


    /**
     * 为了找出所有不同的标签，我们需要进行如下的操作：
     * 为每一个任务提取标签。
     * 将所有的标签收集进一个数据流。
     * 将重复的标签除去。
     * 最后将收集的结果放入一个列表。
     */
    @Test
    public void base2() {
        tasks.stream().flatMap(task -> task.getTags().stream()).
                distinct().collect(Collectors.toList()).
                forEach(System.out::println);
    }


    /**
     * 检查是否所有的阅读任务都有books标签
     * allMatch,anyMatch,findFirst和findAny
     */
    @Test
    public void base4() {
        System.out.println(tasks.stream().allMatch(task -> task.getTags().contains("books")));
        System.out.println(tasks.stream().anyMatch(task -> task.getTags().contains("books")));
        System.out.println(tasks.stream().anyMatch(task -> task.getTags().contains("books")));

    }

    /**
     * 创建一个所有标题的总结
     */
    @Test
    public void base6() {
        System.out.println(tasks.stream().map(Task::getTitle).
                reduce((title1, title2) -> title1 + "**" + title2).
                get());
    }

    private static final List<String> STOP_WORDS = Arrays.asList("AND", "OR", "BY", "OF");

    @Test
    public void acronym() {
        String collect = Arrays
                .stream("i am and tyu".toUpperCase().split("\\s"))
                .filter(word -> !STOP_WORDS.contains(word))
                .map(w -> String.valueOf(w.charAt(0)))
                .collect(Collectors.joining("|", "prefix", "subfix"));
        System.out.println(collect);
    }

}