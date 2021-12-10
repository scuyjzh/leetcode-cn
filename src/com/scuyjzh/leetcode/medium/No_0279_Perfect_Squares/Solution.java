package com.scuyjzh.leetcode.medium.No_0279_Perfect_Squares;

/**
 * 279. 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它
 * 们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等
 * 于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3
 * 和 11 不是。
 */
class Solution {
    public int numSquares(int n) {
        // f[0]=0 为边界条件
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int minN = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                minN = Math.min(minN, f[i - j * j]);
            }
            // 状态转移方程
            f[i] = minN + 1;
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numSquares(12));
        System.out.println(new Solution().numSquares(13));
    }
}
