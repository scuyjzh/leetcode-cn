package com.scuyjzh.leetcode.medium.No_1695_Maximum_Erasure_Value;

import java.util.*;

/**
 * 1695. 删除子数组的最大得分
 *
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数
 * 组。删除子数组的 得分 就是子数组各元素之 和 。
 *
 * 返回 只删除一个 子数组可获得的 最大得分 。
 *
 * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于
 * a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 */
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        // 哈希集合，记录每个数字是否出现过，来判断 是否有重复的数字
        // 在左指针向右移动的时候，从哈希集合中移除一个数字，在右指针向右移动的时候，往哈希集合中添加一个数字
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        int max = 0, sum = 0;
        // 左右指针指向滑动窗口的左右边界，初始都指向下标 0
        int left = 0, right = 0;
        while (right < n) {
            // 若当前右指针指向的元素在 Set 集合中存在，则右移左指针缩小滑动窗口，
            // 直到集合中不包含右指针指向的元素，同时更新当前窗口中的元素和。
            while (set.contains(nums[right])) {
                sum -= nums[left];
                set.remove(nums[left]);
                ++left;
            }
            // 记录结果
            sum += nums[right];
            max = Math.max(sum, max);
            // 不断地向右移动右指针
            set.add(nums[right]);
            ++right;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6}));
        System.out.println(new Solution().maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}));
    }
}
