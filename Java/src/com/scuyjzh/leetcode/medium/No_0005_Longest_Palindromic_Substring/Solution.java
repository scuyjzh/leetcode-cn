package com.scuyjzh.leetcode.medium.No_0005_Longest_Palindromic_Substring;

/**
 * 5. 最长回文子串
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
class Solution {
    /**
     * 方法一：动态规划
     *
     * • 时间复杂度：O(N^2)，其中 N 是字符串的长度。动态规划的状态总数为 O(N^2)，对于每个状态，需
     *   要转移的时间为 O(1)。
     * • 空间复杂度：O(N^2)，即存储动态规划状态需要的空间。
     */
    public String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; ++i) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        // 由于 dp[i][j] 参考它左下方的值：（1）先升序填列；（2）再升序填行
        for (int j = 1; j < len; ++j) {
            for (int i = 0; i < j; ++i) {
                // dp[i][j] = charArray[i] == charArray[j] && (j - i < 3 || dp[i + 1][j - 1]);
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 边界条件：j - 1 - (i + 1) + 1 < 2
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 方法二：中心扩展法
     *
     * • 时间复杂度：O(N^2)，其中 N 是字符串的长度。长度为 1 和 2 的回文中心分别有 N 和 N−1 个，每个回
     *   文中心最多会向外扩展 O(N) 次。
     * • 空间复杂度：O(1)。
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;

        char[] charArray = s.toCharArray();
        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < len - 1; ++i) {
            // 奇数回文串
            int oddLen = expandAroundCenter(charArray, i, i);
            // 偶数回文串
            int evenLen = expandAroundCenter(charArray, i, i + 1);
            int culMaxLen = Math.max(oddLen, evenLen);
            if (culMaxLen > maxLen) {
                maxLen = culMaxLen;
                // 这一步要在纸上画图发现规律
                begin = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private int expandAroundCenter(char[] charArray, int left, int right) {
        // 当 left = right 的时候，回文中心是一个字符，回文串的长度是奇数
        // 当 right = left + 1 的时候，回文中心是一个空隙，回文串的长度是偶数
        int len = charArray.length;
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (charArray[i] == charArray[j]) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // 跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，
        // 回文串的长度是 j - i + 1 - 2 = j - i - 1
        return j - i - 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome1("babad"));
        System.out.println(new Solution().longestPalindrome2("cbbd"));
        System.out.println(new Solution().longestPalindrome1("a"));
        System.out.println(new Solution().longestPalindrome2("ac"));
    }
}
