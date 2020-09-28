package com.scuyjzh.leetcode.easy.No_0538_Convert_BST_to_Greater_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树，把它转换成为累加树，使得每个结点的值是原来的结点值加上所有大于它的结点值之和。
 *
 * @author scuyjzh
 * @date 2020/9/21 15:06
 */
class Solution {
    int sum = 0;

    /**
     * Approach #1 (Recursion by Inverse Inorder Traversal)
     */
    public TreeNode convertBST1(TreeNode root) {
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
     * Approach #2 (Iteration by Inverse Inorder Traversal)
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
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[5,2,13]");
        System.out.println(solution.convertBST1(root));
        System.out.println(solution.convertBST2(root));
    }
}
