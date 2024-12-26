package com.scuyjzh.leetcode.hard.No_0317_Shortest_Distance_from_All_Buildings;

import java.util.*;

/**
 * 317. 离建筑物最近的距离
 *
 * 你是个房地产开发商，想要选择一片空地 建一栋大楼。你想把这栋大楼够
 * 造在一个距离周边设施都比较方便的地方，通过调研，你希望从它出发能
 * 在 最短的距离和 内抵达周边全部的建筑物。请你计算出这个最佳的选址到
 * 周边全部建筑物的 最短距离和。
 *
 * 提示：
 * 你只能通过向上、下、左、右四个方向上移动。
 * 给你一个由 0、1 和 2 组成的二维网格，其中：
 *   • 0 代表你可以自由通过和选择建造的空地
 *   • 1 代表你无法通行的建筑物
 *   • 2 代表你无法通行的障碍物
 */
class Solution {
    public static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    public int shortestDistance(int[][] grid) {
        // bfs
        int row = grid.length;
        int col = grid[0].length;

        // at each point[i][j], how many building you can reach
        int[][] reach = new int[row][col];

        // at each point[i][j], what's the distance to all buildings
        int[][] distance = new int[row][col];

        // the total number of buildings
        int buildingCount = 0;

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {

                if (grid[i][j] == 1) {
                    ++buildingCount;

                    boolean[][] visited = new boolean[row][col];
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});

                    int dist = 0;

                    while (!queue.isEmpty()) {
                        int size = queue.size();

                        for (int k = 0; k < size; ++k) {
                            int[] curr = queue.poll();
                            int x = curr[0];
                            int y = curr[1];
                            distance[x][y] += dist;
                            reach[x][y]++;

                            for (int[] dir : DIRECTIONS) {
                                int newX = x + dir[0];
                                int newY = y + dir[1];

                                if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 0 && !visited[newX][newY]) {
                                    queue.offer(new int[]{newX, newY});
                                    visited[newX][newY] = true;
                                }
                            }
                        }

                        ++dist;
                    }
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == 0 && distance[i][j] < minDistance && reach[i][j] == buildingCount) {
                    minDistance = distance[i][j];
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestDistance(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
    }
}
