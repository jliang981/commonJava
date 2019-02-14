package com.step.jliang.algro;

/**
 * @author jliang
 */
public class PrintAllSortString {

    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        a = a * b;
        b = a / b;
        a = a / b;

        paiLie(0, new String("abcd").toCharArray());
    }

    public static void paiLie(int n, char[] chars) {
        //递归出口：只剩一个字符，即无法交换
        if (n == chars.length - 1) {
            System.out.println(String.valueOf(chars));
        } else {
            char exchange;
            for (int i = n; i < chars.length; i++) {
                //依次交换n与它后边的每一个字符
                swap(chars, i, n);
                //对后边的字符递归调用
                paiLie(n + 1, chars);
                swap(chars, i, n);
            }
        }
    }

    public static void print(String s) {
        if (s == null || s.length() == 0) {
            return;
        }

        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            print(i, chars);
        }
    }

    public static void print(int i, char[] chars) {
        if (i == chars.length - 1) {
            System.out.println(new String(chars));
            return;
        }
        swap(chars, i, i + 1);
        print(i + 1, chars);
        swap(chars, i, i + 1);
    }

    private static void swap(char[] chars, int i, int j) {
        char aChar = chars[i];
        chars[i] = chars[j];
        chars[j] = aChar;
    }

}
