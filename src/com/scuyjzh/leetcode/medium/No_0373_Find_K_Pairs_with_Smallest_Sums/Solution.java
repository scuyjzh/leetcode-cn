package com.scuyjzh.leetcode.medium.No_0373_Find_K_Pairs_with_Smallest_Sums;

import java.util.*;

/**
 * 373. 查找和最小的K对数字
 *
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 创建大根堆
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] + b[1] - (a[0] + a[1]));
        boolean find = false;
        for (int u : nums1) {
            for (int v : nums2) {
                // 已找到 k 个数对
                if (find) {
                    // 当前数对与堆顶最大元素比较
                    int[] pair = maxHeap.peek();
                    if (u + v > pair[0] + pair[1]) {
                        // （剪枝）由于数组升序排列，因此后续数对和不可能比堆顶元素小，直接退出
                        break;
                    }
                }
                maxHeap.add(new int[]{u, v});
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                    find = true;
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int[] pair : maxHeap) {
            res.add(Arrays.asList(pair[0], pair[1]));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        System.out.println(new Solution().kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
        System.out.println(new Solution().kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3));
    }
}
