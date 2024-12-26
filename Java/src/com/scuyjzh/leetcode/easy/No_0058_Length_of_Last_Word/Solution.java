package com.scuyjzh.leetcode.easy.No_0058_Length_of_Last_Word;

/**
 * 58. 最后一个单词的长度
 * <p>
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 */
class Solution {
    /**
     * 方法：反向遍历
     * 时间复杂度：O(N)，其中 N 是字符串的长度。最多需要反向遍历字符串一次。
     * 空间复杂度：O(1)。
     */
    public int lengthOfLastWord(String s) {
        /*
         * 题目要求得到字符串中最后一个单词的长度，可以反向遍历字符串，寻找最后一个单词并计算其长度。
         *
         * 由于字符串中至少存在一个单词，因此字符串中一定有字母。首先找到字符串中的最后一个字母，该字母即
         * 为最后一个单词的最后一个字母。
         *
         * 从最后一个字母开始继续反向遍历字符串，直到遇到空格或者到达字符串的起始位置。遍历到的每个字母都
         * 是最后一个单词中的字母，因此遍历到的字母数量即为最后一个单词的长度。
         */
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int wordLength = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLength++;
            index--;
        }
        return wordLength;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLastWord("Hello World"));
        System.out.println(new Solution().lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(new Solution().lengthOfLastWord("luffy is still joyboy"));
    }
}