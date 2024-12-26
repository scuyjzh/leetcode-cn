package com.scuyjzh.leetcode.easy.No_0026_Remove_Duplicates_from_Sorted_Array;

/**
 * 26. 删除有序数组中的重复项
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只
 * 出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额
 * 外空间的条件下完成。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        // 如果数组 nums 的长度为 0，则数组不包含任何元素，因此返回 0
        if (n < 2) {
            return n;
        }
        // 定义两个指针 fast 和 slow 分别为快指针和慢指针：
        // 快指针表示遍历数组到达的下标位置，
        // 慢指针表示下一个不同元素要填入的下标位置，
        // 初始时两个指针都指向下标 1
        int fast = 1, slow = 1;
        // 当数组 nums 的长度大于 0 时，数组中至少包含一个元素；
        // 因此 nums[0] 保持原状即可，从下标 1 开始删除重复元素
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicates(new int[]{1, 1, 2}));
        System.out.println(new Solution().removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }
}
