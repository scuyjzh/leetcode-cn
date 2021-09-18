package com.scuyjzh.leetcode.medium.No_0081_Search_in_Rotated_Sorted_Array_II;

/**
 * 81. 搜索旋转排序数组 II
 * <p>
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 */
class Solution {
    /**
     * 方法：二分查找
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。最坏情况下数组元素均相等且不为 target，需要访问所有位置才能得出结果。
     * 空间复杂度：O(1)。
     */
    public boolean search(int[] nums, int target) {
        /*
         * 前言：
         * 本篇题解基于「33. 搜索旋转排序数组的官方题解」。
         *
         * 思路：
         * 对于数组中有重复元素的情况，二分查找时可能会有 a[l] = a[mid] = a[r]，此时无法判断区间 [l,mid] 和区间
         * [mid+1,r] 哪个是有序的。
         * 例如 nums=[3,1,2,3,3,3,3]，target=2，首次二分时无法判断区间 [0,3] 和区间 [4,6] 哪个是有序的。
         * 对于这种情况，只能将当前二分区间的左边界加一，右边界减一，然后在新区间上继续二分查找。
         */
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                ++l;
                --r;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(new Solution().search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
    }
}