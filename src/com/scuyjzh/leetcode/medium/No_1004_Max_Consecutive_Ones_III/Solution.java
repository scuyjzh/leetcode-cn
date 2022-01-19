package com.scuyjzh.leetcode.medium.No_1004_Max_Consecutive_Ones_III;

/**
 * 1004. 最大连续1的个数 III
 *
 * 给定一个由若干 0 和 1 组成的数组 A，最多可以将 K 个值从 0 变成 1 。
 *
 * 返回仅包含 1 的最长（连续）子数组的长度。
 */
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        // 双指针 left 和 right 表示滑动窗口的左右边界，cnt 统计当前窗口中 0 的个数
        int left = 0, right = 0, cnt = 0;
        while (right < n) {
            // 只要滑动窗口中 0 个数小于等于 k 个，那么就一直右移 right 指针扩充右边界
            if (nums[right] == 0) {
                ++cnt;
            }
            // 只要滑动窗口中 0 个数大于 k，那么就一直右移 left 指针缩小左边界，直到 0 的个数小于等于 k
            while (cnt > k) {
                if (nums[left] == 0) {
                    --cnt;
                }
                ++left;
            }
            // 在此过程中更新窗口大小（即返回值）
            res = Math.max(res, right - left + 1);
            // 不断右移右指针
            ++right;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }
}
