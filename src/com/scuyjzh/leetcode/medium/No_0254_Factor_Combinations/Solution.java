package com.scuyjzh.leetcode.medium.No_0254_Factor_Combinations;

import java.util.*;

/**
 * 254. 因子的组合
 *
 * 整数可以被看作是其因子的乘积。
 * 例如：
 *   8 = 2 x 2 x 2;
 *     = 2 x 4.
 * 请实现一个函数，该函数接收一个整数 n并返回该整数所有的因子组合。
 * 注意：
 *   1.你可以假定 n 为永远为正数。
 *   2.因子必须大于 1 并且小于 n。
 */
class Solution {
    /**
     * 方法：搜索回溯
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 2) {
            return res;
        }

        dfs(n, 2, new LinkedList<>(), res);
        return res;
    }

    private void dfs(int n, int start, LinkedList<Integer> path, List<List<Integer>> res) {
        for (int i = start; i * i <= n; ++i) {
            // 能分解，一定是取余为 0
            if (n % i == 0) {
                // 一次将分解的两数计入结果
                path.add(i);
                path.add(n / i);
                res.add(new ArrayList<>(path));
                // 最后把大因数取出来
                path.removeLast();
                // 继续回溯，大因数作为下一次回溯界限
                dfs(n / i, i, path, res);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getFactors(1));
        System.out.println(new Solution().getFactors(37));
        System.out.println(new Solution().getFactors(12));
        System.out.println(new Solution().getFactors(32));
    }
}
