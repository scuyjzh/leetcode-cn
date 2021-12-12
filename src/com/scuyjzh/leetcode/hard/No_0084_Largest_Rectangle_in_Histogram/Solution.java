package com.scuyjzh.leetcode.hard.No_0084_Largest_Rectangle_in_Histogram;

import java.util.*;

/**
 * 84. 柱状图中最大的矩形
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相
 * 邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
class Solution {
    /**
     * 方法一：暴力解法（超时）
     */
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        // 特判
        if (len == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < len; i++) {

            // 找左边最后 1 个大于等于 heights[i] 的下标
            int left = i;
            int curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }

            // 找右边最后 1 个大于等于 heights[i] 的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }

            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }
        return res;
    }

    /**
     * 方法二：单调栈
     */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int[] newHeights = new int[len + 2];
        for (int i = 0; i < len; ++i) {
            newHeights[i + 1] = heights[i];
        }
        len = newHeights.length;

        Deque<Integer> stack = new ArrayDeque<>();
        // 先放入哨兵，在循环里就不用做非空判断
        stack.push(0);

        int res = 0;
        for (int i = 1; i < len; ++i) {
            while (newHeights[i] < newHeights[stack.peek()]) {
                int curHeight = newHeights[stack.poll()];
                int curWidth = i - stack.peek() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestRectangleArea1(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(new Solution().largestRectangleArea2(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
