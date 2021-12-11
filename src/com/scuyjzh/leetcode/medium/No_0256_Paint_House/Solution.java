package com.scuyjzh.leetcode.medium.No_0256_Paint_House;

import java.util.*;

/**
 * 256. 粉刷房子
 *
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这
 * 三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不
 * 能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花
 * 费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正
 * 整数矩阵 costs 来表示的。
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1]
 * [2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 * 请计算出粉刷完所有房子最少的花费成本。
 */
class Solution {

    private int[][] costs;
    private Map<String, Integer> memo;

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
        return n + " " + color;
    }

    public int minCost2(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }

        // 自底向上的动态规划
        for (int n = costs.length - 2; n >= 0; --n) {
            // Total cost of painting the nth house red.
            costs[n][0] += Math.min(costs[n + 1][1], costs[n + 1][2]);
            // Total cost of painting the nth house green.
            costs[n][1] += Math.min(costs[n + 1][0], costs[n + 1][2]);
            // Total cost of painting the nth house blue.
            costs[n][2] += Math.min(costs[n + 1][0], costs[n + 1][1]);
        }

        return Math.min(Math.min(costs[0][0], costs[0][1]), costs[0][2]);
    }

    public int minCost3(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }

        // 使用 3 个长度的数组
        int[] previousRow = costs[costs.length - 1];

        for (int n = costs.length - 2; n >= 0; --n) {
            // 通过添加前一行的值来更新当前行中的值
            int[] currentRow = costs[n].clone();
            // Total cost of painting the nth house red.
            currentRow[0] += Math.min(previousRow[1], previousRow[2]);
            // Total cost of painting the nth house green.
            currentRow[1] += Math.min(previousRow[0], previousRow[2]);
            // Total cost of painting the nth house blue.
            currentRow[2] += Math.min(previousRow[0], previousRow[1]);
            // 将前一行设置为当前行，并转到下一个值。重复该过程
            previousRow = currentRow;
        }

        return Math.min(Math.min(previousRow[0], previousRow[1]), previousRow[2]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minCost3(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
    }
}
