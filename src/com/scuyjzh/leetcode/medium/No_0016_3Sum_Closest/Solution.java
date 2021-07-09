package com.scuyjzh.leetcode.medium.No_0016_3Sum_Closest;

import java.util.*;

/**
 * 16. 最接近的三数之和
 * <p>
 * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 */
class Solution {
    /**
     * 方法一：排序 + 双指针
     * 时间复杂度：O(N^2)，其中 N 是数组 nums 的长度。我们首先需要 O(NlogN) 的时间对数组进行排序，随后在枚举的过程中，使用一重循环 O(N) 枚举 a，双指针 O(N) 枚举 b 和 c，故一共是 O(N^2)。
     * 空间复杂度：O(logN)。排序需要使用 O(logN) 的空间。然而我们修改了输入的数组 nums，在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了 nums 的副本并进行排序，此时空间复杂度为 O(N)O(N)。
     */
    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE, res = target;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 跳过重复元素
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    // 如果和小于 target，移动左指针
                    left++;
                    // 跳过重复元素
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else if (sum > target) {
                    // 如果和小于 target，移动右指针
                    right--;
                    // 跳过重复元素
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
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
        Solution solution = new Solution();
        System.out.println(solution.threeSumClosest(new int[]{0, 2, 1, -3}, 1));
    }
}
