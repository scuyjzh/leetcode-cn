package com.scuyjzh.leetcode.medium.No_0159_Longest_Substring_with_At_Most_Two_Distinct_Characters;

/**
 * 159. 至多包含两个不同字符的最长子串
 *
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
 */
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] freq = new int[128];
        int cnt = 0, ans = 0;
        int left = 0, right = 0;
        while (right < n) {
            if (freq[cs[right]]++ == 0) {
                ++cnt;
            }
            while (cnt > 2) {
                if (--freq[cs[left]] == 0) {
                    --cnt;
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
            ++right;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}
