package com.scuyjzh.leetcode.medium.No_016_3Sum_Closest;

import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE, res = target;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 去重
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    // 如果和小于 target，移动左指针
                    while (left < right && nums[left] == nums[left + 1]) {
                        // 去重
                        ++left;
                    }
                    ++left;
                } else if (sum > target) {
                    // 如果和小于 target，移动右指针
                    while (left < right && nums[right] == nums[right - 1]) {
                        // 去重
                        --right;
                    }
                    --right;
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
