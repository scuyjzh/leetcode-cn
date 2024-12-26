package com.scuyjzh.leetcode.medium.No_0211_Design_Add_and_Search_Words_Data_Structure;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 *
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添
 * 加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *   • WordDictionary() 初始化词典对象
 *   • void addWord(word) 将 word 添加到数据结构中，之后可以对它进
 *     行匹配
 *   • bool search(word) 如果数据结构中存在字符串与 word 匹配，则
 *     返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，
 *     每个 . 都可以表示任何一个字母。
 */
class WordDictionary {
    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, root, 0);
    }

    private boolean dfs(String word, TrieNode root, int start) {
        if (start == word.length()) {
            return root.isWord;
        }
        char ch = word.charAt(start);
        if (ch == '.') {
            for (int i = 0; i < 26; ++i) {
                if (root.children[i] != null && dfs(word, root.children[i], start + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            if (root.children[ch - 'a'] == null) {
                return false;
            }
            return dfs(word, root.children[ch - 'a'], start + 1);
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }
}
