package com.scuyjzh.leetcode.hard.No_0037_Sudoku_Solver;

import java.util.*;

/**
 * 37. 解数独
 * <p>
 * 数独的解法需 遵循如下规则：
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 */
class Solution {
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<>();

    /**
     * 方法：递归 + 回溯
     */
    public void solveSudoku(char[][] board) {
        /*
         * 前言：
         * 由于每个数字在同一行、同一列、同一个九宫格中只会出现一次，因此可以使用 line[i]，column[j]，block[x][y] 分别表示第 i 行，第 j 列，第 (x,y) 个九宫格中填写数字的情况。
         * - 九宫格的范围为 0≤x≤2 以及 0≤y≤2。具体地，第 i 行第 j 列的格子位于第 (⌊i/3⌋,⌊j/3⌋) 个九宫格中，其中 ⌊u⌋ 表示对 u 向下取整。
         *
         * 思路：
         * 使用一个长度为 9 的布尔类型的数组记录每个数字是否出现，其中 i 个元素的值为 True，当且仅当数字 i+1 出现过。
         * 例如用 line[2][3]=True 表示数字 4 在第 2 行已经出现过，那么在遍历到第 2 行的空白格时，就不能填入数字 4。
         *
         * 算法：
         * 首先对整个数独数组进行遍历，当遍历到第 i 行第 j 列的位置：
         *   • 如果该位置是一个空白格，那么将其加入一个用来存储空白格位置的列表中，方便后续的递归操作；
         *   • 如果该位置是一个数字 x，那么需要将 line[i][x−1]，column[j][x−1] 以及 block[⌊i/3⌋][⌊j/3⌋][x−1] 均置为 True。
         * 当结束遍历过程之后，就开始递归枚举。当递归到第 i 行第 j 列的位置时，枚举填入的数字 x。
         * 根据题目的要求，数字 x 不能和当前行、列、九宫格中已经填入的数字相同，因此 line[i][x−1]，column[j][x−1] 以及 block[⌊i/3⌋][⌊j/3⌋][x−1] 必须均为 False。
         * 当填入了数字 x 之后，要将上述的三个值都置为 True，并且继续对下一个空白格位置进行递归。
         * 在回溯到当前递归层时，还要将上述的三个值重新置为 False。
         */
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    private void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
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
        solution.solveSudoku(board);
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
