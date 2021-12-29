package com.scuyjzh.leetcode.medium.No_0207_Course_Schedule;

import java.util.*;

/**
 * 207. 课程表
 *
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites
 * 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则
 * 必须 先学习课程  bi 。
 *   • 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成
 *     课程 1 。
 *
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，
 * 返回 false 。
 */
class Solution {
    /**
     * 方法：拓扑排序（Kahn 算法）
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }

        if (prerequisites.length == 0) {
            return true;
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

        // 当前结果集中的顶点个数，正好可以作为下标
        int index = 0;
        while (!queue.isEmpty()) {
            // 当前入度为 0 的顶点
            int curr = queue.poll();
            ++index;

            // 将当前顶点的所有邻接点的入度减 1
            for (int neighbour : adj[curr]) {
                --inDegree[neighbour];
                // 如果该邻接点的入度减一后为 0，则将其加入队列
                if (inDegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        return index == numCourses;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canFinish(2, new int[][]{{1, 0}}));
        System.out.println(new Solution().canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }
}
