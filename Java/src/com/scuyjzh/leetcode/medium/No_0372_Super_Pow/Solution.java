package com.scuyjzh.leetcode.medium.No_0372_Super_Pow;

/**
 * 372. 超级次方
 *
 * 你的任务是计算 a^b 对 1337 取模，a 是一个正整数，b 是一个非常大
 * 的正整数且会以数组形式给出。
 */
class Solution {

    int mod = 1337;
    int index = 1;

    public int superPow(int a, int[] b) {
        int len = b.length;
        if (index == len) {
            return myPow(a, b[0]);
        }
        // superPow(a, [1,5,6,4])  =>  superPow(a, [1,5,6])
        return (myPow(a, b[len - index++]) * myPow(superPow(a, b), 10)) % mod;
    }

    private int myPow(int a, int b) {
        // (a * b) % p = (a % p * b % p) % p
        int ans = 1;
        a %= mod;
        for (int i = 1; i <= b; ++i) {
            ans *= a;
            ans %= mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().superPow(2, new int[]{3}));
        System.out.println(new Solution().superPow(2, new int[]{1, 0}));
        System.out.println(new Solution().superPow(1, new int[]{4, 3, 3, 8, 5, 2}));
        System.out.println(new Solution().superPow(2147483647, new int[]{2, 0, 0}));
    }
}
