package com.scuyjzh.leetcode.medium.No_0487_Max_Consecutive_Ones_II;

/**
 * 487. 最大连续1的个数 II
 *
 * 给定一个二进制数组，你可以最多将 1 个 0 翻转为 1，找出其中最大连续 1 的个数。
 *
 * 进阶：
 * 如果输入的数字是作为 无限流 逐个输入如何处理？
 * 换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
 */
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 双指针 left 和 right 表示滑动窗口的左右边界，cnt 统计当前窗口中 0 的个数
        int left = 0, right = 0, cnt = 0;
        while (right < n) {
            // 只要滑动窗口中 0 个数小于等于 1 个，那么就一直右移 right 指针扩充右边界
            if (nums[right] == 0) {
                ++cnt;
            }
            // 只要滑动窗口中 0 个数大于 1，那么就一直右移 left 指针缩小左边界，直到 0 的个数小于等于 1
            while (cnt > 1) {
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
        System.out.println(new Solution().findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}));
    }
}
