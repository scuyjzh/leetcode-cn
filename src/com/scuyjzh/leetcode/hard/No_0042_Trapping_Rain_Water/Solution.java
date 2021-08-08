package com.scuyjzh.leetcode.hard.No_0042_Trapping_Rain_Water;

import java.util.*;

/**
 * 42. 接雨水
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
class Solution {
    /**
     * 方法一：动态规划
     * 时间复杂度：O(n)，其中 n 是数组 height 的长度。计算数组 containers 的元素值需要左右各遍历数组 height 一次，计算能接的雨水总量还需要遍历一次。
     * 空间复杂度：O(n)，其中 n 是数组 height 的长度。需要创建一个长度为 n 的数组 containers。
     */
    public int trap1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int[] containers = new int[height.length];

        // 先从左往右扫描，维护每一个bar左边的最大高度
        int h = 0;
        for (int i = 0; i < height.length; ++i) {
            containers[i] = h;
            h = Math.max(h, height[i]);
        }

        // 再从右往左扫描，维护每一个bar右边的最大高度，并记录当前bar两边较小的最大高度
        h = 0;
        for (int i = height.length - 1; i >= 0; --i) {
            containers[i] = Math.min(h, containers[i]);
            h = Math.max(h, height[i]);
        }

        // 当前bar接的雨水等于当前bar两边较小的最大高度减去当前bar的高度
        int res = 0;
        for (int i = 0; i < height.length; ++i) {
            if (containers[i] > height[i]) {
                res += containers[i] - height[i];
            }
        }
        return res;
    }

    /**
     * 方法二：双指针
     * 时间复杂度：O(n)，其中 n 是数组 height 的长度。两个指针的移动总次数不超过 n。
     * 空间复杂度：O(1)。只需要使用常数的额外空间。
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int res = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                if (leftMax > height[left]) {
                    res += leftMax - height[left];
                } else {
                    // 维护左边的最大高度
                    leftMax = height[left];
                }
                left += 1;
            } else {
                if (rightMax > height[right]) {
                    res += rightMax - height[right];
                } else {
                    // 维护右边的最大高度
                    rightMax = height[right];
                }
                right -= 1;
            }
        }
        return res;
    }

    /**
     * 方法三：双指针
     * 时间复杂度：O(n)，其中 n 是数组 height 的长度。两个指针的移动总次数不超过 n。
     * 空间复杂度：O(1)。只需要使用常数的额外空间。
     */
    public int trap3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int res = 0;
        while (l < r) {
            int left = height[l], right = height[r];
            if (height[l] < height[r]) {
                while (l < r && height[++l] <= left) {
                    res += left - height[l];
                }
            } else {
                while (l < r && height[--r] <= right) {
                    res += right - height[r];
                }
            }
        }
        return res;
    }

    /**
     * 方法四：单调栈
     * 时间复杂度：O(n)，其中 n 是数组 height 的长度。从 0 到 n−1 的每个下标最多只会入栈和出栈各一次。
     * 空间复杂度：O(n)，其中 n 是数组 height 的长度。空间复杂度主要取决于栈空间，栈的大小不会超过 n。
     */
    public int trap4(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < height.length; ++i) {
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
     * 方法五：数学（韦恩图）
     */
    public int trap5(int[] height) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/trapping-rain-water/solution/wei-en-tu-jie-fa-zui-jian-dan-yi-dong-10xing-jie-j/
         */
        int len = height.length;
        // 同时从左往右和从右往左计算有效面积
        int S1 = 0, S2 = 0, sum = 0;
        int leftMax = 0, rightMax = 0;
        for (int i = 0; i < len; ++i) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            }
            if (height[len - i - 1] > rightMax) {
                rightMax = height[len - i - 1];
            }
            S2 += rightMax;
            S1 += leftMax;
            sum += height[i];
        }
        // 积水面积 = S1 + S2 - 矩形面积 - 柱子面积
        return S1 + S2 - leftMax * len - sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trap1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(solution.trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(solution.trap3(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(solution.trap4(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(solution.trap5(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
