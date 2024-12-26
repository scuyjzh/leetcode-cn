package com.scuyjzh.leetcode.hard.No_0312_Burst_Balloons;

import java.util.*;

/**
 * 312. 戳气球
 *
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数
 * 字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1]
 * * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表
 * 和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，
 * 那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 */
class Solution {
    /**
     * 方法一：回溯（超出时间限制）
     */
    public int maxCoins1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        List<Integer> arr = new LinkedList<>();
        for (int num : nums) {
            arr.add(num);
        }

        return dfs(arr);
    }

    private int dfs(List<Integer> arr) {
        if (arr.size() == 0) {
            return 0;
        }
        int n = arr.size();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int left = (i - 1 >= 0) ? arr.get(i - 1) : 1;
            int right = (i + 1 < n) ? arr.get(i + 1) : 1;
            int sum = left * arr.get(i) * right;

            // 移除当前气球
            int tmp = arr.remove(i);

            // 递归搜索
            sum += dfs(arr);
            res = Math.max(res, sum);

            // 回溯
            arr.add(i, tmp);
        }
        return res;
    }

    /**
     * 方法二：「自顶向下」的记忆化搜索
     */
    public int maxCoins2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        for (int i = 1; i <= n; ++i) {
            arr[i] = nums[i - 1];
        }

        // 记忆化数组
        int[][] memo = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; ++i) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(memo, arr, 0, n + 1);
    }

    private int dfs(int[][] memo, int[] arr, int left, int right) {
        if (left >= right - 1) {
            return 0;
        }

        if (memo[left][right] != -1) {
            return memo[left][right];
        }

        int res = 0;
        for (int i = left + 1; i < right; ++i) {
            int sum = arr[left] * arr[i] * arr[right];
            sum += dfs(memo, arr, left, i) + dfs(memo, arr, i, right);
            res = Math.max(res, sum);
        }
        memo[left][right] = res;
        return res;
    }

    /**
     * 方法三：「自底向上」的动态规划
     */
    public int maxCoins3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        for (int i = 1; i <= n; ++i) {
            arr[i] = nums[i - 1];
        }

        int[][] memo = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 2; j <= n + 1; ++j) {
                for (int k = i + 1; k < j; ++k) {
                    int sum = arr[i] * arr[k] * arr[j];
                    sum += memo[i][k] + memo[k][j];
                    memo[i][j] = Math.max(memo[i][j], sum);
                }
            }
        }

        return memo[0][n + 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxCoins1(new int[]{3, 1, 5, 8}));
        System.out.println(new Solution().maxCoins2(new int[]{3, 1, 5, 8}));
        System.out.println(new Solution().maxCoins3(new int[]{3, 1, 5, 8}));
    }
}
