package com.step.jliang.zfb;

import java.util.Scanner;

/**
 * @author haoliang
 * @Date 2019-03-26
 **/
public class Qujian {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arrays = new int[50];
        if (scanner.hasNextLine()) {
            int num = 1;
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                arrays[i] = scanner.nextInt();
                if (i > 0 && arrays[i] > arrays[i-1] + 1) {
                    num++;
                }
            }
            System.out.println(num);
//            if (n == 1) {
//                System.out.println(1);
//            } else {
//                for (int i = 1; i < n; i++) {
//                    if (arrays[i-1] + 1 == arrays[i]) {
//                        continue;
//                    } else {
//                        num++;
//                    }
//                }
//                System.out.println(num);
//            }
        }
    }
}
