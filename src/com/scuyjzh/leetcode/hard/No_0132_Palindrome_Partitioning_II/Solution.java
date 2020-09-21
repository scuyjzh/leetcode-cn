package com.scuyjzh.leetcode.hard.No_0132_Palindrome_Partitioning_II;

/**
 * @author scuyjzh
 * @date 2020/8/22 2:41
 */
class Solution {
    public int minCut(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return 0;
        }

        // 状态定义：
        // dp[i]：前缀子串 s[0:i] （包括索引 i 处的字符）符合要求的最少分割次数
        // 状态转移方程：
        // dp[i] = min(dp[j] + 1 if s[j + 1: i] 是回文 for j in range(i))
        int[] dp = new int[len];

        // 2 个字符最多分割 1 次
        // 3 个字符最多分割 2 次
        // 初始化的时候，设置成为这个最多分割次数
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }

        // 参考「力扣」第 5 题：最长回文子串 动态规划 的解法
        // isPalindrome[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] isPalindrome = new boolean[len][len];
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组，这一步非必需
        char[] charArray = s.toCharArray();
        for (int j = 0; j < len; j++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int i = 0; i <= j; i++) {
                // 边界条件：j - 1 - (i + 1) + 1 < 2
                isPalindrome[i][j] = charArray[i] == charArray[j] && (j - i < 3 || isPalindrome[i + 1][j - 1]);
            }
        }

        // 1 个字符的时候，不用判断，因此 i 从 1 开始
        for (int i = 1; i < len; i++) {
            if (isPalindrome[0][i]) {
                dp[i] = 0;
                continue;
            }

            // 注意：这里是严格，要保证 s[j + 1:i] 至少得有一个字符串
            // 状态转移方程：dp[i] = min(dp[j] + 1 if s[j + 1: i] 是回文 for j in range(i))
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCut("madam"));
        System.out.println(solution.minCut("revive"));
    }
}
