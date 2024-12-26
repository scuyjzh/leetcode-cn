package com.scuyjzh.leetcode.medium.No_0259_3Sum_Smaller;

import java.util.*;

/**
 * 259. 较小的三数之和
 *
 * 给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条
 * 件nums[i] + nums[j] + nums[k] < target成立的三元组 i, j, k个数
 * （0 <= i < j < k < n）。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public int threeSumSmaller(int[] nums, int target) {
        /*
         * 首先考虑更简单的情况。题目中要求的是三元组 (i,j,k)，可以首先尝试一个简单的版本，求出二元组 (i,j)。
         *
         * 首先对数组进行排序，例如 nums=[3,5,2,8,1] 排序后变成 [1,2,3,5,8]。target 的值为 7。
         *     [1, 2, 3, 5, 8]
         *      ↑           ↑
         *     left       right
         *
         * 用两个指针 left 和 right 分别指向数组中的第一个和最后一个元素，它们的和为 1+8=9>target，这
         * 说明 right 不能出现在二元组中（因为 left 只向左移动，如果此时二者的和已经大于 target，那么在 left 移
         * 动的过程中，二者的和就不可能小于 target 了），因此需要将 right 向左移动一位。
         *     [1, 2, 3, 5, 8]
         *      ↑        ↑
         *     left    right
         *
         * 现在二者的和为 1+5=6<target，那么对于当前的 left，应当有 right−left=3 对二元组满足要求，它们
         * 分别为 (1,2),(1,3),(1,5)。在这之后，将 left 向右移动一位。
         */
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                ++left;
            } else {
                --right;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().threeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
    }
}
