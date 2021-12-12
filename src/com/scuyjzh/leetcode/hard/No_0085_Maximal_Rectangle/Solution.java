package com.scuyjzh.leetcode.hard.No_0085_Maximal_Rectangle;

import java.util.*;

/**
 * 85. 最大矩形
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出
 * 只包含 1 的最大矩形，并返回其面积。
 */
class Solution {
    /**
     * 方法一：使用「84. 柱状图中最大的矩形」中的单调栈方法
     */
    public int maximalRectangle1(char[][] matrix) {
        // 1.将输入拆分成一系列的柱状图
        // 2.为了计算矩形的最大面积，只需要计算每个柱状图中的最大面积，并找到全局最大值
        // 3.使用「84. 柱状图中最大的矩形」中的单调栈的做法，将其应用在生成的柱状图中
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int cols = matrix.length;
        int rows = matrix[0].length;
        int[] heights = new int[rows];
        int res = 0;
        for (int i = 0; i < cols; ++i) {
            for (int j = 0; j < rows; ++j) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            res = Math.max(res, largestRectangleArea(heights));
        }
        return res;
    }

    /**
     * 「84. 柱状图中最大的矩形」中的单调栈方法
     */
    private int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int[] newHeights = new int[len + 2];
        for (int i = 0; i < len; ++i) {
            newHeights[i + 1] = heights[i];
        }
        len = newHeights.length;

        Deque<Integer> stack = new ArrayDeque<>();
        // 先放入哨兵，在循环里就不用做非空判断
        stack.push(0);

        int res = 0;
        for (int i = 1; i < len; ++i) {
            while (newHeights[i] < newHeights[stack.peek()]) {
                int curHeight = newHeights[stack.poll()];
                int curWidth = i - stack.peek() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 方法二：动态规划
     */
    public int maximalRectangle2(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int cols = matrix.length;
        int rows = matrix[0].length;
        int[][] newMatrix = new int[cols][rows];
        for (int i = 0; i < cols; ++i) {
            for (int j = 0; j < rows; ++j) {
                if (matrix[i][j] == '1') {
                    if (i == 0) {
                        newMatrix[i][j] = 1;
                    } else {
                        newMatrix[i][j] = newMatrix[i - 1][j] + 1;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < cols; ++i) {
            for (int j = 0; j < rows; ++j) {
                // 优化条件（类似剪枝）
                if (newMatrix[i][j] * rows <= ans) {
                    continue;
                }
                if (newMatrix[i][j] != 0) {
                    int cnt = 1;
                    // 向左拓展宽度
                    for (int k = j - 1; k >= 0; --k) {
                        if (newMatrix[i][k] < newMatrix[i][j]) {
                            break;
                        }
                        cnt++;
                    }
                    // 向右拓展宽度
                    for (int k = j + 1; k < rows; ++k) {
                        if (newMatrix[i][k] < newMatrix[i][j]) {
                            break;
                        }
                        cnt++;
                    }
                    ans = Math.max(ans, cnt * newMatrix[i][j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximalRectangle1(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
        System.out.println(new Solution().maximalRectangle2(new char[][]{{'1', '1', '0'}, {'1', '0', '1'}, {'1', '1', '1'}}));
    }
}
