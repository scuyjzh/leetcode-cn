package com.scuyjzh.leetcode.medium.No_0518_Coin_Change_II;

/**
 * 518. 零钱兑换 II
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表
 * 示总金额。
 *
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑
 * 出总金额，返回 0 。
 *
 * 假设每一种面额的硬币有无限个。
 *
 * 题目数据保证结果符合 32 位带符号整数。
 */
class Solution {
    public int change1(int amount, int[] coins) {
        int n = coins.length;
        // f[i][j] 为考虑前 i 件物品，凑成总和为 j 的方案数量
        int[][] f = new int[n + 1][amount + 1];
        // 为了方便初始化，一般让 f[0][x] 代表不考虑任何物品的情况
        // 因此有显而易见的初始化条件：
        // f[0][x]=1，其余 f[0][x]=0
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int coin = coins[i - 1];
            for (int j = 0; j <= amount; ++j) {
                // 对于第 i 个硬币，不使用该硬币
                f[i][j] = f[i - 1][j];
                // 使用该硬币，每个硬币可以被选择多次（容量允许的情况下）
                int maxK = j / coin;
                for (int k = 1; k <= maxK; ++k) {
                    f[i][j] += f[i - 1][j - k * coin];
                }
            }
        }
        return f[n][amount];
    }

    public int change2(int amount, int[] coins) {
        int n = coins.length;
        // 1.在二维解法的基础上，直接取消「物品维度」
        int[] f = new int[amount + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int coin = coins[i - 1];
            // 2.确保「容量维度」的遍历顺序为「从小到大」（适用于「完全背包」）
            // 3.将形如 f[i-1][j-k*coin] 的式子更替为 f[j-coin]，
            //   同时解决「数组越界」问题（将物品维度的遍历修改为从 coin 开始）
            for (int j = coin; j <= amount; ++j) {
                f[j] += f[j - coin];
            }
        }
        return f[amount];
    }

    public int change3(int amount, int[] coins) {
        // dp[x] 表示金额之和等于 x 的硬币组合数，目标是求 dp[amount]
        int[] dp = new int[amount + 1];
        // 动态规划的边界是 dp[0]=1。只有当不选取任何硬币时，金额之和才为 0，因此只有 1 种硬币组合
        dp[0] = 1;
        // 遍历 coins，对于其中的每个元素 coin
        for (int coin : coins) {
            // 遍历 i 从 coin 到 amount
            for (int i = coin; i <= amount; ++i) {
                // 将 dp[i−coin] 的值加到 dp[i]
                dp[i] += dp[i - coin];
            }
        }
        // 上述做法不会重复计算不同的排列。
        // 因为外层循环是遍历数组 coins 的值，内层循环是遍历不同的金额之和，
        // 在计算 dp[i] 的值时，可以确保金额之和等于 i 的硬币面额的顺序，
        // 由于顺序确定，因此不会重复计算不同的排列。
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().change1(5, new int[]{1, 2, 5}));
        System.out.println(new Solution().change2(5, new int[]{1, 2, 5}));
        System.out.println(new Solution().change3(5, new int[]{1, 2, 5}));
    }
}
