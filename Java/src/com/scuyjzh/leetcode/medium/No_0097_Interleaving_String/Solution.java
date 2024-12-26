package com.scuyjzh.leetcode.medium.No_0097_Interleaving_String;

/**
 * 97. 交错字符串
 *
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2
 * 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割
 * 成若干 非空 子字符串：
 *     • s = s1 + s2 + ... + sn
 *     • t = t1 + t2 + ... + tm
 *     • |n - m| <= 1
 *     • 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2
 *       + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 */
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // 利用「62. 不同路径」的动态规划思想求解
        int m = s1.length(), n = s2.length();
        if (s3.length() != m + n) {
            return false;
        }
        // 状态定义：dp[i][j] 表示 s1 的前 i 个元素和 s2 的前 j 个元素是否能交错组成 s3 的前 i+j 个元素
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 边界条件
        dp[0][0] = true;
        for (int i = 1; i <= m && s1.charAt(i - 1) == s3.charAt(i - 1); ++i) {
            dp[i][0] = true;
        }
        for (int j = 1; j <= n && s2.charAt(j - 1) == s3.charAt(j - 1); ++j) {
            dp[0][j] = true;
        }
        // 状态转移
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 到达 (i，j) 可能由 (i-1,j) 点向下一步，选择 s1[i-1] 到达
                // 也可能由 (i,j-1) 点向右一步，选择 s2[j-1] 到达
                dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1))
                        || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(new Solution().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(new Solution().isInterleave("", "", ""));
    }
}
