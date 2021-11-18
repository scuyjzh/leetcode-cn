package com.scuyjzh.leetcode.medium.No_0208_Implement_Trie;

/**
 * 208. 实现 Trie (前缀树)
 *
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存
 * 储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自
 * 动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *   • Trie() 初始化前缀树对象。
 *   • void insert(String word) 向前缀树中插入字符串 word 。
 *   • boolean search(String word) 如果字符串 word 在前缀树中，返
 *     回 true（即，在检索之前已经插入）；否则，返回 false 。
 *   • boolean startsWith(String prefix) 如果之前已经插入的字符
 *     串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
 *     。
 */
class Trie {
    class TrieNode {
        boolean isEnd;
        TrieNode[] children;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                return false;
            } else {
                cur = cur.children[ch - 'a'];
            }
        }
        return cur.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char ch : prefix.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                return false;
            } else {
                cur = cur.children[ch - 'a'];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
