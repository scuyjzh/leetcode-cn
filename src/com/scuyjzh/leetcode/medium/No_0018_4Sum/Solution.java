package com.scuyjzh.leetcode.medium.No_0018_4Sum;

import java.util.*;

class Solution {
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 去重
                continue;
            }
            if (nums[i] + 3 * nums[nums.length - 1] < target) {
                // nums[i]太小
                continue;
            }
            if (nums[i] * 4 > target) {
                // nums[i]太大
                break;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    // 去重
                    continue;
                }
                if (nums[i] + nums[j] + 2 * nums[nums.length - 1] < target) {
                    // nums[i] is too small
                    continue;
                }
                if (nums[i] + nums[j] * 3 > target) {
                    // nums[i] is too large
                    break;
                }
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            // 去重
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            // 去重
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, 0, 4, target);
    }

    private List<List<Integer>> kSum(int[] nums, int start, int k, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (2 == k) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> path = new LinkedList<Integer>();
                    path.add(nums[left]);
                    path.add(nums[right]);
                    res.add(path);
                    while (left < right && nums[left] == nums[left + 1]) {
                        // 去重
                        left++;
                    }
                    left++;
                    while (left < right && nums[right] == nums[right - 1]) {
                        // 去重
                        right--;
                    }
                    right--;
                } else if (sum < target) {
                    // 如果和小于 target，移动左指针
                    left++;
                } else {
                    // 如果和小于 target，移动右指针
                    right--;
                }
            }
        } else {
            for (int i = start; i < nums.length - (k - 1); i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    // 去重
                    continue;
                }
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for (List<Integer> t : temp) {
                    t.add(0, nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fourSum1(new int[]{-1, 0, -5, -2, -2, -4, 0, 1, -2}, -9));
        System.out.println(solution.fourSum2(new int[]{-1, 0, -5, -2, -2, -4, 0, 1, -2}, -9));
    }
}
