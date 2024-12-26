package com.scuyjzh.leetcode.medium.No_0210_Course_Schedule_II;

import java.util.*;

/**
 * 210. 课程表 II
 *
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给
 * 你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表
 * 示在选修课程 ai 前 必须 先选修 bi 。
 *   • 例如，想要学习课程 0 ，你需要先完成课程 1 ，用一个匹配
 *     来表示：[0,1] 。
 *
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你
 * 只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组
 * 。
 */
class Solution {
    /**
     * 方法：拓扑排序（Kahn 算法）
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }

        // 邻接表
        HashSet<Integer>[] adj = new HashSet[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            adj[i] = new HashSet<>();
        }

        // 入度数组
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            adj[p[1]].add(p[0]);
            ++inDegree[p[0]];
        }

        Deque<Integer> queue = new ArrayDeque<>();

        // 扫描邻接表，将入度为 0 的顶点放入队列
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        // 当前结果集中的顶点个数，正好可以作为下标
        int index = 0;
        while (!queue.isEmpty()) {
            // 当前入度为 0 的顶点
            int curr = queue.poll();
            res[index++] = curr;

            // 将当前顶点的所有邻接点的入度减 1
            for (int neighbour : adj[curr]) {
                --inDegree[neighbour];
                // 如果该邻接点的入度减一后为 0，则将其加入队列
                if (inDegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        // 当队列为空时，如果结果集中的顶点个数不等于课程数，就不能完成课程任务
        return index == numCourses ? res : new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(new Solution().findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
    }
}
