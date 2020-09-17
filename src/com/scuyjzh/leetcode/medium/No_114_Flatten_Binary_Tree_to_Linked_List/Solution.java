package com.scuyjzh.leetcode.medium.No_114_Flatten_Binary_Tree_to_Linked_List;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration with Stack)
     */
    public void flatten1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur.right != null) {
                stack.addLast(cur.right);
            }
            if (cur.left != null) {
                cur.right = cur.left;
                cur.left = null;
            } else if (!stack.isEmpty()) {
                TreeNode tmp = stack.pollLast();
                cur.right = tmp;
            }
            cur = cur.right;
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
        TreeNode root = TreeNode.initBinaryTree("[1,2,5,3,4,null,6]");
        solution.flatten1(root);
        System.out.println();
    }
}
