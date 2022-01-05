package com.scuyjzh.leetcode.medium.No_0253_Meeting_Rooms_II;

import java.util.*;

/**
 * 253. 会议室 II
 *
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和
 * 结束的时间 intervals[i] = [starti, endi] ，为避免会议冲突，同时要
 * 考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些
 * 会议安排。
 */
class Solution {
    /**
     * 方法一：优先队列（最小堆）
     */
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 1.按照 开始时间 对会议进行排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        // 2.初始化一个新的 最小堆，将第一个会议的结束时间加入到堆中
        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length, Comparator.comparingInt(a -> a));
        allocator.add(intervals[0][1]);

        // 3.对每个会议，检查堆的最小元素（即堆顶部的房间）是否空闲，它是最先开完会腾出房间的
        for (int i = 1; i < intervals.length; ++i) {

            // 3.1.若房间空闲，则从堆顶拿出该元素，将其改为处理的会议的结束时间，加回到堆中
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // 3.2.若房间不空闲，开新房间，并加入到堆中
            allocator.add(intervals[i][1]);
        }

        // 4.处理完所有会议后，堆的大小即为开的房间数量。这就是容纳这些会议需要的最小房间数
        return allocator.size();
    }

    /**
     * 方法二：有序化
     */
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 1.分别将开始时间和结束时间存进两个数组
        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];
        for (int i = 0; i < intervals.length; ++i) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        // 2.分别对开始时间和结束时间进行排序。请注意，这将打乱开始时间和结束时间的原始对应关系。它们将被分别处理
        Arrays.sort(start, Comparator.comparingInt(a -> a));
        Arrays.sort(end, Comparator.comparingInt(a -> a));

        // 3.考虑两个指针：s_ptr 和 e_ptr ，分别代表开始指针和结束指针。开始指针遍历每个会议，结束指针帮助跟踪会议是否结束
        int startPointer = 0, endPointer = 0;

        int usedRooms = 0;

        while (startPointer < intervals.length) {

            // 4.当考虑 s_ptr 指向的特定会议时，检查该开始时间是否大于 e_ptr 指向的会议。
            //   若如此，则说明 s_ptr 开始时，已经有会议结束。于是可以重用房间
            if (start[startPointer] >= end[endPointer]) {
                // 5.若有会议结束，换而言之，start[s_ptr] >= end[e_ptr] ，则自增 e_ptr
                endPointer += 1;
            } else {
                // 否则，就需要开新房间
                usedRooms += 1;
            }
            // 6.重复这一过程，直到 s_ptr 处理完所有会议
            startPointer += 1;
        }

        return usedRooms;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minMeetingRooms1(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(new Solution().minMeetingRooms2(new int[][]{{7, 10}, {2, 4}}));
    }
}
