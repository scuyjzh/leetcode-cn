package com.scuyjzh.leetcode.easy.No_0342_Power_of_Four;

/**
 * 342. 4的幂
 *
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回
 * true ；否则，返回 false 。
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
 */
class Solution {
    public boolean isPowerOfFour1(int n) {
        if (n <= 0) {
            return false;
        }
        // 先对 n 执行 sqrt 函数
        int x = (int) Math.sqrt(n);
        // 然后应用 lowbit 函数快速判断 sqrt{n} 是否为 2 的幂
        return x * x == n && (x & -x) == x;
    }

    public boolean isPowerOfFour2(int n) {
        /*
         * 如果 n 是 4 的幂，那么它可以表示成 4^x 的形式，可以发现它除以 3 的余数一定为 1，
         * 如果 n 是 2 的幂却不是 4 的幂，那么它可以表示成 4^x × 2 的形式，此时它除以 3 的余数一定为 2。
         * 因此可以通过 n 除以 3 的余数是否为 1 来判断 n 是否是 4 的幂。
         */
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPowerOfFour1(16));
        System.out.println(new Solution().isPowerOfFour2(5));
        System.out.println(new Solution().isPowerOfFour2(1));
    }
}
