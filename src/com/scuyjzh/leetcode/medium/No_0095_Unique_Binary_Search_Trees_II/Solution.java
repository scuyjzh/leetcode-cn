package com.scuyjzh.leetcode.medium.No_0095_Unique_Binary_Search_Trees_II;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 95. 不同的二叉搜索树 II
 *
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不
 * 相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        /*
         * 二叉搜索树关键的性质是根节点的值大于左子树所有节点的值，小于右子树所有节点的值，且左子树和右子
         * 树也同样为二叉搜索树。因此在生成所有可行的二叉搜索树的时候，假设当前序列长度为 n，如果枚举
         * 根节点的值为 i，那么根据二叉搜索树的性质可以知道左子树的节点值的集合为 [1…i−1]，右子树的
         * 节点值的集合为 [i+1…n]。而左子树和右子树的生成相较于原问题是一个序列长度缩小的子问题，因此
         * 可以想到用回溯的方法来解决这道题目。
         *
         * 定义 generateTrees(start, end) 函数表示当前值的集合为 [start,end]，返回序列 [start,end] 生成的
         * 所有可行的二叉搜索树。按照上文的思路，考虑枚举 [start,end] 中的值 i 为当前二叉搜索树的根，那么
         * 序列划分为了 [start,i−1] 和 [i+1,end] 两部分。递归调用这两部分，即 generateTrees(start, i -
         * 1) 和 generateTrees(i + 1, end)，获得所有可行的左子树和可行的右子树，那么最后一步只要从
         * 可行左子树集合中选一棵，再从可行右子树集合中选一棵拼接到根节点上，并将生成的二叉搜索树放入答案
         * 数组即可。
         *
         * 递归的入口即为 generateTrees(1, n)，出口为当 start>end 的时候，当前二叉搜索树为空，返回空节点
         * 即可。
         */
        if (n == 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; ++i) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode curTree = new TreeNode(i);
                    curTree.left = leftTree;
                    curTree.right = rightTree;
                    allTrees.add(curTree);
                }
            }
        }
        return allTrees;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateTrees(3));
        System.out.println(new Solution().generateTrees(1));
    }
}
