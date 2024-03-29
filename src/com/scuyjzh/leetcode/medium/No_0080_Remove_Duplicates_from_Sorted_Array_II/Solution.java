package com.scuyjzh.leetcode.medium.No_0080_Remove_Duplicates_from_Sorted_Array_II;

/**
 * 80. 删除有序数组中的重复项 II
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最
 * 多出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额
 * 外空间的条件下完成。
 */
class Solution {
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        // 数组的前两个数必然可以被保留，因此对于长度不超过 2 的数组，无需进行任何处理
        if (n <= 2) {
            return n;
        }
        // 对于长度超过 2 的数组，直接将双指针的初始值设为 2 即可
        // 其中慢指针表示处理出的数组的长度，快指针表示已经检查过的数组的长度
        // 即 nums[fast] 表示待检查的第一个元素，nums[slow−1] 为上一个应该被保留的元素所移动到的指定位置
        int slow = 2;
        int fast = 2;
        // 遍历数组检查每一个元素是否应该被保留，如果应该被保留，就将其移动到指定位置
        while (fast < n) {
            // 当且仅当 nums[slow−2]=nums[fast] 时，当前待检查元素 nums[fast] 不应该被保留
            // （因为此时必然有 nums[slow−2]=nums[slow−1]=nums[fast]）
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        // 最后，slow 即为处理好的数组的长度
        return slow;
    }

    public int removeDuplicates2(int[] nums) {
        // 「删除有序数组重复项」的通解（k=2）
        return process(nums, 2);
    }

    private int process(int[] nums, int k) {
        int i = 0;
        for (int num : nums) {
            if (i < k || nums[i - k] != num) {
                nums[i] = num;
                ++i;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicates1(new int[]{1, 1, 1, 2, 2, 3}));
        System.out.println(new Solution().removeDuplicates2(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
    }
}
