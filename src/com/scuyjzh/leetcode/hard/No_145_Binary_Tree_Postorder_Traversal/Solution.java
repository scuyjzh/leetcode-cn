package com.scuyjzh.leetcode.hard.No_145_Binary_Tree_Postorder_Traversal;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Deque)
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
            TreeNode cur = stack.peekLast();
            boolean existed = (cur.left == null && cur.right == null) || (pre != null && (cur.left == pre || cur.right == pre));
            if (existed) {
                list.add(cur.val);
                stack.pollLast();
                pre = cur;
            } else {
                if (cur.right != null) {
                    stack.addLast(cur.right);
                }
                if (cur.left != null) {
                    stack.addLast(cur.left);
                }
            }
        }
        return list;
    }

    /**
     * Approach #2 (Iteration with Deque)
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollLast();
            list.addFirst(cur.val);
            if (cur.left != null) {
                stack.addLast(cur.left);
            }
            if (cur.right != null) {
                stack.addLast(cur.right);
            }
        }
        return list;
    }

    /**
     * Approach #3 (Recursion - DFS)
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        traversal(root, list);
        return list;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traversal(root.left, list);
        traversal(root.right, list);
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
