package com.scuyjzh.leetcode.hard.No_0212_Word_Search_II;

import java.util.*;

/**
 * 212. 单词搜索 II
 *
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表
 * words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单
 * 元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单
 * 词中不允许被重复使用。
 */
class Solution {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    char[][] board;
    int rows;
    int cols;

    boolean[][] visited;
    Set<String> set;
    List<String> ans;

    /**
     * 方法一：回溯
     */
    public List<String> findWords1(char[][] board, String[] words) {
        ans = new ArrayList<>();
        rows = board.length;
        if (rows == 0) {
            return ans;
        }
        cols = board[0].length;

        this.board = board;
        // visited 数组记录访问过的位置
        visited = new boolean[rows][cols];
        set = new HashSet<>();
        set.addAll(Arrays.asList(words));
        StringBuilder sb = new StringBuilder();
        // 以 board 中的每个点作为起点进行暴力搜索
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                visited[i][j] = true;
                sb.append(board[i][j]);

                dfs(i, j, sb);

                // 回溯
                visited[i][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return ans;
    }

    private void dfs(int i, int j, StringBuilder sb) {
        // 1 <= words[i].length <= 10
        if (sb.length() > 10) {
            // 如果当前暴搜到的字符串长度超过 10，直接剪枝
            return;
        }

        if (set.contains(sb.toString())) {
            ans.add(sb.toString());
            // 防止下一次再搜索到该字符串，需要将该字符串从 Set 中移除
            set.remove(sb.toString());
        }

        for (int[] dir : DIRECTIONS) {
            int dx = i + dir[0];
            int dy = j + dir[1];
            if (!inArea(dx, dy) || visited[dx][dy]) {
                continue;
            }

            visited[dx][dy] = true;
            sb.append(board[dx][dy]);

            dfs(dx, dy, sb);

            // 回溯
            visited[dx][dy] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    class TrieNode {
        String word;
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
        }
        cur.word = word;
    }

    /**
     * 方法二：回溯 + Trie
     */
    public List<String> findWords2(char[][] board, String[] words) {
        ans = new ArrayList<>();
        rows = board.length;
        if (rows == 0) {
            return ans;
        }
        cols = board[0].length;

        // visited 数组记录访问过的位置
        visited = new boolean[rows][cols];
        set = new HashSet<>();
        this.board = board;

        for (String word : words) {
            insert(word);
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (root.children[board[i][j] - 'a'] != null) {
                    visited[i][j] = true;
                    dfs(i, j, root.children[board[i][j] - 'a']);
                    visited[i][j] = false;
                }
            }
        }

        ans.addAll(set);
        return ans;
    }

    private void dfs(int i, int j, TrieNode node) {
        if (node.word != null) {
            set.add(node.word);
        }

        for (int[] dir : DIRECTIONS) {
            int dx = i + dir[0];
            int dy = j + dir[1];
            if (!inArea(dx, dy) || visited[dx][dy]) {
                continue;
            }

            if (node.children[board[dx][dy] - 'a'] != null) {
                visited[dx][dy] = true;
                dfs(dx, dy, node.children[board[dx][dy] - 'a']);
                visited[dx][dy] = false;
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(new Solution().findWords1(board, words));
        System.out.println(new Solution().findWords2(board, words));
    }
}
