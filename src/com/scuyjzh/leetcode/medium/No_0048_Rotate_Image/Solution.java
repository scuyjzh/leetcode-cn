package com.scuyjzh.leetcode.medium.No_0048_Rotate_Image;

import java.util.*;

/**
 * 48. 旋转图像
 * <p>
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
class Solution {
    /**
     * 方法一：两次翻转
     */
    public void rotate1(int[][] matrix) {
        int len = matrix.length;
        // 先沿水平中线上下翻转
        for (int i = 0; i < len / 2; ++i) {
            int[] temp = matrix[i];
            matrix[i] = matrix[len - i - 1];
            matrix[len - i - 1] = temp;
        }
        // 再沿右上 - 左下的对角线翻转
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 方法二：自外向内顺时针循环
     */
    public void rotate2(int[][] matrix) {
        int len = matrix.length;
        // 按圈旋转，因为是对称的，只需要循环计算前半行即可
        for (int i = 0; i < len / 2; i++) {
            for (int j = i; j < len - i - 1; j++) {
                int temp = matrix[i][j];
                int m = len - j - 1;
                int n = len - i - 1;
                matrix[i][j] = matrix[m][i];
                matrix[m][i] = matrix[n][m];
                matrix[n][m] = matrix[j][n];
                matrix[j][n] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        solution.rotate1(matrix);
        System.out.println(Arrays.deepToString(matrix));
        solution.rotate2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
