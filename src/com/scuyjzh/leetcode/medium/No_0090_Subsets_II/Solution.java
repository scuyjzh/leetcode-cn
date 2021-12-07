package com.scuyjzh.leetcode.medium.No_0090_Subsets_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. 子集 II
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子
 * 集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
class Solution {
    /**
     * 方法一：回溯 + 剪枝
     */
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        LinkedList<Integer> path = new LinkedList<>();

        // 排序是剪枝的前提
        Arrays.sort(nums);

        dfs(0, nums, res, path);
        return res;
    }

    private void dfs(int startIndex, int[] nums, List<List<Integer>> res, LinkedList<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int idx = startIndex; idx < nums.length; ++idx) {
            // 剪枝
            if (idx > startIndex && nums[idx] == nums[idx - 1]) {
                continue;
            }

            path.add(nums[idx]);
            // 调试语句 ①
            System.out.println("  递归之前 => " + path);

            dfs(idx + 1, nums, res, path);

            path.removeLast();
            // 调试语句 ②
            System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 方法二：位运算
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        /*
         * 考虑数组 [1,2,2]，选择前两个数，或者第一、三个数，都会得到相同的子集。
         * 也就是说，对于当前选择的数 x，若前面有与其相同的数 y，且没有选择 y，此时包含 x 的子集，必然会出
         * 现在包含 y 的所有子集中。
         * 可以通过判断这种情况，来避免生成重复的子集。代码实现时，可以先将数组排序；迭代时，若发现没
         * 有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集。
         * 例如，nums={1,2,2} 时：
         *   剪枝    0/1 序列      子集          0/1 序列对应的二进制数
         *           000          {}            0
         *           001          {1}           1
         *           010          {2}           2
         *           011          {1,2}         3
         *   cut     100          {2}           4
         *   cut     101          {1,2}         5
         *           110          {2,2}         6
         *           111          {1,2,2}       7
         */
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        // 排序是去重的前提
        Arrays.sort(nums);
        int len = nums.length;
        for (int mask = 0; mask < (1 << len); ++mask) {
            // 清零
            path.clear();
            boolean flag = true;
            for (int i = 0; i < len; ++i) {
                if ((mask & (1 << i)) != 0) {
                    // 若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集
                    if (i > 0 && (mask & (1 << (i - 1))) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    path.add(nums[i]);
                }
            }
            if (flag) {
                // 调试语句
                System.out.println("0/1 序列对应的二进制数 => " + mask);
                System.out.println("子集 => " + path);
                res.add(new ArrayList<>(path));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("输出 => " + new Solution().subsetsWithDup1(new int[]{1, 2, 2}));
        System.out.println("输出 => " + new Solution().subsetsWithDup2(new int[]{0}));
    }
}
