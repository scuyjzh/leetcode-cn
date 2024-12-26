package com.scuyjzh.leetcode.easy.No_0231_Power_of_Two;

/**
 * 231. 2 的幂
 *
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回
 * true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。
 */
class Solution {
    public boolean isPowerOfTwo1(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & -n) == n;
    }

    static final int BIG = 1 << 30;

    public boolean isPowerOfTwo3(int n) {
        return n > 0 && BIG % n == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPowerOfTwo1(1));
        System.out.println(new Solution().isPowerOfTwo2(16));
        System.out.println(new Solution().isPowerOfTwo3(3));
    }
}
