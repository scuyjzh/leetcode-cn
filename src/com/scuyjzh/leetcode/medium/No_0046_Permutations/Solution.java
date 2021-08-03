package com.scuyjzh.leetcode.medium.No_0046_Permutations;

import java.util.*;

/**
 * 46. 全排列
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
class Solution {
    /**
     * 方法：回溯
     * 时间复杂度：O(n×n!)，其中 n 为序列的长度。
     * 空间复杂度：O(n)，其中 n 为序列的长度。
     */
    public List<List<Integer>> permute(int[] nums) {
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        dfs(res, nums, path, used);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, LinkedList<Integer> path, boolean[] used) {
        // 递归终止条件
        if (path.size() == nums.length) {
            // 拷贝
            res.add(new ArrayList<>(path));
            return;
        }
        // 在还未选择的数中依次选择一个元素作为下一个位置的元素
        for (int i = 0; i < nums.length; ++i) {
            // 判断当前数是否被选择过
            if (used[i]) {
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            // 调试语句 ①
            System.out.println("  递归之前 => " + path);

            dfs(res, nums, path, used);

            // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            path.removeLast();
            used[i] = false;
            // 调试语句 ②
            System.out.println("递归之后 => " + path);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("输出 => " + solution.permute(new int[]{1, 2, 3}));
    }
}
