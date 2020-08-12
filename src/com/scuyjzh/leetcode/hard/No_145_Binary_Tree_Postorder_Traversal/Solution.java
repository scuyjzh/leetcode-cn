package com.scuyjzh.leetcode.hard.No_145_Binary_Tree_Postorder_Traversal;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Stack)
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        TreeNode pre = root;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peekLast();
            boolean existed = (curr.left == null && curr.right == null) || (pre != null && (curr.left == pre || curr.right == pre));
            if (existed) {
                list.add(curr.val);
                stack.pollLast();
                pre = curr;
            } else {
                if (curr.right != null) {
                    stack.addLast(curr.right);
                }
                if (curr.left != null) {
                    stack.addLast(curr.left);
                }
            }
        }
        return list;
    }

    /**
     * Approach #2 (Iteration with Stack)
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollLast();
            list.addFirst(curr.val);
            if (curr.left != null) {
                stack.addLast(curr.left);
            }
            if (curr.right != null) {
                stack.addLast(curr.right);
            }
        }
        return list;
    }

    /**
     * Approach #3 (Recursion)
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        helper(root.left, list);
        helper(root.right, list);
        list.add(root.val);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,null,1]");
        System.out.println(solution.postorderTraversal1(root));
        System.out.println(solution.postorderTraversal2(root));
        System.out.println(solution.postorderTraversal3(root));
    }
}
