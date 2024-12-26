package com.scuyjzh.leetcode.medium.No_0062_Unique_Paths;

import java.util.*;

/**
 * 62. 不同路径
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start”
 * ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在
 * 下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 */
class Solution {
    /**
     * 方法一：动态规划
     */
    public int uniquePaths1(int m, int n) {
        /*
         * 用 f(i,j) 表示从左上角走到 (i,j) 的路径数量，其中 i 和 j 的范围分别是 [0,m) 和 [0,n)。
         * 由于每一步只能从向下或者向右移动一步，因此要想走到 (i,j)，如果向下走一步，那么会从 (i−1,j)
         * 走过来；如果向右走一步，那么会从 (i,j−1) 走过来。因此可以写出动态规划转移方程：
         *         f(i,j)=f(i−1,j)+f(i,j−1)
         *
         * 需要注意的是，如果 i=0，那么 f(i−1,j) 并不是一个满足要求的状态，需要忽略这一项；同理，如果
         * j=0，那么 f(i,j−1) 并不是一个满足要求的状态，需要忽略这一项。
         *
         * 初始条件为 f(0,0)=1，即从左上角走到左上角有一种方法。
         *
         * 最终的答案即为 f(m−1,n−1)。
         *
         * 为了方便代码编写，可以将所有的 f(0,j) 以及 f(i,0) 都设置为边界条件，它们的值均为 1。
         */
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    /**
     * 方法二：动态规划（空间优化）
     */
    public int uniquePaths2(int m, int n) {
        // 空间复杂度：O(n)
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().uniquePaths1(3, 7));
        System.out.println(new Solution().uniquePaths2(3, 2));
    }
}
