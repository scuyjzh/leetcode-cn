package com.scuyjzh.leetcode.medium.No_0340_Longest_Substring_with_At_Most_K_Distinct_Characters;

/**
 * 340. 至多包含 K 个不同字符的最长子串
 *
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 */
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] freq = new int[128];
        int cnt = 0, ans = 0;
        int left = 0, right = 0;
        while (right < n) {
            if (freq[cs[right]]++ == 0) {
                ++cnt;
            }
            while (cnt > k) {
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
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("aa", 1));
    }
}
