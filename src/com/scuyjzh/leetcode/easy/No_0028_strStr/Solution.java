package com.scuyjzh.leetcode.easy.No_0028_strStr;

/**
 * 28. 实现 strStr()
 * <p>
 * 实现strStr()函数。
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 */
class Solution {
    /**
     * 方法一：Sunday算法
     */
    public int strStr1(String haystack, String needle) {
        int hayLen = haystack.length();
        int nLen = needle.length();

        // haystack串的游标索引
        int i = 0;
        // needle串的游标索引
        int j = 0;

        // haystack剩余字符少于needle串时跳过比较
        while (i <= hayLen - nLen) {
            // 将needle串与haystack串中参与比较的子串进行逐个字符比对
            while (j < nLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j += 1;
            }

            // 如果j等于needle串的长度说明此时匹配成功，可以直接返回此时主串的游标索引
            if (j == nLen) {
                return i;
            }

            // 不匹配时计算需要跳过的字符数，移动主串游标i
            if (i < hayLen - nLen) {
                // 对照字符在needle串存在，则需要跳过的字符数为从对照字符在needle串中最后出现的位置起剩余的字符个数
                // 不存在则跳过的字符数为needle串长度+1，也就是代码nLen-(-1)的情况
                i += nLen - lastIndex(needle, haystack.charAt(i + nLen));
            } else {
                return -1;
            }
            // 每次比较之后将needle游标置为0
            j = 0;
        }

        return -1;
    }

    private int lastIndex(String str, char ch) {
        // 从后往前检索
        for (int idx = str.length() - 1; idx >= 0; --idx) {
            if (str.charAt(idx) == ch) {
                return idx;
            }
        }
        return -1;
    }

    /**
     * 方法二：KMP算法
     */
    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0) {
            return -1;
        }

        char[] hayArray = haystack.toCharArray();
        char[] nArray = needle.toCharArray();

        return kmp(hayArray, nArray);
    }

    private int kmp(char[] haystack, char[] needle) {
        int hayLen = haystack.length;
        int nLen = needle.length;
        int[] next = next(needle);
        int j = 0;
        for (int i = 0; i < hayLen; ++i) {
            while (j > 0 && haystack[i] != needle[j]) {
                j = next[j - 1] + 1;
                if (nLen - j + i > hayLen) {
                    return -1;
                }
            }
            if (haystack[i] == needle[j]) {
                ++j;
            }
            if (j == nLen) {
                return i - nLen + 1;
            }
        }
        return -1;
    }

    private int[] next(char[] needle) {
        int nLen = needle.length;
        int[] next = new int[nLen];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < nLen; ++i) {
            while (k != -1 && needle[k + 1] != needle[i]) {
                k = next[k];
            }
            if (needle[k + 1] == needle[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.strStr1("hello", "ll"));
        System.out.println(solution.strStr2("aaaaa", "bba"));
    }
}
