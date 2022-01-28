package com.scuyjzh.leetcode.medium.No_1438_Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_or_Equal_to_Limit;

import java.util.*;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 *
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长
 * 连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者
 * 等于 limit 。
 *
 * 如果不存在满足条件的子数组，则返回 0 。
 */
class Solution {
    /**
     * 方法一：滑动窗口 + 有序集合
     */
    public int longestSubarray1(int[] nums, int limit) {
        // 为了方便统计当前窗口内的最大值与最小值，使用平衡树来维护窗口内元素构成的有序集合
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        int left = 0, right = 0;
        int res = 0;
        // 枚举每一个位置作为右端点，找到其对应的最靠左的左端点，满足区间中最大值与最小值的差不超过 limit
        while (right < n) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 方法二：滑动窗口 + 单调队列
     */
    public int longestSubarray2(int[] nums, int limit) {
        // 由于仅需要统计当前窗口内的最大值与最小值，因此也可以分别使用两个单调队列解决本题
        // 使用一个单调递增的队列 queMin 维护最小值，一个单调递减的队列 queMax 维护最大值
        // 这样只需要计算两个队列的队首的差值，即可知道当前窗口是否满足条件
        Deque<Integer> queMin = new ArrayDeque<>();
        Deque<Integer> queMax = new ArrayDeque<>();
        int n = nums.length;
        int left = 0, right = 0;
        int res = 0;
        while (right < n) {
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                queMax.pollLast();
            }
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                if (nums[left] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestSubarray1(new int[]{8, 2, 4, 7}, 4));
        System.out.println(new Solution().longestSubarray2(new int[]{10, 1, 2, 4, 7, 2}, 5));
    }
}
