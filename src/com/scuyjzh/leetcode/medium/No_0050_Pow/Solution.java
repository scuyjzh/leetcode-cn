package com.scuyjzh.leetcode.medium.No_0050_Pow;

/**
 * 50. Pow(x, n)
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。
 */
class Solution {
    /**
     * 方法一：快速幂 + 递归
     * 时间复杂度：O(log n)，即为递归的层数。
     * 空间复杂度：O(log n)，即为递归的层数。这是由于递归的函数调用会使用栈空间。
     */
    public double myPow1(double x, int n) {
        /*
         * 思路：
         * 「快速幂算法」的本质是分治算法：
         * • 当计算 x^n 时，可以先递归地计算出 y = x^⌊n/2⌋，其中 ⌊a⌋ 表示对 a 进行下取整；
         * • 根据递归计算的结果，如果 n 为偶数，那么 x^n = y^2；如果 n 为奇数，那么 x^n = y^2 × x；
         * • 递归的边界为 n = 0，任意数的 0 次方均为 1。
         */
        long N = n;
        // 符号判断
        if (N >= 0) {
            return quickMul1(x, N);
        } else {
            return 1.0 / quickMul1(x, -N);
        }
    }

    private double quickMul1(double x, long N) {
        // 递归终止条件
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul1(x, N / 2);
        // N 为偶数
        if (N % 2 == 0) {
            return y * y;
        }
        // N 为奇数
        else {
            return y * y * x;
        }
    }

    /**
     * 方法二：快速幂 + 迭代
     * 时间复杂度：O(log n)，即为对 n 进行二进制拆分的时间复杂度。
     * 空间复杂度：O(1)。
     */
    public double myPow2(double x, int n) {
        long N = n;
        // 符号判断
        if (N >= 0) {
            return quickMul2(x, N);
        } else {
            return 1.0 / quickMul2(x, -N);
        }
    }

    private double quickMul2(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myPow1(2.0, 5));
        System.out.println(solution.myPow2(2.0, 5));
    }
}
