package com.scuyjzh.leetcode.easy.No_0028_strStr;

/**
 * 28. 实现 strStr()
 * <p>
 * 实现strStr()函数。
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 * 如果不存在，则返回 -1 。
 * 说明：
 * 当 needle 是空字符串时，应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 */
class Solution {
    /**
     * 方法一：暴力匹配
     * 时间复杂度：O(n×m)，其中 n 是字符串 haystack 的长度，m 是字符串 needle 的长度。最坏情况下需要将字符串 needle 与字符串 haystack 的所有长度为 m 的子串均匹配一次。
     * 空间复杂度：O(1)。只需要常数的空间保存若干变量。
     */
    public int strStr1(String haystack, String needle) {
        /*
         * 思路及算法：
         * 可以让字符串 needle 与字符串 haystack 的所有长度为 m 的子串均匹配一次。
         *
         * 为了减少不必要的匹配，每次匹配失败即立刻停止当前子串的匹配，对下一个子串继续匹配。如果当前
         * 子串匹配成功，返回当前子串的开始位置即可。如果所有子串都匹配失败，则返回 −1。
         */
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法二：Knuth-Morris-Pratt 算法
     * 时间复杂度：O(n+m)，其中 n 是字符串 haystack 的长度，m 是字符串 needle 的长度。至多需要遍历两字符串一次。
     * 空间复杂度：O(m)，其中 m 是字符串 needle 的长度。只需要保存字符串 needle 的前缀函数。
     */
    public int strStr2(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; ++i) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i < n; ++i) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    /**
     * 方法三：Sunday 算法
     */
    public int strStr3(String haystack, String needle) {
        /*
         * 思想：
         * Sunday算法由Daniel M.Sunday在1990年提出，它的思想跟BM算法很相似：
         * 只不过Sunday算法是从前往后匹配，在匹配失败时关注的是主串中参加匹配的最末位字符的下一位字符。
         *   • 如果该字符没有在模式串中出现则直接跳过，即移动位数 = 模式串长度 + 1；
         *   • 否则，其移动位数 = 模式串长度 - 该字符最右出现的位置(以0开始) = 模式串中该字符最右出现的位置到尾部的距离 + 1。
         */
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

            // 如果 j 等于needle串的长度说明此时匹配成功，可以直接返回此时主串的游标索引
            if (j == nLen) {
                return i;
            }

            // 不匹配时计算需要跳过的字符数，移动主串游标 i
            if (i < hayLen - nLen) {
                // 对照字符在needle串存在，则需要跳过的字符数为从对照字符在needle串中最后出现的位置起剩余的字符个数
                // 不存在则跳过的字符数为needle串长度加1，也就是代码 nLen-(-1) 的情况
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.strStr1("hello", "ll"));
        System.out.println(solution.strStr2("aaaaa", "bba"));
        System.out.println(solution.strStr3("", ""));
    }
}