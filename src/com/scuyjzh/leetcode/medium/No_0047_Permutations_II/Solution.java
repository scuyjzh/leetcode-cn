package com.scuyjzh.leetcode.medium.No_0047_Permutations_II;

import java.util.*;

/**
 * 47. 全排列 II
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全
 * 排列。
 */
class Solution {
    /**
     * 方法：回溯 + 剪枝
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] visited = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();

        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);

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
        for (int i = 0; i < nums.length; ++i) {
            // 判断当前数是否被选择过
            if (visited[i]) {
                continue;
            }

            // 剪枝条件：
            // i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !visited[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择（回撤），即此时 nums[i - 1] 还未被使用
            // 因此需要剪枝，否则下面的搜索中还会使用到，就会产生重复
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            // 填入当前数并记录访问状态
            path.add(nums[i]);
            visited[i] = true;

            // 继续递归填下一个数
            dfs(res, nums, path, visited);

            // 回溯部分的代码，和 dfs 之前的代码是对称的
            path.removeLast();
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permuteUnique(new int[]{1, 1, 2}));
        System.out.println(new Solution().permuteUnique(new int[]{1, 2, 3}));
    }
}
