package com.scuyjzh.leetcode.medium.No_0003_Longest_Substring_Without_Repeating_Characters;

import java.util.*;

/**
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于在字符串的左边界的左侧，还没有开始移动
        int right = -1, ans = 0;
        // 左指针，不断将左指针向右移动一格，表示开始枚举下一个字符作为起始位置
        for (int left = 0; left < n; ++left) {
            if (left != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(left - 1));
            }
            while (right + 1 < n && !occ.contains(s.charAt(right + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(right + 1));
                ++right;
            }
            // 第 left 到 right 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new Solution().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    }
}
