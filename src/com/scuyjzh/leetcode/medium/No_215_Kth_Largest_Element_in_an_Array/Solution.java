package com.scuyjzh.leetcode.medium.No_215_Kth_Largest_Element_in_an_Array;

class Solution {
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }

    private static int quickSelect(int[] nums, int lo, int hi, int k) {
        int i = lo, j = hi, pivot = nums[hi];
        while (i < j) {
            if (nums[i++] > pivot) {
                swap(nums, --i, --j);
            }
        }
        swap(nums, i, hi);

        if (i == k - 1) {
            return nums[i];
        } else if (i > k - 1) {
            return quickSelect(nums, lo, i - 1, k);
        } else {
            return quickSelect(nums, i + 1, hi, k);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 7, 2, 9, 1, 6, 4};
        System.out.println(findKthLargest(nums, 3));
    }
}
