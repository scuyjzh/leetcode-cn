package com.scuyjzh.leetcode.medium.No_0074_Search_a_2D_Matrix;

/**
 * 74. 搜索二维矩阵
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵
 * 具有如下特性：
 *   • 每行中的整数从左到右按升序排列。
 *   • 每行的第一个整数大于前一行的最后一个整数。
 */
class Solution {
    /**
     * 方法：一次二分查找
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        /*
         * 若将矩阵每一行拼接在上一行的末尾，则会得到一个升序数组，可以在该数组上二分找到目标元素。
         * 代码实现时，可以二分升序数组的下标，将其映射到原矩阵的行和列上。
         */
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;

        // 二分查找
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            // 选取虚数组最中间的序号作为中间序号: pivot_idx = left + (right - left) / 2
            pivotIdx = left + (right - left) / 2;
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

    public static void main(String[] args) {
        System.out.println(new Solution().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        System.out.println(new Solution().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        }
}
