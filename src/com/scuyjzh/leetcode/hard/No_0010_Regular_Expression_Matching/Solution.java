package com.scuyjzh.leetcode.hard.No_0010_Regular_Expression_Matching;

/**
 * 10. 正则表达式匹配
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * • '.' 匹配任意单个字符
 * • '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
class Solution {
    /**
     * 方法：动态规划
     * 时间复杂度：O(mn)，其中 m 和 n 分别是字符串 s 和 p 的长度。需要计算出所有的状态，并且每个状态在进行转移时的时间复杂度为 O(1)。
     * 空间复杂度：O(mn)，即为存储所有状态使用的空间。
     */
    public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        // dp[i][j]表示文本串s的前i个字符与模式串p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];

        // 初始值
        // s为空，p为空，能匹配上
        // p为空，s不为空，必为false(boolean数组默认值为false，无需处理)
        dp[0][0] = true;

        // s为空，p不为空，由于*可以匹配0个字符，所以有可能为true
        // s = ""
        // p = "a*b*c*d*e*"
        for (int j = 2; j <= cp.length; ++j) {
            if (cp[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= cs.length; ++i) {
            for (int j = 1; j <= cp.length; ++j) {
                // 文本串s的末位字符和模式串p的末位字符能匹配上
                // s = "abc"
                // p = "abc" or "ab."
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 文本串s的末位字符和模式串p的末位字符匹配不上，但模式串p末位是*
                else if (cp[j - 1] == '*') {
                    // 模式串p末位*的前一个字符能够跟文本串s的末位匹配上
                    // s = "abc"
                    // p = "abc*" or "ab.*"
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
                        // *匹配0次的情况    or    *匹配大于等于一次的情况
                        // s = "ab"              s = "abb"
                        // p = "abc*"            p = "ab*"
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    }
                    // 模式串p末位*的前一个字符不能够跟文本串s的末位匹配
                    // s = "abc"
                    // p = "aba*"
                    else {
                        // *只能匹配0次，干掉模式串p末位两个字符
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[cs.length][cp.length];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatch("aa", "a"));
        System.out.println(solution.isMatch("aa", "a*"));
        System.out.println(solution.isMatch("ab", ".*"));
        System.out.println(solution.isMatch("aab", "c*a*b"));
        System.out.println(solution.isMatch("mississippi", "mis*is*p*."));
    }
}
