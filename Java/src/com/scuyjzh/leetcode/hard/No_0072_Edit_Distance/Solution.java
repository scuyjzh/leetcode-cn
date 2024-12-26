package com.scuyjzh.leetcode.hard.No_0072_Edit_Distance;

/**
 * 72. 编辑距离
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所
 * 使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 *   • 插入一个字符
 *   • 删除一个字符
 *   • 替换一个字符
 */
class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j] 表示从源字符串 word1 位置 i 到目标字符串 word2 位置 j 处所需的最少编辑操作次数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); ++i) {
            // 从 i 个字符变成 0 个字符，需要删除 i 个字符 word1[0..i-1]
            dp[i][0] = i;
        }
        for (int i = 1; i <= word2.length(); ++i) {
            // 从 0 个字符变成 i 个字符，需要插入 i 个字符 word2[0..i-1]
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); ++i) {
            for (int j = 1; j <= word2.length(); ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 不用操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 插入操作（插入一个字符 word2[j]）
                    int insert = dp[i][j - 1] + 1;
                    // 删除操作（删除一个字符 word1[i]）
                    int delete = dp[i - 1][j] + 1;
                    // 替换操作（把字符 word1[i] 替换成 word2[j]）
                    int replace = dp[i - 1][j - 1] + 1;
                    // 取三种操作结果的最小值
                    dp[i][j] = Math.min(replace, Math.min(delete, insert));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minDistance("horse", "ros"));
        System.out.println(new Solution().minDistance("intention", "execution"));
    }
}
