package com.scuyjzh.leetcode.easy.No_0977_Squares_of_a_Sorted_Array;

import java.util.*;

/**
 * 977. 有序数组的平方
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成
 * 的新数组，要求也按 非递减顺序 排序。
 */
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        // 使用两个指针分别指向位置 0 和 n-1
        int i = 0, j = n - 1;
        int pos = n - 1;
        while (i <= j) {
            // 每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().sortedSquares(new int[]{-4, -1, 0, 3, 10})));
    }
}
