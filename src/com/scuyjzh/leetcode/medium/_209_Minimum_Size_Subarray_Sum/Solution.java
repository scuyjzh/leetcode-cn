package com.scuyjzh.leetcode.medium._209_Minimum_Size_Subarray_Sum;

/**
 * @author scuyjzh
 * @data 2020/6/28 2:01
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int start = 0, end = 0, sum = 0, min = Integer.MAX_VALUE;
        while (end < nums.length) {
            // 维护变量sum存储子数组中的元素和（即从nums[start] 到 nums[end]的元素和）
            sum += nums[end];
            while (sum >= s) {
                // 将nums[end]加到sum，如果sum ≥ s，则更新子数组的最小长度
                min = Math.min(min, end - start + 1);
                // 然后将nums[start]从sum中减去并将start右移
                // 直到sum < s，在此过程中同样更新子数组的最小长度
                sum -= nums[start++];
            }
            // 在每一轮迭代的最后，将end右移
            end++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
