package com.scuyjzh.leetcode.medium.No_0029_Divide_Two_Integers;

/**
 * 29. 两数相除
 * <p>
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数dividend除以除数divisor得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 */
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 用异或来判断符号是否相异
        boolean negative = (dividend ^ divisor) < 0;
        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int result = 0;
        for (int i = 31; i >= 0; --i) {
            // 找出足够大的数 2^n*divisor
            if ((t >> i) >= d) {
                // 将结果加上 2^n
                result += 1 << i;
                // 将被除数减去 2^n*divisor
                t -= d << i;
            }
        }
        // 符号相异取反
        return negative ? -result : result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide(10, 3));
        System.out.println(solution.divide(7, -3));
    }
}
