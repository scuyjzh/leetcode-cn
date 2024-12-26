package com.scuyjzh.leetcode.easy.No_0121_Best_Time_to_Buy_and_Sell_Stock;

/**
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票
 * 第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖
 * 出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
class Solution {
    /**
     * 方法一：暴力法
     */
    public int maxProfit1(int[] prices) {
        // 需要找出给定数组中两个数字之间的最大差值（即，最大利润）
        // 此外，第二个数字（卖出价格）必须大于第一个数字（买入价格）
        // 形式上，对于每组 i 和 j（其中 j>i）需要找出 max(prices[j]−prices[i])
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; ++i) {
            for (int j = i + 1; j < prices.length; ++j) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    /**
     * 方法一：动态规划
     */
    public int maxProfit2(int[] prices) {
        /*
         * 一、状态定义
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
         *   或者前一天结束时还没有股票，即 dp[i−1][0]，这时候要将其买入，并减少 prices[i] 的收益
         *   （因为只允许交易一次，所以此时利润就是当天股价的相反数）。
         *   因此可以列出如下的转移方程：
         *               dp[i][1] = max{dp[i−1][1], −prices[i]}
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
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[n - 1][0];
    }

    /**
     * 方法二：动态规划 + 空间优化
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        /*
         * 空间优化：
         * 注意到上面的状态转移方程中，每一天的状态只与前一天的状态有关，而与更早的状态都无关，
         * 因此不必存储这些无关的状态，只需要将 dp[i−1][0] 和 dp[i−1][1] 存放在两个变量中，
         * 通过它们计算出 dp[i][0] 和 dp[i][1] 并存回对应的变量，以便于第 i+1 天的状态转移即可。
         */
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, -prices[i]);
        }
        return dp0;
    }

    /**
     * 方法三：贪心
     */
    public int maxProfit4(int[] prices) {
        // 用一个变量记录一个历史最低价格 min
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            // 更新历史最低价格
            minPrice = Math.min(minPrice, price);
            // 更新最大利润
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new Solution().maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new Solution().maxProfit3(new int[]{7, 6, 4, 3, 1}));
        System.out.println(new Solution().maxProfit4(new int[]{7, 6, 4, 3, 1}));
    }
}
