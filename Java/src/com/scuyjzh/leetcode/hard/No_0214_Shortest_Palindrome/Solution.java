package com.scuyjzh.leetcode.hard.No_0214_Shortest_Palindrome;

/**
 * 214. 最短回文串
 *
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。
 * 找到并返回可以用这种方式转换的最短回文串。
 */
class Solution {
    /**
     * 方法：KMP 算法
     *
     * • 时间复杂度：O(∣s∣)。
     * • 空间复杂度：O(1)。
     */
    public String shortestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        // 获取反转后的字符串
        String rev = new StringBuffer(s).reverse().toString();
        // 将翻转后的字符串与原字符串通过“#”拼接
        rev = s + "#" + rev;
        int n = rev.length();
        // 通过 KMP 算法中的 next 数组求解方法获取拼接字符串的最长公共前后缀
        int[] next = new int[n];
        next[0] = -1;
        for (int i = 1, j = -1; i < n; ++i) {
            while (j != -1 && rev.charAt(i) != rev.charAt(j + 1)) {
                j = next[j];
            }
            if (rev.charAt(i) == rev.charAt(j + 1)) {
                j += 1;
            }
            next[i] = j;
        }
        return new StringBuffer(s.substring(next[n - 1] + 1)).reverse() + s;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestPalindrome("aacecaaa"));
        System.out.println(new Solution().shortestPalindrome("abcd"));
    }
}
