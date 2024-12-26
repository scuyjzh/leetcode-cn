package com.scuyjzh.leetcode.medium.No_0298_Binary_Tree_Longest_Consecutive_Sequence;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 298. 二叉树最长连续序列
 *
 * 给你一棵指定的二叉树的根节点 root ，请你计算其中 最长连续序列路径
 * 的长度。
 * 最长连续序列路径 是依次递增 1 的路径。该路径，可以是从某个初始节点
 * 到树中任意节点，通过「父 - 子」关系连接而产生的任意路径。且必须从父
 * 节点到子节点，反过来是不可以的。
 */
class Solution {
    /**
     * 方法一：自顶向下深度优先搜索
     */
    private int maxLength1 = 0;

    public int longestConsecutive1(TreeNode root) {
        /*
         * 一个自顶向下的搜索方法与前序遍历类似。用一个变量 length 保存当前连续的路径长度并将这个变量
         * 沿着树传递。当遍历的时候，比较当前节点和父节点是否是连续的。如果不是，将长度重置为 1 。
         */
        dfs(root, null, 0);
        return maxLength1;
    }

    private void dfs(TreeNode p, TreeNode parent, int length) {
        if (p == null) {
            return;
        }
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        maxLength1 = Math.max(maxLength1, length);
        dfs(p.left, p, length);
        dfs(p.right, p, length);
    }

    /**
     * 方法二：自底向上深度优先搜索
     */
    private int maxLength2 = 0;

    public int longestConsecutive2(TreeNode root) {
        /*
         * 自底向上的方法与后序遍历类似。将从当前节点开始的连续路径长度返回给它的父节点。父节点来判断
         * 它的值是否能被添加进这个连续路径里。
         */
        dfs(root);
        return maxLength2;
    }

    private int dfs(TreeNode p) {
        if (p == null) {
            return 0;
        }
        int L = dfs(p.left) + 1;
        int R = dfs(p.right) + 1;
        if (p.left != null && p.val + 1 != p.left.val) {
            L = 1;
        }
        if (p.right != null && p.val + 1 != p.right.val) {
            R = 1;
        }
        int length = Math.max(L, R);
        maxLength2 = Math.max(maxLength2, length);
        return length;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive1(TreeNode.initBinaryTree("[1,null,3,null,null,2,4,null,null,null,null,null,null,null,5]")));
        System.out.println(new Solution().longestConsecutive2(TreeNode.initBinaryTree("[2,null,3,null,null,2,null,null,null,null,null,1]")));
    }
}
