package com.scuyjzh.leetcode.medium.No_0016_3Sum_Closest;

import java.util.*;

/**
 * 16. 最接近的三数之和
 *
 * 给你一个长度为 n 的整数数组nums和 一个目标值target。请你从
 * nums 中选出三个整数，使它们的和与target最接近。
 *
 * 返回这三个数的和。
 *
 * 假定每组输入只存在恰好一个解。
 */
class Solution {
    /**
     * 方法：排序 + 双指针
     */
    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE, res = target;
        for (int i = 0; i < nums.length - 2; ++i) {
            // 跳过重复元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    // 如果和小于 target，移动左指针
                    ++left;
                    // 跳过重复元素
                    while (left < right && nums[left] == nums[left - 1]) {
                        ++left;
                    }
                } else if (sum > target) {
                    // 如果和小于 target，移动右指针
                    --right;
                    // 跳过重复元素
                    while (left < right && nums[right] == nums[right + 1]) {
                        --right;
                    }
                } else {
                    // 如果和为 target，直接返回答案
                    return sum;
                }
                if (Math.abs(sum - target) < diff) {
                    // 根据差值的绝对值来更新答案
                    diff = Math.abs(sum - target);
                    res = sum;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(new Solution().threeSumClosest(new int[]{0, 0, 0}, 1));
    }
}
