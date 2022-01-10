package com.scuyjzh.leetcode.hard.No_0265_Paint_House_II;

/**
 * 265. 粉刷房子 II
 *
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你
 * 需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花
 * 费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩
 * 阵来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；
 * costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请
 * 你计算出粉刷完所有房子最少的花费成本。
 *
 * 注意：
 * 所有花费均为正整数。
 *
 * 进阶：
 * 您能否在 O(nk) 的时间复杂度下解决此问题？
 */
class Solution {
    public int minCostII1(int[][] costs) {
        // 自顶向下的动态规划，时间复杂度 O(nk*k)
        int n = costs.length;
        int k = costs[0].length;
        // dp[i][j] 表示将房子 [0..i] 粉刷完，且第 i 号房子粉刷成 j 号颜色 的最少花费
        int[][] dp = new int[n][k];
        for (int i = 0; i < k; ++i) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                int minCost = Integer.MAX_VALUE;
                for (int l = 0; l < k; ++l) {
                    if (l == j) {
                        continue;
                    }
                    minCost = Math.min(minCost, dp[i - 1][l] + costs[i][j]);
                }
                dp[i][j] = minCost;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; ++i) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }

    public int minCostII2(int[][] costs) {
        // 自顶向下的动态规划，时间复杂度 O(nk)
        int n = costs.length;
        int k = costs[0].length;
        // dp[i][j] 表示将房子 [0..i] 粉刷完，且第 i 号房子粉刷成 j 号颜色 的最少花费
        int[][] dp = new int[n][k];
        for (int i = 0; i < k; ++i) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < n; ++i) {
            // 记录上一轮粉刷完 [0..i-1] 且花费最少时，第 i-1 号房子粉刷的颜色
            int minIndex = -1;
            // 记录上一轮粉刷完 [0..i-1] 的最少花费
            int minCost = Integer.MAX_VALUE;
            // 记录上一轮粉刷完 [0..i-1] 的次最少花费
            int secondMinCost = Integer.MAX_VALUE;
            for (int j = 0; j < k; ++j) {
                if (dp[i - 1][j] < minCost) {
                    // 最少变次最少
                    secondMinCost = minCost;
                    // 更新最少
                    minIndex = j;
                    minCost = dp[i - 1][j];
                } else if (dp[i - 1][j] < secondMinCost) {
                    // 更新次最少
                    secondMinCost = dp[i - 1][j];
                }
            }
            for (int j = 0; j < k; ++j) {
                if (j != minIndex) {
                    // 若当前颜色 j 与上一轮的最少颜色 minIndex 不同，就取上一轮的最少花费
                    dp[i][j] = minCost + costs[i][j];
                } else {
                    // 否则，就取上一轮的次最少花费
                    dp[i][j] = secondMinCost + costs[i][j];
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; ++i) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minCostII1(new int[][]{{1, 5, 3}, {2, 9, 4}}));
        System.out.println(new Solution().minCostII2(new int[][]{{1, 5, 3}, {2, 9, 4}}));
    }
}
