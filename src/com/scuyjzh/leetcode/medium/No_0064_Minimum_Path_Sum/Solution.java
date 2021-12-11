package com.scuyjzh.leetcode.medium.No_0064_Minimum_Path_Sum;

/**
 * 64. 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右
 * 下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 */
class Solution {
    /**
     * 方法：动态规划
     */
    public int minPathSum(int[][] grid) {
        /*
         * 由于路径的方向只能是向下或向右，因此网格的第一行的每个元素只能从左上角元素开始向右移动到达，网
         * 格的第一列的每个元素只能从左上角元素开始向下移动到达，此时的路径是唯一的，因此每个元素对应的最
         * 小路径和即为对应的路径上的数字总和。
         *
         * 对于不在第一行和第一列的元素，可以从其上方相邻元素向下移动一步到达，或者从其左方相邻元素向右移
         * 动一步到达，元素对应的最小路径和等于其上方相邻元素与其左方相邻元素两者对应的最小路径和中的最小
         * 值加上当前元素的值。由于每个元素对应的最小路径和与其相邻元素对应的最小路径和有关，因此可以使用
         * 动态规划求解。
         *
         * 创建二维数组 dp，与原始网格的大小相同，dp[i][j] 表示从左上角出发到 (i,j) 位置的最小路径和。显然，
         * dp[0][0]=grid[0][0]。对于 dp 中的其余元素，通过以下状态转移方程计算元素值。
         *   • 当 i>0 且 j=0 时，dp[i][0]=dp[i−1][0]+grid[i][0]。
         *   • 当 i=0 且 j>0 时，dp[0][j]=dp[0][j-1]+grid[0][j]。
         *   • 当 i>0 且 j>0 时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]。
         *
         * 最后得到 dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和。
         */
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        // 状态定义：dp[i][j] 表示从左上角出发到 (i,j) 位置的最小路径和
        int[][] dp = new int[rows][columns];
        // 状态初始化
        dp[0][0] = grid[0][0];
        // 状态转移：当 i>0 且 j=0 时
        for (int i = 1; i < rows; ++i) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 状态转移：当 i=0 且 j>0 时
        for (int j = 1; j < columns; ++j) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // 状态转移：当 i>0 且 j>0 时
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < columns; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        // dp[rows−1][columns−1] 的值即为从网格左上角到网格右下角的最小路径和
        return dp[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }
}
