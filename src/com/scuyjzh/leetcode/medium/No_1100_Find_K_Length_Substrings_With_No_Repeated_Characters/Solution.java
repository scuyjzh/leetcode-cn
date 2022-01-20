package com.scuyjzh.leetcode.medium.No_1100_Find_K_Length_Substrings_With_No_Repeated_Characters;

import java.util.*;

/**
 * 1100. 长度为 K 的无重复字符子串
 *
 * 给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。
 */
class Solution {
    public int numKLenSubstrNoRepeats1(String s, int k) {
        // 哈希集合，记录每个字符是否出现过，来判断是否有重复的字符
        // 在左指针向右移动的时候，从哈希集合中移除一个字符，在右指针向右移动的时候，往哈希集合中添加一个字符
        Set<Character> set = new HashSet<>();
        char[] cs = s.toCharArray();
        int n = s.length();
        int ans = 0;
        // 左右指针指向滑动窗口的左右边界，初始都指向下标 0
        int left = 0, right = 0;
        while (right < n) {
            // 若当前右指针指向的元素在 Set 集合中存在，则右移左指针缩小滑动窗口，直到集合中不包含右指针指向的元素
            while (set.contains(cs[right])) {
                set.remove(cs[left++]);
            }
            set.add(cs[right]);
            // 记录结果
            if (right - left + 1 == k) {
                ans += 1;
                // 找到一个字串后，需将左指针右移一位继续寻找
                set.remove(cs[left++]);
            }
            // 不断地向右移动右指针
            ++right;
        }
        return ans;
    }

    public int numKLenSubstrNoRepeats2(String s, int k) {
        // 用数组代替哈希集合，记录每个字符的出现次数，来判断是否有重复的字符
        int[] freq = new int[128];
        char[] cs = s.toCharArray();
        int n = s.length();
        int ans = 0;
        // 左右指针指向滑动窗口的左右边界，初始都指向下标 0
        int left = 0, right = 0;
        while (right < n) {
            // 若当前右指针指向的元素在集合中存在，则右移左指针缩小滑动窗口，直到集合中不包含右指针指向的元素
            while (freq[cs[right]] != 0) {
                --freq[cs[left++]];
            }
            ++freq[cs[right]];
            // 记录结果
            if (right - left + 1 == k) {
                ans += 1;
                // 找到一个子串后，需要将左指针右移一位继续寻找
                --freq[cs[left++]];
            }
            // 不断地向右移动右指针
            ++right;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numKLenSubstrNoRepeats1("havefunonleetcode", 5));
        System.out.println(new Solution().numKLenSubstrNoRepeats2("havefunonleetcode", 5));
    }
}
