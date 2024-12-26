package com.scuyjzh.leetcode.easy.No_0461_Hamming_Distance;

/**
 * 461. 汉明距离
 *
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 */
class Solution {
    /**
     * 方法一：移位实现位计数
     */
    public int hammingDistance1(int x, int y) {
        /*
         * 具体地，记 s=x⊕y，可以不断地检查 s 的最低位，如果最低位为 1，那么令计数器加一，然后令
         * s 整体右移一位，这样 s 的最低位将被舍去，原本的次低位就变成了新的最低位。重复这个过程直到
         * s=0 为止。这样计数器中就累计了 s 的二进制表示中 1 的数量。
         */
        int s = x ^ y, ret = 0;
        while (s != 0) {
            ret += s & 1;
            s >>= 1;
        }
        return ret;
    }

    /**
     * 方法二：Brian Kernighan 算法
     */
    public int hammingDistance2(int x, int y) {
        /*
         * 在方法二中，对于 s=(10001100)_2 的情况，需要循环右移 8 次才能得到答案。而实际上如果可以
         * 跳过两个 1 之间的 0，直接对 1 进行计数，那么就只需要循环 3 次即可。
         *
         * 可以使用 Brian Kernighan 算法进行优化，具体地，该算法可以被描述为这样一个结论：记 f(x) 表示 x
         * 和 x−1 进行与运算所得的结果（即 f(x)=x&(x−1)），那么 f(x) 恰为 x 删去其二进制表示中最右侧的
         * 1 的结果。
         *
         * 基于该算法，当计算出 s=x⊕y，只需要不断让 s=f(s)，直到 s=0 即可。这样每循环一次，s 都会
         * 删去其二进制表示中最右侧的 1，最终循环的次数即为 s 的二进制表示中 1 的数量。
         */
        int s = x ^ y, ret = 0;
        while (s != 0) {
            s &= s - 1;
            ++ret;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().hammingDistance1(1, 4));
        System.out.println(new Solution().hammingDistance2(3, 1));
    }
}
