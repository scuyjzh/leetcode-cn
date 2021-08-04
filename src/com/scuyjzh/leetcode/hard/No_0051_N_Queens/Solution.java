package com.scuyjzh.leetcode.hard.No_0051_N_Queens;

import java.util.*;

/**
 * 51. N 皇后
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
class Solution {
    /**
     * 方法：回溯
     * 时间复杂度：O(N!)，其中 N 是皇后数量。
     * 空间复杂度：O(N)，其中 N 是皇后数量。空间复杂度主要取决于递归调用层数、记录每行放置的皇后的列下标的数组以及三个集合，递归调用层数不会超过 N，数组的长度为 N，每个集合的元素个数都不会超过 N。
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // 使用一个数组记录每行放置的皇后的列下标，依次在每一行放置一个皇后
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        // 使用三个集合columns、diagonals1和diagonals2分别记录每一列以及两个方向的每条斜线上是否有皇后
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        dfs(res, queens, n, 0, columns, diagonals1, diagonals2);
        return res;
    }

    private void dfs(List<List<String>> res, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        // 递归终止条件
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            res.add(board);
            return;
        }
        for (int i = 0; i < n; ++i) {
            // 一共有 N 列，每一列的下标范围从 0 到 N−1，使用列的下标即可明确表示每一列
            if (columns.contains(i)) {
                continue;
            }
            // 方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等
            // 例如 ((0,0) 和 (3,3) 在同一条方向一的斜线上
            int diagonal1 = row - i;
            if (diagonals1.contains(diagonal1)) {
                continue;
            }
            // 方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等
            // 例如 (3,0) 和 (1,2) 在同一条方向二的斜线上
            int diagonal2 = row + i;
            if (diagonals2.contains(diagonal2)) {
                continue;
            }

            // 每次放置皇后时，对于每个位置判断其是否在三个集合中，如果三个集合都不包含当前位置，则当前位置是可以放置皇后的位置
            queens[row] = i;
            columns.add(i);
            diagonals1.add(diagonal1);
            diagonals2.add(diagonal2);

            // 下一轮搜索，行数为 row + 1
            dfs(res, queens, n, row + 1, columns, diagonals1, diagonals2);

            // 回溯：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            queens[row] = -1;
            columns.remove(i);
            diagonals1.remove(diagonal1);
            diagonals2.remove(diagonal2);
        }
    }

    private List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solveNQueens(4));
    }
}
