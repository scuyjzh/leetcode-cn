package com.scuyjzh.leetcode.hard.No_0052_N_Queens_II;

import java.util.*;

/**
 * 52. N皇后 II
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 */
class Solution {
    public int totalNQueens(int n) {
        // 使用三个集合columns、diagonals1和diagonals2分别记录每一列以及两个方向的每条斜线上是否有皇后
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        return dfs(n, 0, columns, diagonals1, diagonals2);
    }

    private int dfs(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        // 递归终止条件
        if (row == n) {
            return 1;
        }

        int count = 0;
        // 按列遍历
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
            columns.add(i);
            diagonals1.add(diagonal1);
            diagonals2.add(diagonal2);

            // 下一轮搜索，行数为 row + 1
            count += dfs(n, row + 1, columns, diagonals1, diagonals2);

            // 回溯：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            columns.remove(i);
            diagonals1.remove(diagonal1);
            diagonals2.remove(diagonal2);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().totalNQueens(8));
    }
}
