package com.scuyjzh.leetcode.medium.No_1038_Binary_Search_Tree_to_Greater_Sum_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 1038. 从二叉搜索树到更大和树
 * 给出二叉搜索树的根结点，该二叉树的结点值各不相同，修改二叉树，使每个结点 node 的新值等于原树中大于或等于 node.val 的值之和。
 *
 * @author scuyjzh
 * @date 2020/9/21 15:04
 */
class Solution {
    int sum = 0;

    /**
     * Approach #1 (Recursion by Inverse Inorder Traversal)
     */
    public TreeNode bstToGst1(TreeNode root) {
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
    public TreeNode bstToGst2(TreeNode root) {
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
        System.out.println(solution.bstToGst1(root));
        System.out.println(solution.bstToGst2(root));
    }
}
