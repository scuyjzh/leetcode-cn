package com.scuyjzh.leetcode.easy.No_0205_Isomorphic_Strings;

import java.util.*;

/**
 * 205. 同构字符串
 * <p>
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构
 * 的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字
 * 符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以
 * 映射到自己本身。
 */
class Solution {
    /**
     * 方法：哈希表
     * 时间复杂度：O(N)，其中 N 为字符串的长度。只需同时遍历一遍字符串 s 和 t 即可。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 是字符串的字符集。哈希表存储字符的空间取决于字符串的字符集大小，最坏情况下每个字符均不相同，需要 O(∣Σ∣) 的空间。
     */
    public boolean isIsomorphic(String s, String t) {
        /*
         * 此题是「290. 单词规律」的简化版，需要判断 s 和 t 每个位置上的字符是否都一一对应，即 s 的任意一
         * 个字符被 t 中唯一的字符对应，同时 t 的任意一个字符被 s 中唯一的字符对应。这也被称为「双射」的关
         * 系。
         *
         * 因此，维护两张哈希表，第一张哈希表 s2t 以 s 中字符为键，映射至 t 的字符为值，第二张哈希表 t2s
         * 以 t 中字符为键，映射至 s 的字符为值。从左至右遍历两个字符串的字符，不断更新两张哈希表，如果出现
         * 冲突（即当前下标 index 对应的字符 s[index] 已经存在映射且不为 t[index] 或当前下标 index 对应的字符
         * t[index] 已经存在映射且不为 s[index]）时说明两个字符串无法构成同构，返回 false。
         *
         * 如果遍历结束没有出现冲突，则表明两个字符串是同构的，返回 true 即可。
         */
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isIsomorphic("egg", "add"));
        System.out.println(new Solution().isIsomorphic("foo", "bar"));
        System.out.println(new Solution().isIsomorphic("paper", "title"));
    }
}