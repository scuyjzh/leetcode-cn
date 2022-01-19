package com.scuyjzh.leetcode.medium.No_0567_Permutation_in_String;

/**
 * 567. 字符串的排列
 *
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排
 * 列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * 提示：s1 和 s2 仅包含小写字母
 */
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        // 使用数组来存储字符串 s1 和滑动窗口中每种字符的数量
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for (int i = 0; i < len1; ++i) {
            ++cnt1[ch1[i] - 'a'];
        }

        // 用两个指针 left 和 right 表示考察的 s2 的子串（滑动窗口） [left..right]
        int left = 0, right = 0;
        while (right < len2) {
            int rightIdx = ch2[right] - 'a';
            ++cnt2[rightIdx];
            // 如果 right 当前遍历到的字符进入滑动窗口后，不满足 s1 的字符数量要求，则将滑动窗口左侧字符不断弹出
            // 也就是右移左指针，减少离开滑动窗口的字符的 cnt 值直到 cnt2[rightIdx] ≤ cnt1[rightIdx]
            while (cnt2[rightIdx] > cnt1[rightIdx]) {
                --cnt2[ch2[left] - 'a'];
                ++left;
            }
            // 当前滑动窗口大小等于字符串 s1 的长度时，此时子串 s2[left..right] 就是 s1 的排列
            if (right - left + 1 == len1) {
                return true;
            }
            // 右指针不断右移
            ++right;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().checkInclusion("hello", "ooolleoooleh"));
        System.out.println(new Solution().checkInclusion("ab", "eidbaooo"));
    }
}
