package com.scuyjzh.leetcode.medium.No_0357_Count_Numbers_with_Unique_Digits;

/**
 * 357. 计算各个位数不同的数字个数
 *
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x <
 * 10^n 。
 */
class Solution {
    /**
     * 方法一：数学
     */
    public int countNumbersWithUniqueDigits1(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/solution/cbiao-ge-jie-shi-shu-xue-si-lu-die-dai-ac-by-monol/
         */
        if (n == 0) {
            return 1;
        }
        n = Math.min(n, 10);
        int ans = 10, base = 9, sum = 9;
        for (int i = 1; i < n; ++i) {
            ans += sum *= base--;
        }
        return ans;
    }

    /**
     * 方法二：动态规划
     */
    public int countNumbersWithUniqueDigits2(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/comments/56794
         */
        if (n == 0) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= n; ++i) {
            dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - (i - 1));
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countNumbersWithUniqueDigits1(2));
        System.out.println(new Solution().countNumbersWithUniqueDigits2(2));
    }
}
