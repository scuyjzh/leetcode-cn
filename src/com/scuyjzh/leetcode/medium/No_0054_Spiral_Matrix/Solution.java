package com.scuyjzh.leetcode.medium.No_0054_Spiral_Matrix;

import java.util.*;

/**
 * 54. 螺旋矩阵
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩
 * 阵中的所有元素。
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int num = matrix.length * matrix[0].length;
        while (num > 0) {
            // left to right.
            for (int i = left; i <= right && num >= 1; ++i) {
                res.add(matrix[top][i]);
                --num;
            }
            ++top;
            // top to bottom.
            for (int i = top; i <= bottom && num >= 1; ++i) {
                res.add(matrix[i][right]);
                --num;
            }
            --right;
            // right to left.
            for (int i = right; i >= left && num >= 1; --i) {
                res.add(matrix[bottom][i]);
                --num;
            }
            --bottom;
            // bottom to top.
            for (int i = bottom; i >= top && num >= 1; --i) {
                res.add(matrix[i][left]);
                --num;
            }
            ++left;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(new Solution().spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }
}
