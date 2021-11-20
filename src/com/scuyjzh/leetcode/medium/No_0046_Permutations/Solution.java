package com.scuyjzh.leetcode.medium.No_0046_Permutations;

import java.util.*;

/**
 * 46. 全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以
 * 按任意顺序 返回答案。
 */
class Solution {
    /**
     * 方法：回溯
     */
    public List<List<Integer>> permute(int[] nums) {
        // 使用一个动态数组保存所有可能的排列
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 布尔数组 visited 初始化为 false，表示这些数还没有被选择
        boolean[] visited = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        dfs(res, nums, path, visited);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, LinkedList<Integer> path, boolean[] visited) {
        // 递归终止条件
        if (path.size() == nums.length) {
            // 拷贝
            res.add(new ArrayList<>(path));
            return;
        }
        // 在还未选择的数中依次选择一个元素作为下一个位置的元素
        for (int i = 0; i < nums.length; ++i) {
            // 判断当前数是否被选择过
            if (visited[i]) {
                continue;
            }

            // 填入当前数
            path.add(nums[i]);
            visited[i] = true;

            // 继续递归填下一个数
            dfs(res, nums, path, visited);

            // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            path.removeLast();
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1, 2, 3}));
        System.out.println(new Solution().permute(new int[]{0, 1}));
        System.out.println(new Solution().permute(new int[]{1}));
    }
}
