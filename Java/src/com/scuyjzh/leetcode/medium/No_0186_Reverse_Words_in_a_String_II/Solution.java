package com.scuyjzh.leetcode.medium.No_0186_Reverse_Words_in_a_String_II;

/**
 * 186. 翻转字符串里的单词 II
 * <p>
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 示例：
 * 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 注意：
 *   • 单词的定义是不包含空格的一系列字符
 *   • 输入字符串中不会包含前置或尾随的空格
 *   • 单词与单词之间永远是以单个空格隔开的
 * 进阶：使用 O(1) 额外空间复杂度的原地解法。
 */
class Solution {
    public void reverseWords(char[] s) {
        // 先对字符串中的每个单词进行翻转
        int N = s.length;
        int start = 0;
        for (int i = 0; i < N; ++i) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
        reverse(s, start, N - 1);

        // 然后翻转整个字符串
        reverse(s, 0, N - 1);
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        char[] s = "the sky is blue".toCharArray();
        new Solution().reverseWords(s);
        System.out.println(s);
    }
}