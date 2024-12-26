package com.scuyjzh.leetcode.medium.No_0375_Guess_Number_Higher_or_Lower_II;

/**
 * 375. 猜数字大小 II
 *
 * https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
 */
class Solution {
    public int getMoneyAmount(int n) {
        if (n == 1) {
            return 0;
        }
        /*
            状态定义：
            dp[i][j] 表示在范围 [i,j] 内确保胜利的最少金额，目标是计算 dp[1][n]。

            状态转移方程：
            dp[i][j] = min(i + dp[i + 1][j],
                           ...
                           k + max(dp[i][k - 1], dp[k + 1][j]), (i+1≤k≤j-1)
                           ...
                           j + dp[i][j - 1])

            状态初始化：
            dp[i][i] = 0 (1≤i≤n)
        */
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i != j) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // 按列来，从第 2 列开始
        for (int j = 2; j <= n; ++j) {
            // 按行来，从下往上
            for (int i = j - 1; i >= 1; --i) {
                // 算除了两端的每一个分割点
                for (int k = i + 1; k <= j - 1; ++k) {
                    dp[i][j] = Math.min(k + Math.max(dp[i][k - 1], dp[k + 1][j]), dp[i][j]);
                }
                // 算两端
                dp[i][j] = Math.min(dp[i][j], i + dp[i + 1][j]);
                dp[i][j] = Math.min(dp[i][j], j + dp[i][j - 1]);
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getMoneyAmount(10));
        System.out.println(new Solution().getMoneyAmount(1));
        System.out.println(new Solution().getMoneyAmount(2));
    }
}
