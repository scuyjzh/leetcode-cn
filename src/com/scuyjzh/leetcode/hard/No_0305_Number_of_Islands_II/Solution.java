package com.scuyjzh.leetcode.hard.No_0305_Number_of_Islands_II;

import java.util.*;

/**
 * 305. 岛屿数量 II
 *
 * 给你一个大小为 m x n 的二进制网格 grid 。网格表示一个地图，其中，
 * 0 表示水，1 表示陆地。最初，grid 中的所有单元格都是水单元格
 * （即，所有单元格都是 0）。
 *
 * 可以通过执行 addLand 操作，将某个位置的水转换成陆地。给你一个数组
 * positions ，其中 positions[i] = [ri, ci] 是要执行第 i 次操作的位
 * 置 (ri, ci) 。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是将单元格 (ri, ci) 转换
 * 为陆地后，地图中岛屿的数量。
 *
 * 岛屿 的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相
 * 邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包
 * 围。
 */
class Solution {
    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        UnionFind(int size) {
            count = 0;
            parent = new int[size];
            rank = new int[size];

            for (int i = 0; i < size; ++i) {
                parent[i] = -1;
                rank[i] = 1;
            }
        }

        int find(int x) {
            while (x != parent[x]) {
                // 路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootY] > rank[rootX]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                ++rank[rootY];
            }

            --count;
        }

        int getCount() {
            return count;
        }

        boolean isValid(int x) {
            return parent[x] != -1;
        }

        void setParent(int x) {
            if (parent[x] != x) {
                ++count;
            }
            parent[x] = x;
        }
    }

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int rows;
    int cols;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        rows = m;
        cols = n;

        List<Integer> ans = new ArrayList<>();

        UnionFind uf = new UnionFind(rows * cols);

        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];

            // 将数组中的二维坐标转化为一维坐标
            int index = row * n + col;

            // 初始化转换位置的 parent 数组
            uf.setParent(index);

            // 检查周围是否有陆地
            List<Integer> neighbours = new ArrayList<>();

            for (int[] dir : directions) {
                int newX = row + dir[0];
                int newY = col + dir[1];

                if (inArea(newX, newY) && uf.isValid(newX * n + newY)) {
                    neighbours.add(newX * n + newY);
                }
            }

            for (int neighbour : neighbours) {
                uf.union(index, neighbour);
            }

            ans.add(uf.getCount());
        }

        return ans;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numIslands2(3, 3, new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}}));
    }
}
