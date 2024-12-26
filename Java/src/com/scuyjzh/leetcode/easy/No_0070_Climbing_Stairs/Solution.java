package com.scuyjzh.leetcode.easy.No_0070_Climbing_Stairs;

/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 */
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        // 状态初始化
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; ++i) {
            // 状态转移：f[n] = f[n−1] + f[n−2]
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(2));
        System.out.println(new Solution().climbStairs(3));
    }
}
