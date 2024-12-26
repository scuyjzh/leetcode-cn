package com.scuyjzh.leetcode.medium.No_0279_Perfect_Squares;

import java.util.*;

/**
 * 279. 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它
 * 们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等
 * 于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3
 * 和 11 不是。
 */
class Solution {
    int INF = -1;

    public int numSquares1(int n) {
        // 完全背包（朴素解法）
        // 给定了若干个数字，每个数字可以被使用无限次，求凑出目标值所需要用到的是最少数字个数是多少。

        // 预处理出所有可能用到的「完全平方数」
        List<Integer> squares = new ArrayList<>();
        int num = 1;
        while (num * num <= n) {
            squares.add(num * num);
            ++num;
        }

        // f[i][j] 代表考虑前 i 个物品，凑出 j 所使用到的最小元素个数
        int len = squares.size();
        int[][] f = new int[len][n + 1];

        // 处理第一个数的情况（i=0）
        for (int j = 0; j <= n; ++j) {
            int t = squares.get(0);
            int k = j / t;
            if (k * t == j) {
                // 只有容量为第一个数的整数倍的才能凑出
                f[0][j] = k;
            } else {
                // 其余则为无效值
                f[0][j] = INF;
            }
        }

        // 处理剩余数的情况（1≤i<len）
        for (int i = 1; i < len; ++i) {
            int t = squares.get(i);
            // 状态转移方程：f[i][j]=min(f[i-1][j-k*t]+k), 0≤k*t<j
            for (int j = 0; j <= n; ++j) {

                // 对于不选第 i 个数的情况
                f[i][j] = f[i - 1][j];

                // 对于选 k 次第 i 个数的情况
                for (int k = 1; k * t <= j; ++k) {
                    // 能够选择 k 个 t 的前提是剩余的数字 j - k * t 也能被凑出
                    if (f[i - 1][j - k * t] != INF) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][j - k * t] + k);
                    }
                }
            }
        }

        return f[len - 1][n];
    }

    public int numSquares2(int n) {
        // 完全背包（进阶）

        // 预处理出所有可能用到的「完全平方数」
        List<Integer> squares = new ArrayList<>();
        int num = 1;
        while (num * num <= n) {
            squares.add(num * num);
            ++num;
        }

        // f[j] 代表考虑到当前物品为止，凑出 j 所使用到的最小元素个数
        int len = squares.size();
        int[] f = new int[n + 1];

        // 处理第一个数的情况（i=0）
        for (int j = 0; j <= n; ++j) {
            int t = squares.get(0);
            int k = j / t;
            if (k * t == j) {
                // 只有容量为第一个数的整数倍的才能凑出
                f[j] = k;
            } else {
                // 其余则为无效值
                f[j] = INF;
            }
        }

        // 处理剩余数的情况（1≤i<len）
        for (int i = 1; i < len; ++i) {
            int t = squares.get(i);
            // 状态转移方程：f[j]=min(f[j],f[j-t]+1)
            for (int j = t; j <= n; ++j) {
                // 当不更新 f[j] 的时候，对应了二维表示中的 f[i - 1][j]

                // 可以更新 f[j] 的前提是：剩余的 j - k * t 也能够被凑出
                // 更新 f[j] 所依赖的 f[j - t] 对应了二维表示中的 f[i - 1][j - k * t]
                if (f[j - t] != INF) {
                    f[j] = Math.min(f[j], f[j - t] + 1);
                }
            }
        }

        return f[n];
    }

    public int numSquares3(int n) {
        // f[i] 表示最少需要多少个数的平方来表示整数 i
        // 其中 f[0]=0 为边界条件，实际上无法表示数字 0，只是为了保证状态转移过程中遇到 j*j==i 的情况合法
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                min = Math.min(min, f[i - j * j]);
            }
            f[i] = min + 1;
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numSquares1(12));
        System.out.println(new Solution().numSquares2(12));
        System.out.println(new Solution().numSquares3(12));
    }
}
