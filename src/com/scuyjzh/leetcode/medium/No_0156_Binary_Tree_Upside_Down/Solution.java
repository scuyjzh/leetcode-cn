package com.scuyjzh.leetcode.medium.No_0156_Binary_Tree_Upside_Down;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 156. 上下翻转二叉树
 *
 * 给你一个二叉树的根节点 root ，请你将此二叉树上下翻转，并返回新的根节点。
 * 你可以按下面的步骤翻转一棵二叉树：
 *   1.原来的左子节点变成新的根节点
 *   2.原来的根节点变成新的右子节点
 *   3.原来的右子节点变成新的左子节点
 */
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode leftChild = root.left, rightChild = root.right;
        TreeNode head = upsideDownBinaryTree(root.left);
        root.left = null;
        root.right = null;
        leftChild.right = root;
        leftChild.left = rightChild;
        return head;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().upsideDownBinaryTree(TreeNode.initBinaryTree("[1,2,3,4,5]")));
    }
}
