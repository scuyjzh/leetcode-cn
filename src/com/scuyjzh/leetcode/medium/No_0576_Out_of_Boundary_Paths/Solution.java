package com.scuyjzh.leetcode.medium.No_0576_Out_of_Boundary_Paths;

/**
 * 576. 出界的路径数
 *
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow,
 * startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过
 * 网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 *
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出
 * 并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 10^9
 * + 7 取余 后的结果。
 */
class Solution {
    int mod = (int) 1e9 + 7;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int m, n, maxMove;
    int[][][] cache;

    /**
     * 方法一：记忆化搜索
     */
    public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        this.maxMove = maxMove;

        cache = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k <= maxMove; ++k) {
                    cache[i][j][k] = -1;
                }
            }
        }

        return dfs(startRow, startColumn, maxMove);
    }

    private int dfs(int row, int col, int k) {
        // 越界就说明找到了一条路径
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 1;
        }

        // 没有移动次数了，返回 0
        if (k == 0) {
            return 0;
        }

        // 缓存中存在
        if (cache[row][col][k] != -1) {
            return cache[row][col][k];
        }

        // 剪枝：如果小球不管怎么移动都无法移出网格，就返回 0
        if (row - k >= 0 && col - k >= 0 && row + k < m && col + k < n) {
            return 0;
        }

        // 从这个点出发的符合条件的路径数量
        int ans = 0;
        for (int[] dir : dirs) {
            ans += dfs(row + dir[0], col + dir[1], k - 1);
            ans %= mod;
        }

        // 从这个点出发的符合条件的路径数量
        cache[row][col][k] = ans;

        return ans;
    }

    /**
     * 方法二：动态规划
     */
    public int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        this.maxMove = maxMove;

        // 根据「记忆化搜索」，设计一个三维数组 dp[][][]：
        // 第一维代表 DFS 可变参数中的 row 所对应 idx。取值范围为 [0,m)
        // 第二维代表 DFS 可变参数中的 col 所对应 idx。取值范围为 [0,n)
        // 第三维代表 DFS 可变参数中的 k。取值范围为 [1,maxMove]
        // 根据 dp 数组中的维度设计和存储目标值，可以得知「状态定义」为：
        // dp[i][j][k] 代表从位置 (i,j) 出发，可用步数不超过 k 时的路径数量
        int[][][] dp = new int[m][n][maxMove + 1];
        // 初始化边缘格子的路径数量
        for (int k = 1; k <= maxMove; ++k) {
            for (int row = 0; row < m; ++row) {
                for (int col = 0; col < n; ++col) {
                    if (row == 0) {
                        ++dp[row][col][k];
                    }
                    if (row == m - 1) {
                        ++dp[row][col][k];
                    }
                    if (col == 0) {
                        ++dp[row][col][k];
                    }
                    if (col == n - 1) {
                        ++dp[row][col][k];
                    }

                    for (int[] dir : dirs) {
                        int newRow = row + dir[0];
                        int newCol = col + dir[1];
                        if (inArea(newRow, newCol)) {
                            dp[row][col][k] = (dp[row][col][k] + dp[newRow][newCol][k - 1]) % mod;
                        }
                    }
                }
            }
        }

        return dp[startRow][startColumn][maxMove];
    }

    /**
     * 方法三：动态规划 + 空间优化
     */
    public int findPaths3(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        this.maxMove = maxMove;

        int[][] dp = new int[m][n];
        for (int k = 1; k <= maxMove; ++k) {
            int[][] tmp = new int[m][n];
            for (int row = 0; row < m; ++row) {
                for (int col = 0; col < n; ++col) {
                    if (row == 0) {
                        ++tmp[row][col];
                    }
                    if (row == m - 1) {
                        ++tmp[row][col];
                    }
                    if (col == 0) {
                        ++tmp[row][col];
                    }
                    if (col == n - 1) {
                        ++tmp[row][col];
                    }

                    for (int[] dir : dirs) {
                        int newRow = row + dir[0];
                        int newCol = col + dir[1];
                        if (inArea(newRow, newCol)) {
                            tmp[row][col] = (tmp[row][col] + dp[newRow][newCol]) % mod;
                        }
                    }
                }
            }

            dp = tmp;
        }

        return dp[startRow][startColumn];
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findPaths1(2, 2, 2, 0, 0));
        System.out.println(new Solution().findPaths2(2, 2, 2, 0, 0));
        System.out.println(new Solution().findPaths3(2, 2, 2, 0, 0));
    }
}
