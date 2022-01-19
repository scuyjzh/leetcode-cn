package com.scuyjzh.leetcode.medium.No_0003_Longest_Substring_Without_Repeating_Characters;

import java.util.*;

/**
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过，来判断 是否有重复的字符
        // 在左指针向右移动的时候，从哈希集合中移除一个字符，在右指针向右移动的时候，往哈希集合中添加一个字符
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int max = 0;
        // 左右指针指向滑动窗口的左右边界，初始都指向下标 0
        int left = 0, right = 0;
        while (right < n) {
            // 若当前右指针指向的元素在 Set 集合中存在，则右移左指针缩小滑动窗口，直到集合中不包含右指针指向的元素
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                ++left;
            }
            // 记录结果
            max = Math.max(max, right - left + 1);
            // 不断地向右移动右指针
            set.add(s.charAt(right));
            ++right;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new Solution().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    }
}
