package com.scuyjzh.leetcode.medium.No_0162_Find_Peak_Element;

/**
 * 162. 寻找峰值
 * <p>
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 */
class Solution {
    /**
     * 方法：二分查找 + 贪心
     * 时间复杂度：O(logN)，其中 N 是数组 nums 的长度。
     * 空间复杂度：O(1)。
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                // 由于 nums[-1] = nums[n] = -∞，因此沿着递增方向一定可以寻找到一个峰值
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(new Solution().findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
    }
}