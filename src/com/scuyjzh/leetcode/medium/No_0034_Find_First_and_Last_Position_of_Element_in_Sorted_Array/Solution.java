package com.scuyjzh.leetcode.medium.No_0034_Find_First_and_Last_Position_of_Element_in_Sorted_Array;

import java.util.*;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 进阶：你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 */
class Solution {
    /**
     * 方法：二分查找
     * 时间复杂度：O(log n)，其中 n 为数组的长度。二分查找的时间复杂度为 O(log n)，一共会执行两次，因此总时间复杂度为 O(log n)。
     * 空间复杂度：O(1)。只需要常数空间存放若干变量。
     */
    public int[] searchRange(int[] nums, int target) {
        /*
         * 思路：
         * 由于数组已经排序，因此整个数组是单调递增的，可以利用二分法来加速查找的过程。
         * 考虑 target 开始和结束位置，其实要找的就是数组中「第一个等于 target 的位置」（记为 leftIdx）和「第一个大于 target 的位置减一」（记为 rightIdx）。
         * 二分查找中，寻找 leftIdx 即为在数组中寻找第一个大于等于 target 的下标，寻找 rightIdx 即为在数组中寻找第一个大于 target 的下标，然后将下标减一。
         * 最后，因为 target 可能不存在数组中，因此需要重新校验得到的两个下标 leftIdx 和 rightIdx。
         */
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        // 校验
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    /**
     * @param nums   查找数组
     * @param target 查找目标值
     * @param lower  标记位，如果值为 true，则查找第一个大于等于 target 的下标，否则查找第一个大于 target 的下标
     * @return target 的位置
     */
    private int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
    }
}
