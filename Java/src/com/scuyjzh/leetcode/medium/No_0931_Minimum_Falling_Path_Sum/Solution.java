package com.scuyjzh.leetcode.medium.No_0931_Minimum_Falling_Path_Sum;

/**
 * 931. 下降路径最小和
 *
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过
 * matrix 的下降路径 的 最小和 。
 *
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿
 * 对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下
 * 一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1,
 * col + 1) 。
 */
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        // 用 dp(r, c) 表示到达位置为 (r, c) 的元素的下降路径最小和
        int[][] dp = new int[n][n];
        // 初始化：对于首行而言，每个位置的「最小成本」就是其「矩阵值」
        for (int i = 0; i < n; ++i) {
            dp[0][i] = matrix[0][i];
        }
        // 从第二行开始，根据题目给定的条件进行转移
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + matrix[i][0];
            for (int j = 1; j < n - 1; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1])) + matrix[i][j];
            }
            dp[i][n - 1] = Math.min(dp[i - 1][n - 1], dp[i - 1][n - 2]) + matrix[i][n - 1];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));
    }
}
