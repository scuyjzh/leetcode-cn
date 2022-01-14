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
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        // 优先级队列，保存 [index1, index2]
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, Comparator.comparingInt(o -> nums1[o[0]] + nums2[o[1]]));
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        // 把 nums1 的所有索引入队，nums2 的索引初始时都是 0
        // 优化：最多入队 k 个就可以了，因为提示中 k 的范围较小，这样可以提高效率
        for (int i = 0; i < Math.min(m, k); ++i) {
            pq.offer(new int[]{i, 0});
        }
        // 最多弹出 k 次
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            ans.add(Arrays.asList(nums1[idxPair[0]], nums2[idxPair[1]]));
            // 将 index2 加 1 之后继续入队
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }
        return ans;
    }

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        /*二分查找第 k 小的数对和的大小*/
        int left = nums1[0] + nums2[0];
        int right = nums1[m - 1] + nums2[n - 1];
        int pairSum = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long cnt = 0;
            int start = 0;
            int end = n - 1;
            while (start < m && end >= 0) {
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    cnt += end + 1;
                    start++;
                }
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos = n - 1;
        /*找到小于目标值 pairSum 的数对*/
        for (int i = 0; i < m; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }
        }

        /*找到等于目标值 pairSum 的数对*/
        pos = n - 1;
        for (int i = 0; i < m && k > 0; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            for (int j = i; k > 0 && j >= 0 && nums1[j] + nums2[pos] == pairSum; j--, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[j]);
                list.add(nums2[pos]);
                ans.add(list);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kSmallestPairs1(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        System.out.println(new Solution().kSmallestPairs2(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
    }
}
