package com.scuyjzh.leetcode.hard.No_0336_Palindrome_Pairs;

import java.util.*;

/**
 * 336. 回文对
 *
 * 给定一组 互不相同 的单词， 找出所有 不同 的索引对 (i, j)，使得列表
 * 中的两个单词， words[i] + words[j] ，可拼接成回文串。
 */
class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int wordIndex = -1;
        List<Integer> restIsPalindrome;

        TrieNode() {
            restIsPalindrome = new ArrayList<>();
        }
    }

    TrieNode root = new TrieNode();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        for (int i = 0; i < words.length; ++i) {
            add(words[i], i);
        }

        for (int i = 0; i < words.length; ++i) {
            search(words[i], i);
        }

        return res;
    }

    private void search(String word, int wordIndex) {
        char[] chs = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < chs.length; ++i) {
            if (cur.wordIndex != -1 && isPalindrome(chs, i, chs.length - 1)) {
                res.add(Arrays.asList(wordIndex, cur.wordIndex));
            }
            if (cur.children[chs[i] - 'a'] == null) {
                return;
            } else {
                cur = cur.children[chs[i] - 'a'];
            }
        }

        // cur.wordIndex != wordIndex，避免单词本身就是回文串的情况，例如 "aaa"
        if (cur.wordIndex != -1 && cur.wordIndex != wordIndex) {
            res.add(Arrays.asList(wordIndex, cur.wordIndex));
        }

        for (int idx : cur.restIsPalindrome) {
            res.add(Arrays.asList(wordIndex, idx));
        }
    }

    private void add(String word, int wordIndex) {
        char[] chs = word.toCharArray();
        TrieNode cur = root;
        // 倒序插入
        for (int i = chs.length - 1; i >= 0; --i) {
            if (isPalindrome(chs, 0, i)) {
                cur.restIsPalindrome.add(wordIndex);
            }
            if (cur.children[chs[i] - 'a'] == null) {
                cur.children[chs[i] - 'a'] = new TrieNode();
            }
            cur = cur.children[chs[i] - 'a'];
        }
        // 倒序遍历结束，记录单词索引
        cur.wordIndex = wordIndex;
    }

    private boolean isPalindrome(char[] chs, int l, int r) {
        while (l < r) {
            if (chs[l++] != chs[r--]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
        System.out.println(new Solution().palindromePairs(new String[]{"bat", "tab", "cat"}));
        System.out.println(new Solution().palindromePairs(new String[]{"a", ""}));
    }
}
