package com.scuyjzh.leetcode.medium.No_0040_Combination_Sum_II;

import java.util.*;

/**
 * 40. 组合总和 II
 *
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以
 * 使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 */
class Solution {
    /**
     * 方法：搜索回溯 + 剪枝
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
     * @param candidates 候选数组
     * @param begin      从候选数组的 begin 位置开始搜索
     * @param target     表示剩余，这个值一开始等于 target，基于题目中说明的"所有数字（包括目标数）都是正整数"这个条件
     * @param path       从根节点到叶子节点的路径
     * @param res        结果列表
     */
    private void dfs(int[] candidates, int begin, int target, LinkedList<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; ++i) {
            // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
            if (target - candidates[i] < 0) {
                break;
            }

            // 小剪枝：同一层相同数值的节点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            // 调试语句 ①
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(candidates, i + 1, target - candidates[i], path, res);

            path.removeLast();
            // 调试语句 ②
            System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
        }
    }

    public static void main(String[] args) {
        System.out.println("输出 => " + new Solution().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println("输出 => " + new Solution().combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }
}
