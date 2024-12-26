package com.scuyjzh.leetcode.medium.No_0018_4Sum;

import java.util.*;

/**
 * 18. 四数之和
 *
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找
 * 出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b],
 * nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重
 * 复）：
 *     • 0 <= a, b, c, d < n
 *     • a、b、c 和 d 互不相同
 *     • nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * 你可以按 任意顺序 返回答案 。
 */
class Solution {
    /**
     * 方法：排序 + 双指针
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }

        // 排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; ++i) {
            // 剪枝：跳过重复元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 剪枝：nums[i] 太小，跳过
            if (nums[i] + 3 * nums[nums.length - 1] < target) {
                continue;
            }
            // 剪枝：nums[i] 太大，直接结束循环
            if (nums[i] * 4 > target) {
                break;
            }
            for (int j = i + 1; j < nums.length - 2; ++j) {
                // 剪枝：跳过重复元素
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 剪枝：nums[j] 太小，跳过
                if (nums[i] + nums[j] + 2 * nums[nums.length - 1] < target) {
                    continue;
                }
                // 剪枝：nums[j] 太大，结束此轮循环
                if (nums[i] + nums[j] * 3 > target) {
                    break;
                }
                // 二分查找
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        // 如果和等于 target，则将枚举到的四个数加到答案中
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        ++left;
                        --right;
                        // 然后将左指针右移直到遇到不同的数
                        while (left < right && nums[left] == nums[left - 1]) {
                            // 跳过重复元素
                            ++left;
                        }
                        // 将右指针左移直到遇到不同的数
                        while (left < right && nums[right] == nums[right + 1]) {
                            // 跳过重复元素
                            --right;
                        }
                    } else if (sum < target) {
                        // 如果和小于 target，则将左指针右移一位
                        ++left;
                    } else {
                        // 如果和大于 target，则将右指针左移一位
                        --right;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(new Solution().fourSum(new int[]{2, 2, 2, 2, 2}, 8));
    }
}
