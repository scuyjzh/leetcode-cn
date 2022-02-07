package com.scuyjzh.leetcode.medium.No_1229_Meeting_Scheduler;

import java.util.*;

/**
 * 1229. 安排会议日程
 *
 * 给定两个人的空闲时间表：slots1 和 slots2，以及会议的预计持续时
 * 间 duration，请你为他们安排 时间段最早 且合适的会议时间。
 *
 * 如果没有满足要求的会议时间，就请返回一个 空数组。
 *
 * 「空闲时间」的格式是 [start, end]，由开始时间 start 和结束时
 * 间 end 组成，表示从 start 开始，到 end 结束。
 *
 * 题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，
 * 对于同一个人的两个空闲时间 [start1, end1] 和 [start2, end2]，要
 * 么 start1 > end2，要么 start2 > end1。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        // 对两个人的空闲时间表按照开始时间排序
        Arrays.sort(slots1, (a, b) -> (a[0] - b[0]));
        Arrays.sort(slots2, (a, b) -> (a[0] - b[0]));
        int idx1 = 0, idx2 = 0;
        // 遍历两个人的空闲时间表
        while (idx1 < slots1.length && idx2 < slots2.length) {
            // 当前遍历到的 slots 的结束时间
            int ed1 = slots1[idx1][1];
            int ed2 = slots2[idx2][1];
            // 当前 slots 的最晚开始时间
            int st = Math.max(slots1[idx1][0], slots2[idx2][0]);
            if (ed1 < ed2) {
                if (ed1 - st >= duration) {
                    return Arrays.asList(st, st + duration);
                }
                ++idx1;
            } else {
                if (ed2 - st >= duration) {
                    return Arrays.asList(st, st + duration);
                }
                ++idx2;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        int[][] slots1 = {{10, 50}, {60, 120}, {140, 210}};
        int[][] slots2 = {{0, 15}, {60, 70}};
        System.out.println(new Solution().minAvailableDuration(slots1, slots2, 8));
    }
}
