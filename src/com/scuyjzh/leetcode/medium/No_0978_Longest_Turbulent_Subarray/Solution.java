package com.scuyjzh.leetcode.medium.No_0978_Longest_Turbulent_Subarray;

import java.util.*;

/**
 * 978. 最长湍流子数组
 *
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，称其为湍流子数组：
 *   • 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 *   • 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 *
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 返回 A 的最大湍流子数组的长度。
 */
class Solution {
    public int maxTurbulenceSize1(int[] arr) {
        // 动态规划解法
        int n = arr.length;
        // 定义 up[i] 表示以位置 i 结尾的，并且 arr[i - 1] < arr[i] 的最长湍流子数组长度
        int[] up = new int[n];
        // 定义 down[i] 表示以位置 i 结尾的，并且 arr[i - 1] > arr[i] 的最长湍流子数组长度
        int[] down = new int[n];
        // up[i] 和 down[i] 初始化都是 1，因为每个数字本身都是一个最小的湍流子数组
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        int res = 1;
        // 状态转移
        for(int i = 1; i < n; ++i) {
            if(arr[i] > arr[i - 1]) {
                // 上升：up[i] = down[i - 1] + 1，当 arr[i - 1] < arr[i]
                up[i] = down[i - 1] + 1;
                res = Math.max(up[i], res);
            } else if(arr[i] < arr[i - 1]) {
                // 下降：down[i] = up[i - 1] + 1，当 arr[i - 1] > arr[i]
                down[i] = up[i - 1] + 1;
                res = Math.max(down[i], res);
            }
        }
        return res;
    }

    public int maxTurbulenceSize2(int[] arr) {
        // 空间优化
        int n = arr.length;
        int up = 1, down = 1;
        int res = 1;
        for (int i = 1; i < n; ++i) {
            if (arr[i - 1] < arr[i]) {
                up = down + 1;
                down = 1;
            } else if (arr[i - 1] > arr[i]) {
                down = up + 1;
                up = 1;
            } else {
                up = 1;
                down = 1;
            }
            res = Math.max(res, Math.max(up, down));
        }
        return res;
    }

    public int maxTurbulenceSize3(int[] arr) {
        // 滑动窗口解法
        int n = arr.length;
        int res = 1;
        // 设窗口 [left,right](0≤left≤right≤n−1) 为当前的窗口，窗口内构成了一个「湍流子数组」
        int left = 0, right = 0;
        // 根据「湍流子数组」的定义，当 0<right<n−1 时：
        while (right < n - 1) {
            // 首先需要特殊考虑窗口长度为 1 (即 left 和 right 相等的情况)：
            if (left == right) {
                // 只要 arr[left]≠arr[left+1]，就可以将 right 右移一个单位；
                // 否则，left 和 right 都要同时右移。
                if (arr[left] == arr[left + 1]) {
                    ++left;
                }
                ++right;
            } else {
                // 如果 arr[right−1]<arr[right] 且 arr[right]>arr[right+1]，
                // 则 [left,right+1] 也构成「湍流子数组」，因此需要将 right 右移一个单位；
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    ++right;
                }
                // 如果 arr[right−1]>arr[right] 且 arr[right]<arr[right+1]，
                // 同理，也需要将 right 右移一个单位；
                else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    ++right;
                }
                // 否则，[right−1,right+1] 无法构成「湍流子数组」，
                // 当 left<right 时，[left,right+1] 也无法构成「湍流子数组」，
                // 因此需要将 left 移到 right，即令 left=right。
                else {
                    left = right;
                }
            }
            // 在此过程中更新最大窗口长度
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxTurbulenceSize1(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        System.out.println(new Solution().maxTurbulenceSize2(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        System.out.println(new Solution().maxTurbulenceSize3(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
    }
}
