package com.scuyjzh.leetcode.medium.No_0005_Longest_Palindromic_Substring;

/**
 * 5. 最长回文子串
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 */
class Solution {
    /**
     * 方法一：动态规划
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N^2)，二维 dp 问题，一个状态得用二维有序数对表示，因此空间复杂度是 O(N^2)
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
        char[] charArray = s.toCharArray();
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
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
     * 方法二：中心扩散
     * 时间复杂度：O(N^2)，枚举“中心位置”时间复杂度为 O(N)，从“中心位置”扩散得到“回文子串”的时间复杂度为 O(N)，因此时间复杂度可以降到 O(N^2)
     * 空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关
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
        for (int i = 0; i < len - 1; i++) {
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
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome1("madam"));
        System.out.println(solution.longestPalindrome2("madam"));
        System.out.println(solution.longestPalindrome1("revive"));
        System.out.println(solution.longestPalindrome2("revive"));
    }
}
