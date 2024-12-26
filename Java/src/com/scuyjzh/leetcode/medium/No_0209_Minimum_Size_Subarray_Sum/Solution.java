package com.scuyjzh.leetcode.medium.No_0209_Minimum_Size_Subarray_Sum;

/**
 * 209. 长度最小的子数组
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [nums_l,
 * nums_{l+1}, ..., nums_{r-1}, nums_r] ，并返回其长度。如果不存在符合条件的
 * 子数组，返回 0 。
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        // 定义两个指针 left 和 right 分别表示子数组（滑动窗口）的左边界和右边界，
        // 维护变量 sum 存储滑动窗口中的元素和（即从 nums[left] 到 nums[right] 的元素和）。
        // 初始状态下，left 和 right 都指向下标 0，sum 的值为 0。
        int left = 0, right = 0, sum = 0;
        while (right < n) {
            // 每一轮迭代，将 nums[right] 加到 sum
            sum += nums[right];
            // 如果 sum ≥ target
            while (sum >= target) {
                // 则更新滑动窗口的最小长度（此时滑动窗口的长度是 right−left+1）
                ans = Math.min(ans, right - left + 1);
                // 然后将 nums[left] 从 sum 中减去
                sum -= nums[left];
                // 并将 left 右移，直到 sum < s，在此过程中同样更新滑动窗口的最小长度
                ++left;
            }
            // 在每一轮迭代的最后，将 right 右移
            ++right;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
