package com.scuyjzh.leetcode.easy.No_0070_Climbing_Stairs;

class Solution {
    /**
     * 爬第n阶楼梯的方法数量，等于2部分之和:
     * 1.爬上 n−1 阶楼梯的方法数量。因为再爬1阶就能到第n阶
     * 2.爬上 n-2 阶楼梯的方法数量。因为再爬2阶就能到第n阶
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] f = new int[n + 1];
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(3));
    }
}
