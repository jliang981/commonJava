package com.step.jliang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        //Main main = new Main();
       //main.addRouteBuilder(new MyRouteBuilder());
       // main.run(args);

        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("php");
        list.add("python");
        list.add("lisp");
        list.add("c++");

        //filter function
        Stream<String> stream = list.stream().filter(p -> p.length() > 3);

        // list.removeIf(String::startsWith());

        String[] arr = stream.toArray(String[]::new);

        System.out.println(list);
        System.out.println(Arrays.toString(arr));
    }


    public static void astaticmethod(){

    }

}

