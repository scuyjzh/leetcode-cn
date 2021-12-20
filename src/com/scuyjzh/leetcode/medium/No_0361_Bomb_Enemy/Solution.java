package com.scuyjzh.leetcode.medium.No_0361_Bomb_Enemy;

/**
 * 361. 轰炸敌人
 *
 * 给你一个大小为 m x n 的矩阵 grid ，其中每个单元格都放置有一个字
 * 符：
 *   • 'W' 表示一堵墙
 *   • 'E' 表示一个敌人
 *   • '0'（数字 0）表示一个空位
 * 返回你使用 一颗炸弹 可以击杀的最大敌人数目。你只能把炸弹放在一个空
 * 位里。
 * 由于炸弹的威力不足以穿透墙体，炸弹只能击杀同一行和同一列没被墙体挡
 * 住的敌人。
 */
class Solution {
    int rows;
    int cols;

    /**
     * 方法一：暴力
     */
    public int maxKilledEnemies1(char[][] grid) {
        /*
         * 暴力枚举所有空位，统计答案，统计答案就是按题意朝 4 个方向延伸，直到碰到墙或者边界停止，统计这中
         * 间碰到的敌人数即可。
         */
        rows = grid.length;
        cols = rows == 0 ? 0 : grid[0].length;
        if (cols == 0) {
            return 0;
        }

        int[] dirX = new int[]{0, 1, 0, -1};
        int[] dirY = new int[]{1, 0, -1, 0};

        int ans = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '0') {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i, ty = j;
                        while (isInRange(tx, ty) && grid[tx][ty] != 'W') {
                            if (grid[tx][ty] == 'E') {
                                cnt += 1;
                            }
                            tx += dirX[k];
                            ty += dirY[k];
                        }
                    }
                    ans = Math.max(ans, cnt);
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：动态规划
     */
    public int maxKilledEnemies2(char[][] grid) {
        /*
         * 考虑上面的暴力，可以发现其实在统计一个空格的答案的时候没有考虑利用已有的信息。考虑第 x
         * 行格子往左延伸答案，定义 ans[i] 为这一行第 i 个格子往左延伸能碰到的敌人数，那么很容易的可以从
         * 前一个格子的答案递推过来，即：
         *                      ans[i]=         0, grid[x][i]=='W'
         *                      ans[i]=  ans[i-1], grid[x][i]=='0'
         *                      ans[i]=ans[i-1]+1, grid[x][i]=='E'
         *
         * 注意到这样只统计了一个方向的答案，但其他三个方向也是一样的道理，统计完四个方向即统计出了这
         * 个位置放炸弹能炸到的敌人数，不用再暴力延伸。
         */
        rows = grid.length;
        cols = rows == 0 ? 0 : grid[0].length;
        if (cols == 0) {
            return 0;
        }

        // dpUp[i][j] 表示在 grid[i][j] 处放置炸弹，向上可以击杀的敌人数目
        int[][] dpUp = new int[rows][cols];
        // dpLeft[i][j] 表示在 grid[i][j] 处放置炸弹，向左可以击杀的敌人数目
        int[][] dpLeft = new int[rows][cols];
        // dpDown[i][j] 表示在 grid[i][j] 处放置炸弹，向下可以击杀的敌人数目
        int[][] dpDown = new int[rows][cols];
        // dpRight[i][j] 表示在 grid[i][j] 处放置炸弹，向右可以击杀的敌人数目
        int[][] dpRight = new int[rows][cols];

        // 计算向上、向左的击杀数目需要从上往下、从左往右遍历矩阵
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 'W') {
                    dpUp[i][j] = 0;
                    dpLeft[i][j] = 0;
                } else {
                    if (grid[i][j] == 'E') {
                        dpUp[i][j] = 1;
                        dpLeft[i][j] = 1;
                    }
                    if (isInRange(i - 1, j)) {
                        dpUp[i][j] += dpUp[i - 1][j];
                    }
                    if (isInRange(i, j - 1)) {
                        dpLeft[i][j] += dpLeft[i][j - 1];
                    }
                }
            }
        }

        // 计算向下、向右的击杀数目需要从下往上、从右往左遍历矩阵
        for (int i = rows - 1; i >= 0; --i) {
            for (int j = cols - 1; j >= 0; --j) {
                if (grid[i][j] == 'W') {
                    dpDown[i][j] = 0;
                    dpRight[i][j] = 0;
                } else {
                    if (grid[i][j] == 'E') {
                        dpDown[i][j] = 1;
                        dpRight[i][j] = 1;
                    }
                    if (isInRange(i + 1, j)) {
                        dpDown[i][j] += dpDown[i + 1][j];
                    }
                    if (isInRange(i, j + 1)) {
                        dpRight[i][j] += dpRight[i][j + 1];
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, dpUp[i][j] + dpDown[i][j] + dpLeft[i][j] + dpRight[i][j]);
                }
            }
        }

        return res;
    }

    private boolean isInRange(int i, int j) {
        return i >= 0 && j >= 0 && i < rows && j < cols;
    }

    /**
     * 方法三：动态规划 + 空间优化
     */
    public int maxKilledEnemies3(char[][] grid) {
        /*
         * 针对 ans[i] 注意到每次只与前一个位置的答案有关，所以不用再开数组，直接用一个 pre 变量存储
         * ans[i−1] 的答案即可。
         */
        rows = grid.length;
        cols = rows == 0 ? 0 : grid[0].length;
        if (cols == 0) {
            return 0;
        }

        // dpUp[i][j] 表示在 grid[i][j] 处放置炸弹可以击杀的敌人数目
        int[][] boom = new int[rows][cols];

        int pre;
        for (int i = 0; i < rows; ++i) {
            pre = 0;
            // 从左往右
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 'W') {
                    pre = 0;
                } else if (grid[i][j] == 'E') {
                    pre += 1;
                }
                boom[i][j] += pre;
            }

            pre = 0;
            // 从右往左
            for (int j = cols - 1; j >= 0; --j) {
                if (grid[i][j] == 'W') {
                    pre = 0;
                } else if (grid[i][j] == 'E') {
                    pre += 1;
                }
                boom[i][j] += pre;
            }
        }

        for (int j = 0; j < cols; ++j) {
            pre = 0;
            // 从上到下
            for (int i = 0; i < rows; ++i) {
                if (grid[i][j] == 'W') {
                    pre = 0;
                } else if (grid[i][j] == 'E') {
                    pre += 1;
                }
                boom[i][j] += pre;
            }

            pre = 0;
            // 从下到上
            for (int i = rows - 1; i >= 0; --i) {
                if (grid[i][j] == 'W') {
                    pre = 0;
                } else if (grid[i][j] == 'E') {
                    pre += 1;
                }
                boom[i][j] += pre;
            }
        }

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, boom[i][j]);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxKilledEnemies1(new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}}));
        System.out.println(new Solution().maxKilledEnemies2(new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}}));
        System.out.println(new Solution().maxKilledEnemies3(new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}}));
    }
}
