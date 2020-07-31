package com.scuyjzh.leetcode.medium._547_Friend_Circles;

import java.util.*;

class Solution {
    class UnionFind {
        private int count;
        private int[] id;

        public UnionFind(int N) {
            this.count = N;
            this.id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]]; // path compression by halving
                p = id[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                return;
            }
            id[pRoot] = qRoot;
            count--;
        }
    }

    /**
     * Approach #1 (Union-Find)
     */
    public int findCircleNum1(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        UnionFind uf = new UnionFind(M.length);
        for (int i = 0; i < M.length - 1; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count;
    }

    /**
     * Approach #2 (Iteration with Queue - BFS)
     */
    public int findCircleNum2(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        boolean[] visited = new boolean[M.length];
        Queue<Integer> queue = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < M.length; ++i) {
            if (visited[i] == false) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int t = queue.poll();
                    visited[t] = true;
                    for (int j = 0; j < M.length; j++) {
                        if (M[t][j] == 1 && visited[j] == false) {
                            queue.add(j);
                        }
                    }
                }
                num++;
            }
        }
        return num;
    }

    /**
     * Approach #3 (Recursion - DFS)
     */
    public int findCircleNum3(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        boolean[] visited = new boolean[M.length];
        int num = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == false) {
                helper(M, visited, i);
                num++;
            }
        }
        return num;
    }

    private void helper(int[][] M, boolean[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                helper(M, visited, j);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findCircleNum2(new int[][]{{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 1, 1}}));
        System.out.println(solution.findCircleNum3(new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }
}
