package com.scuyjzh.leetcode.hard.No_0042_Trapping_Rain_Water;

import java.util.*;

/**
 * 42. 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的
 * 柱子，下雨之后能接多少雨水。
 */
class Solution {
    /**
     * 方法一：动态规划
     */
    public int trap1(int[] height) {
        /*
         * 对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，下标 i 处能接的雨水量等于下
         * 标 i 处的水能到达的最大高度减去 height[i]。
         *
         * 如果已经知道每个位置两边的最大高度，则可以在 O(n) 的时间内得到能接的雨水总量。使用动态规划的方法，
         * 可以在 O(n) 的时间内预处理得到每个位置两边的最大高度。
         *
         * 创建两个长度为 n 的数组 leftMax 和 rightMax。对于 0≤i<n，leftMax[i] 表示下标 i 及其左边的位置中，
         * height 的最大高度，rightMax[i] 表示下标 i 及其右边的位置中，height 的最大高度。
         *
         * 显然，leftMax[0] = height[0]，rightMax[n−1] = height[n−1]。两个数组的其余元素的计算如下：
         *   • 当 1≤i≤n−1 时，leftMax[i] = max(leftMax[i−1], height[i])；
         *   • 当 0≤i≤n−2 时，rightMax[i] = max(rightMax[i+1], height[i])。
         *
         * 因此可以正向遍历数组 height 得到数组 leftMax 的每个元素值，反向遍历数组 height 得到数组 rightMax 的
         * 每个元素值。
         *
         * 在得到数组 leftMax 和 rightMax 的每个元素值之后，对于 0≤i<n，下标 i 处能接的雨水量等于
         * min(leftMax[i], rightMax[i]) − height[i]。遍历每个下标位置即可得到能接的雨水总量。
         */
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    /**
     * 方法二：单调栈
     */
    public int trap2(int[] height) {
        /*
         * 除了计算并存储每个位置两边的最大高度以外，也可以用单调栈计算能接的雨水总量。
         *
         * 维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组 height 中的元素递减。
         *
         * 从左到右遍历数组，遍历到下标 i 时，如果栈内至少有两个元素，记栈顶元素为 top，top 的下面一个元素是
         * left，则一定有 height[left] ≥ height[top]。如果 height[i] > height[top]，则得到一个可以接雨水的区域，该区
         * 域的宽度是 i−left−1，高度是 min(height[left], height[i]) − height[top]，根据宽度和高度即可计算得到该区
         * 域能接的雨水量。
         *
         * 为了得到 left，需要将 top 出栈。在对 top 计算能接的雨水量之后，left 变成新的 top，重复上述操作，直到
         * 栈变为空，或者栈顶下标对应的 height 中的元素大于或等于 height[i]。
         *
         * 在对下标 i 处计算能接的雨水量之后，将 i 入栈，继续遍历后面的下标，计算能接的雨水量。遍历结束之后
         * 即可得到能接的雨水总量。
         */
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                // 相同元素
                while (!stack.isEmpty() && height[top] == height[stack.peek()]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int left = stack.peek();
                    // 宽
                    int currWidth = i - left - 1;
                    // 高
                    int currHeight = Math.min(height[left], height[i]) - height[top];
                    res += currWidth * currHeight;
                }
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 方法三：双指针
     */
    public int trap3(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int res = 0;
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                res += leftMax - height[left];
                ++left;
            } else {
                res += rightMax - height[right];
                --right;
            }
        }
        return res;
    }

    /**
     * 方法四：数学（韦恩图）
     */
    public int trap4(int[] height) {
        int len = height.length;
        // 同时从左往右和从右往左计算有效面积
        int s1 = 0, s2 = 0, sum = 0;
        int leftMax = 0, rightMax = 0;
        for (int i = 0; i < len; ++i) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            }
            if (height[len - i - 1] > rightMax) {
                rightMax = height[len - i - 1];
            }
            s2 += rightMax;
            s1 += leftMax;
            sum += height[i];
        }
        // 积水面积 = S1 + S2 - 矩形面积 - 柱子面积
        return s1 + s2 - leftMax * len - sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().trap1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new Solution().trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new Solution().trap3(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new Solution().trap4(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
