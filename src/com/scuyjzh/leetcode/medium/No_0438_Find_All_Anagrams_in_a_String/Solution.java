package com.scuyjzh.leetcode.medium.No_0438_Find_All_Anagrams_in_a_String;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些
 * 子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
class Solution {
    /**
     * 方法：滑动窗口 + 双指针
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        // 当字符串 s 的长度小于字符串 p 的长度时，字符串 s 中一定不存在字符串 p 的异位词
        if (sLen < pLen) {
            return res;
        }

        char[] chs = s.toCharArray();
        char[] chp = p.toCharArray();

        // 使用数组来存储字符串 p 和滑动窗口中每种字母的数量
        int[] sCount = new int[26];
        int[] pCount = new int[26];

        for (int i = 0; i < pLen; ++i) {
            ++pCount[chp[i] - 'a'];
        }

        // 左右指针指向滑动窗口的左右边界
        int left = 0, right = 0;
        while (right < sLen) {
            int rightIdx = chs[right] - 'a';
            ++sCount[rightIdx];
            // 右移左指针，目的是保证滑动窗口中每种字母的数量与字符串 p 中的数量一致
            while (sCount[rightIdx] > pCount[rightIdx]) {
                --sCount[chs[left] - 'a'];
                ++left;
            }
            // 如果此时滑动窗口大小等于字符串 p 的长度，则记录答案
            if (right - left + 1 == pLen) {
                res.add(left);
            }
            // 右指针不断右移
            ++right;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findAnagrams("cbaebabacd", "abc"));
        System.out.println(new Solution().findAnagrams("abab", "ab"));
    }
}
