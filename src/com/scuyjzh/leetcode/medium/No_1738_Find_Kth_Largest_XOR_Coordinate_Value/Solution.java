package com.scuyjzh.leetcode.medium.No_1738_Find_Kth_Largest_XOR_Coordinate_Value;

import java.util.*;

/**
 * 1738. 找出第 K 大的异或坐标值
 *
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素
 * matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 */
class Solution {
    public int kthLargestValue(int[][] mat, int k) {
        /*
         * 用 ⊕ 表示按位异或运算。
         *
         * 由于「按位异或运算」与「加法运算」有着十分相似的性质，它们都满足交换律：
         *                                    a⊕b=b⊕a
         * 以及结合律：
         *                               (a⊕b)⊕c=a⊕(b⊕c)
         *
         * 因此可以使用「前缀和」这一技巧对按位异或运算的结果进行维护。由于本题中给定的矩阵 matrix 是二
         * 维的，因此需要使用二维前缀和。
         *
         * 设二维前缀和 pre(i,j) 表示矩阵 matrix 中所有满足 0≤x<i 且 0≤y<j 的元素执行按位异或运算的结
         * 果。与一维前缀和类似，要想快速得到 pre(i,j)，需要已经知道 pre(i−1,j)，pre(i,j−1) 以及 pre(i−
         * 1,j−1) 的结果，即：
         *             pre(i,j)=pre(i−1,j)⊕pre(i,j−1)⊕pre(i−1,j−1)⊕matrix(i,j)
         *
         * 当将 pre(i−1,j) 和 pre(i,j−1) 进行按位异或运算后，由于对一个数 x 异或两次 y，结果仍然为 x 本
         * 身，即：
         *                                     x⊕y⊕y=x
         *
         * 因此 pre(i−1,j−1) 对应区域的按位异或结果被抵消，需要将其补上，并对位置 (i,j) 的元素进行按位
         * 异或运算，这样就得到了 pre(i,j)。
         *
         * 在得到了所有的二维前缀和之后，只需要找出其中第 k 大的元素即为答案。这一步可以直接将 mn
         * 个二维前缀和进行排序后返第 k 大的元素，也可以参考「215. 数组中的第K个最大元素」的官方题解中时
         * 间复杂度更低的做法。
         */
        int m = mat.length, n = mat[0].length;
        int[][] pre = new int[m + 1][n + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ mat[i - 1][j - 1];
                if (queue.size() < k) {
                    queue.offer(pre[i][j]);
                } else {
                    if (pre[i][j] > queue.peek()) {
                        queue.poll();
                        queue.offer(pre[i][j]);
                    }
                }
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kthLargestValue(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 5));
    }
}
