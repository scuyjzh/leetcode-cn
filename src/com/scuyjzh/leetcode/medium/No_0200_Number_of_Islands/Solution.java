package com.scuyjzh.leetcode.medium.No_0200_Number_of_Islands;

import java.util.*;

/**
 * 200. 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格
 * 中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的
 * 陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
class Solution {
    public static final int[][] DIRECTIONS = {
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
    public int numIslands1(char[][] grid) {
        /*
         * 可以将二维网格看成一个无向图，竖直或水平相邻的 1 之间有边相连。
         *
         * 为了求出岛屿的数量，可以扫描整个二维网格。如果一个位置为 1，则以其为起始节点开始进行深度优
         * 先搜索。在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。
         *
         * 最终岛屿的数量就是进行深度优先搜索的次数。
         */
        if (grid == null || grid.length == 0) {
            return 0;
        }

        rows = grid.length;
        cols = grid[0].length;

        int ans = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    ++ans;
                    dfs(grid, i, j);
                }
            }
        }

        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == '0') {
            return;
        }

        // 每个搜索到的 1 都重新标记为 0
        grid[i][j] = '0';

        for (int[] dir : DIRECTIONS) {
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }

    /**
     * 方法二：广度优先搜索
     */
    public int numIslands2(char[][] grid) {
        /*
         * 同样地，也可以使用广度优先搜索代替深度优先搜索。
         *
         * 为了求出岛屿的数量，可以扫描整个二维网格。如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。在广度优
         * 先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。直到队列为空，搜索结束。
         *
         * 最终岛屿的数量就是进行广度优先搜索的次数。
         */
        if (grid == null || grid.length == 0) {
            return 0;
        }

        rows = grid.length;
        cols = grid[0].length;

        int ans = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    ++ans;
                    bfs(grid, i, j);
                }
            }
        }

        return ans;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<Integer[]> neighbors = new ArrayDeque<>();
        neighbors.offer(new Integer[]{i, j});
        grid[i][j] = '0';

        while (!neighbors.isEmpty()) {
            Integer[] curr = neighbors.poll();

            for (int[] dir : DIRECTIONS) {
                int row = curr[0] + dir[0];
                int col = curr[1] + dir[1];
                if (row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == '1') {
                    neighbors.offer(new Integer[]{row, col});
                    grid[row][col] = '0';
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numIslands1(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}));
        System.out.println(new Solution().numIslands2(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}));
    }
}
