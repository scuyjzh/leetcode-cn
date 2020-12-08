package com.scuyjzh.leetcode.hard.No_0004_Median_of_Two_Sorted_Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * 示例：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 0) {
            return (helper(nums1, 0, nums2, 0, len / 2) + helper(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
        }
        return helper(nums1, 0, nums2, 0, (len + 1) / 2);
    }

    private int helper(int[] nums1, int m, int[] nums2, int n, int k) {
        if (m >= nums1.length) {
            return nums2[n + k - 1];
        }
        if (n >= nums2.length) {
            return nums1[m + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[m], nums2[n]);
        }

        int p1 = m + k / 2 - 1;
        int p2 = n + k / 2 - 1;
        int mid1 = p1 < nums1.length ? nums1[p1] : Integer.MAX_VALUE;
        int mid2 = p2 < nums2.length ? nums2[p2] : Integer.MAX_VALUE;
        if (mid1 < mid2) {
            return helper(nums1, m + k / 2, nums2, n, k - k / 2);
        }
        return helper(nums1, m, nums2, n + k / 2, k - k / 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}
