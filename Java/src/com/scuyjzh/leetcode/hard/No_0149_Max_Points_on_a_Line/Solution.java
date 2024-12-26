package com.scuyjzh.leetcode.hard.No_0149_Max_Points_on_a_Line;

import java.util.*;

/**
 * 149. 直线上最多的点数
 *
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的
 * 一个点。求最多有多少个点在同一条直线上。
 */
class Solution {
    /**
     * 方法一：朴素解法（枚举直线 + 枚举统计）
     */
    public int maxPoints1(int[][] points) {
        /*
         * 一个朴素的做法是先枚举两条点（确定一条线），然后检查其余点是否落在该线中。
         *
         * 为了避免除法精度问题，当枚举两个点 i 和 j 时，不直接计算其对应直线的 斜率 和 截距，而是通过
         * 判断 i 和 j 与第三个点 k 形成的两条直线斜率是否相等（斜率相等的两条直线要么平行，要么重合）。
         */
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            int[] x = points[i];
            for (int j = i + 1; j < n; ++j) {
                int[] y = points[j];
                int cnt = 2;
                for (int k = j + 1; k < n; ++k) {
                    int[] p = points[k];
                    int s1 = (y[1] - x[1]) * (p[0] - y[0]);
                    int s2 = (p[1] - y[1]) * (y[0] - x[0]);
                    if (s1 == s2) {
                        ++cnt;
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    /**
     * 方法二：优化（枚举直线 + 哈希表统计）
     */
    public int maxPoints2(int[][] points) {
        /*
         * 根据「朴素解法」的思路，枚举所有直线的过程不可避免，但统计点数的过程可以优化。
         *
         * 具体的，可以先枚举所有可能出现的 直线斜率（根据两点确定一条直线，即枚举所有的「点对」），
         * 使用「哈希表」统计所有 斜率 对应的点的数量，在所有值中取个 max 即是答案。
         *
         * 一些细节：在使用「哈希表」进行保存时，为了避免精度问题，直接使用字符串进行保存，同时需要将
         * 斜率 约干净。
         */
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            Map<String, Integer> map = new HashMap<>();
            // 由当前点 i 发出的直线所经过的最多点数量
            int max = 0;
            for (int j = i + 1; j < n; ++j) {
                int a = points[i][0] - points[j][0];
                int b = points[i][1] - points[j][1];
                int k = gcd(a, b);
                String key = (a / k) + "_" + (b / k);
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            ans = Math.max(ans, max + 1);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxPoints1(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println(new Solution().maxPoints2(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
    }
}
