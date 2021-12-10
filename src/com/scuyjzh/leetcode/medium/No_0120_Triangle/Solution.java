package com.scuyjzh.leetcode.medium.No_0120_Triangle;

import java.util.*;

/**
 * 120. 三角形最小路径和
 *
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标
 * 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是
 * 说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标
 * i 或 i + 1 。
 * 进阶：
 *   • 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 */
class Solution {
    /**
     * 方法一：动态规划
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        /*
         * 在本题中，给定的三角形的行数为 n，并且第 i 行（从 0 开始编号）包含了 i+1 个数。如果将每一行的左端
         * 对齐，那么会形成一个等腰直角三角形，如下所示：
         *   [2]
         *   [3,4]
         *   [6,5,7]
         *   [4,1,8,3]
         *
         * 用 f[i][j] 表示从三角形顶部走到位置 (i,j) 的最小路径和。这里的位置 (i,j) 指的是三角形中第 i 行第 j
         * 列（均从 0 开始编号）的位置。
         *
         * 由于每一步只能移动到下一行「相邻的节点」上，因此要想走到位置 (i,j)，上一步就只能在位置 (i−1,j−1)
         * 或者位置 (i−1,j)。在这两个位置中选择一个路径和较小的来进行转移，状态转移方程为：
         *         f[i][j] = min(f[i−1][j−1], f[i−1][j]) + c[i][j]
         * 其中 c[i][j] 表示位置 (i,j) 对应的元素值。
         *
         * 注意第 i 行有 i+1 个元素，它们对应的 j 的范围为 [0,i]。当 j=0 或 j=i 时，上述状态转移方程中有一些
         * 项是没有意义的。
         *   • 当 j=0 时，f[i−1][j−1] 没有意义，因此状态转移方程为：
         *         f[i][0] = f[i−1][0] + c[i][0]
         *     即当在第 i 行的最左侧时，只能从第 i−1 行的最左侧移动过来。
         *   • 当 j=i 时，f[i−1][j] 没有意义，因此状态转移方程为：
         *         f[i][i] = f[i−1][i−1] + c[i][i]
         *     即当在第 i 行的最右侧时，只能从第 i−1 行的最右侧移动过来。
         *
         * 最终的答案即为 f[n−1][0] 到 f[n−1][n−1] 中的最小值，其中 n 是三角形的行数。
         *
         * 状态转移方程的边界条件是什么？由于已经去除了所有「没有意义」的状态，因此边界条件可以定为：
         *         f[0][0]=c[0][0]
         * 即在三角形的顶部时，最小路径和就等于对应位置的元素值。这样一来，从 1 开始递增地枚举 i，并在
         * [0,i] 的范围内递增地枚举 j，就可以完成所有状态的计算。
         */
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }

    /**
     * 方法二：动态规划 + 空间优化
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        // 空间复杂度为 O(n)，使用了 2n 的空间存储状态
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }

    /**
     * 方法三：动态规划 + 空间优化
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        // 空间复杂度仍然为 O(n)，但只使用了 n 的空间存储状态，减少了一半的空间消耗
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(new Solution().minimumTotal1(triangle));
        System.out.println(new Solution().minimumTotal2(triangle));
        System.out.println(new Solution().minimumTotal3(triangle));
    }
}
