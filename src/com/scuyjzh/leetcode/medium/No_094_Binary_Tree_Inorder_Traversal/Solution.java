package com.scuyjzh.leetcode.medium.No_094_Binary_Tree_Inorder_Traversal;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Stack)
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while (curr != null || !stack.empty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
        return list;
    }

    /**
     * Approach #2 (Recursion)
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);

    }

    /**
     * Approach #3 (Morris Traversal)
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if (root == null) {
            return list;
        }
        TreeNode curr = root, pre = null;
        while (curr != null) {
            if (curr.left == null) {
                list.add(curr.val);
                curr = curr.right;
            } else {
                pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    pre.right = null;
                    list.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree(
                "[6,2,7,1,4,null,9,null,null,3,5,null,null,8]");
        System.out.println(solution.inorderTraversal1(root));
        System.out.println(solution.inorderTraversal2(root));
        System.out.println(solution.inorderTraversal3(root));
    }
}
