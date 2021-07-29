package com.scuyjzh.leetcode.easy.No_0035_Search_Insert_Position;

/**
 * 35. 搜索插入位置
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
class Solution {
    /**
     * 方法一：二分查找
     * 时间复杂度：O(log n)，其中 n 为数组的长度。二分查找所需的时间复杂度为 O(log n)。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     */
    public int searchInsert(int[] nums, int target) {
        /*
         * 思路：
         * 考虑这个插入的位置 pos，它成立的条件为：nums[pos−1] < target ≤ nums[pos]
         * 由于如果存在这个目标值，返回的索引也是 pos，因此可以将两个条件合并得出最后的目标：「在一个有序数组中找第一个大于等于 target 的下标」。
         */
        int len = nums.length;
        // ans 初值设置为数组长度可以省略边界条件的判断，因为存在一种情况是 target 大于数组中的所有数，此时需要插入到数组长度的位置
        int left = 0, right = len - 1, ans = len;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.searchInsert(new int[]{5, 7, 7, 8, 8, 10}, 7));
    }
}
