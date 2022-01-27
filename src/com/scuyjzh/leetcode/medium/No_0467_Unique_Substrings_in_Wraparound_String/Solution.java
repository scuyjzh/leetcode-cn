package com.scuyjzh.leetcode.medium.No_0467_Unique_Substrings_in_Wraparound_String;

/**
 * 467. 环绕字符串中唯一的子字符串
 *
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是
 * 这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 *
 * 现在有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，
 * 尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。
 *
 * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
 */
class Solution {
    public int findSubstringInWraproundString(String p) {
        int n = p.length();
        if (n < 1) {
            return 0;
        }

        char[] chp = p.toCharArray();
        // 记录 p 中以每个小写英文字母结尾的最长连续子串的长度
        int[] dp = new int[26];
        // 记录当前连续子串的长度
        int curMaxLen = 1;
        // 遍历 p 中的所有字符
        for (int i = 0; i < n; ++i) {
            // 判断字符是否连续
            if (i > 0 && (chp[i] - chp[i - 1] == 1 || chp[i - 1] - chp[i] == 25)) {
                // 连续则加一
                curMaxLen += 1;
            } else {
                // 不连续则重置
                curMaxLen = 1;
            }
            // 只存储最长的连续长度
            dp[chp[i] - 'a'] = Math.max(dp[chp[i] - 'a'], curMaxLen);
        }

        int res = 0;
        // 统计所有以每个小写英文字母结尾的最长连续子串的长度，就是所求结果
        for (int temp : dp) {
            res += temp;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findSubstringInWraproundString("zab"));
        System.out.println(new Solution().findSubstringInWraproundString("cac"));
    }
}
