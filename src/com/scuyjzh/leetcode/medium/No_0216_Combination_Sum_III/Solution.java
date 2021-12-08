package com.scuyjzh.leetcode.medium.No_0216_Combination_Sum_III;

import java.util.*;

/**
 * 216. 组合总和 III
 *
 * 找出所有相加之和为n 的k个数的组合。组合中只允许含有 1 -9 的正整数，并且
 * 每种组合中不存在重复的数字。
 * 说明：
 *   • 所有数字都是正整数。
 *   • 解集不能包含重复的组合。
 */
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();

        // 一开始做一些特殊判断
        if (k <= 0 || n <= 0 || k > n) {
            return res;
        }

        // 寻找 n 的上限：[9, 8, ... , (9 - k + 1)]，它们的和为 (19 - k) * k / 2
        // 比上限还大，就不用搜索了：
        if (n > (19 - k) * k / 2) {
            return res;
        }

        LinkedList<Integer> path = new LinkedList<>();
        dfs(k, n, 1, path, res);
        return res;
    }

    /**
     * @param k      剩下要找 k 个数
     * @param remain 剩余多少
     * @param start  下一轮搜索的起始元素是多少
     * @param path   深度优先遍历的路径参数（状态变量）
     * @param res    保存结果集的列表
     */
    private void dfs(int k, int remain, int start, LinkedList<Integer> path, List<List<Integer>> res) {
        // 剪枝：[start, 9] 这个区间里的数都不够 k 个，不用继续往下搜索
        if (start > 10 - k) {
            return;
        }

        if (k == 0) {
            if (remain == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
        }

        // 枚举起点值 [..., 7, 8, 9]
        // 找 3 个数，起点最多到 7
        // 找 2 个数，起点最多到 8
        // 规律是，起点上界 + k = 10，故起点上界 = 10 - k
        for (int i = start; i <= 10 - k; ++i) {
            // 剪枝
            if (remain - i < 0) {
                break;
            }
            path.addLast(i);
            dfs(k - 1, remain - i, i + 1, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum3(3, 7));
        System.out.println(new Solution().combinationSum3(3, 9));
    }
}
