package com.scuyjzh.leetcode.medium.No_0360_Sort_Transformed_Array;

import java.util.*;

/**
 * 360. 有序转化数组
 *
 * 给你一个已经排好序的整数数组nums和整数a、b、c。对于数组中的每
 * 一个数 x，计算函数值f(x) = ax^2 + bx + c，请将函数值产生的数组返回。
 * 要注意，返回的这个数组必须按照 升序排列，并且所期望的解法时间
 * 复杂度为O(n)。
 */
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/sort-transformed-array/solution/zhong-gui-zhong-ju-you-shi-mian-shi-guan-awen/
         */
        int n = nums.length;
        int[] res = new int[n];
        int idx = a > 0 ? n - 1 : 0;
        int L = 0, R = n - 1;
        while (L <= R) {
            int v1 = a * nums[L] * nums[L] + b * nums[L] + c;
            int v2 = a * nums[R] * nums[R] + b * nums[R] + c;
            if (a <= 0) {
                // 两端小中间大
                if (v1 < v2) {
                    res[idx] = v1;
                    L++;
                } else {
                    res[idx] = v2;
                    R--;
                }
                idx++;
            } else {
                // 两端大中间小
                if (v1 > v2) {
                    res[idx] = v1;
                    L++;
                } else {
                    res[idx] = v2;
                    R--;
                }
                idx--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(new int[]{-4, -2, 2, 4}, 1, 3, 5)));
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(new int[]{-4, -2, 2, 4}, -1, 3, 5)));
    }
}
