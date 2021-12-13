package com.scuyjzh.leetcode.hard.No_0363_Max_Sum_of_Rectangle_No_Larger_Than_K;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 *
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的
 * 不超过 k 的最大数值和。
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 */
class Solution {
    /**
     * 方法一：暴力 + 动态规划（超出内存限制）
     */
    public int maxSumSubmatrix1(int[][] matrix, int k) {
        // 时间复杂度 O(rows*rows*cols*cols)，空间复杂度 O(rows*rows*cols*cols)
        int res = Integer.MIN_VALUE;
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 状态定义：
        // dp(i1,j1,i2,j2)：from (i1,j1) to (i2,j2)
        int[][][][] dp = new int[rows + 1][cols + 1][rows + 1][cols + 1];
        // 状态转移方程：
        // dp(i1,j1,i2,j2) = dp(i1,j1,i2-1,j2) + dp(i1,j1,i2,j2-1) - dp(i1,j1,i2-1,j2-1) + matrix[i2-1][j2-1];
        for (int i1 = 1; i1 <= rows; ++i1) {
            for (int j1 = 1; j1 <= cols; ++j1) {
                dp[i1][j1][i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= rows; ++i2) {
                    for (int j2 = j1; j2 <= cols; ++j2) {
                        dp[i1][j1][i2][j2] = dp[i1][j1][i2 - 1][j2] + dp[i1][j1][i2][j2 - 1] - dp[i1][j1][i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i1][j1][i2][j2] <= k && dp[i1][j1][i2][j2] > res) {
                            res = dp[i1][j1][i2][j2];
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 方法二：暴力 + 动态规划 + 状态压缩
     */
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        // 时间复杂度 O(rows*rows*cols*cols)，空间复杂度 O(rows*cols)
        int res = Integer.MIN_VALUE;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i1 = 1; i1 <= rows; ++i1) {
            for (int j1 = 1; j1 <= cols; ++j1) {
                // renew: from (i1,j1) to (i2,j2)
                int[][] dp = new int[rows + 1][cols + 1];
                dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= rows; ++i2) {
                    for (int j2 = j1; j2 <= cols; ++j2) {
                        dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i2][j2] <= k && dp[i2][j2] > res) {
                            res = dp[i2][j2];
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 方法三：数组滚动
     */
    public int maxSumSubmatrix3(int[][] matrix, int k) {
        // 时间复杂度 O(cols ^ 2 * rows)
        int res = Integer.MIN_VALUE;
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 枚举左边界
        for (int l = 0; l < cols; ++l) {
            int[] rowSum = new int[rows];
            // 枚举右边界
            for (int r = l; r < cols; ++r) {
                // 将每一行的和累计到 rowSum
                for (int i = 0; i < rows; ++i) {
                    rowSum[i] += matrix[i][r];
                }
                // 求数组 rowSum 的不超过 k 的最大子数组和
                res = Math.max(res, dpmax(rowSum, k));
            }
        }
        return res;
    }

    private int dpmax(int[] nums, int k) {
        // 在数组 nums 中，求不超过 k 的最大子数组和
        int sum = nums[0], max = nums[0];
        // 时间复杂度 O(rows)
        for (int i = 1; i < nums.length; ++i) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            if (sum > max) {
                max = sum;
            }
        }

        // 如果这个数组的最大子数组和小于等于 k
        // 说明这个数组得到的不超过 k 的最大子数组和就是 max，直接返回
        if (max <= k) {
            return max;
        }

        // 如果这个数组中的最大子数组和大于 k
        // 那么其中必定存在一个最接近 k 并小于等于 k 的值
        max = Integer.MIN_VALUE;
        // 采用暴力解法，两层循环求解最接近 k 的子数组和
        // 时间复杂度 O(rows ^ 2)
        for (int i = 0; i < nums.length; ++i) {
            sum = 0;
            for (int j = i; j < nums.length; ++j) {
                sum += nums[j];
                if (sum > max && sum <= k) {
                    max = sum;
                }
                if (max == k) {
                    // 尽量提前
                    return k;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxSumSubmatrix1(new int[][]{{1, 0, 1}, {0, 2, -3}}, 2));
        System.out.println(new Solution().maxSumSubmatrix2(new int[][]{{1, 0, 1}, {0, 2, -3}}, 2));
        System.out.println(new Solution().maxSumSubmatrix3(new int[][]{{1, 0, 1}, {0, 2, -3}}, 2));
    }
}
