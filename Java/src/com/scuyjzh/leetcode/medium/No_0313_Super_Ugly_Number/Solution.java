package com.scuyjzh.leetcode.medium.No_0313_Super_Ugly_Number;

import java.util.*;

/**
 * 313. 超级丑数
 *
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 */
class Solution {
    /**
     * 方法一：最小堆
     */
    public int nthSuperUglyNumber1(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.add(1L);
        set.add(1L);
        while (n-- > 0) {
            long curr = heap.poll();
            if (n == 0) {
                return (int) curr;
            }
            for (int prime : primes) {
                if (!set.contains(prime * curr)) {
                    set.add(prime * curr);
                    heap.add(prime * curr);
                }
            }
        }
        // never
        return -1;
    }

    /**
     * 方法二：动态规划
     */
    public int nthSuperUglyNumber2(int n, int[] primes) {
        // dp[i] 表示第 i+1 个超级丑数，第 n 个超级丑数即为 dp[n-1]
        int[] dp = new int[n];
        // 由于最小的超级丑数是 1，因此 dp[0]=1
        dp[0] = 1;

        int m = primes.length;
        // 创建与数组 primes 相同长度的数组 pointers，表示下一个超级丑数是当前指针指向的超级丑数乘以对应的质因数
        int[] pointers = new int[m];

        for (int i = 1; i < n; ++i) {
            dp[i] = Integer.MAX_VALUE;
            // dp[i]=min(dp[pointers[j]] × primes[j])，0≤j<m
            for (int j = 0; j < m; ++j) {
                dp[i] = Math.min(dp[i], dp[pointers[j]] * primes[j]);
            }

            for (int j = 0; j < m; ++j) {
                // 对于每个 0≤j<m，分别比较 dp[i] 和 dp[pointers[j]]×primes[j] 是否相等
                if (dp[i] == dp[pointers[j]] * primes[j]) {
                    // 如果相等则将 pointers[j] 加 1
                    ++pointers[j];
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().nthSuperUglyNumber1(12, new int[]{2, 7, 13, 19}));
        System.out.println(new Solution().nthSuperUglyNumber2(1, new int[]{2, 3, 5}));
    }
}
