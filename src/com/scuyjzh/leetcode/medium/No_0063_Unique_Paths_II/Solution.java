package com.scuyjzh.leetcode.medium.No_0063_Unique_Paths_II;

/**
 * 63. 不同路径 II
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start”
 * ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在
 * 下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路
 * 径？
 */
class Solution {
    /**
     * 方法：动态规划（空间优化）
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /*
         * 用 f(i,j) 来表示从坐标 (0,0) 到坐标 (i,j) 的路径总数，u(i,j) 表示坐标 (i,j) 是否可行，如果坐标 (i,j)
         * 有障碍物，u(i,j)=0，否则 u(i,j)=1。
         *
         * 因为「机器人每次只能向下或者向右移动一步」，所以从坐标 (0,0) 到坐标 (i,j) 的路径总数的值只取决于从
         * 坐标 (0,0) 到坐标 (i−1,j) 的路径总数和从坐标 (0,0) 到坐标 (i,j−1) 的路径总数，即 f(i,j) 只能通过
         * f(i−1,j) 和 f(i,j−1) 转移得到。当坐标 (i,j) 本身有障碍的时候，任何路径都到到不了 f(i,j)，此时
         * f(i,j)=0；下面来讨论坐标 (i,j) 没有障碍的情况：如果坐标 (i−1,j) 没有障碍，那么就意味着从坐标
         * (i−1,j) 可以走到 (i,j)，即 (i−1,j) 位置对 f(i,j) 的贡献为 f(i−1,j)，同理，当坐标 (i,j−1) 没有障碍
         * 的时候，(i,j−1) 位置对 f(i,j) 的贡献为 f(i,j−1)。综上所述，可以得到这样的动态规划转移方程：
         *         f(i,j) =                   0, if u(i,j) = 0
         *         f(i,j) = f(i−1,j) + f(i,j−1), if u(i,j) = 1
         *
         * 很显然可以给出一个时间复杂度 O(nm) 并且空间复杂度也是 O(nm) 的实现，由于这里 f(i,j) 只与
         * f(i−1,j) 和 f(i,j−1) 相关，可以运用「滚动数组思想」把空间复杂度优化称 O(m)。「滚动数组思
         * 想」是一种常见的动态规划优化方法，当定义的状态在动态规划的转移方程中只和某几个状态相关的时候，
         * 就可以考虑这种优化方法，目的是给空间复杂度「降维」。
         */
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        System.out.println(new Solution().uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));
    }
}
