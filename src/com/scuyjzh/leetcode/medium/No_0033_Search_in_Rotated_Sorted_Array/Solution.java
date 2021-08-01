package com.scuyjzh.leetcode.medium.No_0033_Search_in_Rotated_Sorted_Array;

/**
 * 33. 搜索旋转排序数组
 * <p>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 */
class Solution {
    /**
     * 方法：二分查找
     * 时间复杂度：O(log n)，其中 n 为 nums 数组的大小。整个算法时间复杂度即为二分查找的时间复杂度 O(log n)。
     * 空间复杂度：O(1) 。只需要常数级别的空间存放变量。
     */
    public int search(int[] nums, int target) {
        /*
         * 思路：
         * 可以发现，将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。
         * 因此可以在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分 [l, mid] 和 [mid + 1, r] 哪个部分是有序的，
         * 并根据有序的那个部分确定该如何改变二分查找的上下界，因为根据有序的那部分能够判断出 target 在不在这个部分：
         *   • 如果 [l, mid - 1] 是有序数组，且 target 的大小满足 [nums[l],nums[mid])，则应该将搜索范围缩小至 [l, mid - 1]，否则在 [mid + 1, r] 中寻找。
         *   • 如果 [mid, r] 是有序数组，且 target 的大小满足 (nums[mid+1],nums[r]]，则应该将搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
         */
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                // 左边部分有序
                if (nums[0] <= target && target < nums[mid]) {
                    // target的值在左边
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 右边部分有序
                if (nums[mid] < target && target <= nums[len - 1]) {
                    // target的值在右边
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }
}
