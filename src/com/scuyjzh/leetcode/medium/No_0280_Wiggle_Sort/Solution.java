package com.scuyjzh.leetcode.medium.No_0280_Wiggle_Sort;

import java.util.*;

/**
 * 280. 摆动排序
 * <p>
 * 给你一个无序的数组 nums, 将该数字 原地 重排后使得 nums[0] <= nums[1] >= nums[2] <= nums[3]...。
 */
class Solution {
    /**
     * 方法一：排序
     * 时间复杂度：O(NlogN)。算法的时间开销由排序过程决定。
     * 空间复杂度：O(logN)。排序需要使用 O(logN) 的空间。
     */
    public void wiggleSort1(int[] nums) {
        // 一个显而易见的解法是先将数组排序
        Arrays.sort(nums);
        // 再从第二个元素开始逐对交换元素的位置
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 方法二：一次遍历
     * 时间复杂度：O(N)。在最坏的情况下，最多交换了 N/2 次。例如输入为 [2,1,3,1,4,1]。
     * 空间复杂度：O(1)。
     */
    public void wiggleSort2(int[] nums) {
        boolean less = true;
        for (int i = 0; i < nums.length - 1; ++i) {
            // 在奇数位元素小于上一个数则互换
            if (less) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            // 在偶数位元素大于上一个则互换
            else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            // less 的布尔值事实上取决于索引的奇偶性
            less = !less;
        }
    }

    public static void main(String[] args) {
        int[] nums;

        nums = new int[]{3, 5, 2, 1, 6, 4};
        new Solution().wiggleSort1(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 5, 2, 1, 6, 4};
        new Solution().wiggleSort2(nums);
        System.out.println(Arrays.toString(nums));
    }
}