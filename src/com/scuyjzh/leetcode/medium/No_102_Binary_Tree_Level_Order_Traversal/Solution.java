package com.scuyjzh.leetcode.medium.No_102_Binary_Tree_Level_Order_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration with BFS)
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new LinkedList<>();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                subList.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            list.add(subList);
        }
        return list;
    }

    /**
     * Approach #2 (Recursion with DFS)
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        helper(list, root, 0);
        return list;
    }

    private void helper(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level >= list.size()) {
            list.add(new LinkedList<Integer>());
        }
        list.get(level).add(root.val);
        helper(list, root.left, level + 1);
        helper(list, root.right, level + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.levelOrder1(root));
        System.out.println(solution.levelOrder2(root));
    }
}
