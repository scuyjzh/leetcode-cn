package com.scuyjzh.leetcode.hard.No_0239_Sliding_Window_Maximum;

import java.util.*;

/**
 * 239. 滑动窗口最大值
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移
 * 动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每
 * 次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 */
class Solution {
    /**
     * 方法：单调队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int n = nums.length;
        // 为了可以同时弹出队首和队尾的元素，需要使用双端队列。
        // 队列中下标对应的元素是严格单调递减的，满足这种单调性的双端队列一般称作「单调队列」。
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; ++i) {
            // 当滑动窗口向右移动时，需要把一个新的元素放入队列中。
            // 为了保持队列的性质，会不断地将新的元素与队尾的元素相比较，
            // 如果前者大于等于后者，那么队尾的元素就可以被永久地移除，将其弹出队列。
            // 需要不断地进行此项操作，直到队列为空或者新的元素小于队尾的元素。
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);

            // 保证队首元素在窗口中
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 队首下标对应的元素就是滑动窗口中的最大值
            if (i + 1 >= k) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
