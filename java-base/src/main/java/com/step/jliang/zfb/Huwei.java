package com.step.jliang.zfb;

import java.util.Scanner;

/**
 * 代表1，字符 - 代表 0。输入类型“–.#。#-.-”的字符串，字符#是分隔符，可能会有连续的多个#。把形如“–.”的无符号二进制转换成十进制数字后，有张图表，根据这张图表上的映射关系，输出相应的英文字符（编程的时候要苦逼的把这张表手工输入到map里有没有。。。）
 * 。如果输入只是一连串的#，则输出一个空字符串，如果转换后的整数超出了图
 * ---------------------
 * 作者：IT小bai
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_26590199/article/details/74614580
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 * @author haoliang
 * @Date 2019-03-30
 **/
public class Huwei {

    private static String[] dict = new String[]{"F", "G", "R", "S", "T", "L", "M", "N", "O", "P", "Q",
            "W", "X", "Y", "Z", "U", "A", "G", "H", "I", "J", "K",
            "B", "C", "D", "E", "l", "m", "n", "o", "p", "i", "j", "k", "f", "g", "h",
            "a", "b", "c", "d", "e", "q", "r", "w", "x", "y", "z", "s", "t", "u", "v"};
    private static final String ERROR = "ERROR";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String result = trans(input);
            System.out.println(result);
        }
        sc.close();
    }

    private static String trans(String input) {
        if (input == null || input.length() == 0)
            return ERROR;
        // 特殊字符判断
        StringBuilder sb = new StringBuilder();
        StringBuilder finalString = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != '#' && c != '.' && c != '-')
                return ERROR;

            if (c == '.') {
                sb.append("1");
            } else if (c == '-') {
                sb.append("0");
            }

            if (c == '#' || i == chars.length - 1) {
                if (sb.length() > 0) {
                    int num = Integer.valueOf(sb.toString(), 2);
                    if (num >= 0 && num <= 51) {
                        finalString.append(dict[num]);
                    } else {
                        // 说明值是错误的
                        return ERROR;
                    }
                    sb.setLength(0);
                }
            }
        }

        return finalString.toString();
    }
}
