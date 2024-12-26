package com.scuyjzh.leetcode.medium.No_0079_Word_Search;

/**
 * 79. 单词搜索
 *
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如
 * 果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元
 * 格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重
 * 复使用。
 *
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的
 * 情况下可以更快解决问题？
 */
class Solution {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private char[][] board;
    private char[] charArray;
    private int rows;
    private int cols;
    private int len;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        /*
         * 设函数 dfs(i,j,k) 表示判断以网格的 (i,j) 位置出发，能否搜索到单词 word[k..]，其中 word[k..] 表示字符
         * 串 word 从第 k 个字符开始的后缀子串。如果能搜索到，则返回 true，反之返回 false。函数 dfs(i,j,k)
         * 的执行步骤如下：
         *   • 如果 board[i][j] != word[k]，当前字符不匹配，直接返回 false。
         *   • 如果当前已经访问到字符串的末尾，且对应字符依然匹配，此时直接返回 true。
         *   • 否则，遍历当前位置的所有相邻位置。如果从某个相邻位置出发，能够搜索到子串 word[k+1..]，则返
         *     回 true，否则返回 false。
         *
         * 这样，对每一个位置 (i,j) 都调用函数 dfs(i,j,0) 进行检查：只要有一处返回 true，就说明网格中能
         * 够找到相应的单词，否则说明不能找到。
         *
         * 为了防止重复遍历相同的位置，需要额外维护一个与 board 等大的 visited 数组，用于标识每个位置是否被访
         * 问过。每次遍历相邻位置时，需要跳过已经被访问的位置。
         */
        rows = board.length;
        if (rows == 0) {
            return false;
        }
        cols = board[0].length;
        visited = new boolean[rows][cols];

        this.board = board;
        this.charArray = word.toCharArray();
        this.len = word.length();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int begin) {
        if (begin == len - 1) {
            return board[x][y] == charArray[begin];
        }
        if (board[x][y] == charArray[begin]) {
            visited[x][y] = true;
            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (inArea(newX, newY) && !visited[newX][newY]) {
                    if (dfs(newX, newY, begin + 1)) {
                        return true;
                    }
                }
            }
            // 状态回溯
            visited[x][y] = false;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));
        System.out.println(new Solution().exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE"));
        System.out.println(new Solution().exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCB"));
    }
}
