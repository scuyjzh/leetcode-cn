package com.scuyjzh.leetcode.easy.No_0026_Remove_Duplicates_from_Sorted_Array;

/**
 * 26. 删除有序数组中的重复项
 * <p>
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 */
class Solution {
    /**
     * 方法一：双指针
     * 时间复杂度：O(n)，其中 n 是数组的长度。快指针和慢指针最多各移动 n 次。
     * 空间复杂度：O(1)。只需要使用常数的额外空间。
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        // 如果数组 nums 的长度为 0，则数组不包含任何元素，因此返回 0
        if (n == 0) {
            return 0;
        }
        // 定义两个指针 fast 和 slow 分别为快指针和慢指针
        // 快指针表示遍历数组到达的下标位置，慢指针表示下一个不同元素要填入的下标位置，初始时两个指针都指向下标 1
        int fast = 1, slow = 1;
        // 当数组 nums 的长度大于 0 时，数组中至少包含一个元素；因此 nums[0] 保持原状即可，从下标 1 开始删除重复元素
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
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates(new int[]{1, 1, 2}));
        System.out.println(solution.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }
}
