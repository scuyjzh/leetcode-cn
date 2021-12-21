package com.scuyjzh.leetcode.medium.No_0036_Valid_Sudoku;

import java.util.*;

/**
 * 36. 有效的数独
 *
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经
 * 填入的数字是否有效即可。
 *   1.数字 1-9 在每一行只能出现一次。
 *   2.数字 1-9 在每一列只能出现一次。
 *   3.数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 注意：
 *   • 一个有效的数独（部分已被填充）不一定是可解的。
 *   • 只需要根据以上规则，验证已经填入的数字是否有效即可。
 *   • 空白格用 '.' 表示。
 */
class Solution {
    /**
     * 方法：一次迭代
     *
     * • 时间复杂度：O(1)。数独共有 81 个单元格，只需要对每个单元格遍历一次即可。
     * • 空间复杂度：O(1)。由于数独的大小固定，因此哈希表的空间也是固定的。
     */
    public boolean isValidSudoku(char[][] board) {
        /*
         * 一个简单的解决方案是遍历该 9 x 9 数独 三 次，以确保：
         * 1.行中没有重复的数字。
         * 2.列中没有重复的数字。
         * 3.3 x 3 子数独内没有重复的数字。
         * 实际上，所有这一切都可以在一次迭代中完成。
         *
         * 讨论两个问题：
         * 1.如何枚举子数独？
         *   - 可以使用 box_index = (row / 3) * 3 + columns / 3，其中 / 是整数除法。
         *
         * 2.如何确保行 / 列 / 子数独中没有重复项？
         *   - 可以利用 value -> count 哈希映射来跟踪所有已经遇到的值。
         */
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] subboxes = new HashMap[9];
        for (int i = 0; i < 9; ++i) {
            rows[i] = new HashMap<>(9);
            columns[i] = new HashMap<>(9);
            subboxes[i] = new HashMap<>(9);
        }
        // 遍历数独
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char ch = board[i][j];
                if (ch != '.') {
                    int n = ch - '0';
                    // idx = ⌊i/3⌋ ∗ 3 + ⌊j/3⌋
                    int idx = (i / 3) * 3 + j / 3;
                    // 保留当前单元格值
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    subboxes[idx].put(n, subboxes[idx].getOrDefault(n, 0) + 1);
                    // 检查看到每个单元格值是否已经在当前的行 / 列 / 子数独中出现过
                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || subboxes[idx].get(n) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(new Solution().isValidSudoku(board));
    }
}
