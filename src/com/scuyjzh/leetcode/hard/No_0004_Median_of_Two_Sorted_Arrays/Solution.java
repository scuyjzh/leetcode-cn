package com.scuyjzh.leetcode.hard.No_0004_Median_of_Two_Sorted_Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
class Solution {
    /**
     * 方法：二分查找
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 这道题可以转化成寻找两个有序数组中的第 k 小的数，其中 k 为 (m+n)/2 或 (m+n)/2+1。
        int len1 = nums1.length, len2 = nums2.length;
        int k = (len1 + len2) / 2;
        if ((len1 + len2) % 2 == 1) {
            // 根据中位数的定义，
            // 当 m+n 是奇数时，中位数是两个有序数组中的第 (m+n)/2+1 个元素；
            return findKthElement(nums1, nums2, k + 1);
        } else {
            // 当 m+n 是偶数时，中位数是两个有序数组中的第 (m+n)/2 个元素和第 (m+n)/2+1 个元素的平均值。
            return (findKthElement(nums1, nums2, k) + findKthElement(nums1, nums2, k + 1)) / 2.0;
        }
    }

    /**
     * 寻找两个有序数组中的第 k 小的数
     */
    private int findKthElement(int[] nums1, int[] nums2, int k) {
        /*
         * 主要思路：
         * 要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         *
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         *
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int len1 = nums1.length, len2 = nums2.length;
        int idx1 = 0, idx2 = 0;

        while (true) {
            // 边界情况（注意 1 和 2 顺序不能颠倒）：
            // 1.首先，如果一个数组为空，说明该数组中的所有元素都被排除，可以直接返回另一个数组中第 k 小的元素；
            if (idx1 == len1) {
                return nums2[idx2 + k - 1];
            }
            if (idx2 == len2) {
                return nums1[idx1 + k - 1];
            }
            // 2.其次，如果 k=1，只要返回两个数组首元素的最小值即可。
            if (k == 1) {
                return Math.min(nums1[idx1], nums2[idx2]);
            }

            // 正常情况：
            int newIdx1 = Math.min(idx1 + k / 2, len1) - 1;
            int newIdx2 = Math.min(idx2 + k / 2, len2) - 1;
            int pivot1 = nums1[newIdx1];
            int pivot2 = nums2[newIdx2];
            // 取 pivot = min(pivot1, pivot2)，更新 k 和 idx
            if (pivot1 <= pivot2) {
                // 如果 pivot = pivot1，那么 "删除" nums1[idx1 .. newIdx1]
                k -= (newIdx1 - idx1 + 1);
                idx1 = newIdx1 + 1;
            } else {
                // 如果 pivot = pivot2，那么 "删除" nums2[idx2 .. newIdx2]
                k -= (newIdx2 - idx2 + 1);
                idx2 = newIdx2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays(new int[]{1, 2, 3, 6}, new int[]{1, 3, 4, 5, 9, 10}));
    }
}
