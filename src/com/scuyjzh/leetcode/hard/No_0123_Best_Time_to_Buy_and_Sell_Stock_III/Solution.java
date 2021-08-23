package com.scuyjzh.leetcode.hard.No_0123_Best_Time_to_Buy_and_Sell_Stock_III;

/**
 * 123. 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
class Solution {
    /**
     * 方法一：动态规划
     * 时间复杂度：O(n)，其中 n 是数组 prices 的长度。
     * 空间复杂度：O(n)。
     */
    public int maxProfit1(int[] prices) {
        /*
         * 思路与算法：
         * 一、状态定义
         * 由于最多可以完成两笔交易，因此在任意一天结束之后，会处于以下五个状态中的一种：
         *   • 0 - 未进行过任何操作；
         *   • 1 - 只进行过一次买操作；
         *   • 2 - 进行了一次买操作和一次卖操作，即完成了一笔交易；
         *   • 3 - 在完成了一笔交易的前提下，进行了第二次买操作；
         *   • 4 - 完成了全部两笔交易。
         * dp[i][j]中 i 表示第 i 天，j 为 [0-4] 五个状态，dp[i][j]表示第 i 天状态 j 时的最大利润。
         *
         * 二、状态转移
         * 知道了第 i−1 天结束后的这四个状态，通过状态转移方程得到第 i 天结束后的这四个状态（状态 0 的最大利润显然为 0）：
         * ① 对于状态 1 而言，在第 i 天可以不进行任何操作，保持不变，也可以在未进行任何操作的前提下以 prices[i] 的价格买入股票，那么状态 1 的状态转移方程即为：
         *    dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i]);
         * ② 对于状态 2 而言，在第 i 天可以不进行任何操作，保持不变，也可以在只进行过一次买操作的前提下以 prices[i] 的价格卖出股票，那么状态 2 的状态转移方程即为：
         *    dp[i][2] = max(dp[i-1][2], dp[i-1][1] + prices[i]);
         * ③ 对于状态 3 而言，在第 i 天可以不进行任何操作，保持不变，也可以在完成了一笔交易的前提下以 prices[i] 的价格买入股票，那么状态 3 的状态转移方程即为：
         *    dp[i][3] = max(dp[i-1][3], dp[i-1][2] - prices[i]);
         * ④ 对于状态 4 而言，在第 i 天可以不进行任何操作，保持不变，也可以在进行过第二次买操作的前提下以 prices[i] 的价格卖出股票，那么状态 4 的状态转移方程即为：
         *    dp[i][4] = max(dp[i-1][4], dp[i-1][3] + prices[i]);
         *
         * 三、状态初始化
         * 那么对于边界条件，考虑第 i=0 天时的四个状态：
         *   • 状态 0 的利润显然为 0，因此 dp[0][0] = 0；
         *   • 状态 1 即为以 prices[0] 的价格买入股票，因此 dp[0][1] = −prices[0]；
         *   • 状态 2 即为在同一天买入并且卖出，因此 dp[0][2] = 0；
         *   • 状态 3 即为在同一天买入并且卖出后再以 prices[0] 的价格买入股票，因此 dp[0][3] = −prices[0]；
         *   • 状态 4 即为第二次卖出，因此 dp[0][4] = 0；
         * 将这四个状态作为边界条件，从 i=1 开始进行动态规划，即可得到答案。
         *
         * 在动态规划结束后，由于可以进行不超过两笔交易，因此最终的答案在 dp[n-1][0]、dp[n-1][2]、dp[n-1][4] 中，且为三者中的最大值。
         * 然而可以发现，由于在边界条件中 dp[0][2] 和 dp[0][4] 的值已经为 0，并且在状态转移的过程中维护的是最大值，因此 dp[n-1][2] 和 dp[n-1][4] 最终一定大于等于 0。
         * 同时，如果最优的情况对应的是恰好一笔交易，那么它也会因为在转移时允许在同一天买入并且卖出这一宽松的条件，从 dp[n-1][2] 转移至 dp[n-1][4]，因此最终的答案即为 dp[n-1][4]。
         */
        int n = prices.length;
        int[][] dp = new int[n][5];
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[n - 1][4];
    }

    /**
     * 方法二：动态规划（空间优化）
     * 时间复杂度：O(n)，其中 n 是数组 prices 的长度。
     * 空间复杂度：O(1)。
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        // 注意到第 i 天的最大利润只和第 i-1 天的最大利润相关，空间复杂度可以降到 O(1)
        int[] dp = new int[5];
        dp[1] = -prices[0];
        dp[3] = -prices[0];
        for (int i = 1; i < n; ++i) {
            // dp[1] = Math.max(dp[1], dp[0] - prices[i]);
            // dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            // dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            // dp[4] = Math.max(dp[4], dp[3] + prices[i]);
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
        }
        return dp[4];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit1(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(new Solution().maxProfit2(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(new Solution().maxProfit1(new int[]{1, 2, 3, 4, 5}));
        System.out.println(new Solution().maxProfit2(new int[]{1, 2, 3, 4, 5}));
        System.out.println(new Solution().maxProfit1(new int[]{7, 6, 4, 3, 1}));
        System.out.println(new Solution().maxProfit2(new int[]{7, 6, 4, 3, 1}));
        System.out.println(new Solution().maxProfit1(new int[]{1}));
        System.out.println(new Solution().maxProfit2(new int[]{1}));
    }
}
