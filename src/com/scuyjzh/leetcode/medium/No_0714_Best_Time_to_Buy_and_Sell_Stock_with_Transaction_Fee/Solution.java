package com.scuyjzh.leetcode.medium.No_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee;

/**
 * 714. 买卖股票的最佳时机含手续费
 *
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；
 * 整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购
 * 买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需
 * 要为支付一次手续费。
 */
class Solution {
    /**
     * 方法一：动态规划
     */
    public int maxProfit1(int[] prices, int fee) {
        /*
         * 一、状态定义
         * 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
         * • 定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润（i 从 0 开始）
         * • 定义状态 dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润
         *
         * 二、状态转移
         * • 考虑 dp[i][0] 的转移方程，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股
         *   票，即 dp[i−1][0]；或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]，这时候要将其卖出，并
         *   获得 prices[i] 的收益，但需要支付 fee 的手续费。因此为了收益最大化，列出如下的转移方程：
         *       dp[i][0] = max{dp[i−1][0], dp[i−1][1] + prices[i] − fee}
         *
         * • 再来按照同样的方式考虑 dp[i][1] 按状态转移，那么可能的转移状态为前一天已经持有一支股票，即
         *   dp[i−1][1]；或者前一天结束时还没有股票，即 dp[i−1][0]，这时候要将其买入，并减少 prices[i]
         *   的收益。可以列出如下的转移方程：
         *       dp[i][1] = max{dp[i−1][1], dp[i−1][0] − prices[i]}
         *
         * 三、状态初始化
         * 对于初始状态，根据状态定义可以知道第 0 天交易结束的时候有 dp[0][0]=0 以及 dp[0][1]=−prices[0]。
         *
         * 由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，
         * 因此这时候 dp[n−1][0] 的收益必然是大于 dp[n−1][1] 的，最后的答案即为 dp[n−1][0]。
         */
        int n = prices.length;

        // 定义状态：
        // dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润
        // dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润
        int[][] dp = new int[n][2];

        // 对于初始状态，根据状态定义可以知道第 0 天交易结束的时候有 dp[0][0]=0 以及 dp[0][1]=−prices[0]
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // // 从第 2 天开始进行状态转移
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    /**
     * 方法二：动态规划 + 空间优化
     */
    public int maxProfit2(int[] prices, int fee) {
        /*
         * 空间优化：
         * 注意到在状态转移方程中，dp[i][0] 和 dp[i][1] 只会从 dp[i−1][0] 和 dp[i−1][1] 转移而来，
         * 因此不必使用数组存储所有的状态，而是使用两个变量 sell 以及 buy 分别表示 dp[..][0] 和 dp[..][1] 直接进行状态转移即可。
         */
        int n = prices.length;
        int sell = 0, buy = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newSell = Math.max(sell, buy + prices[i] - fee);
            int newBuy = Math.max(buy, sell - prices[i]);
            sell = newSell;
            buy = newBuy;
        }
        return sell;
    }

    /**
     * 方法三：贪心
     */
    public int maxProfit3(int[] prices, int fee) {
        int res = 0;
        // 记录最低价格
        int min = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            // 情况二：相当于买入
            if (prices[i] < min) {
                min = prices[i];
            }

            // 情况三：保持原有状态（因为此时买则不便宜，卖则亏本）
            if (prices[i] >= min && prices[i] <= min + fee) {
                continue;
            }

            // 计算利润，可能有多次计算利润，最后一次计算利润才是真正意义的卖出
            if (prices[i] > min + fee) {
                res += prices[i] - min - fee;
                // 情况一：收获利润的这一天并不是收获利润区间里的最后一天（不是真正的卖出，相当于持有股票），所以后面要继续收获利润
                min = prices[i] - fee;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit1(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(new Solution().maxProfit2(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(new Solution().maxProfit3(new int[]{1, 3, 7, 5, 10, 3}, 3));
    }
}
