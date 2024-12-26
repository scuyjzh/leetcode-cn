package com.scuyjzh.leetcode.medium.No_0187_Repeated_DNA_Sequences;

import java.util.*;

/**
 * 187. 重复的DNA序列
 *
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，
 * 例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对
 * 研究非常有帮助。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符
 * 串 s 中出现次数超过一次。
 */
class Solution {
    static final int L = 10;

    /**
     * 方法一：哈希表
     *
     * • 时间复杂度：O(NL)，其中 N 是字符串 s 的长度，L=10 即目标子串的长度。
     * • 空间复杂度：O(NL)。
     */
    public List<String> findRepeatedDnaSequences1(String s) {
        List<String> ans = new ArrayList<>();
        // 用一个哈希表统计 s 所有长度为 10 的子串的出现次数
        Map<String, Integer> cnt = new HashMap<>();
        int n = s.length();
        // 一边遍历子串一边记录答案
        for (int i = 0; i <= n - L; ++i) {
            String sub = s.substring(i, i + L);
            cnt.put(sub, cnt.getOrDefault(sub, 0) + 1);
            // 为了不重复记录答案，只统计当前出现次数为 2 的子串
            if (cnt.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }

    /**
     * 方法二：前缀树
     */
    public List<String> findRepeatedDnaSequences2(String s) {
        TrieNode trie = new TrieNode();
        Set<String> res = new HashSet<>();
        int n = s.length();
        for (int i = 0; i <= n - L; ++i) {
            if (built(s, i, trie)) {
                res.add(s.substring(i, i + L));
            }
        }
        return new ArrayList<>(res);
    }

    private boolean built(String s, int start, TrieNode trie) {
        for (int i = start; i < start + L; ++i) {
            char ch = s.charAt(i);
            if (trie.children[ch - 'A'] == null) {
                trie.children[ch - 'A'] = new TrieNode();
            }
            trie = trie.children[ch - 'A'];
        }
        if (!trie.isEnd) {
            trie.isEnd = true;
            return false;
        }
        return true;
    }

    private class TrieNode {
        boolean isEnd;
        TrieNode[] children;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findRepeatedDnaSequences1("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(new Solution().findRepeatedDnaSequences2("AAAAAAAAAAAAA"));
    }
}
