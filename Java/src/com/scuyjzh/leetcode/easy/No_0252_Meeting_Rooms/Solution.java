package com.scuyjzh.leetcode.easy.No_0252_Meeting_Rooms;

import java.util.*;

/**
 * 252. 会议室
 *
 * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和
 * 结束的时间 intervals[i] = [starti, endi] ，请你判断一个人是否能够
 * 参加这里面的全部会议。
 */
class Solution {
    /**
     * 方法一：暴力法
     */
    public boolean canAttendMeetings1(int[][] intervals) {
        // 最简单的方法是将数组中的会议全部两两比较，判断它们是否有冲突（即它们的时间是否有交叠）
        for (int i = 0; i < intervals.length; ++i) {
            for (int j = i + 1; j < intervals.length; ++j) {
                // 若一个会议开始时另一个会议依然没有结束，则它们存在交叠
                if (overlap(intervals[i], intervals[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean overlap(int[] interval1, int[] interval2) {
        // 考虑两个不交叠的会议。前面的会议在后面的会议开始之前结束。
        // 因此，两次会议的 最小 结束时间（即前面会议的结束时间）小于或等于两次会议的 最大 开始时间（即后面会议的开始时间)。
        return Math.min(interval1[1], interval2[1]) > Math.max(interval1[0], interval2[0]);
    }

    /**
     * 方法二：排序
     */
    public boolean canAttendMeetings2(int[][] intervals) {
        // 按照开始时间对会议进行排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        // 接着依次遍历会议，检查它是否在下个会议开始前结束
        for (int i = 0; i < intervals.length - 1; ++i) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canAttendMeetings1(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(new Solution().canAttendMeetings2(new int[][]{{7, 10}, {2, 4}}));
    }
}
