package com.scuyjzh.leetcode.medium.No_1658_Minimum_Operations_to_Reduce_X_to_Zero;

import java.util.Arrays;

/**
 * 1658. 将 x 减到 0 的最小操作数
 *
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组
 * nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需
 * 要 修改 数组以供接下来的操作使用。
 *
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 */
class Solution {
    public int minOperations(int[] nums, int x) {
        // 相当于求和为 sum - x 的最长连续子数组
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum - x;
        if (target < 0) {
            return -1;
        }

        sum = 0;
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        // 维护 [left..right] 窗口中所有元素的和，并与 target 比较
        while (right < len) {
            sum += nums[right];
            while (sum > target) {
                sum -= nums[left];
                ++left;
            }
            if (sum == target) {
                ans = Math.min(ans, len - (right - left + 1));
            }
            ++right;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minOperations(new int[]{1, 1, 4, 2, 3}, 5));
    }
}
