package com.scuyjzh.leetcode.medium.No_0059_Spiral_Matrix_II;

import java.util.*;

/**
 * 59. 螺旋矩阵 II
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针
 * 顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
class Solution {
    public int[][] generateMatrix(int n) {
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int[][] matrix = new int[n][n];
        int num = 1, total = n * n;
        while (num <= total) {
            // left to right.
            for (int i = left; i <= right; ++i) {
                matrix[top][i] = num++;
            }
            ++top;
            // top to bottom.
            for (int i = top; i <= bottom; ++i) {
                matrix[i][right] = num++;
            }
            --right;
            // right to left.
            for (int i = right; i >= left; --i) {
                matrix[bottom][i] = num++;
            }
            --bottom;
            // bottom to top.
            for (int i = bottom; i >= top; --i) {
                matrix[i][left] = num++;
            }
            ++left;
        }
        return matrix;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().generateMatrix(3)));
        System.out.println(Arrays.deepToString(new Solution().generateMatrix(1)));
    }
}
