package com.scuyjzh.leetcode.medium.No_0286_Walls_and_Gates;

import java.util.*;

/**
 * 286. 墙与门
 *
 * 你被给定一个 m × n 的二维网格 rooms ，网格中有以下三种可能的初始
 * 化值：
 *   1.-1 表示墙或是障碍物
 *   2.0 表示一扇门
 *   3.INF 无限表示一个空的房间。然后，用 231 - 1 =
 *     2147483647 代表 INF。你可以认为通往门的距离总是小
 *     于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近门的距离 ，如果无法到达门，则
 * 填 INF 即可。
 */
class Solution {
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final int WALL = -1;
    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    int rows;
    int cols;

    /**
     * 方法一：暴力
     */
    public void wallsAndGates1(int[][] rooms) {
        /*
         * 暴力方法十分简单，只需要从每个空的房间开始做宽度优先搜索，找到最近的门即可。
         *
         * 搜索的时候，用一个二维的数组，记作 distance，记录从起点出发的距离。它也能隐含一个位置是否
         * 已经被访问过的信息，以免被重复放入队列。
         */
        if (rooms == null || rooms.length == 0) {
            return;
        }

        rows = rooms.length;
        cols = rooms[0].length;

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (rooms[row][col] == EMPTY) {
                    rooms[row][col] = distanceToNearestGate(rooms, row, col);
                }
            }
        }
    }

    private int distanceToNearestGate(int[][] rooms, int startRow, int startCol) {
        int[][] distance = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols || rooms[newRow][newCol] == WALL || distance[newRow][newCol] != 0) {
                    continue;
                }
                distance[newRow][newCol] = distance[row][col] + 1;
                if (rooms[newRow][newCol] == GATE) {
                    return distance[newRow][newCol];
                }
                queue.offer(new int[]{newRow, newCol});
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * 方法二：广度优先搜索
     */
    public void wallsAndGates2(int[][] rooms) {
        /*
         * 与其从一个空的房间开始找门，何不按另一种方式来搜索？换言之，从门开始做宽度优先搜索。由
         * 于宽度优先搜索保证在搜索 d + 1 距离的位置时， 距离为 d 的位置都已经被搜索过了，所以到达每一个
         * 房间的时候都一定是最短距离。
         */
        if (rooms == null || rooms.length == 0) {
            return;
        }

        rows = rooms.length;
        cols = rooms[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (rooms[row][col] == GATE) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols || rooms[newRow][newCol] != EMPTY) {
                    continue;
                }
                rooms[newRow][newCol] = rooms[row][col] + 1;
                queue.add(new int[]{newRow, newCol});
            }
        }
    }

    public static void main(String[] args) {
        int[][] rooms1 = new int[][]{
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}};
        new Solution().wallsAndGates1(rooms1);
        System.out.println(Arrays.deepToString(rooms1));

        int[][] rooms2 = new int[][]{
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}};
        new Solution().wallsAndGates2(rooms2);
        System.out.println(Arrays.deepToString(rooms2));
    }
}
