package com.scuyjzh.leetcode.hard.No_0188_Best_Time_to_Buy_and_Sell_Stock_IV;

import java.util.*;

/**
 * 188. 买卖股票的最佳时机 IV
 *
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的
 * 股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
class Solution {
    /**
     * 方法：动态规划
     */
    public int maxProfit(int k, int[] prices) {
        /*
         * 一、状态定义
         * 与其余的股票问题类似，使用一系列变量存储「买入」的状态，再用一系列变量存储「卖出」的状态，
         * 通过动态规划的方法即可解决本题。
         * • 用 buy[i][j] 表示第 i 天进行恰好 j 笔交易，并且当前手上持有一支股票，这种情况下的最大利润；
         * • 用 sell[i][j] 表示第 i 天恰好进行 j 笔交易，并且当前手上不持有股票，这种情况下的最大利润。
         *
         * 二、状态转移
         * • 对于 buy[i][j]，考虑当前手上持有的股票是否是在第 i 天买入的。
         *   如果是第 i 天买入的，那么在第 i−1 天时，手上不持有股票，对应状态 sell[i−1][j]，并且需要扣除 prices[i] 的买入花费；
         *   如果不是第 i 天买入的，那么在第 i−1 天时，手上持有股票，对应状态 buy[i−1][j]。
         *   那么可以得到状态转移方程：
         *       buy[i][j] = max{buy[i−1][j], sell[i−1][j] − price[i]}
         *
         * • 对于 sell[i][j]，考虑当前手上持有的股票是否是在第 i 天卖出的。
         *   如果是第 i 天卖出的，那么在第 i−1 天时，手上持有股票，对应状态 buy[i−1][j−1]，并且需要增加 prices[i] 的卖出收益；
         *   如果不是第 i 天卖出的，那么在第 i−1 天时，手上不持有股票，对应状态 sell[i−1][j]。
         *   那么可以得到状态转移方程：
         *       sell[i][j] = max{sell[i−1][j], buy[i−1][j−1] + price[i]}
         *
         * 由于在所有的 n 天结束后，手上不持有股票对应的最大利润一定是严格优于手上持有股票对应的最大利润
         * 的，然而完成的交易数并不是越多越好（例如数组 prices 单调递减，不进行任何交易才是最优的），因
         * 此最终的答案即为 sell[n−1][0..k] 中的最大值。
         *
         * 三、状态初始化
         * 在上述的状态转移方程中，确定边界条件是非常重要的步骤。可以考虑将所有的 buy[0][0..k] 以及 sell[0][0..k] 设置为边界。
         * • 对于 buy[0][0..k]，由于只有 prices[0] 唯一的股价，因此不可能进行过任何交易，那么可以将所有的 buy[0][1..k] 设置为一个非常小的值，表示不合法的状态。
         *   而对于 buy[0][0]，它的值为 −prices[0]，即「在第 0 天以 prices[0] 的价格买入股票」是唯一满足手上持有股票的方法。
         *
         * • 对于 sell[0][0..k]，同理可以将所有的 sell[0][1..k] 设置为一个非常小的值，表示不合法的状态。
         *   而对于 sell[0][0]，它的值为 0，即「在第 0 天不做任何事」是唯一满足手上不持有股票的方法。
         *
         * 在设置完边界之后，就可以使用二重循环，在 i∈[1,n),j∈[0,k] 的范围内进行状态转移。
         * 需要注意的是，sell[i][j] 的状态转移方程中包含 buy[i−1][j−1]，在 j=0 时其表示不合法的状态，
         * 因此在 j=0 时，无需对 sell[i][j] 进行转移，让其保持值为 0 即可。
         *
         * 最后需要注意的是，本题中 k 的最大值可以达到 10^9，然而这是毫无意义的，
         * 因为 n 天最多只能进行 ⌊n/2⌋ 笔交易，其中 ⌊x⌋ 表示对 x 向下取整。
         * 因此可以将 k 对 ⌊n/2⌋ 取较小值之后再进行动态规划。
         */
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // n 天最多只能进行 ⌊n/2⌋ 笔交易，因此可以将 k 对 ⌊n/2⌋ 取较小值之后再进行动态规划
        k = Math.min(k, n / 2);

        // 状态定义：
        // 用 buy[i][j] 表示第 i 天进行恰好 j 笔交易，并且当前手上持有一支股票，这种情况下的最大利润
        // 用 sell[i][j] 表示第 i 天恰好进行 j 笔交易，并且当前手上不持有股票，这种情况下的最大利润
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];

        // 状态初始化
        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; ++i) {
            // 除以 2：防止 Integer.MIN_VALUE - prices[i] 越界
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        // 使用二重循环，在 i∈[1,n),j∈[0,k] 的范围内进行状态转移
        for (int i = 1; i < n; ++i) {
            // 在 j=0 时，无需对 sell[i][j] 进行转移，让其保持值为 0 即可
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(2, new int[]{2, 4, 1}));
        System.out.println(new Solution().maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }
}
