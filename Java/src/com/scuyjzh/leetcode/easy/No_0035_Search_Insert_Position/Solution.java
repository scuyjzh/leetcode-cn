package com.scuyjzh.leetcode.easy.No_0035_Search_Insert_Position;

/**
 * 35. 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如
 * 果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
class Solution {
    /**
     * 方法：二分查找
     *
     * • 时间复杂度：O(log n)，其中 n 为数组的长度。二分查找所需的时间复杂度为 O(log n)。
     * • 空间复杂度：O(1)。只需要常数空间存放若干变量。
     */
    public int searchInsert(int[] nums, int target) {
        /*
         * 假设题意是叫你在排序数组中寻找是否存在一个目标值，那么训练有素的读者肯定立马就能想到利用二分法
         * 在 O(log n) 的时间内找到是否存在目标值。但这题还多了个额外的条件，即如果不存在数组中的时候需要返
         * 回按顺序插入的位置，那还能用二分法么？答案是可以的，只需要稍作修改即可。
         *
         * 考虑这个插入的位置 pos，它成立的条件为：
         *                      nums[pos−1] < target ≤ nums[pos]
         * 其中 nums 代表排序数组。由于如果存在这个目标值，返回的索引也是 pos，因此可以将两个条件
         * 合并得出最后的目标：「在一个有序数组中找第一个大于等于 target 的下标」。
         *
         * 问题转化到这里，直接套用二分法即可，即不断用二分法逼近查找第一个大于等于 target 的下标。下文给出
         * 的代码是笔者习惯的二分写法，ans 初值设置为数组长度可以省略边界条件的判断，因为存在一种情况是
         * target 大于数组中的所有数，此时需要插入到数组长度的位置。
         */
        int len = nums.length;
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
        System.out.println(new Solution().searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(new Solution().searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(new Solution().searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(new Solution().searchInsert(new int[]{1, 3, 5, 6}, 0));
        System.out.println(new Solution().searchInsert(new int[]{1}, 0));
    }
}
