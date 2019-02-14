package com.step.jliang.aviator;

import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;

import java.util.Map;

/**
 * @author jliang
 */
public class AviatorExector {

    public static void main(String[] args) {

        Boolean result2 = (Boolean) AviatorEvaluator.execute("3>1 && 2!=4 || true");
        System.out.println(result2);//true

        String result5 = (String) AviatorEvaluator.execute("3>0? 'yes':'no'");
        System.out.println(result5);

        System.out.println("----test function call--------");
        System.out.println(AviatorEvaluator.execute("string.length('hello')"));
        System.out.println(AviatorEvaluator.execute("string.contains('hello','h')"));
        System.out.println(AviatorEvaluator.execute("math.pow(-3,2)"));


        System.out.println("----------test 替换变量----------------");
        Map env = Maps.newHashMap();
        env.put("yourname", "aviator");
        String result3 = (String) AviatorEvaluator.execute(" 'hello ' + yourname ", env);
        System.out.println(result3);

        String yourname = "atest";
        System.out.println(AviatorEvaluator.exec(" 'hello ' + yourname ", yourname));

    }

}
