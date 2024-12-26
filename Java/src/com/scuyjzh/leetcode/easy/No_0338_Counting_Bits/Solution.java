package com.scuyjzh.leetcode.easy.No_0338_Counting_Bits;

import java.util.*;

/**
 * 338. 比特位计数
 *
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中
 * 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 * 进阶：
 *   • 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在
 *     线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 */
class Solution {
    /**
     * 方法一：Brian Kernighan 算法
     */
    public int[] countBits1(int n) {
        /*
         * 这道题需要计算从 0 到 n 的每个整数的二进制表示中的 1 的数目。
         *
         * 部分编程语言有相应的内置函数用于计算给定的整数的二进制表示中的 1 的数目，例如 Java 的
         * Integer.bitCount。
         *
         * 为了表述简洁，下文用「一比特数」表示二进制表示中的 1 的数目。
         *
         * 最直观的做法是对从 0 到 n 的每个整数直接计算「一比特数」。每个 int 型的数都可以用 32 位二进制数表
         * 示，只要遍历其二进制表示的每一位即可得到 1 的数目。
         *
         * 利用 Brian Kernighan 算法，可以在一定程度上进一步提升计算速度。Brian Kernighan 算法的原理是：对于
         * 任意整数 x，令 x=x & (x−1)，该运算将 x 的二进制表示的最后一个 1 变成 0。因此，对 x 重复该操作，
         * 直到 x 变成 0，则操作次数即为 x 的「一比特数」。
         *
         * 对于给定的 n，计算从 0 到 n 的每个整数的「一比特数」的时间都不会超过 O(logn)，因此总时间复杂度为
         * O(nlogn)。
         */
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    private int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ++ones;
        }
        return ones;
    }

    /**
     * 方法二：动态规划
     */
    public int[] countBits2(int n) {
        // 时间复杂度 O(n)
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            // 偶数，右移一位（折半）
            // 奇数，右移一位 加 1
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().countBits1(2)));
        System.out.println(Arrays.toString(new Solution().countBits2(5)));
    }
}
