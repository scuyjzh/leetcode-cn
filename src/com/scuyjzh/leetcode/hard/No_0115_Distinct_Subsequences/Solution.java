package com.scuyjzh.leetcode.hard.No_0115_Distinct_Subsequences;

/**
 * 115. 不同的子序列
 *
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个
 * 数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰
 * 剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个
 * 子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 */
class Solution {
    /**
     * 方法一：递归 + 记忆化
     */
    public int numDistinct1(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }

        // 二维 memo 数组，存储计算过的子问题的结果
        // memo[i][j] 表示 s[0:i] 的子序列中 t[0:j] 出现的个数
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                memo[i][j] = -1;
            }
        }

        return helper(memo, m - 1, n - 1, s, t);
    }

    private int helper(int[][] memo, int i, int j, String s, String t) {
        // base case：当 j 指针越界，此时 t 为空串，s 不管是否为空串，匹配方式数都为 1
        if (j < 0) {
            return 1;
        }
        // base case：当 i 指针越界，此时 s 为空串，t 不是空串，s 匹配不了 t，匹配方式数为 0
        if (i < 0) {
            return 0;
        }
        // memo 中存有当前遇到的子问题的解，直接获取
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s.charAt(i) == t.charAt(j)) {
            // t[j] 被匹配掉，对应 helper(i-1, j-1)；不被匹配掉，对应 helper(i-1, j)
            memo[i][j] = helper(memo, i - 1, j - 1, s, t) + helper(memo, i - 1, j, s, t);
        } else {
            memo[i][j] = helper(memo, i - 1, j, s, t);
        }
        // 返回当前递归子问题的解
        return memo[i][j];
    }

    /**
     * 方法二：动态规划
     */
    public int numDistinct2(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }

        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();

        // dp[i][j] 表示 s[0:i-1] 的子序列中 t[0:j-1] 出现的个数
        // 即：前 i 个字符的 s 子串中，出现前 j 个字符的 t 子串的个数
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; ++i) {
            for (int j = 0; j < n + 1; ++j) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 0;
                } else {
                    if (chs[i - 1] == cht[j - 1]) {
                        // t[j-1] 被匹配掉，对应 helper(i-1, j-1)；不被匹配掉，对应 helper(i-1, j)
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numDistinct1("rabbbit", "rabbit"));
        System.out.println(new Solution().numDistinct2("babgbag", "bag"));
    }
}
