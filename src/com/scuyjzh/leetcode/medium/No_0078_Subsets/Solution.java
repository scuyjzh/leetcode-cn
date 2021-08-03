package com.scuyjzh.leetcode.medium.No_0078_Subsets;

import java.util.*;

/**
 * 78. 子集
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
class Solution {
    /**
     * 方法一：回溯
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        LinkedList<Integer> path = new LinkedList<>();
        dfs(0, nums, res, path);
        return res;
    }

    private void dfs(int startIndex, int[] nums, List<List<Integer>> res, LinkedList<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int idx = startIndex; idx < nums.length; ++idx) {
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
     * 时间复杂度：O(n×2^n)。一共 2^n 个状态，每种状态需要 O(n) 的时间来构造子集。
     * 空间复杂度：O(n)。即构造子集使用的临时数组 path 的空间代价。
     */
    public List<List<Integer>> subsets2(int[] nums) {
        /*
         * 思路：
         * 记原序列中元素的总数为 n。原序列中的每个数字 a_i 的状态可能有两种，即「在子集中」和「不在子集中」。
         * 用 1 表示「在子集中」，0 表示不在子集中，那么每一个子集可以对应一个长度为 n 的 0/1 序列，第 i 位表示 a_i 是否在子集中。
         * 例如，n=3 ，a={5,2,9} 时：
         *   0/1 序列      子集          0/1 序列对应的二进制数
         *   000          {}            0
         *   001          {5}           1
         *   010          {2}           2
         *   011          {5,2}         3
         *   100          {9}           4
         *   101          {5,9}         5
         *   110          {2,9}         6
         *   111          {5,2,9}       7
         * 可以发现 0/1 序列对应的二进制数正好从 0 到 2^n - 1。
         *
         * 算法：
         * 可以枚举 mask∈[0, 2^n−1]，mask 的二进制表示是一个 0/1 序列，可以按照这个 0/1 序列在原集合当中取数。
         * 当枚举完所有 2^n 个 mask，也就能构造出所有的子集。
         */
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        int len = nums.length;
        for (int mask = 0; mask < (1 << len); ++mask) {
            // 清零
            path.clear();
            for (int i = 0; i < len; ++i) {
                if ((mask & (1 << i)) != 0) {
                    path.add(nums[i]);
                }
            }
            // 调试语句
            System.out.println("0/1 序列对应的二进制数 => " + mask);
            System.out.println("子集 => " + path);
            res.add(new ArrayList<>(path));
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("输出 => " + solution.subsets1(new int[]{1, 2, 3}));
        System.out.println("输出 => " + solution.subsets2(new int[]{5, 2, 9}));
    }
}
