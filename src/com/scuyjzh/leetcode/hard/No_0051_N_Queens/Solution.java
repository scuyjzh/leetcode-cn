package com.scuyjzh.leetcode.hard.No_0051_N_Queens;

import java.util.*;

/**
 * 51. N 皇后
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后
 * 彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q'
 * 和 '.' 分别代表了皇后和空位。
 */
class Solution {
    private Set<Integer> columns;
    private Set<Integer> diagonals1;
    private Set<Integer> diagonals2;

    /**
     * 方法：回溯
     */
    public List<List<String>> solveNQueens(int n) {
        /*
         * 「N 皇后问题」研究的是如何将 N 个皇后放置在 N×N 的棋盘上，并且使皇后彼此之间不能相互攻击。
         * 皇后的走法是：可以横直斜走，格数不限。因此要求皇后彼此之间不能相互攻击，等价于要求任何两个皇后
         * 都不能在同一行、同一列以及同一条斜线上。
         *
         * 直观的做法是暴力枚举将 N 个皇后放置在 N×N 的棋盘上的所有可能的情况，并对每一种情况判断是否满
         * 足皇后彼此之间不相互攻击。暴力枚举的时间复杂度是非常高的，因此必须利用限制条件加以优化。
         *
         * 显然，每个皇后必须位于不同行和不同列，因此将 N 个皇后放置在 N×N 的棋盘上，一定是每一行有且仅
         * 有一个皇后，每一列有且仅有一个皇后，且任何两个皇后都不能在同一条斜线上。基于上述发现，可以通过
         * 回溯的方式寻找可能的解。
         *
         * 回溯的具体做法是：使用一个数组记录每行放置的皇后的列下标，依次在每一行放置一个皇后。每次新放置
         * 的皇后都不能和已经放置的皇后之间有攻击：即新放置的皇后不能和任何一个已经放置的皇后在同一列以及
         * 同一条斜线上，并更新数组中的当前行的皇后列下标。当 N 个皇后都放置完毕，则找到一个可能的解。当找
         * 到一个可能的解之后，将数组转换成表示棋盘状态的列表，并将该棋盘状态的列表加入返回列表。
         *
         * 由于每个皇后必须位于不同列，因此已经放置的皇后所在的列不能放置别的皇后。第一个皇后有 N 列可以选
         * 择，第二个皇后最多有 N−1 列可以选择，第三个皇后最多有 N−2 列可以选择（如果考虑到不能在同一条
         * 斜线上，可能的选择数量更少），因此所有可能的情况不会超过 N! 种，遍历这些情况的时间复杂度是
         * O(N!)。
         *
         * 为了降低总时间复杂度，每次放置皇后时需要快速判断每个位置是否可以放置皇后，显然，最理想的情况是
         * 在 O(1) 的时间内判断该位置所在的列和两条斜线上是否已经有皇后。
         *
         * 以下方法使用集合对皇后的放置位置进行判断，可以在 O(1) 的时间内判断一个位置是否可以放置皇后，
         * 算法的总时间复杂度都是 O(N!)。
         *
         * 为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合 columns、diagonals1 和 diagonals2
         * 分别记录每一列以及两个方向的每条斜线上是否有皇后。
         *
         * 列的表示法很直观，一共有 N 列，每一列的下标范围从 0 到 N−1，使用列的下标即可明确表示每一列。
         *
         * 如何表示两个方向的斜线呢？对于每个方向的斜线，需要找到斜线上的每个位置的行下标与列下标之间的关
         * 系。
         *
         * 方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等，例如 (0,0) 和
         * (3,3) 在同一条方向一的斜线上。因此使用行下标与列下标之差即可明确表示每一条方向一的斜线。
         *
         * 方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等，例如 (3,0) 和
         * (1,2) 在同一条方向二的斜线上。因此使用行下标与列下标之和即可明确表示每一条方向二的斜线。
         *
         * 每次放置皇后时，对于每个位置判断其是否在三个集合中，如果三个集合都不包含当前位置，则当前位置是
         * 可以放置皇后的位置。
         */
        List<List<String>> res = new ArrayList<>();

        // 使用一个数组记录每行放置的皇后的列下标，依次在每一行放置一个皇后
        int[] queens = new int[n];
        Arrays.fill(queens, -1);

        // 使用三个集合 columns、diagonals1 和 diagonals2 分别记录每一列以及两个方向的每条斜线上是否有皇后
        columns = new HashSet<>();
        diagonals1 = new HashSet<>();
        diagonals2 = new HashSet<>();

        // 通过回溯的方式寻找可能的解
        dfs(res, queens, n, 0);

        // 返回结果
        return res;
    }

    private void dfs(List<List<String>> res, int[] queens, int n, int row) {
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
            // 例如 (0,0) 和 (3,3) 在同一条方向一的斜线上
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
            dfs(res, queens, n, row + 1);

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
        System.out.println(new Solution().solveNQueens(4));
        System.out.println(new Solution().solveNQueens(1));
    }
}
