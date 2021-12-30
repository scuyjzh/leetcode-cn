package com.scuyjzh.leetcode.medium.No_0547_Number_of_Provinces;

import java.util.*;

/**
 * 547. 省份数量
 *
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市
 * b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接
 * 相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表
 * 示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示
 * 二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 */
class Solution {
    class UnionFind {
        int[] root;
        int[] rank;
        int count;

        UnionFind(int size) {
            this.root = new int[size];
            this.rank = new int[size];
            this.count = size;
            for (int i = 0; i < size; ++i) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        int find(int x) {
            while (x != root[x]) {
                // 路径压缩
                root[x] = root[root[x]];
                x = root[x];
            }
            return x;
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
                --count;
            }
        }

        int getCount() {
            return count;
        }
    }

    /**
     * 方法一：并查集
     */
    public int findCircleNum1(int[][] isConnected) {
        /*
         * 可以把 n 个城市和它们之间的相连关系看成图，城市是图中的节点，相连关系是图中的边，给定的矩阵
         * isConnected 即为图的邻接矩阵，省份即为图中的连通分量。
         *
         * 计算省份总数，等价于计算图中的连通分量数，可以通过深度优先搜索或广度优先搜索实现，也可以通过并
         * 查集实现。
         */
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        UnionFind uf = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length - 1; ++i) {
            for (int j = i + 1; j < isConnected.length; ++j) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }

    /**
     * 方法二：深度优先搜索
     */
    public int findCircleNum2(int[][] isConnected) {
        /*
         * 深度优先搜索的思路是很直观的。遍历所有城市，对于每个城市，如果该城市尚未被访问过，则从该城市开
         * 始深度优先搜索，通过矩阵 isConnected 得到与该城市直接相连的城市有哪些，这些城市和该城市属于同一
         * 个连通分量，然后对这些城市继续深度优先搜索，直到同一个连通分量的所有城市都被访问到，即可得到一
         * 个省份。遍历完全部城市以后，即可得到连通分量的总数，即省份的总数。
         */
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        boolean[] visited = new boolean[isConnected.length];
        int num = 0;
        for (int i = 0; i < isConnected.length; ++i) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                ++num;
            }
        }
        return num;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        for (int j = 0; j < isConnected.length; ++j) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, j);
            }
        }
    }

    /**
     * 方法三：广度优先搜索
     */
    public int findCircleNum3(int[][] isConnected) {
        /*
         * 也可以通过广度优先搜索的方法得到省份的总数。对于每个城市，如果该城市尚未被访问过，则从该城市开
         * 始广度优先搜索，直到同一个连通分量中的所有城市都被访问到，即可得到一个省份。
         */
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        boolean[] visited = new boolean[isConnected.length];
        Deque<Integer> queue = new ArrayDeque<>();
        int num = 0;
        for (int i = 0; i < isConnected.length; ++i) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < isConnected.length; ++k) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                ++num;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findCircleNum1(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(new Solution().findCircleNum2(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(new Solution().findCircleNum3(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
    }
}
