package com.scuyjzh.leetcode.medium.No_0322_Coin_Change;

import java.util.*;

/**
 * 322. 零钱兑换
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount
 * ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬
 * 币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 */
class Solution {
    int[] memo;

    public int coinChange1(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        // 记忆化数组
        memo = new int[amount];
        // 记忆化搜索
        return dfs(coins, amount);
    }

    private int dfs(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (memo[amount - 1] != 0) {
            return memo[amount - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; ++i) {
            int res = dfs(coins, amount - coins[i]);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        memo[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[amount - 1];
    }

    int INF = 0x3f3f3f3f;

    public int coinChange2(int[] coins, int amount) {
        // 完全背包（朴素解法）
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];

        // 初始化（没有任何硬币的情况）：只有 f[0][0] = 0；其余情况均为无效值。
        // 这是由「状态定义」决定的，当不考虑任何硬币的时候，只能凑出总和为 0 的方案，所使用的硬币数量为 0
        for (int j = 1; j <= amount; ++j) {
            f[0][j] = INF;
        }

        // 有硬币的情况
        for (int i = 1; i <= n; ++i) {
            int coin = coins[i - 1];
            for (int j = 0; j <= amount; ++j) {
                // 不考虑当前硬币的情况
                f[i][j] = f[i - 1][j];

                // 考虑当前硬币的情况（可选当前硬币个数基于当前容量大小）
                int maxK = j / coin;
                for (int k = 1; k <= maxK; ++k) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - k * coin] + k);
                }
            }
        }

        return f[n][amount] == INF ? -1 : f[n][amount];
    }

    public int coinChange3(int[] coins, int amount) {
        // 完全背包（一维优化）
        int n = coins.length;
        int[] f = new int[amount + 1];
        for (int j = 1; j <= amount; ++j) {
            f[j] = INF;
        }
        for (int i = 1; i <= n; ++i) {
            int coin = coins[i - 1];
            for (int j = coin; j <= amount; ++j) {
                f[j] = Math.min(f[j], f[j - coin] + 1);
            }
        }
        return f[amount] == INF ? -1 : f[amount];
    }

    public int coinChange4(int[] coins, int amount) {
        /*
         * 采用自下而上的方式进行思考。仍定义 F(i) 为组成金额 i 所需最少的硬币数量，假设在计算 F(i) 之
         * 前，已经计算出 F(0)~F(i−1) 的答案。 则 F(i) 对应的转移方程应为
         *         F(i) = min(F(i-c_j)) + 1, j=0...n-1
         * 其中 c_j 代表的是第 j 枚硬币的面值，即枚举最后一枚硬币面额是 c_j，那么需要从 i-c_j 这个金额的状态
         * F(i-c_j) 转移过来，再算上枚举的这枚硬币数量 1 的贡献，由于要硬币数量最少，所以 F(i) 为前面能转移
         * 过来的状态的最小值加上枚举的硬币数量 1。
         */
        if (amount == 0) {
            return 0;
        }
        // 状态定义
        int[] dp = new int[amount + 1];
        // 状态初始化
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // 状态转移
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; ++j) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().coinChange1(new int[]{1, 2, 5}, 11));
        System.out.println(new Solution().coinChange2(new int[]{1, 2, 5}, 11));
        System.out.println(new Solution().coinChange3(new int[]{1, 2, 5}, 11));
        System.out.println(new Solution().coinChange4(new int[]{1, 2, 5}, 11));
    }
}
