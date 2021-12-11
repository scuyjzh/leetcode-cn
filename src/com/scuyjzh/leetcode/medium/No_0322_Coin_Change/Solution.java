package com.scuyjzh.leetcode.medium.No_0322_Coin_Change;

import java.util.*;

/**
 * 322. 零钱兑换
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount
 * ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬
 * 币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 */
class Solution {
    /**
     * 方法一：记忆化搜索
     */
    public int coinChange1(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return dfs(coins, amount, new int[amount]);
    }

    private int dfs(int[] coins, int remain, int[] memo) {
        if (remain < 0) {
            return -1;
        }
        if (remain == 0) {
            return 0;
        }
        if (memo[remain - 1] != 0) {
            return memo[remain - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, remain - coin, memo);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        memo[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[remain - 1];
    }

    /**
     * 方法二：动态规划
     */
    public int coinChange2(int[] coins, int amount) {
        /*
         * 采用自下而上的方式进行思考。仍定义 F(i) 为组成金额 i 所需最少的硬币数量，假设在计算 F(i) 之
         * 前，已经计算出 F(0)~F(i−1) 的答案。 则 F(i) 对应的转移方程应为
         *         F(i) = min(F(i-c_j)) + 1, j=0...n-1
         * 其中 c_j 代表的是第 j 枚硬币的面值，即枚举最后一枚硬币面额是 c_j，那么需要从 i-c_j 这个金额的状态
         * F(i-c_j) 转移过来，再算上枚举的这枚硬币数量 1 的贡献，由于要硬币数量最少，所以 F(i) 为前面能转移
         * 过来的状态的最小值加上枚举的硬币数量 1。
         *
         * 例子：假设
         *     coins = [1, 2, 5], amount = 11
         * 则，当 i=0 时无法用硬币组成，为 0 。当 i<0 时，忽略 F(i)
         *     F(i)	    最小硬币数量
         *     F(0)	    0 //金额为0不能由硬币组成
         *     F(1)	    1 //F(1)=min(F(1−1),F(1−2),F(1−5))+1=1
         *     F(2)	    1 //F(2)=min(F(2−1),F(2−2),F(2−5))+1=1
         *     F(3)	    2 //F(3)=min(F(3−1),F(3−2),F(3−5))+1=2
         *     F(4)	    2 //F(4)=min(F(4-1),F(4-2),F(4-5))+1=2
         *     ...	    ...
         *     F(11)	3 //F(11)=min(F(11-1),F(11-2),F(11-5))+1=3
         *
         * 可以看到问题的答案是通过子问题的最优解得到的。
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
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().coinChange1(new int[]{1, 2, 5}, 11));
        System.out.println(new Solution().coinChange1(new int[]{2}, 3));
        System.out.println(new Solution().coinChange2(new int[]{1}, 0));
        System.out.println(new Solution().coinChange2(new int[]{1}, 2));
    }
}
