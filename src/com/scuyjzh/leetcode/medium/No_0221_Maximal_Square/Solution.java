package com.scuyjzh.leetcode.medium.No_0221_Maximal_Square;

/**
 * 221. 最大正方形
 *
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方
 * 形，并返回其面积。
 */
class Solution {
    public int maximalSquare(char[][] matrix) {
        /*
         * 使用动态规划降低暴力法的时间复杂度。用 dp(i,j) 表示以 (i,j) 为右下角，且只包含 1 的正方形的边长最大
         * 值。如果能计算出所有 dp(i,j) 的值，那么其中的最大值即为矩阵中只包含 1 的正方形的边长最大值，
         * 其平方即为最大正方形的面积。
         *
         * 那么如何计算 dp 中的每个元素值呢？对于每个位置 (i,j)，检查在矩阵中该位置的值：
         *   • 如果该位置的值是 0，则 dp(i,j)=0，因为当前位置不可能在由 1 组成的正方形中；
         *   • 如果该位置的值是 1，则 dp(i,j) 的值由其上方、左方和左上方的三个相邻位置的 dp 值决定。具体而
         *     言，当前位置的元素值等于三个相邻位置的元素中的最小值加 1，状态转移方程如下：
         *             dp(i,j) = min(dp(i−1,j), dp(i−1,j−1), dp(i,j−1)) + 1
         *     此外，还需要考虑边界条件。如果 i 和 j 中至少有一个为 0，则以位置 (i,j) 为右下角的最大正方形的
         *     边长只能是 1，因此 dp(i,j)=1。
         */
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length, columns = matrix[0].length;
        // 状态定义：dp[i][j] 表示以 (i,j) 为右下角，且只包含 1 的正方形的边长最大值
        int[][] dp = new int[rows][columns];
        int maxSide = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        // 边界条件：如果 i 和 j 中至少有一个为 0，则以位置 (i,j) 为右下角的最大正方形的边长只能是 1
                        dp[i][j] = 1;
                    } else {
                        // 状态转移：dp(i,j) = min(dp(i−1,j), dp(i−1,j−1), dp(i,j−1)) + 1
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    // 更新边长最大值
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
        System.out.println(new Solution().maximalSquare(new char[][]{{'0', '1'}, {'1', '0'}}));
        System.out.println(new Solution().maximalSquare(new char[][]{{'0'}}));
    }
}
