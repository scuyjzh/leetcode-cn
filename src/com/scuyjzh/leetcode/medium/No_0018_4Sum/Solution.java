package com.scuyjzh.leetcode.medium.No_0018_4Sum;

import java.util.*;

/**
 * 18. 四数之和
 * <p>
 * 给定一个包含n个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 */
class Solution {
    /**
     * 方法一：排序 + 双指针
     * 时间复杂度：O(N^3)，其中 N 是数组的长度。排序的时间复杂度是 O(NlogN)，枚举四元组的时间复杂度是 O(N^3)，因此总时间复杂度为 O(N^3+NlogN)=O(N^3)。
     * 空间复杂度：O(logN)，其中 N 是数组的长度。空间复杂度主要取决于排序额外使用的空间。此外排序修改了输入数组 nums，实际情况中不一定允许，因此也可以看成使用了一个额外的数组存储了数组 nums 的副本并排序，空间复杂度为 O(N)。
     */
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; ++i) {
            // 剪枝
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 跳过重复元素
                continue;
            }
            if (nums[i] + 3 * nums[nums.length - 1] < target) {
                // 说明 nums[i] 太小
                continue;
            }
            if (nums[i] * 4 > target) {
                // 说明 nums[i] 太大，直接结束循环
                break;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                // 剪枝
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    // 跳过重复元素
                    continue;
                }
                if (nums[i] + nums[j] + 2 * nums[nums.length - 1] < target) {
                    // nums[i] 太小
                    continue;
                }
                if (nums[i] + nums[j] * 3 > target) {
                    // nums[i] 太大，结束此轮循环
                    break;
                }
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            // 跳过重复元素
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            // 跳过重复元素
                            right--;
                        }
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

    /**
     * 方法二：递归
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    private List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if (start == nums.length || nums[start] * k > target || nums[nums.length - 1] * k < target) {
            return res;
        }
        // twoSum
        if (2 == k) {
            return twoSum(nums, target, start);
        }
        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> list : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Collections.singletonList(nums[i])));
                    res.get(res.size() - 1).addAll(list);
                }
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new LinkedList<>();
        int left = start, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                res.add(Arrays.asList(nums[left], nums[right]));
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]) {
                    // 去重
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    // 去重
                    right--;
                }
            } else if (sum < target) {
                // 如果和小于 target，移动左指针
                left++;
            } else {
                // 如果和小于 target，移动右指针
                right--;
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
