package com.scuyjzh.leetcode.hard.No_0004_Median_of_Two_Sorted_Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 * <p>
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 要在一个短的数组上搜索 i，需始保证 nums1 为较短的数组，否则可能会导致 nums2[j - 1] 的访问越界
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        /*
         * 在分割线左边的所有元素需要满足的个数 m + (n - m + 1) / 2
         * m+n 为奇数，分割线要求左侧有 (m+n)/2 + 1 个元素
         * m+n 为偶数，分割线要求左侧有 (m+n)/2     个元素
         * 两种情况其实可以统一写作 (m+n+1)/2，表示对 (m+n)/2 向上取整
         */
        int totalLeft = (m + n + 1) / 2;

        // 在 nums1 的区间 [0, m] 里查找恰当的分割线，
        // 使得 nums1[i - 1] <= mums2[j] && nums2[j - 1] <= nums1[i]
        int left = 0;
        int right = m;

        // nums1[i - 1] <= nums2[j]：此处要求第一个数组中分割线的左边的值 不大于(小于等于) 第二个数组中分割线的右边的值
        // nums2[j - 1] <= nums1[i]：此处要求第二个数组中分割线的左边的值 不大于(小于等于) 第一个数组中分割线的右边的值
        // 循环条件结束的条件为指针重合，即分割线已找到
        while (left < right) {
            // +1 向上取整避免 left + 1 = right 时可能无法继续缩小区间而陷入死循环
            int i = left + (right - left + 1) / 2;
            // 第一个数组中左边的元素个数确定后，用左边元素的总和-第一个数组中元素的总和=第二个数组中左边的元素的总和
            // 此时j就是第二个数组中左边的元素的个数
            int j = totalLeft - i;
            /*
             * 分割线左侧元素小于等于分割线右侧元素。
             * 由于两个数组均为正序数组，则只需要要求：nums1[i-1] <= nums2[j] && nums2[j-1] <= nums1[i]；
             * 由于该条件等价于在[0, m]中找到最大的i使得nums1[i-1] <= nums2[j]，因此可以使用二分查找。
             * （证明：假设已经找到了满足条件的最大i，使得nums1[i-1] <= nums2[j]，那么此时必有nums[i] >= nums2[j]，进而有nums[i] >= nums2[j-1]）
             */
            if (nums1[i - 1] > nums2[j]) {
                // 下一轮搜索的区间 [left, i - 1]
                right = i - 1;
            } else {
                // 下一轮搜索的区间 [i, right]
                left = i;
            }
        }

        // 退出循环时left=right，表示最终nums1中分割线的位置
        int i = left;
        //nums2中分割线的位置
        int j = totalLeft - i;
        // nums1分割线左边没有元素
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        // nums1分割线右边没有元素
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        // nums2分割线左边没有元素
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        // nums2分割线右边没有元素
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];
        // 如果两个数组的长度之和为奇数，直接返回两个数组在分割线左边的最大值即可
        if (((m + n) % 2) == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            // 如果两个数组的长度之和为偶数，返回的是两个数组在左边的最大值和两个数组在右边的最小值的和的二分之一
            return 1.0 * ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}
