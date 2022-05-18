package com.scuyjzh.leetcode.medium.No_0036_Valid_Sudoku;

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
    public boolean isValidSudoku(char[][] board) {
        // 创建二维数组 rows、columns 和 subboxes 分别记录数独的每一行、每一列和每一个小九宫格中的每个数字的出现次数
        int[][] rows = new int[9][10];
        int[][] columns = new int[9][10];
        int[][] subboxes = new int[9][10];
        // 遍历数独一次，在遍历的过程中更新二维数组中的计数
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char ch = board[i][j];
                if (ch != '.') {
                    int curNum = ch - '0';
                    rows[i][curNum]++;
                    columns[j][curNum]++;
                    subboxes[(i / 3) * 3 + j / 3][curNum]++;
                    // 判断是否满足有效的数独的条件
                    if (rows[i][curNum] > 1 || columns[j][curNum] > 1 || subboxes[(i / 3) * 3 + j / 3][curNum] > 1) {
                        return false;
                    }
                }

            }
        }
        // 如果遍历结束之后没有出现计数大于 1 的情况，则符合有效的数独的条件，返回 true
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
