package com.scuyjzh.leetcode.easy.No_0283_Move_Zeroes;

import java.util.*;

/**
 * 283. 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
class Solution {
    /**
     * 方法：双指针
     * 时间复杂度：O(N)，其中 N 为序列长度。每个位置至多被遍历两次。
     * 空间复杂度：O(1)。只需要常数的空间存放若干变量。
     */
    public void moveZeroes(int[] nums) {
        /*
         * 思路及解法：
         * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
         * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
         *
         * 注意到以下性质：
         *   1.左指针左边均为非零数；
         *   2.右指针左边直到左指针处均为零。
         * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
         */
        int n = nums.length;
        int left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left++, right);
            }
            right++;
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}