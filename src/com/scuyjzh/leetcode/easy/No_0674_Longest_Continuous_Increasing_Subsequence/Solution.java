package com.scuyjzh.leetcode.easy.No_0674_Longest_Continuous_Increasing_Subsequence;

/**
 * 674. 最长连续递增序列
 *
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每
 * 个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l],
 * nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 */
class Solution {
    public int findLengthOfLCIS1(int[] nums) {
        int len = nums.length;
        int res = 0;
        int i = 0;
        int j = 0;
        // 循环不变量 [i..j) 严格单调递增
        while (j < len) {
            if (j > 0 && nums[j - 1] >= nums[j]) {
                i = j;
            }
            ++j;
            res = Math.max(res, j - i);
        }
        return res;
    }

    public int findLengthOfLCIS2(int[] nums) {
        int len = nums.length;
        int res = 0;
        int i = 0;
        int j = 0;
        // 循环不变量 [i..j] 严格单调递增
        while (j < len) {
            if (j > 0 && nums[j - 1] >= nums[j]) {
                i = j;
            }
            res = Math.max(res, j - i + 1);
            ++j;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findLengthOfLCIS1(new int[]{1, 3, 5, 4, 7}));
        System.out.println(new Solution().findLengthOfLCIS2(new int[]{2, 2, 2, 2, 2}));
    }
}
