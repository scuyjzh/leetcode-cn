package com.scuyjzh.leetcode.medium.No_0159_Longest_Substring_with_At_Most_Two_Distinct_Characters;

import java.util.*;

/**
 * 159. 至多包含两个不同字符的最长子串
 *
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该
 * 子串的长度。
 */
class Solution {
    /**
     * 方法：滑动窗口
     *
     * • 时间复杂度：O(N)，其中 N 是输入串的字符数目。
     * • 空间复杂度：O(1)，这是因为额外的空间只有 hashmap，且它包含不超过 3 个元素。
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        /*
         * 想法：
         * 为了遍历一遍就得到答案，使用一个左指针和一个右指针表示滑动窗口的边界。
         *
         * 一开始，让两个指针都指向 0，当窗口包含的字符不超过 2 个不同的字符时，就不断将右指针往右边移动。
         * 如果在某一个位置有 3 个不同的字符，就开始移动左指针，直到窗口内包含不超过 2 个不同字符。
         *
         * 这就是基本的想法：沿着字符串移动滑动窗口，并保持窗口内只有不超过 2 个不同字符，同时每一步都更新
         * 最长子串的长度。
         *
         * 只有一个问题还没解决 - 如何移动左指针确保窗口内只有 2 种不同的字符？
         * 使用一个 hashmap，把字符串里的字符都当做键，在窗口中的最右边的字符位置作为值。每一个时
         * 刻，这个 hashmap 包括不超过 3 个元素。
         *
         * 方法时间复杂度是否是最优的呢？答案是是的。只将字符串的 N 个字符遍历了一次，时间复杂度
         * 是 O(N) 。
         */
        int n = s.length();
        if (n < 3) {
            return n;
        }

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<>(3);

        int ans = 2;

        while (right < n) {
            // sliding window contains less than 3 characters
            if (hashmap.size() < 3) {
                hashmap.put(s.charAt(right), right++);
            }

            // sliding window contains 3 characters
            if (hashmap.size() == 3) {
                // delete the leftmost character
                int delIdx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(delIdx));
                // move left pointer of the sliding window
                left = delIdx + 1;
            }

            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}
