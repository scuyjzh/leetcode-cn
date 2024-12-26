package com.scuyjzh.leetcode.medium.No_0621_Task_Scheduler;

/**
 * 621. 任务调度器
 *
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个
 * 字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都
 * 可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个
 * 任务，或者处于待命状态。
 *
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此
 * 至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的 最短时间 。
 */
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // 贪心思想：
        // 1.先安排出现次数最多的任务，让这个任务两次执行的时间间隔正好为 n；
        // 2.再在这个时间间隔内填充其它任务（或者冷却间隔）。
        if (tasks.length <= 1 || n < 1) {
            return tasks.length;
        }

        int[] times = new int[26];
        for (char task : tasks) {
            ++times[task - 'A'];
        }

        // maxTimes 为出现次数最多的那个任务出现的次数
        int maxTimes = 0;
        for (int i = 0; i < 26; i++) {
            maxTimes = Math.max(maxTimes, times[i]);
        }

        // maxCount 为一共有多少个任务和出现次数最多的那个任务出现次数一样
        int maxCount = 1;
        for (int i = 0; i < 26; i++) {
            if (times[i] == maxTimes) {
                ++maxCount;
            }
        }

        int res = (maxTimes - 1) * (n + 1) + maxCount;
        return Math.max(res, tasks.length);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'}, 2));
    }
}
