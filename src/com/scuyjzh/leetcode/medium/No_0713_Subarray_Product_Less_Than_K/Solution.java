package com.scuyjzh.leetcode.medium.No_0713_Subarray_Product_Less_Than_K;

/**
 * 713. 乘积小于K的子数组
 *
 * 给定一个正整数数组 nums和整数 k 。
 *
 * 请找出该数组内乘积小于 k 的连续的子数组的个数。
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int n = nums.length;
        int res = 0;
        // [left, right] 中的元素乘积
        int prod = 1;
        int left = 0, right = 0;
        // 循环不变量：[left, right] 中的元素乘积小于 k
        while (right < n) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            res += right - left + 1;
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }
}
