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
        int[] factors = {2, 3, 5};

        // 要得到从小到大的第 n 个丑数，可以使用最小堆实现
        PriorityQueue<Long> heap = new PriorityQueue<>();
        // 初始时堆为空。首先将最小的丑数 1 加入堆
        heap.offer(1L);

        // 为了避免重复元素，可以使用哈希集合去重
        Set<Long> seen = new HashSet<>();
        seen.add(1L);

        int ugly = 0;
        for (int i = 0; i < n; ++i) {
            // 每次取出堆顶元素 x，则 x 是堆中最小的丑数
            long curr = heap.poll();
            ugly = (int) curr;
            // 由于 2x,3x,5x 也是丑数，因此将 2x,3x,5x 加入堆
            for (int factor : factors) {
                long next = curr * factor;
                // 排除重复元素，避免相同元素多次加入堆
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }

        // 在排除重复元素的情况下，第 n 次从最小堆中取出的元素即为第 n 个丑数
        return ugly;
    }

    /**
     * 方法二：动态规划
     */
    public int nthUglyNumber2(int n) {
        // dp[i] 表示第 i 个丑数，第 n 个丑数即为 dp[n]
        int[] dp = new int[n + 1];
        // 由于最小的丑数是 1，因此 dp[1]=1
        dp[1] = 1;
        // 定义三个指针 p2,p3,p5，表示下一个丑数是当前指针指向的丑数乘以对应的质因数
        // 初始时，三个指针的值都是 1
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; ++i) {
            // 当 2≤i≤n 时，dp[i] = min(dp[p2]×2, dp[p3]×3, dp[p5]×5)
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            // 然后分别比较 dp[i] 和 dp[p2]×2, dp[p3]×3, dp[p5]×5 是否相等，
            // 如果相等则将对应的指针加 1
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
        System.out.println(new Solution().nthUglyNumber2(1));
    }
}
