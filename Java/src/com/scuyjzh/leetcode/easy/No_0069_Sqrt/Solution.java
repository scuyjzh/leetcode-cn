package com.scuyjzh.leetcode.easy.No_0069_Sqrt;

/**
 * 69. Sqrt(x)
 *
 * 给你一个非负整数 x ，计算并返回x的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
class Solution {
    /**
     * 方法一：二分查找
     */
    public int mySqrt1(int x) {
        if (x <= 1) {
            return x;
        }
        int left = 1, right = (x + 1) / 2;
        while (left <= right) {
            // prevent '+' integer overflow
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                // prevent '*' integer overflow
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 方法二：牛顿迭代法
     */
    public int mySqrt2(int x) {
        /*
         * 思路：
         * https://www.matongxue.com/madocs/205/
         */
        long r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt1(12));
        System.out.println(solution.mySqrt2(78));
    }
}
