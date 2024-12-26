package com.scuyjzh.leetcode.hard.No_0480_Sliding_Window_Median;

import java.util.*;

/**
 * 480. 滑动窗口中位数
 *
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间
 * 的数；此时中位数是最中间的两个数的平均数。
 *
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口
 * 中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得
 * 到的新窗口中元素的中位数，并输出由它们组成的数组。
 */
class Solution {
    /**
     * 方法一：暴力解法
     */
    public double[] medianSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        int[] temp = new int[k];
        for (int i = 0; i < n - k + 1; ++i) {
            for (int j = 0; j < k; ++j) {
                temp[j] = nums[i + j];
            }
            Arrays.sort(temp);
            if (k % 2 == 0) {
                ans[i] = (double) (temp[k / 2 - 1]) / 2 + (double) (temp[k / 2]) / 2;
            } else {
                ans[i] = temp[k / 2];
            }
        }
        return ans;
    }

    private List<Integer> window;

    /**
     * 方法二：滑动窗口 + 二分查找
     */
    public double[] medianSlidingWindow2(int[] nums, int k) {
        // 针对暴力解法进行优化，没有必要每次都重复把 k 个元素放到数组中再排序，
        // 只需要删除最左边元素，再插入最右边元素，窗口中其它元素继续保持有序状态。
        // 插入和删除用二分法加速。
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        window = new ArrayList<>();
        int left = 0, right = 0;
        while (right < n) {
            insert(nums[right]);
            if (window.size() == k) {
                if (k % 2 == 0) {
                    ans[left] = (double) window.get(k / 2 - 1) / 2 + (double) window.get(k / 2) / 2;
                } else {
                    ans[left] = window.get(k / 2);
                }
                remove(nums[left]);
                ++left;
            }
            ++right;
        }
        return ans;
    }

    private void insert(int val) {
        int left = 0;
        int right = window.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (window.get(mid) < val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        window.add(left, val);
    }

    private void remove(int val) {
        int left = 0;
        int right = window.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (window.get(mid) < val) {
                left = mid + 1;
            } else if (window.get(mid) > val) {
                right = mid - 1;
            } else {
                window.remove(mid);
                return;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().medianSlidingWindow1(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(new Solution().medianSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
