package com.scuyjzh.leetcode.easy.No_0263_Ugly_Number;

/**
 * 263. 丑数
 *
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false
 * 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 */
class Solution {
    /**
     * 方法：数学
     */
    public boolean isUgly(int n) {
        // 根据丑数的定义，0 和负整数一定不是丑数
        if (n <= 0) {
            return false;
        }

        // 若 n 是丑数，则 n 可以写成 n = 2^a × 3^b × 5^c 的形式
        int[] factors = {2, 3, 5};

        // 为判断 n 是否满足上述形式，可以对 n 反复除以 2,3,5，直到 n 不再包含质因数 2,3,5
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }

        // 若剩下的数等于 1，则说明 n 不包含其他质因数，是丑数；否则，说明 n 包含其他质因数，不是丑数
        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isUgly(6));
        System.out.println(new Solution().isUgly(8));
        System.out.println(new Solution().isUgly(14));
        System.out.println(new Solution().isUgly(1));
    }
}
