package com.scuyjzh.leetcode.medium.No_114_Flatten_Binary_Tree_to_Linked_List;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Stack)
     */
    public void flatten1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr.right != null) {
                stack.addLast(curr.right);
            }
            if (curr.left != null) {
                curr.right = curr.left;
                curr.left = null;
            } else if (!stack.isEmpty()) {
                TreeNode tmp = stack.pollLast();
                curr.right = tmp;
            }
            curr = curr.right;
        }
    }

    /**
     * Approach #2 (Recursion)
     */
    public void flatten2(TreeNode root) {
        helper(root, null);
    }

    private TreeNode helper(TreeNode root, TreeNode pre) {
        if (root == null) {
            return pre;
        }
        pre = helper(root.right, pre);
        pre = helper(root.left, pre);
        root.right = pre;
        root.left = null;
        pre = root;
        return pre;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.createBinaryTree("[1,2,5,3,4,null,6]");
        solution.flatten1(root);
        System.out.println();
    }
}
