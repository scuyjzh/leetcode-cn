package com.scuyjzh.leetcode.medium.No_0311_Sparse_Matrix_Multiplication;

import java.util.*;

/**
 * 311. 稀疏矩阵的乘法
 *
 * 给你两个 稀疏矩阵 A 和 B，请你返回 AB 的结果。你可以默认 A 的列数等
 * 于 B 的行数。
 */
class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        // 稀疏矩阵可以用三元组存储（行标，列标，值）
        Map<Integer, Map<Integer, Integer>> map1 = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> map2 = new HashMap<>();
        // 矩阵 A 的哈希表格式 Map<行标, Map<列标，值>>
        for (int i = 0; i < mat1.length; ++i) {
            map1.put(i, new HashMap<>());
            for (int j = 0; j < mat1[i].length; ++j) {
                if (mat1[i][j] != 0) {
                    map1.get(i).put(j, mat1[i][j]);
                }
            }
        }
        // 矩阵 B 的哈希表格式 Map<列标, Map<行标，值>>
        for (int i = 0; i < mat2.length; ++i) {
            for (int j = 0; j < mat2[i].length; ++j) {
                if (mat2[i][j] != 0) {
                    if (!map2.containsKey(j)) {
                        map2.put(j, new HashMap<>());
                    }
                    map2.get(j).put(i, mat2[i][j]);
                }
            }
        }
        // 矩阵的乘法是第一个矩阵的行和第二个矩阵的列相乘
        int[][] res = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; ++i) {
            for (int j = 0; j < mat2[0].length; ++j) {
                Map<Integer, Integer> row = map1.get(i);
                Map<Integer, Integer> col = map2.get(j);
                for (int key : row.keySet()) {
                    if (col != null && col.containsKey(key)) {
                        res[i][j] += row.get(key) * col.get(key);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().multiply(new int[][]{{1, 0, 0}, {-1, 0, 3}}, new int[][]{{7, 0, 0}, {0, 0, 0}, {0, 0, 1}})));
    }
}
