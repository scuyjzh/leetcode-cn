package com.scuyjzh.leetcode.hard.No_0719_Find_K_th_Smallest_Pair_Distance;

import java.util.*;

/**
 * 719. 找出第 k 小的距离对
 *
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。
 * 一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 */
class Solution {
    /**
     * 方法：二分查找 + 双指针
     */
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[n - 1] - nums[0];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (getCount(nums, mid) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int getCount(int[] nums, int dis) {
        int n = nums.length;
        int cnt = 0;
        int left = 0, right = 0;
        while (right < n) {
            while (nums[right] - nums[left] > dis) {
                ++left;
            }
            cnt += right - left;
            ++right;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().smallestDistancePair(new int[]{1, 3, 1}, 1));
    }
}
