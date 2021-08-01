package com.scuyjzh.leetcode.medium.No_0029_Divide_Two_Integers;

/**
 * 29. 两数相除
 * <p>
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数dividend除以除数divisor得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 */
class Solution {
    /**
     * 方法一：二分 + 倍增乘法
     */
    public int divide1(int dividend, int divisor) {
        long x = dividend, y = divisor;
        boolean isNeg = (x > 0 && y < 0) || (x < 0 && y > 0);
        x = x < 0 ? -x : x;
        y = y < 0 ? -y : y;

        // 思路：通过实现一个「倍增乘法」，然后利用对于 x 除以 y，结果 x / y 必然落在范围 [0, x] 的规律进行二分
        long l = 0, r = x;
        while (l < r) {
            /*
             * 「二分」模板中计算 mid 的方式有两套，根据 check(mid) 函数为 true 时，需要调整的是 l 指针还是 r 指针来判断：
             * • 当 check(mid) == true 调整的是 l 时：计算 mid 的方式应该为 mid = l + r + 1 >> 1：
             * • 当 check(mid) == true 调整的是 r 时：计算 mid 的方式应该为 mid = l + r >> 1：
             */
            long mid = l + r + 1 >> 1;
            if (mul(mid, y) <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        long ans = isNeg ? -l : l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) ans;
    }

    /**
     * 倍增乘法
     *
     * @param a 乘数
     * @param k 被乘数
     * @return 积
     */
    private long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) {
                ans += a;
            }
            k >>= 1;
            a += a;
        }
        return ans;
    }

    public int divide2(int dividend, int divisor) {
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
        System.out.println(solution.divide1(10, 4));
        System.out.println(solution.divide2(7, -3));
    }
}
