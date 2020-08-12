package com.scuyjzh.leetcode.medium.No_240_Search_a_2D_Matrix_II;

/**
 * @author scuyjzh
 * @data 2020/6/28 23:19
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                // 如果当前指向的值大于目标值，则可以“向上”移动一行
                row--;
            } else if (matrix[row][col] < target) {
                // 如果当前指向的值小于目标值，则可以“向右”移动一列
                col++;
            } else { // found it
                return true;
            }
        }
        return false;
    }
}
