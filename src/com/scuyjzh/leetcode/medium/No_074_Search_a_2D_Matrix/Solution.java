package com.scuyjzh.leetcode.medium.No_074_Search_a_2D_Matrix;

/**
 * @author scuyjzh
 * @data 2020/6/28 22:05
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;

        // 二分查找
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            // 选取虚数组最中间的序号作为中间序号: pivot_idx = (left + right) / 2
            pivotIdx = (left + right) / 2;
            // 该序号对应于原矩阵中的（row = pivot_idx // n）行（col = pivot_idx % n）列
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) {
                return true;
            } else {
                if (target < pivotElement) {
                    right = pivotIdx - 1;
                } else {
                    left = pivotIdx + 1;
                }
            }
        }
        return false;
    }
}
