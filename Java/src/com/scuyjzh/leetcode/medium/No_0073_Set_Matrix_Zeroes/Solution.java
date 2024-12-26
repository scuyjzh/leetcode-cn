package com.scuyjzh.leetcode.medium.No_0073_Set_Matrix_Zeroes;

import java.util.Arrays;

/**
 * 73. 矩阵置零
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元
 * 素都设为 0 。请使用 原地 算法。
 */
class Solution {
    /**
     * 方法一：使用标记数组
     *
     * • 时间复杂度：O(mn)，其中 m 是矩阵的行数，n 是矩阵的列数。至多只需要遍历该矩阵两次。
     * • 空间复杂度：O(m+n)，其中 m 是矩阵的行数，n 是矩阵的列数。需要分别记录每一行或每一列
     *   是否有零出现。
     */
    public void setZeroes1(int[][] matrix) {
        /*
         * 可以用两个标记数组分别记录每一行和每一列是否有零出现。
         *
         * 具体地，首先遍历该数组一次，如果某个元素为 0，那么就将该元素所在的行和列所对应标记数组的位
         * 置置为 true。最后再次遍历该数组，用标记数组更新原数组即可。
         */
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 方法二：使用两个标记变量
     *
     * • 时间复杂度：O(mn)，其中 m 是矩阵的行数，n 是矩阵的列数。至多只需要遍历该矩阵两次。
     * • 空间复杂度：O(1)。只需要常数空间存储若干变量。
     */
    public void setZeroes2(int[][] matrix) {
        /*
         * 可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1) 的额外空间。但这样会导致
         * 原数组的第一行和第一列被修改，无法记录它们是否原本包含 0。因此需要额外使用两个标记变量分别
         * 记录第一行和第一列是否原本包含 0。
         *
         * 在实际代码中，首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，然后反过来
         * 使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可。
         */
        int m = matrix.length, n = matrix[0].length;
        // 使用两个标记变量分别记录第一行和第一列是否原本包含 0
        boolean flagCol0 = false, flagRow0 = false;
        // 预处理出两个标记变量
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
                break;
            }
        }
        for (int j = 0; j < n; ++j) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
                break;
            }
        }
        // 使用其他行与列去处理第一行与第一列
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 然后反过来使用第一行与第一列去更新其他行与列
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最后使用两个标记变量更新第一行与第一列
        if (flagCol0) {
            for (int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; ++j) {
                matrix[0][j] = 0;
            }
        }
    }

    /**
     * 方法三：使用一个标记变量
     *
     * • 时间复杂度：O(mn)，其中 m 是矩阵的行数，n 是矩阵的列数。至多只需要遍历该矩阵两次。
     * • 空间复杂度：O(1)。只需要常数空间存储若干变量。
     */
    public void setZeroes3(int[][] matrix) {
        /*
         * 可以对方法二进一步优化，只使用一个标记变量记录第一列是否原本存在 0。这样，第一列的第一个元
         * 素即可以标记第一行是否出现 0。但为了防止每一列的第一个元素被提前更新，需要从最后一行开始，
         * 倒序地处理矩阵元素。
         */
        int m = matrix.length, n = matrix[0].length;
        // 使用一个标记变量记录第一列是否原本存在 0
        boolean flagCol0 = false;
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        new Solution().setZeroes1(matrix1);
        System.out.println(Arrays.deepToString(matrix1));

        int[][] matrix2 = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        new Solution().setZeroes2(matrix2);
        System.out.println(Arrays.deepToString(matrix2));

        int[][] matrix3 = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        new Solution().setZeroes3(matrix3);
        System.out.println(Arrays.deepToString(matrix3));
    }
}
