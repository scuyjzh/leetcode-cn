package com.scuyjzh.leetcode.medium.No_0291_Word_Pattern_II;

import java.util.*;

/**
 * 291. 单词规律 II
 *
 * 给你一种规律 pattern 和一个字符串 str，请你判断 str 是否遵循其相
 * 同的规律。
 * 这里指的是 完全遵循，例如 pattern 里的每个字母和字符串 str 中
 * 每个 非空 单词之间，存在着 双射 的对应规律。双射 意味着映射双方一一
 * 对应，不会存在两个字符映射到同一个字符串，也不会存在一个字符分别映
 * 射到两个不同的字符串。
 */
class Solution {
    private String pattern;
    private String s;

    Map<Character, String> map1;
    Map<String, Character> map2;

    public boolean wordPatternMatch(String pattern, String s) {
        this.pattern = pattern;
        this.s = s;
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        return dfs(0, 0);
    }

    public boolean dfs(int idx1, int idx2) {
        if (idx1 == pattern.length() && idx2 == s.length()) {
            return true;
        }
        if (idx1 >= pattern.length() && idx2 < s.length()) {
            return false;
        }
        if (idx1 < pattern.length() && idx2 >= s.length()) {
            return false;
        }
        if (map1.containsKey(pattern.charAt(idx1))) {
            String str = map1.get(pattern.charAt(idx1));
            if (idx2 + str.length() > s.length()) {
                return false;
            }
            if (s.substring(idx2, idx2 + str.length()).equals(str)) {
                return dfs(idx1 + 1, idx2 + str.length());
            } else {
                return false;
            }
        } else {
            for (int idx = idx2 + 1; idx <= s.length(); ++idx) {
                String str = s.substring(idx2, idx);
                if (map2.containsKey(str)) {
                    continue;
                }
                map1.put(pattern.charAt(idx1), str);
                map2.put(str, pattern.charAt(idx1));
                if (dfs(idx1 + 1, idx)) {
                    return true;
                } else {
                    map1.remove(pattern.charAt(idx1));
                    map2.remove(str);
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().wordPatternMatch("abab", "redblueredblue"));
        System.out.println(new Solution().wordPatternMatch("aaaa", "asdasdasdasd"));
    }
}
