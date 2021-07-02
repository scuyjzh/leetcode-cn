package com.scuyjzh.leetcode.easy.No_0088_Merge_Sorted_Array;

import java.util.*;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 示例：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 */
class Solution {
    /**
     * 方法一：双指针
     * 时间复杂度：O(m+n)。指针移动单调递增，最多移动 m+n 次，因此时间复杂度为 O(m+n)。
     * 空间复杂度：O(m)。需要建立长度为 m 的中间数组。
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        int p1 = 0;
        int p2 = 0;

        int p = 0;

        while ((p1 < m) && (p2 < n)) {
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        }

        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < m) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    /**
     * 方法二：逆向双指针
     * 时间复杂度：O(m+n)。指针移动单调递增，最多移动 m+n 次，因此时间复杂度为 O(m+n)。
     * 空间复杂度：O(1)。直接对数组 nums1 原地修改，不需要额外空间。
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;

        int p = m + n - 1;

        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = (nums1[p1] > nums2[p2]) ? nums1[p1--] : nums2[p2--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        solution.merge1(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        nums1 = new int[]{1, 2, 3, 0, 0, 0};
        nums2 = new int[]{2, 5, 6};
        solution.merge2(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
