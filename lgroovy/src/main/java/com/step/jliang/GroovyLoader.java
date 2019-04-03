package com.step.jliang;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;

import java.net.URL;

/**
 * @author haoliang
 * @Date 2019-03-26
 **/
public class GroovyLoader {

    public static void main(String[] args) {
        String script = "class Example {\n" +
                "\n" +
                "    def static Map<String, Object> transform(Map<String, Object> realValueMap) {\n" +
                "        Map<String, Object> retMap = new HashMap<>()\n" +
                "        retMap.putAll(realValueMap)\n" +
                "        retMap.put(\"result\", true)\n" +
                "        return retMap\n" +
                "    }\n" +
                "}";
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        Class groovyClass = classLoader.parseClass(script);
        Class aClass = classLoader.parseClass(script);

        URL fileUrl = Thread.currentThread().getContextClassLoader().getResource("script/rangechecker.groovy");
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(fileUrl);
//        groovyCodeSource.setCachable(true);
        Class codeClass1 = classLoader.parseClass(groovyCodeSource);
        Class codeCloass2 = classLoader.parseClass(groovyCodeSource);

        System.out.println(groovyClass);
        System.out.println(aClass);
        System.out.println(aClass.equals(groovyClass));

        System.out.println(codeClass1.equals(codeCloass2));
    }

}
