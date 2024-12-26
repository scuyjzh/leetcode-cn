package com.scuyjzh.leetcode.easy.No_0643_Maximum_Average_Subarray_I;

/**
 * 643. 子数组最大平均数 I
 *
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 *
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 */
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        // 由于题目限制了 k <= len，因此不用做特判
        int windowSum = 0;
        // 第 1 步：先求出第 1 个窗口的和
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        // 第 2 步：通过遍历求出除了第 1 个窗口的和
        int res = windowSum;
        // 循环不变量定义：[left..right) 是长度为 k 的窗口
        for (int right = k; right < len; right++) {
            // 加上一个数再减去一个数
            windowSum = windowSum + nums[right] - nums[right - k];
            res = Math.max(res, windowSum);
        }
        return (double) res / k;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }
}
