package com.scuyjzh.leetcode.easy.No_0001_Two_Sum;

import java.util.*;

/**
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出
 * 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里
 * 不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 */
class Solution {
    /**
     * 方法一：暴力枚举
     *
     * • 时间复杂度：O(N^2)，其中 N 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     * • 空间复杂度：O(1)。
     */
    public int[] twoSum1(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (nums[j] + nums[i] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 方法二：哈希表
     *
     * • 时间复杂度：O(N)，其中 N 是数组中的元素数量。对于每一个元素 x，可以 O(1) 地寻找
     *   target - x。
     * • 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销。
     */
    public int[] twoSum2(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < len; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum1(new int[]{3, 2, 4}, 5)));
        System.out.println(Arrays.toString(new Solution().twoSum2(new int[]{3, 2, 4}, 7)));
    }
}
