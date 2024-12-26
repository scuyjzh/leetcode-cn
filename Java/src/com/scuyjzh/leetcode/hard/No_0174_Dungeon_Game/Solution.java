package com.scuyjzh.leetcode.hard.No_0174_Dungeon_Game;

import java.util.*;

/**
 * 174. 地下城游戏
 *
 * https://leetcode-cn.com/problems/dungeon-game/
 */
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        /*
         * 几个要素：「M×N 的网格」「每次只能向右或者向下移动一步」，让人很容易想到该题使用动态规划的方
         * 法。
         *
         * 但是发现，如果按照从左上往右下的顺序进行动态规划，对于每一条路径，需要同时记录两个值。
         * 第一个是「从出发点到当前点的路径和」，第二个是「从出发点到当前点所需的最小初始值」。而这两个值
         * 的重要程度相同。
         *
         * 希望「从出发点到当前点的路径和」尽可能大，而「从出发点到当前点所需的最小初始值」尽可能小。
         * 因此，如果按照从左上往右下的顺序进行动态规划，有两个重要程度相同的参数同时影响后续的决策。
         * 也就是说，这样的动态规划是不满足「无后效性」的。
         *
         * 于是考虑从右下往左上进行动态规划。令 dp[i][j] 表示从坐标 (i,j) 到终点所需的最小初始值。换句话
         * 说，当到达坐标 (i,j) 时，如果此时的路径和不小于 dp[i][j]，就能到达终点。
         *
         * 这样一来，就无需担心路径和的问题，只需要关注最小初始值。对于 dp[i][j]，只要关心 dp[i][j+1]
         * 和 dp[i+1][j] 的最小值 minN。记当前格子的值为 dungeon(i,j)，那么在坐标 (i,j) 的初始值只要达到
         * minN−dungeon(i,j) 即可。同时，初始值还必须大于等于 1。这样就可以得到状态转移方程：
         *         dp[i][j] = max(min(dp[i+1][j], dp[i][j+1]) − dungeon(i,j), 1)
         * 最终答案即为 dp[0][0]。
         *
         * 边界条件为，当 i=n−1 或者 j=m−1 时，dp[i][j] 转移需要用到的 dp[i][j+1] 和 dp[i+1][j] 中有无效
         * 值，因此代码实现中给无效值赋值为极大值。特别地，dp[n−1][m−1] 转移需要用到的 dp[n−1][m] 和
         * dp[n][m−1] 均为无效值，因此给这两个值赋值为 1。
         */
        int n = dungeon.length, m = dungeon[0].length;
        // 状态定义：dp[i][j] 表示从坐标 (i,j) 到终点所需的最小初始值
        int[][] dp = new int[n + 1][m + 1];
        // 边界条件：dp[i][j] 转移需要用到的 dp[i][j+1] 和 dp[i+1][j] 中有无效值，因此给无效值赋值为极大值
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 特别地，dp[n−1][m−1] 转移需要用到的 dp[n−1][m] 和 dp[n][m−1] 均为无效值，因此给这两个值赋值为 1
        dp[n][m - 1] = dp[n - 1][m] = 1;
        // 状态转移
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                int minN = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minN - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
    }
}
