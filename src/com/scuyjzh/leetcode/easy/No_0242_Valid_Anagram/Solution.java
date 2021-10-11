package com.scuyjzh.leetcode.easy.No_0242_Valid_Anagram;

import java.util.*;

/**
 * 242. 有效的字母异位词
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位
 * 词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母
 * 异位词。
 */
class Solution {
    /**
     * 方法一：排序
     * 时间复杂度：O(NlogN)，其中 N 为 s 的长度。排序的时间复杂度为 O(NlogN)，比较两个字符串是否相等时间复杂度为 O(N)，因此总体时间复杂度为 O(NlogN+N)=O(NlogN)。
     * 空间复杂度：O(logN)。排序需要 O(logN) 的空间复杂度。
     */
    public boolean isAnagram1(String s, String t) {
        /*
         * t 是 s 的异位词等价于「两个字符串排序后相等」。因此可以对字符串 s 和 t 分别排序，看排序后的字
         * 符串是否相等即可判断。此外，如果 s 和 t 的长度不同，t 必然不是 s 的异位词。
         */
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    /**
     * 方法二：哈希表
     * 时间复杂度：O(N)，其中 N 为 s 的长度。
     * 空间复杂度：O(S)，其中 S 为字符集大小，此处 S=26。
     */
    public boolean isAnagram2(String s, String t) {
        /*
         * 从另一个角度考虑，t 是 s 的异位词等价于「两个字符串中字符出现的种类和次数均相等」。由于字符串只
         * 包含 26 个小写字母，因此可以维护一个长度为 26 的频次数组 table，先遍历记录字符串 s 中字符出现的
         * 频次，然后遍历字符串 t，减去 table 中对应的频次，如果出现 table[i]<0，则说明 t 包含一个不在 s 中的额
         * 外字符，返回 false 即可。
         */
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isAnagram1("anagram", "nagaram"));
        System.out.println(new Solution().isAnagram2("rat", "cat"));
    }
}