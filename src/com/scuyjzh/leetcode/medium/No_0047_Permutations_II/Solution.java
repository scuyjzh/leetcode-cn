package com.scuyjzh.leetcode.medium.No_0047_Permutations_II;

import java.util.*;

/**
 * 47. 全排列 II
 * <p>
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
class Solution {
    /**
     * 方法：回溯 + 剪枝
     * 时间复杂度：O(n×n!)，其中 n 为序列的长度。
     * 空间复杂度：O(n)，其中 n 为序列的长度。
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();

        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);

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
        for (int i = 0; i < nums.length; ++i) {
            // 判断当前数是否被选择过
            if (used[i]) {
                continue;
            }

            // 剪枝条件：
            // i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择，即 nums[i - 1] 还未被使用
            // 因此需要剪枝，否则下面的搜索中还会使用到，就会产生重复
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            // 调试语句 ①
            System.out.println("  递归之前 => " + path);

            dfs(res, nums, path, used);

            // 回溯部分的代码，和 dfs 之前的代码是对称的
            path.removeLast();
            used[i] = false;
            // 调试语句 ②
            System.out.println("递归之后 => " + path);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("输出 => " + solution.permuteUnique(new int[]{1, 1, 1, 2}));
    }
}
