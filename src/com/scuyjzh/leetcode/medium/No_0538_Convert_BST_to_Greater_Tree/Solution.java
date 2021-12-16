package com.scuyjzh.leetcode.medium.No_0538_Convert_BST_to_Greater_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 538. 把二叉搜索树转换为累加树
 *
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加
 * 树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等
 * 于 node.val 的值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 *   • 节点的左子树仅包含键 小于 节点键的节点。
 *   • 节点的右子树仅包含键 大于 节点键的节点。
 *   • 左右子树也必须是二叉搜索树。
 * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 */
class Solution {
    private int sum = 0;

    /**
     * 方法一：反序中序遍历（递归）
     */
    public TreeNode convertBST1(TreeNode root) {
        /*
         * 二叉搜索树是一棵空树，或者是具有下列性质的二叉树：
         *   1.若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
         *   2.若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
         *   3.它的左、右子树也分别为二叉搜索树。
         *
         * 由这样的性质可以发现，二叉搜索树的中序遍历是一个单调递增的有序序列。如果反序地中序遍历
         * 该二叉搜索树，即可得到一个单调递减的有序序列。
         *
         * 本题中要求将每个节点的值修改为原来的节点值加上所有大于它的节点值之和。这样只需要反序中
         * 序遍历该二叉搜索树，记录过程中的节点值之和，并不断更新当前遍历到的节点的节点值，即可得到题目要
         * 求的累加树。
         */
        helper(root);
        return root;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.right);
        sum += root.val;
        root.val = sum;
        helper(root.left);
    }

    /**
     * 方法二：反序中序遍历（迭代）
     */
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode cur = root;
        int sum = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            sum += cur.val;
            cur.val = sum;
            cur = cur.left;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().convertBST1(TreeNode.initBinaryTree("[5,2,13]")));
        System.out.println(new Solution().convertBST2(TreeNode.initBinaryTree("[5,2,13]")));
    }
}
