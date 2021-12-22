package com.scuyjzh.leetcode.medium.No_0130_Surrounded_Regions;

import java.util.*;

/**
 * 130. 被围绕的区域
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被
 * 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 */
class Solution {
    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    int rows;
    int cols;

    /**
     * 方法一：深度优先搜索
     */
    public void solve1(char[][] board) {
        /*
         * 本题给定的矩阵中有三种元素：
         *   • 字母 X；
         *   • 被字母 X 包围的字母 O；
         *   • 没有被字母 X 包围的字母 O。
         *
         * 本题要求将所有被字母 X 包围的字母 O 都变为字母 X，但很难判断哪些 O 是被包围的，哪些 O 不是
         * 被包围的。
         *
         * 注意到题目解释中提到：任何边界上的 O 都不会被填充为 X。可以想到，所有的不被包围的 O 都
         * 直接或间接与边界上的 O 相连。可以利用这个性质判断 O 是否在边界上，具体地说：
         *   • 对于每一个边界上的 O，以它为起点，标记所有与它直接或间接相连的字母 O；
         *   • 最后遍历这个矩阵，对于每一个字母：
         *       ○ 如果该字母被标记过，则该字母为没有被字母 X 包围的字母 O，将其还原为字母 O；
         *       ○ 如果该字母没有被标记过，则该字母为被字母 X 包围的字母 O，将其修改为字母 X。
         *
         * 可以使用深度优先搜索实现标记操作。把标记过的字母 O 修改为字母 A。
         */
        if (board == null || board.length == 0) {
            return;
        }

        rows = board.length;
        cols = board[0].length;

        // 左右边界
        for (int i = 0; i < rows; ++i) {
            dfs(board, i, 0);
            dfs(board, i, cols - 1);
        }

        // 上下边界
        for (int i = 1; i < cols - 1; ++i) {
            dfs(board, 0, i);
            dfs(board, rows - 1, i);
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || board[x][y] != 'O') {
            return;
        }

        board[x][y] = 'A';

        for (int[] dir : DIRECTIONS) {
            dfs(board, x + dir[0], y + dir[1]);
        }
    }

    /**
     * 方法二：广度优先搜索
     */
    public void solve2(char[][] board) {
        // 也可以使用广度优先搜索实现标记操作。把标记过的字母 O 修改为字母 A。
        if (board == null || board.length == 0) {
            return;
        }

        rows = board.length;
        cols = board[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        // 左右边界上的 O
        for (int i = 0; i < rows; ++i) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                board[i][0] = 'A';
            }
            if (board[i][cols - 1] == 'O') {
                queue.offer(new int[]{i, cols - 1});
                board[i][cols - 1] = 'A';
            }
        }
        // 上下边界上的 O
        for (int i = 1; i < cols - 1; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
                board[0][i] = 'A';
            }
            if (board[rows - 1][i] == 'O') {
                queue.offer(new int[]{rows - 1, i});
                board[rows - 1][i] = 'A';
            }
        }
        // 直接或间接与边界上的 O 相连的 O
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : DIRECTIONS) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                if (x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == 'O') {
                    queue.offer(new int[]{x, y});
                    board[x][y] = 'A';
                }
            }
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        // new Solution().solve1(board);
        new Solution().solve2(board);
        for (char[] chars : board) {
            for (char ch : chars) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
}
