package com.scuyjzh.leetcode.medium.No_0845_Longest_Mountain_in_Array;

/**
 * 845. 数组中的最长山脉
 *
 * 把符合下列属性的数组 arr 称为 山脉数组 ：
 *   • arr.length >= 3
 *   • 存在下标 i（0 < i < arr.length - 1），满足
 *       ○ arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 *       ○ arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 * 给出一个整数数组 arr，返回最长山脉子数组的长度。如果不存在山脉子
 * 数组，返回 0 。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int res = 0;
        int left = 0, right;
        while (left + 2 < n) {
            right = left + 1;
            // 山脉开始一定上升
            if (arr[left] < arr[left + 1]) {
                // 找到山顶
                while (right + 1 < n && arr[right] < arr[right + 1]) {
                    ++right;
                }
                // 山顶之后必须下降
                if (right + 1 < n && arr[right] > arr[right + 1]) {
                    while (right + 1 < n && arr[right] > arr[right + 1]) {
                        ++right;
                    }
                    res = Math.max(res, right - left + 1);
                }
            }
            left = right;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
    }
}
