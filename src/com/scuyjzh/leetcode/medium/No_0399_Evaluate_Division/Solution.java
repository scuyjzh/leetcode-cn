package com.scuyjzh.leetcode.medium.No_0399_Evaluate_Division;

import java.util.*;

/**
 * 399. 除法求值
 *
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条
 * 件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi
 * = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示
 * 第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这
 * 个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用
 * -1.0 替代这个答案。
 *
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，
 * 且不存在任何矛盾的结果。
 */
class Solution {
    /**
     * 方法：Floyd 算法
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 统计所有的变量
        Map<String, Integer> variables = new HashMap<>();
        int nvars = 0;
        for (List<String> equation : equations) {
            if (!variables.containsKey(equation.get(0))) {
                variables.put(equation.get(0), nvars++);
            }
            if (!variables.containsKey(equation.get(1))) {
                variables.put(equation.get(1), nvars++);
            }
        }

        // 构建图的邻接矩阵
        double[][] graph = new double[nvars][nvars];
        for (int i = 0; i < nvars; ++i) {
            Arrays.fill(graph[i], -1.0);
        }
        for (int i = 0; i < equations.size(); ++i) {
            int ia = variables.get(equations.get(i).get(0));
            int ib = variables.get(equations.get(i).get(1));
            graph[ia][ib] = values[i];
            graph[ib][ia] = 1.0 / values[i];
        }

        // 使用 Floyd 算法预先计算出任意两点之间的距离
        for (int k = 0; k < nvars; ++k) {
            for (int i = 0; i < nvars; ++i) {
                for (int j = 0; j < nvars; ++j) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }

        // 直接通过查询矩阵得到答案
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); ++i) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ic = variables.get(query.get(0));
                int id = variables.get(query.get(1));
                if (graph[ic][id] > 0) {
                    result = graph[ic][id];
                }
            }
            res[i] = result;
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));

        double[] values = new double[]{2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));

        System.out.println(Arrays.toString(new Solution().calcEquation(equations, values, queries)));
    }
}
