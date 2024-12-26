package com.scuyjzh.leetcode.medium.No_0400_Nth_Digit;

/**
 * 400. 第 N 位数字
 *
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9,
 * 10, 11, ...] 中找出并返回第 n 位数字。
 */
class Solution {
    public int findNthDigit(int n) {
        // 区间位数 [0,10),[10, 100),[100,1000)...
        int digits = 1;
        long base = 9, startNum = 1;
        while (n > digits * base) {
            n -= digits * base;
            base *= 10;
            startNum *= 10;
            digits++;
        }

        // 找到这个数字
        long cur = startNum + (n - 1) / digits;
        int idx = (n - 1) % digits;
        char[] arr = String.valueOf(cur).toCharArray();
        return arr[idx] - '0';
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findNthDigit(3));
        System.out.println(new Solution().findNthDigit(11));
    }
}
