package com.scuyjzh.leetcode.medium.No_0264_Ugly_Number_II;

import java.util.*;

/**
 * 264. 丑数 II
 *
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 */
class Solution {
    /**
     * 方法一：最小堆
     */
    public int nthUglyNumber1(int n) {
        /*
         * 要得到从小到大的第 n 个丑数，可以使用最小堆实现。
         * 初始时堆为空。首先将最小的丑数 1 加入堆。
         * 每次取出堆顶元素 x，则 x 是堆中最小的丑数，由于 2x,3x,5x 也是丑数，因此将 2x,3x,5x 加入堆。
         * 上述做法会导致堆中出现重复元素的情况。为了避免重复元素，可以使用哈希集合去重，避免相同元素多次
         * 加入堆。
         * 在排除重复元素的情况下，第 n 次从最小堆中取出的元素即为第 n 个丑数。
         */
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; ++i) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    /**
     * 方法二：动态规划（三指针）
     */
    public int nthUglyNumber2(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/ugly-number-ii/solution/san-zhi-zhen-fang-fa-de-li-jie-fang-shi-by-zzxn/
         */
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; ++i) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber1(10));
        System.out.println(new Solution().nthUglyNumber2(10));
    }
}
