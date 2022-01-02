package com.scuyjzh.leetcode.medium.No_0201_Bitwise_AND_of_Numbers_Range;

/**
 * 201. 数字范围按位与
 *
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间
 * 内所有数字 按位与 的结果（包含 left 、right 端点）。
 */
class Solution {
    /**
     * 方法一：位移
     */
    public int rangeBitwiseAnd1(int m, int n) {
        // 可以发现，对所有数字执行按位与运算的结果是所有对应二进制字符串的公共前缀再用零补上后面的剩余位
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    /**
     * 方法二：Brian Kernighan 算法
     */
    public int rangeBitwiseAnd2(int m, int n) {
        /*
         * 还有一个位移相关的算法叫做「Brian Kernighan 算法」，它用于清除二进制串中最右边的 1。
         *
         * Brian Kernighan 算法的关键在于每次对 number 和 number−1 之间进行按位与运算后，number 中最右
         * 边的 1 会被抹去变成 0。
         *
         * 基于上述技巧，可以用它来计算两个二进制字符串的公共前缀。
         *
         * 其思想是，对于给定的范围 [m,n]（m<n），可以对数字 n 迭代地应用上述技巧，清除最右边的 1，
         * 直到它小于或等于 m，此时非公共前缀部分的 1 均被消去。因此最后返回 n 即可。
         */
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rangeBitwiseAnd1(5, 7));
        System.out.println(new Solution().rangeBitwiseAnd1(0, 0));
        System.out.println(new Solution().rangeBitwiseAnd2(0, 2147483647));
    }
}
