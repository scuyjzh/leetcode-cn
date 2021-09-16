package com.scuyjzh.leetcode.easy.No_0163_Missing_Ranges;

import java.util.*;

/**
 * 163. 缺失的区间
 * <p>
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 */
class Solution {
    /**
     * 方法：一次遍历
     * 时间复杂度：O(N)，其中 N 为数组 nums 的长度。
     * 空间复杂度：O(1)。除了用于输出的空间外，额外使用的空间为常数。
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        // 特判
        if (n == 0) {
            ans.add(helper(lower - 1, upper + 1));
            return ans;
        }
        // 从数组的位置 0 出发，向右遍历。每次遇到相邻元素之间的差值大于 1 时，就找到了一个缺失区间
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] + 1 != nums[i + 1]) {
                ans.add(helper(nums[i], nums[i + 1]));
            }
        }
        // 检查 lower 到 nums[0] 以及 nums[n -1] 到 upper 的区间
        if (lower < nums[0]) {
            ans.add(helper(lower - 1, nums[0]));
        }
        if (nums[n - 1] < upper) {
            ans.add(helper(nums[n - 1], upper + 1));
        }
        return ans;
    }

    private String helper(int left, int right) {
        StringBuilder builder = new StringBuilder();
        // 如果 left 与 right 中间仅缺失一个数，直接将该数添加到答案
        if (left + 1 == right - 1) {
            builder.append(left + 1);
        }
        // 否则说明缺失一组数，将字符串 start->end 添加到答案
        else {
            builder.append(left + 1).append("->").append(right - 1);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
    }
}