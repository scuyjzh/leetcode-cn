package com.scuyjzh.leetcode.medium.No_0324_Wiggle_Sort_II;

import java.util.*;

/**
 * 324. 摆动排序 II
 * <p>
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * 提示：
 * • 1 <= nums.length <= 5 * 104
 * • 0 <= nums[i] <= 5000
 * • 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
class Solution {
    /**
     * 方法：桶排序
     * 时间复杂度：O(N)。
     * 空间复杂度：O(1)。
     */
    public void wiggleSort(int[] nums) {
        // 0 <= nums[i] <= 5000
        int[] bucket = new int[5001];
        for (int num : nums) {
            bucket[num]++;
        }
        int len = nums.length;
        // 穿插数字时的上界
        int small, big;
        // 总长度为奇数时，“小 大 小 大 小”，边界左右都为较小的数；
        // 总长度为偶数时，“小 大 小 大”，边界左为较小的数，边界右为较大的数
        if ((len & 1) == 1) {
            small = len - 1;
            big = len - 2;
        } else {
            small = len - 2;
            big = len - 1;
        }
        // 从后往前，将桶中数字穿插到数组中，后界为 j
        int j = 5000;
        // 先将桶中的较大的数字穿插放在数组中
        for (int i = 1; i <= big; i += 2) {
            while (bucket[j] == 0) {
                // 找到值不为 0 的桶
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
        // 再将桶中的较小的数字穿插放在数组中
        for (int i = 0; i <= small; i += 2) {
            while (bucket[j] == 0) {
                // 找到值不为0的桶
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
    }

    public static void main(String[] args) {
        int[] nums;

        nums = new int[]{1, 5, 1, 1, 6, 4};
        new Solution().wiggleSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 3, 2, 2, 3, 1};
        new Solution().wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}