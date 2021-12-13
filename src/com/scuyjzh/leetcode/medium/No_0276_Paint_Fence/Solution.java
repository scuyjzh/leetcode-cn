package com.scuyjzh.leetcode.medium.No_0276_Paint_Fence;

/**
 * 276. 栅栏涂色
 *
 * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，请你按下述规则为栅栏设计涂色方案：
 *   • 每个栅栏柱可以用其中 一种 颜色进行上色。
 *   • 相邻的栅栏柱 最多连续两个 颜色相同。
 * 给你两个整数 k 和 n ，返回所有有效的涂色 方案数 。
 */
class Solution {
    public int numWays(int n, int k) {
        // 状态定义：dp[i] 表示包含 i 个栅栏柱的栅栏的有效涂色方案数
        int[] dp = new int[n + 1];
        for (int i = 1; i < dp.length; ++i) {
            if (i == 1) {
                // 边界条件
                dp[i] = k;
            } else if (i == 2) {
                // 边界条件
                dp[i] = k * k;
            } else {
                // 状态转移，子问题分解。对于第 i 个栏栅柱的涂色方案，可以分解为两种情况：
                // 1) 第 i 个栏栅柱与第 i-1 个栏栅柱的颜色不同，则 dp[i] = dp[i-1] * (k-1)
                // 2) 第 i 个栏栅柱与第 i-1 个栏栅柱的颜色相同，即 i、i-1 与 i-2 颜色不同，
                //    此时 i 与 i-1 的情况 1) 的方案数一样，则 dp[i] = dp[i-2] * (k-1)
                dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numWays(3, 2));
        System.out.println(new Solution().numWays(1, 1));
        System.out.println(new Solution().numWays(7, 2));
    }
}
