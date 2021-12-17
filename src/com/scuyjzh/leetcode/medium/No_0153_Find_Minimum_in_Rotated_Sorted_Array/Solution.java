package com.scuyjzh.leetcode.medium.No_0153_Find_Minimum_in_Rotated_Sorted_Array;

/**
 * 153. 寻找旋转排序数组中的最小值
 *
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转
 * 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能
 * 得到：
 *   • 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 *   • 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 *
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，
 * 并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 */
class Solution {
    /**
     * 方法：二分查找
     *
     * • 时间复杂度：O(log n)，其中 n 是数组 nums 的长度。在二分查找的过程中，每一步
     *   会忽略一半的区间，因此时间复杂度为 O(log n)。
     * • 空间复杂度：O(1)。
     */
    public int findMin(int[] nums) {
        /*
         * 考虑数组中的最后一个元素 x：在最小值右侧的元素（不包括最后一个元素本身），它们的值一定都严
         * 格小于 x；而在最小值左侧的元素，它们的值一定都严格大于 x。因此，可以根据这一条性质，通过二
         * 分查找的方法找出最小值。
         *
         * 在二分查找的每一步中，左边界为 left，右边界为 right，区间的中点为 mid，最小值就在该区间内。将
         * 中轴元素 nums[mid] 与右边界元素 nums[right] 进行比较，可能会有以下的三种情况：
         *   1.第一种情况是 nums[mid] < nums[right]。这说明 nums[mid] 是最小值右侧的元素，因此
         *     可以忽略二分查找区间的右半部分。
         *   2.第二种情况是 nums[mid] > nums[right]。这说明 nums[mid] 是最小值左侧的元素，因此
         *     可以忽略二分查找区间的左半部分。
         *   3.由于数组不包含重复元素，并且只要当前的区间长度不为 1，mid 就不会与 right 重合；而如果当前的区间
         *     长度为 1，这说明已经可以结束二分查找了。因此不会存在 nums[mid] = nums[right] 的情况。
         *
         * 当二分查找结束时，就得到了最小值所在的位置。
         */
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(new Solution().findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(new Solution().findMin(new int[]{11, 13, 15, 17}));
    }
}
