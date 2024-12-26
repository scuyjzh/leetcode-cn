package com.scuyjzh.leetcode.medium.No_0256_Paint_House;

import java.util.*;

/**
 * 256. 粉刷房子
 *
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这
 * 三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不
 * 能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花
 * 费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正
 * 整数矩阵 costs 来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1]
 * [2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 *
 * 请计算出粉刷完所有房子最少的花费成本。
 */
class Solution {

    private int[][] costs;
    private Map<String, Integer> memo;

    /**
     * 方法一：记忆化深度优先搜索
     */
    public int minCost1(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        this.costs = costs;
        // 记忆化
        this.memo = new HashMap<>();
        return Math.min(paintCost(0, 0), Math.min(paintCost(0, 1), paintCost(0, 2)));
    }

    private int paintCost(int n, int color) {
        // 首先检查答案是否在字典中。如果是，可以立即得到答案，如果没有则进行计算
        if (memo.containsKey(getKey(n, color))) {
            return memo.get(getKey(n, color));
        }
        int totalCost = costs[n][color];
        if (n == costs.length - 1) {
        }
        // Red
        else if (color == 0) {
            totalCost += Math.min(paintCost(n + 1, 1), paintCost(n + 1, 2));
        }
        // Green
        else if (color == 1) {
            totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 2));
        }
        // Blue
        else {
            totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 1));
        }
        // 记录计算过的值，写入到字典中，其中输入值作为键，返回值为结果
        memo.put(getKey(n, color), totalCost);

        return totalCost;
    }

    private String getKey(int n, int color) {
        return n + "_" + color;
    }

    /**
     * 方法二：动态规划
     */
    public int minCost2(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int length = costs.length;
        int[][] dp = new int[length][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < length; ++i) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return Math.min(dp[length - 1][0], Math.min(dp[length - 1][1], dp[length - 1][2]));
    }

    /**
     * 方法三：动态规划 + 空间优化
     */
    public int minCost3(int[][] costs) {
        int redCost = costs[0][0], blueCost = costs[0][1], greenCost = costs[0][2];
        for (int i = 1; i < costs.length; ++i) {
            int newRedCost = Math.min(blueCost, greenCost) + costs[i][0];
            int newBlueCost = Math.min(redCost, greenCost) + costs[i][1];
            int newGreenCost = Math.min(redCost, blueCost) + costs[i][2];
            redCost = newRedCost;
            blueCost = newBlueCost;
            greenCost = newGreenCost;
        }
        return Math.min(redCost, Math.min(blueCost, greenCost));
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minCost1(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
        System.out.println(new Solution().minCost2(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
        System.out.println(new Solution().minCost3(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
    }
}
