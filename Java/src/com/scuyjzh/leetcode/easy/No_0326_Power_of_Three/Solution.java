package com.scuyjzh.leetcode.easy.No_0326_Power_of_Three;

/**
 * 326. 3的幂
 *
 * 给定一个整数，写一个函数来判断它是否是 3的幂次方。如果是，返回
 * true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 */
class Solution {
    public boolean isPowerOfThree1(int n) {
        // 不断地将 n 除以 3，直到 n=1
        // 如果此过程中 n 无法被 3 整除，就说明 n 不是 3 的幂
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    public boolean isPowerOfThree2(int n) {
        // 在题目给定的 32 位有符号整数的范围内，最大的 3 的幂为 3^19 = 1162261467
        // 只需要判断 n 是否是 3^19 的约数即可
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPowerOfThree1(27));
        System.out.println(new Solution().isPowerOfThree1(0));
        System.out.println(new Solution().isPowerOfThree2(9));
        System.out.println(new Solution().isPowerOfThree2(45));
    }
}
