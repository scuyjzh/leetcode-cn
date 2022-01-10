package com.scuyjzh.leetcode.hard.No_1289_Minimum_Falling_Path_Sum_II;

/**
 * 1289. 下降路径最小和  II
 *
 * 给你一个整数方阵 arr ，定义「非零偏移下降路径」为：从 arr 数组中
 * 的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
 *
 * 请你返回非零偏移下降路径数字和的最小值。
 */
class Solution {
    public int minFallingPathSum1(int[][] grid) {
        // 自顶向下的动态规划，时间复杂度 O(n*n*n)
        int n = grid.length;
        int[][] dp = new int[n][n];
        // 初始化首行的路径和
        for (int i = 0; i < n; ++i) {
            dp[0][i] = grid[0][i];
        }
        // 从第一行进行转移
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                // 枚举上一行的每个列下标
                for (int k = 0; k < n; ++k) {
                    // 只有列下标不同时，才能更新状态
                    if (j != k) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]);
                    }
                }
            }
        }
        // 在所有的 dp[n - 1][i] 中取最小值
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }

    public int minFallingPathSum2(int[][] grid) {
        // 自顶向下的动态规划，时间复杂度 O(n*n)
        int n = grid.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[0][i] = grid[0][i];
        }
        for (int i = 1; i < n; ++i) {
            // 记录上一行路径和最小值列下标
            int minIndex = -1;
            // 记录上一行路径和最小值
            int min = Integer.MAX_VALUE;
            // 记录上一行路径和次最小值
            int secondMin = Integer.MAX_VALUE;
            for (int j = 0; j < n; ++j) {
                if (dp[i - 1][j] < min) {
                    // 最小变次最小
                    secondMin = min;
                    // 更新最小
                    min = dp[i - 1][j];
                    minIndex = j;
                } else if (dp[i - 1][j] < secondMin) {
                    // 更新次最小
                    secondMin = dp[i - 1][j];
                }
            }
            for (int j = 0; j < n; ++j) {
                if (j != minIndex) {
                    // 若当前行列下标与上一行的路径和最小值列下标不同，就取上一行的最小路径和
                    dp[i][j] = min + grid[i][j];
                } else {
                    // 否则，就取上一轮的次最小路径和
                    dp[i][j] = secondMin + grid[i][j];
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minFallingPathSum1(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(new Solution().minFallingPathSum2(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
