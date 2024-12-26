package com.scuyjzh.leetcode.medium.No_0096_Unique_Binary_Search_Trees;

/**
 * 96. 不同的二叉搜索树
 *
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜
 * 索树 有多少种？返回满足题意的二叉搜索树的种数。
 */
class Solution {
    /**
     * 方法：动态规划
     */
    public int numTrees(int n) {
        /*
         * 给定一个有序序列 1⋯n，为了构建出一棵二叉搜索树，可以遍历每个数字 i，将该数字作为树根，将
         * 1⋯(i−1) 序列作为左子树，将 (i+1)⋯n 序列作为右子树。接着可以按照同样的方式递归构建左子
         * 树和右子树。
         *
         * 在上述构建的过程中，由于根的值不同，因此能保证每棵二叉搜索树是唯一的。
         * 由此可见，原问题可以分解成规模较小的两个子问题，且子问题的解可以复用。因此，可以想到使用动
         * 态规划来求解本题。
         *
         * 题目要求是计算不同二叉搜索树的个数。为此，可以定义两个函数：
         *   1.G(n): 长度为 n 的序列能构成的不同二叉搜索树的个数。
         *   2.F(i,n): 以 i 为根、序列长度为 n 的不同二叉搜索树个数 (1≤i≤n)。
         * 可见，G(n) 是求解需要的函数。
         *
         * 根据上述思路，不同的二叉搜索树的总数 G(n)，是对遍历所有 i (1≤i≤n) 的 F(i,n) 之和。
         * 对于边界情况，当序列长度为 1（只有根）或为 0（空树）时，只有一种情况，即：
         *     G(0)=1,G(1)=1。
         * 给定序列 1⋯n，选择数字 i 作为根，则根为 i 的所有二叉搜索树的集合是左子树集合和右子树集合的
         * 笛卡尔积，对于笛卡尔积中的每个元素，加上根节点之后形成完整的二叉搜索树，即：
         *     F(i,n)=G(i−1)⋅G(n−i)。
         *
         * 至此，从小到大计算 G 函数即可，因为 G(n) 的值依赖于 G(0)⋯G(n−1)。
         */
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numTrees(3));
        System.out.println(new Solution().numTrees(1));
    }
}