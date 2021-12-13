package com.scuyjzh.leetcode.hard.No_0010_Regular_Expression_Matching;

/**
 * 10. 正则表达式匹配
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持
 * '.' 和 '*' 的正则表达式匹配。
 *   • '.' 匹配任意单个字符
 *   • '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
class Solution {
    public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        // dp[i][j] 表示文本串 s 的前 i 个字符与模式串 p 的前 j 个字符是否能够匹配
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];

        // 初始值：
        // s 为空，p 为空，能匹配上
        // p 为空，s 不为空，必为 false（boolean 数组默认值为 false，无需处理）
        dp[0][0] = true;

        // 边界值：
        // s 为空，p 不为空，由于 * 可以匹配 0 个字符，所以有可能为 true
        // s = ""
        // p = "a*b*c*d*e*"
        for (int j = 2; j <= cp.length; ++j) {
            if (cp[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 状态转移
        for (int i = 1; i <= cs.length; ++i) {
            for (int j = 1; j <= cp.length; ++j) {
                // 文本串 s 的末位字符和模式串 p 的末位字符能匹配上
                // s = "abc"
                // p = "abc" or "ab."
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 文本串 s 的末位字符和模式串 p 的末位字符匹配不上，但模式串 p 末位是 *
                else if (cp[j - 1] == '*') {
                    // 模式串 p 末位 * 的前一个字符能够跟文本串 s 的末位匹配上
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
                        // * 匹配 0 次的情况  or  * 匹配一次的情况  or  * 匹配多次的情况
                        // s = "ab"             s = "ab"            s = "abb"
                        // p = "abb*"           p = "ab*"           p = "ab*"
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
                    }
                    // 模式串 p 末位 * 的前一个字符不能够跟文本串 s 的末位匹配
                    // s = "ab"
                    // p = "abc*"
                    else {
                        // * 只能匹配 0 次，干掉模式串 p 末位两个字符
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[cs.length][cp.length];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aa", "a"));
        System.out.println(new Solution().isMatch("aa", "a*"));
        System.out.println(new Solution().isMatch("ab", ".*"));
        System.out.println(new Solution().isMatch("aab", "c*a*b"));
        System.out.println(new Solution().isMatch("mississippi", "mis*is*p*."));
    }
}
