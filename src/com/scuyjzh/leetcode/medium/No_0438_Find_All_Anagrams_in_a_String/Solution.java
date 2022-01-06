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
     * 方法一：滑动窗口 + 数组
     */
    public List<Integer> findAnagrams1(String s, String p) {
        /*
         * 根据题目要求，需要在字符串 s 寻找字符串 p 的异位词。因为字符串 p 的异位词的长度一定与字符串 p
         * 的长度相同，所以可以在字符串 s 中构造一个长度为与字符串 p 的长度相同的滑动窗口，并在滑动中维
         * 护窗口中每种字母的数量；当窗口中每种字母的数量与字符串 p 中每种字母的数量相同时，则说明当前窗口
         * 为字符串 p 的异位词。
         *
         * 在算法的实现中，可以使用数组来存储字符串 p 和滑动窗口中每种字母的数量。
         *
         * 当字符串 s 的长度小于字符串 p 的长度时，字符串 s 中一定不存在字符串 p 的异位词。但是因为字符串 s 中
         * 无法构造长度与字符串 p 的长度相同的窗口，所以这种情况需要单独处理。
         */
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return res;
        }

        char[] chs = s.toCharArray();
        char[] chp = p.toCharArray();

        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[chs[i] - 'a'];
            ++pCount[chp[i] - 'a'];
        }

        if (Arrays.equals(sCount, pCount)) {
            res.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[chs[i] - 'a'];
            ++sCount[chs[i + pLen] - 'a'];

            if (Arrays.equals(sCount, pCount)) {
                res.add(i + 1);
            }
        }

        return res;
    }

    /**
     * 方法二：滑动窗口 + 双指针
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return res;
        }

        char[] chs = s.toCharArray();
        char[] chp = p.toCharArray();

        int[] sCount = new int[26];
        int[] pCount = new int[26];

        for(int i = 0; i < pLen; ++i){
            pCount[chp[i] - 'a'] ++;
        }

        int left = 0;
        for (int right = 0; right < sLen; ++right){
            int rightIdx = chs[right] - 'a';
            ++sCount[rightIdx];
            while (sCount[rightIdx] > pCount[rightIdx]){
                --sCount[chs[left] - 'a'];
                ++left;
            }
            if(right - left + 1 == pLen){
                res.add(left);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findAnagrams1("cbaebabacd", "abc"));
        System.out.println(new Solution().findAnagrams2("abab", "ab"));
    }
}
