package com.scuyjzh.leetcode.medium.No_0039_Combination_Sum;

import java.util.*;

/**
 * 39. 组合总和
 *
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，找
 * 出candidates中所有可以使数字和为目标数target的唯一组合。
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，
 * 则两种组合是唯一的。
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 */
class Solution {
    /**
     * 方法：搜索回溯 + 剪枝
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) {
            return res;
        }

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        LinkedList<Integer> path = new LinkedList<>();
        dfs(candidates, 0, target, path, res);
        return res;
    }

    /**
     * 搜索回溯
     *
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param target     每减去一个元素，目标值变小
     * @param path       从根节点到叶子节点的路径
     * @param res        结果列表
     */
    private void dfs(int[] candidates, int begin, int target, LinkedList<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意，不再搜索产生同一层节点已经使用过的 candidate 里的元素
        for (int i = begin; i < candidates.length; ++i) {
            // 重点理解这里剪枝，前提是候选数组已经有序
            if (target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, target - candidates[i], path, res);

            // 状态重置
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }

    public static void main(String[] args) {
        System.out.println("输出 => " + new Solution().combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println("输出 => " + new Solution().combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println("输出 => " + new Solution().combinationSum(new int[]{2}, 1));
    }
}
