package com.scuyjzh.leetcode.medium.No_0240_Search_a_2D_Matrix_II;

/**
 * 240. 搜索二维矩阵 II
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值
 * target 。该矩阵具有以下特性：
 *   • 每行的元素从左到右升序排列。
 *   • 每列的元素从上到下升序排列。
 */
class Solution {
    /**
     * 方法一：二分查找
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        /*
         * 由于矩阵 matrix 中每一行的元素都是升序排列的，因此可以对每一行都使用一次二分查找，判断 target
         * 是否在该行中，从而判断 target 是否出现。
         */
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    private int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 方法二：Z 字形查找
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                // 如果当前指向的值大于目标值，则可以“向上”移动一行
                --row;
            } else if (matrix[row][col] < target) {
                // 如果当前指向的值小于目标值，则可以“向右”移动一列
                ++col;
            } else { // found it
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().searchMatrix1(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5));
        System.out.println(new Solution().searchMatrix2(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20));
    }
}
