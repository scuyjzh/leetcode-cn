package com.scuyjzh.leetcode.medium._015_3Sum;

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (nums[i] > 0) {
                // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 去重
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (0 == sum) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        // 去重
                        ++left;
                    }
                    ++left;
                    while (left < right && nums[right] == nums[right - 1]) {
                        // 去重
                        --right;
                    }
                    --right;
                } else if (sum < 0) {
                    ++left;
                } else {
                    --right;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
