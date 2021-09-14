package com.scuyjzh.leetcode.medium.No_0253_Meeting_Rooms_II;

import java.util.*;

/**
 * 253. 会议室 II
 * <p>
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [start_i, end_i] ，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 */
class Solution {
    /**
     * 方法一：优先队列
     * 时间复杂度：O(NlogN)。时间开销主要有两部分。第一部分是数组的 排序 过程，消耗 O(NlogN) 的时间。数组中有 N 个元素。接下来是 最小堆 占用的时间。在最坏的情况下，全部 N 个会议都会互相冲突。在任何情况下，都要向堆执行 N 次插入操作。在最坏的情况下，要对堆进行 N 次查找并删除最小值操作。总的时间复杂度为 (NlogN) ，因为查找并删除最小值操作只消耗 O(logN) 的时间。
     * 空间复杂度：O(N) 。额外空间用于建立 最小堆 。在最坏的情况下，堆需要容纳全部 N 个元素。因此空间复杂度为 O(N) 。
     */
    public int minMeetingRooms1(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        // Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length, Comparator.comparingInt(a -> a));

        // Sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        // Add the first meeting
        allocator.add(intervals[0][1]);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; ++i) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }

    /**
     * 方法二：有序化
     * 时间复杂度: O(NlogN)。所做的只是将 开始时间 和 结束时间 两个数组分别进行排序。每个数组有 N 个元素，因为有 N 个时间间隔。
     * 空间复杂度: O(N)。建立了两个 N 大小的数组。分别用于记录会议的开始时间和结束时间。
     */
    public int minMeetingRooms2(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; ++i) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        // Sort the intervals by end time
        Arrays.sort(end, Comparator.comparingInt(a -> a));

        // Sort the intervals by start time
        Arrays.sort(start, Comparator.comparingInt(a -> a));

        // The two pointers in the algorithm: e_ptr and s_ptr.
        int startPointer = 0, endPointer = 0;

        // Variables to keep track of maximum number of rooms used.
        int usedRooms = 0;

        // Iterate over intervals.
        while (startPointer < intervals.length) {

            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (start[startPointer] >= end[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }

            // We do this irrespective of whether a room frees up or not.
            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
            // remain the same in that case. If no room was free, then this would increase used_rooms
            usedRooms += 1;
            startPointer += 1;

        }

        return usedRooms;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minMeetingRooms1(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(new Solution().minMeetingRooms2(new int[][]{{7, 10}, {2, 4}}));
    }
}