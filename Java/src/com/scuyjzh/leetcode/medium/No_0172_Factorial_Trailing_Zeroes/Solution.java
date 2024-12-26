package com.scuyjzh.leetcode.medium.No_0172_Factorial_Trailing_Zeroes;

/**
 * 172. 阶乘后的零
 *
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 */
class Solution {
    public int trailingZeroes(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/xiang-xi-tong-su-de-si-lu-fen-xi-by-windliang-3/
         */
        int count = 0;
        while (n > 0) {
            n /= 5;
            count += n;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().trailingZeroes(3));
        System.out.println(new Solution().trailingZeroes(5));
        System.out.println(new Solution().trailingZeroes(0));
    }
}
