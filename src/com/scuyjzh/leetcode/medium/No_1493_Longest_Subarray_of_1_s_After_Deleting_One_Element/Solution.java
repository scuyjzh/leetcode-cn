package com.scuyjzh.leetcode.medium.No_1493_Longest_Subarray_of_1_s_After_Deleting_One_Element;

/**
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 *
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 *
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 *
 * 如果不存在这样的子数组，请返回 0 。
 */
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 双指针 left 和 right 表示滑动窗口的左右边界，cnt 统计当前窗口中 0 的个数
        int left = 0, right = 0, cnt = 0;
        while (right < n) {
            // 只要滑动窗口中 0 的个数小于等于 1 个，那么就一直右移 right 指针扩充右边界
            if (nums[right] == 0) {
                ++cnt;
            }
            // 只要滑动窗口中 0 的个数大于 1，那么就一直右移 left 指针缩小左边界，直到 0 的个数小于等于 1
            while (cnt > 1) {
                if (nums[left] == 0) {
                    --cnt;
                }
                ++left;
            }
            // 此时滑动窗口中 0 的个数小于等于 1，更新窗口长度（即返回值）
            res = Math.max(res, right - left + 1);
            ++right;
        }
        // 由于需要删除一个元素（0 或 1），因此需要返回窗口长度减去 1
        return res - 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
    }
}
