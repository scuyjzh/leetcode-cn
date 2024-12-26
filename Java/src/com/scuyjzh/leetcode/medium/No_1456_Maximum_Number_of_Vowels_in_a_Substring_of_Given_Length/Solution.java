package com.scuyjzh.leetcode.medium.No_1456_Maximum_Number_of_Vowels_in_a_Substring_of_Given_Length;

/**
 * 1456. 定长子串中元音的最大数目
 *
 * 给你字符串 s 和整数 k 。
 *
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 *
 * 英文中的 元音字母 为（a, e, i, o, u）。
 */
class Solution {
    public int maxVowels(String s, int k) {
        // 先求出第 1 个窗口的和
        int windowSum = 0;
        for (int i = 0; i < k; ++i) {
            windowSum += isVowels(s.charAt(i));
        }
        // 通过遍历求出除了第 1 个窗口的和
        int maxAns = windowSum;
        // 循环不变量定义：[left..right) 是长度为 k 的窗口
        for (int right = k; right < s.length(); ++right) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            windowSum = windowSum + isVowels(s.charAt(right)) - isVowels(s.charAt(right - k));
            maxAns = Math.max(windowSum, maxAns);
        }
        return maxAns;
    }

    private int isVowels(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxVowels("abciiidef", 3));
    }
}
