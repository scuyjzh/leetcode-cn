package com.scuyjzh.leetcode.medium.No_0276_Paint_Fence;

/**
 * 276. 栅栏涂色
 *
 * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，请你按下述规则为栅
 * 栏设计涂色方案：
 *   • 每个栅栏柱可以用其中 一种 颜色进行上色。
 *   • 相邻的栅栏柱 最多连续两个 颜色相同。
 *
 * 给你两个整数 k 和 n ，返回所有有效的涂色 方案数 。
 */
class Solution {
    /**
     * 方法一：动态规划
     */
    public int numWays1(int n, int k) {
        if (n == 1) {
            return k;
        }
        // 状态定义：dp[i] 表示包含 i 个栅栏柱的栅栏的有效涂色方案数
        int[] dp = new int[n + 1];
        // 边界条件：1&2
        dp[1] = k;
        dp[2] = k * k;
        for (int i = 3; i <= n; ++i) {
            // 状态转移：
            // 子问题分解，对于第 i 个栏栅柱的涂色方案，可以分解为两种情况：
            // 1) 第 i 个栏栅柱与第 i-1 个栏栅柱的颜色不同，则 dp[i] = dp[i-1] * (k-1)
            // 2) 第 i 个栏栅柱与第 i-1 个栏栅柱的颜色相同，即 i、i-1 与 i-2 颜色不同，
            //    此时 i 与 i-1 的情况 1) 的方案数一样，则 dp[i] = dp[i-2] * (k-1)
            dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
        }
        return dp[n];
    }

    /**
     * 方法二：动态规划 + 空间优化
     */
    public int numWays2(int n, int k) {
        if (n == 1) {
            return k;
        }
        int dp1 = k;
        int dp2 = k * k;
        for (int i = 3; i <= n; ++i) {
            int tmp = dp2;
            dp2 = (k - 1) * (dp2 + dp1);
            dp1 = tmp;
        }
        return dp2;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numWays1(3, 2));
        System.out.println(new Solution().numWays1(1, 1));
        System.out.println(new Solution().numWays2(7, 2));
    }
}
