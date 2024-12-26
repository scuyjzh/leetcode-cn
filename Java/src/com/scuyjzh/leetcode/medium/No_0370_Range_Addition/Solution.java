package com.scuyjzh.leetcode.medium.No_0370_Range_Addition;

import java.util.*;

/**
 * 370. 区间加法
 *
 * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给
 * 出 k 个更新的操作。
 * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你
 * 需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）
 * 增加 inc。
 * 请你返回 k 次操作后的数组。
 */
class Solution {
    /**
     * 方法：差分数组
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // 定义与原数组对应的差分数组 d
        // 根据定义，原数组元素全为 0，差分数组也全初始化为默认值 0
        int[] d = new int[length];

        // 所有更新操作“映射”到差分数组上进行
        for (int[] u : updates) {
            // 更新左端点
            d[u[0]] += u[2];
            // 更新右端点的下一个位置
            if (u[1] < length - 1) {
                d[u[1] + 1] -= u[2];
            }
        }

        // 根据差分数组从前往后遍历递推来还原原数组
        for (int i = 1; i < length; ++i) {
            d[i] += d[i - 1];
        }
        return d;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().getModifiedArray(5, new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}})));
    }
}
