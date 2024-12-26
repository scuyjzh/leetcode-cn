package com.scuyjzh.leetcode.medium.No_0581_Shortest_Unsorted_Continuous_Subarray;

import java.util.*;

/**
 * 581. 最短无序连续子数组
 *
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数
 * 组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 */
class Solution {
    /**
     * 方法一：排序
     */
    public int findUnsortedSubarray1(int[] nums) {
        /*
         * 将给定的数组 nums 表示为三段子数组拼接的形式，分别记作 numsA，numsB，numsC，当对
         * numsB 进行排序，整个数组将变为有序。换而言之，当对整个序列进行排序，numsA 和 numsC 都不会
         * 改变。
         *
         * 本题要求找到最短的 numsB，即找到最大的 numsA 和 numsC 的长度之和。因此将原数组 nums 排
         * 序与原数组进行比较，取最长的相同的前缀为 numsA，取最长的相同的后缀为 numsC，这样就可以取到
         * 最短的 numsB。
         *
         * 具体地，创建数组 nums 的拷贝，记作数组 numsSorted，并对该数组进行排序，然后从左向右找到
         * 第一个两数组不同的位置，即为 numsB 的左边界。同理也可以找到 numsB 的右边界。最后输出 numsB
         * 的长度即可。
         *
         * 特别地，当原数组有序时，numsB 的长度为 0，可以直接返回结果。
         */
        if (isSorted(nums)) {
            return 0;
        }
        int[] numsSorted = new int[nums.length];
        System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        Arrays.sort(numsSorted);
        int left = 0;
        while (nums[left] == numsSorted[left]) {
            ++left;
        }
        int right = nums.length - 1;
        while (nums[right] == numsSorted[right]) {
            --right;
        }
        return right - left + 1;
    }

    private boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二：单调栈
     */
    public int findUnsortedSubarray2(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        // 单调栈从前往后遍历一遍可得到左边界
        int left = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }

        stack.clear();
        // 单调栈从后往前遍历一遍可得到右边界
        int right = -1;
        for (int i = nums.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }

        return right - left > 0 ? right - left + 1 : 0;
    }

    /**
     * 方法三：双指针
     */
    public int findUnsortedSubarray3(int[] nums) {
        int n = nums.length;
        int maxN = Integer.MIN_VALUE, right = -1;
        int minN = Integer.MAX_VALUE, left = -1;
        // 两个方向同时遍历数组
        for (int i = 0; i < n; ++i) {
            // 从左到右维护一个最大值 maxN，要求的 right 就是最后一个小于 maxN 的元素的位置
            if (nums[i] < maxN) {
                right = i;
            } else {
                maxN = nums[i];
            }
            // 同理，从右到左维护一个最小值 minN，要求的 left 也就是最后一个大于 minN 的元素的位置
            if (nums[n - i - 1] > minN) {
                left = n - i - 1;
            } else {
                minN = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findUnsortedSubarray1(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new Solution().findUnsortedSubarray2(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new Solution().findUnsortedSubarray3(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }
}
