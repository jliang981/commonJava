package com.step.jliang.zfb;

import java.util.Scanner;

/**
 * @author haoliang
 * @Date 2019-03-26
 **/
public class Shulie {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            int n = scanner.nextInt();// 总题数
            int t = scanner.nextInt();//蒙的正确
            int a = scanner.nextInt();

            if (t <= a) {
                System.out.println(t + n - a);
            } else {
                System.out.println(a + n - t);
            }

            String string = new String();
            string.toLowerCase();
        }
    }

    /**
     * Single Element in a Sorted Array
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int find(int[] nums, int start, int end) {
        if (start >= end) {
            return nums[start];
        }
        int mid = (start + end) / 2;
        if (nums[mid] == nums[mid - 1]) {
            if ((mid - 1) % 2 == 0) {
                return find(nums, mid + 1, end);
            } else {
                return find(nums, start, mid - 2);
            }
        } else if (nums[mid] == nums[mid + 1]) {
            if (mid % 2 == 0) {
                return find(nums, mid + 2, end);
            } else {
                return find(nums, start, mid - 1);
            }
        } else {
            return nums[mid];
        }
    }

}
