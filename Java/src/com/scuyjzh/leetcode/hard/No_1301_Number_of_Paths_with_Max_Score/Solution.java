package com.scuyjzh.leetcode.hard.No_1301_Number_of_Paths_with_Max_Score;

import java.util.*;

/**
 * 1301. 最大得分的路径数目
 *
 * 给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。
 *
 * 你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字
 * 符 1, 2, ..., 9 或者障碍 'X'。在每一步移动中，你可以向上、向左或
 * 者左上方移动，可以移动的前提是到达的格子没有障碍。
 *
 * 一条路径的 「得分」 定义为：路径上所有数字的和。
 *
 * 请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第
 * 二个整数是得到最大得分的方案数，请把结果对 10^9 + 7 取余。
 *
 * 如果没有任何路径可以到达终点，请返回 [0, 0] 。
 */
class Solution {
    int mod = (int) 1e9 + 7;
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {1, 1}};

    int n;

    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();

        // 将 board 转存成二维数组
        char[][] chs = new char[n][n];
        for (int i = 0; i < n; ++i) {
            chs[i] = board.get(i).toCharArray();
        }

        // f(i)(j) 代表从右下角到位置 (i,j) 路径的「最大得分」
        int[][] f = new int[n][n];
        // g(i)(j) 代表从右下角到位置 (i,j) 并取到最大得分的「方案数」
        int[][] g = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {

                // 状态初始化，如果是在最后一格（即起点位置）：
                // f[i][j] = 0 : 代表在起点得分为 0
                // g[i][j] = 1 : 代表到达起点的路径只有一条，这样就有了一个「有效值」可以滚动下去
                if (i == n - 1 && j == n - 1) {
                    g[i][j] = 1;
                    continue;
                }

                // 如果该位置是「障碍点」，那么对应状态为：
                // f[i][j] = INF : 「障碍点」不可访问，得分为无效值
                // g[i][j] = 0   : 「障碍点」不可访问，路径为 0
                if (chs[i][j] == 'X') {
                    f[i][j] = Integer.MIN_VALUE;
                    continue;
                }

                // 如果是第一个格子（即终点位置），这时候位置得分为 0
                int val = (i == 0 && j == 0) ? 0 : chs[i][j] - '0';

                // score 代表当前转移位置的「最大得分」；num 代表取得最大得分的「方案数」
                int score = Integer.MIN_VALUE, num = 0;
                // 尝试从「下方格子」「右边格子」「右下角格子」进行转移
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (inArea(x, y)) {
                        // 如果当前值大于 score，更新「最大得分」和「方案数」
                        if (f[x][y] > score) {
                            score = f[x][y];
                            num = g[x][y];
                        }
                        // 如果当前值等于 score，增加「方案数」
                        else if (f[x][y] == score) {
                            num += g[x][y];
                            num %= mod;
                        }
                    }
                }

                // 更新 dp 值
                f[i][j] = score == Integer.MIN_VALUE ? Integer.MIN_VALUE : score + val;
                g[i][j] = num;
            }
        }

        // 构造答案
        int[] ans = new int[2];
        // 如果终点不可达（动规值为 INF）时，写入 0
        ans[0] = f[0][0] == Integer.MIN_VALUE ? 0 : f[0][0];
        // 如果终点不可达（动规值为 INF）时，写入 0
        ans[1] = f[0][0] == Integer.MIN_VALUE ? 0 : g[0][0];
        return ans;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("E23");
        list.add("2X2");
        list.add("12S");
        System.out.println(Arrays.toString(new Solution().pathsWithMaxScore(list)));
    }
}
