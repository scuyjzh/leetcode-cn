package com.scuyjzh.leetcode.medium.No_0795_Number_of_Subarrays_with_Bounded_Maximum;

/**
 * 795. 区间子数组个数
 *
 * 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连
 * 续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足
 * 条件的子数组的个数。
 *
 * 生成的测试用例保证结果符合 32-bit 整数范围。
 */
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        // 最大元素在区间 [L..R] 里的连续子数组的个数，可以看成是一个「区间」的问题，处理区间的问题可以按照「前缀和」的思路。
        // 把原问题转化成为「最大元素小于等于 R 的子数组的个数」与「最大元素小于等于 L-1 的子数组的个数」的差。
        return lessEqualsThan(nums, right) - lessEqualsThan(nums, left - 1);
    }

    /**
     * 最大元素不超过 K 的子数组的个数
     */
    private int lessEqualsThan(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        int left = 0, right = 0;
        // 循环不变量：nums[left..right] 里的所有元素都小于等于 k
        while (right < n) {
            if (nums[right] > k) {
                left = right + 1;
            }
            res += right - left + 1;
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
    }
}
