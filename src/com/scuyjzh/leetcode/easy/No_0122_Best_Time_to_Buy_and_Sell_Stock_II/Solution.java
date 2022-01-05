package com.scuyjzh.leetcode.easy.No_0122_Best_Time_to_Buy_and_Sell_Stock_II;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价
 * 格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交
 * 易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
class Solution {
    /**
     * 方法一：动态规划
     */
    public int maxProfit1(int[] prices) {
        /*
         * 一、状态定义
         * 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态：
         * • 定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润（i 从 0 开始）
         * • 定义状态 dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润
         *
         * 二、状态转移
         * • 对于 dp[i][0]，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]；
         *   或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]，这时候要将其卖出，并获得 prices[i] 的收益。
         *   因此为了收益最大化，列出如下的转移方程：
         *           dp[i][0] = max{dp[i−1][0], dp[i−1][1] + prices[i]}
         *
         * • 对于 dp[i][1]，可能的转移状态为前一天已经持有一支股票，即 dp[i−1][1]；
         *   或者前一天结束时还没有股票，即 dp[i−1][0]，这时候要将其买入，并减少 prices[i] 的收益。
         *   因此可以列出如下的转移方程：
         *           dp[i][1] = max{dp[i−1][1], dp[i−1][0] − prices[i]}
         *
         * 三、状态初始化
         * 对于初始状态，根据状态定义可以知道第 0 天交易结束的时候 dp[0][0]=0，dp[0][1]=−prices[0]。
         *
         * 由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，
         * 因此这时候 dp[n−1][0] 的收益必然是大于 dp[n−1][1] 的，最后的答案即为 dp[n−1][0]。
         */
        int n = prices.length;

        // 定义状态：
        // dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润
        // dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润
        int[][] dp = new int[n][2];

        // 对于初始状态，根据状态定义可以知道第 0 天交易结束的时候 dp[0][0]=0, dp[0][1]=−prices[0]
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第 2 天开始进行状态转移
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    /**
     * 方法二：动态规划 + 空间优化
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        /*
         * 空间优化：
         * 注意到上面的状态转移方程中，每一天的状态只与前一天的状态有关，而与更早的状态都无关，
         * 因此不必存储这些无关的状态，只需要将 dp[i−1][0] 和 dp[i−1][1] 存放在两个变量中，
         * 通过它们计算出 dp[i][0] 和 dp[i][1] 并存回对应的变量，以便于第 i+1 天的状态转移即可。
         */
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }

    /**
     * 方法三：贪心
     */
    public int maxProfit3(int[] prices) {
        /*
         * 该算法仅可以用于计算，计算的过程并不是真正交易的过程，但可以用贪心算法计算题目要求的最大利润。
         *
         * 这道题 「贪心」 的地方在于，对于 「今天的股价 - 昨天的股价」，得到的结果有 3 种可能：① 正
         * 数，② 0，③ 负数。贪心算法的决策是：只加正数。
         */
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            int profit = prices[i] - prices[i - 1];
            if (profit > 0) {
                res += profit;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new Solution().maxProfit2(new int[]{1, 2, 3, 4, 5}));
        System.out.println(new Solution().maxProfit3(new int[]{7, 6, 4, 3, 1}));
    }
}
