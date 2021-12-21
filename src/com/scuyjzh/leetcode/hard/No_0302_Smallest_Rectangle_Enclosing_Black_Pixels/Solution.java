package com.scuyjzh.leetcode.hard.No_0302_Smallest_Rectangle_Enclosing_Black_Pixels;

import java.util.*;

/**
 * 302. 包含全部黑色像素的最小矩形
 *
 * 图片在计算机处理中往往是使用二维矩阵来表示的。
 * 给你一个大小为 m x n 的二进制矩阵 image 表示一张黑白图片，0 代表
 * 白色像素，1 代表黑色像素。
 * 黑色像素相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素。
 * 像素点是水平或竖直方向连接的。
 * 给你两个整数 x 和 y 表示某一个黑色像素的位置，请你找出包含全部黑
 * 色像素的最小矩形（与坐标轴对齐），并返回该矩形的面积。
 * 你必须设计并实现一个时间复杂度低于 O(mn) 的算法来解决此问题。
 */
class Solution {
    public static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    private int top, bottom, left, right;

    /**
     * 方法一：简单的线性查找
     */
    public int minArea1(char[][] image, int x, int y) {
        // 记录矩形的四个边界
        top = Integer.MAX_VALUE;
        left = Integer.MAX_VALUE;
        bottom = Integer.MIN_VALUE;
        right = Integer.MIN_VALUE;
        int rows = image.length, cols = image[0].length;
        // 遍历所有的像素点，记录黑色像素坐标的最大和最小值
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (image[i][j] == '1') {
                    top = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }
        return (right - left + 1) * (bottom - top + 1);
    }

    /**
     * 方法二：记忆化深度优先搜索
     */
    public int minArea2(char[][] image, int x, int y) {
        // 简单方法没有使用所有黑色像素是连接在一起的且一个黑色像素的坐标会被给出这一条件
        // 一个简单的利用此条件的办法是从这个像素开始穷尽搜索
        // 由于所有黑色像素是连接在一起的， DFS 或者 BFS 会从给定的这一黑色像素开始将它们全部访问到
        if (image.length == 0 || image[0].length == 0) {
            return 0;
        }

        top = Integer.MAX_VALUE;
        left = Integer.MAX_VALUE;
        bottom = Integer.MIN_VALUE;
        right = Integer.MIN_VALUE;

        // 找到所有彼此连接的黑色像素并更新边界
        dfs(image, x, y);

        return (right - left + 1) * (bottom - top + 1);
    }

    private void dfs(char[][] image, int x, int y) {
        if (x < 0 || y < 0 || x >= image.length || y >= image[0].length || image[x][y] == '0') {
            return;
        }

        // mark visited black pixel as white
        image[x][y] = '0';

        top = Math.min(top, x);
        bottom = Math.max(bottom, x);
        left = Math.min(left, y);
        right = Math.max(right, y);

        for (int[] dir : DIRECTIONS) {
            dfs(image, x + dir[0], y + dir[1]);
        }
    }

    /**
     * 方法三：记忆化广度优先搜索
     */
    public int minArea3(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) {
            return 0;
        }

        top = Integer.MAX_VALUE;
        left = Integer.MAX_VALUE;
        bottom = Integer.MIN_VALUE;
        right = Integer.MIN_VALUE;

        bfs(image, x, y);

        return (right - left + 1) * (bottom - top + 1);
    }

    private void bfs(char[][] image, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        // mark visited black pixel as white
        image[x][y] = '0';

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            top = Math.min(top, curr[0]);
            bottom = Math.max(bottom, curr[0]);
            left = Math.min(left, curr[1]);
            right = Math.max(right, curr[1]);

            for (int[] dir : DIRECTIONS) {
                int newX = curr[0] + dir[0];
                int newY = curr[1] + dir[1];
                if (newX >= 0 && newX < image.length && newY >= 0 && newY < image[0].length && image[newX][newY] == '1') {
                    queue.offer(new int[]{newX, newY});
                    image[newX][newY] = '0';
                }
            }
        }
    }

    /**
     * 方法四：二分查找
     */
    public int minArea4(char[][] image, int x, int y) {
        // 把二维图形投影到一维数组，并使用二分查找的方法来找边界
        int rows = image.length, cols = image[0].length;

        // 在行数组 [0, y] 中二分查找 left
        left = searchLeft(image, 0, y, 0, rows);
        // 在行数组 [y, cols - 1] 中二分查找 right
        right = searchRight(image, y, cols - 1, 0, rows);

        // 在列数组 [0, x] 中二分查找 top
        top = searchTop(image, 0, x, 0, cols);
        // 在列数组 [x, rows - 1] 中二分查找 bottom
        bottom = searchBottom(image, x, rows - 1, 0, cols);

        return (right - left + 1) * (bottom - top + 1);
    }

    private int searchLeft(char[][] image, int low, int high, int top, int bottom) {
        while (low < high) {
            int k = top, mid = (low + high) / 2;
            while (k < bottom && image[k][mid] == '0') {
                ++k;
            }
            // k < bottom means the column mid has black pixel
            if (k < bottom) {
                // search the boundary in the smaller half
                high = mid;
            } else {
                // search the boundary in the greater half
                low = mid + 1;
            }
        }
        return low;
    }

    private int searchRight(char[][] image, int low, int high, int top, int bottom) {
        while (low < high) {
            int k = top, mid = (low + high + 1) / 2;
            while (k < bottom && image[k][mid] == '0') {
                ++k;
            }
            // k < bottom means the column mid has black pixel
            if (k < bottom) {
                // search the boundary in the smaller half
                low = mid;
            } else {
                // search the boundary in the greater half
                high = mid - 1;
            }
        }
        return low;
    }

    private int searchTop(char[][] image, int low, int high, int left, int right) {
        while (low < high) {
            int k = left, mid = (low + high) / 2;
            while (k < right && image[mid][k] == '0') {
                ++k;
            }
            // k < right means the row mid has black pixel
            if (k < right) {
                // search the boundary in the smaller half
                high = mid;
            } else {
                // search the boundary in the greater half
                low = mid + 1;
            }
        }
        return low;
    }

    private int searchBottom(char[][] image, int low, int high, int left, int right) {
        while (low < high) {
            int k = left, mid = (low + high + 1) / 2;
            while (k < right && image[mid][k] == '0') {
                ++k;
            }
            // k < right means the row mid has black pixel
            if (k < right) {
                // search the boundary in the smaller half
                low = mid;
            } else {
                // search the boundary in the greater half
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minArea1(new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}}, 0, 2));
        System.out.println(new Solution().minArea2(new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}}, 0, 2));
        System.out.println(new Solution().minArea3(new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}}, 0, 2));
        System.out.println(new Solution().minArea4(new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}}, 0, 2));
    }
}
