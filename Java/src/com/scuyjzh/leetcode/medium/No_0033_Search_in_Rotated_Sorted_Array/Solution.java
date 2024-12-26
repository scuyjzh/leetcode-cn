package com.scuyjzh.leetcode.medium.No_0033_Search_in_Rotated_Sorted_Array;

/**
 * 33. 搜索旋转排序数组
 *
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k <
 * nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ...,
 * nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计
 * 数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变
 * 为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目
 * 标值 target ，则返回它的下标，否则返回 -1 。
 */
class Solution {
    /**
     * 方法：二分查找
     *
     * • 时间复杂度：O(log n)，其中 n 为 nums 数组的大小。整个算法时间复杂度即为二分查找的时间复杂度
     *   O(log n)。
     * • 空间复杂度：O(1)。只需要常数级别的空间存放变量。
     */
    public int search(int[] nums, int target) {
        /*
         * 对于有序数组，可以使用二分查找的方法查找元素。
         *
         * 但是这道题中，数组本身不是有序的，进行旋转后只保证了数组的局部是有序的，这还能进行二分查找吗？
         * 答案是可以的。
         *
         * 可以发现的是，将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。拿示例来看，
         * 从 6 这个位置分开以后数组变成了 [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，其中左边 [4, 5, 6]
         * 这个部分的数组是有序的，其他也是如此。
         *
         * 这启示可以在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分 [l, mid] 和 [mid
         * + 1, r] 哪个部分是有序的，并根据有序的那个部分确定该如何改变二分查找的上下界，因为能
         * 够根据有序的那部分判断出 target 在不在这个部分：
         *   • 如果 [l, mid - 1] 是有序数组，且 target 的大小满足 [nums[l],nums[mid])，则应该将搜索范
         *     围缩小至 [l, mid - 1]，否则在 [mid + 1, r] 中寻找。
         *   • 如果 [mid, r] 是有序数组，且 target 的大小满足 (nums[mid+1],nums[r]]，则应该将搜索范
         *     围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
         *
         * 需要注意的是，二分的写法有很多种，所以在判断 target 大小与有序部分的关系的时候可能会出现细节上
         * 的差别。
         */
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 左半部分有序
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    // target 的值在左边
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 右半部分有序
            else {
                if (nums[mid] < target && target <= nums[len - 1]) {
                    // target 的值在右边
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(new Solution().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(new Solution().search(new int[]{1}, 0));
    }
}
