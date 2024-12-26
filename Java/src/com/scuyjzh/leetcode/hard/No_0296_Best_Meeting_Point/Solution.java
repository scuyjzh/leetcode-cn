package com.scuyjzh.leetcode.hard.No_0296_Best_Meeting_Point;

import java.util.*;

/**
 * 296. 最佳的碰头地点
 *
 * 有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的
 * 总行走距离。
 * 给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。
 * 1 表示某个人的家所处的位置。这里，将使用 曼哈顿距离 来计算，其
 * 中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|。
 */
class Solution {
    class Point {
        int row;
        int col;
        int distance;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    /**
     * 方法一：广度优先搜索
     */
    public int minTotalDistance1(int[][] grid) {
        /*
         * 一种暴力的解法是枚举网格中所有可能的相遇地点。从每个点出发做广度优先搜索。
         *
         * 在将某个点放进队列时，需要记录从相遇点到这个点的曼哈顿距离。同时，需要一个额外的数组
         * visited 记录哪些点已经被访问过了，以避免将一个已经访问过的点重复放入队列。
         */
        int minDistance = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                int distance = bfs(grid, row, col);
                minDistance = Math.min(distance, minDistance);
            }
        }
        return minDistance;
    }

    private int bfs(int[][] grid, int row, int col) {
        Queue<Point> queue = new ArrayDeque<>();
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        queue.offer(new Point(row, col, 0));
        int totalDistance = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int r = point.row;
            int c = point.col;
            int d = point.distance;
            if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c]) {
                continue;
            }
            if (grid[r][c] == 1) {
                totalDistance += d;
            }
            visited[r][c] = true;
            queue.offer(new Point(r + 1, c, d + 1));
            queue.offer(new Point(r - 1, c, d + 1));
            queue.offer(new Point(r, c + 1, d + 1));
            queue.offer(new Point(r, c - 1, d + 1));
        }
        return totalDistance;
    }

    /**
     * 方法二：曼哈顿距离公式
     */
    public int minTotalDistance2(int[][] grid) {
        /*
         * 另一种暴力的解法是使用曼哈顿距离公式。可以直接用以下公式来计算曼哈顿距离：
         *          distance(p1,p2)=∣p2.x−p1.x∣+∣p2.y−p1.y∣
         */
        List<Point> points = getAllPoints(grid);
        int minDistance = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                int distance = calculateDistance(points, row, col);
                minDistance = Math.min(distance, minDistance);
            }
        }
        return minDistance;
    }

    private int calculateDistance(List<Point> points, int row, int col) {
        int distance = 0;
        for (Point point : points) {
            distance += Math.abs(point.row - row) + Math.abs(point.col - col);
        }
        return distance;
    }

    private List<Point> getAllPoints(int[][] grid) {
        List<Point> points = new ArrayList<>();
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                if (grid[row][col] == 1) {
                    points.add(new Point(row, col));
                }
            }
        }
        return points;
    }

    /**
     * 方法三：排序 + 中位数
     */
    public int minTotalDistance3(int[][] grid) {
        /*
         * 在二维格子里找到相遇点看起来似乎很困难。退一步考虑一维的情况，这会让问题更简单。注意到曼
         * 哈顿距离其实是两个独立变量的子问题的和。因此只要解决一维的情况，就可以把二维的情况当做
         * 两个一维独立的子问题的和。
         *
         * 实现方法是十分直接的。首先，将行和列的坐标收集并排序，然后选择它们中间的元素。然后计算两个
         * 独立的一维子问题的距离之和，就是答案。
         */
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                    cols.add(col);
                }
            }
        }
        // 因为是从上往下遍历，所以行的坐标已经有序
        int row = rows.get(rows.size() / 2);
        Collections.sort(cols);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    /**
     * 方法四：按顺序收集坐标
     */
    public int minTotalDistance4(int[][] grid) {
        /*
         * 可以使用 快速选择算法 在 O(mn) 的时间内得到中位数，但还有更简单的方法。可以按顺序收集行
         * 和列的坐标，就不需要排序了。
         */
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        int row = rows.get(rows.size() / 2);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; ++col) {
            for (int row = 0; row < grid.length; ++row) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }

    /**
     * 方法五：按顺序收集坐标 + 双指针
     */
    public int minTotalDistance5(int[][] grid) {
        /*
         * 还可以直接用两个指针的方法来计算距离而不需要知道中位数。
         */
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        return minDistance1D(rows) + minDistance1D(cols);
    }

    private int minDistance1D(List<Integer> points) {
        int distance = 0;
        int i = 0;
        int j = points.size() - 1;
        while (i < j) {
            distance += points.get(j) - points.get(i);
            ++i;
            --j;
        }
        return distance;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minTotalDistance1(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(new Solution().minTotalDistance2(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(new Solution().minTotalDistance3(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(new Solution().minTotalDistance4(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(new Solution().minTotalDistance5(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
    }
}
