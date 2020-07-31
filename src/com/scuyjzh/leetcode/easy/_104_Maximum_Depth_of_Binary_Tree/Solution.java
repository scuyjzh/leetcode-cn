package com.scuyjzh.leetcode.easy._104_Maximum_Depth_of_Binary_Tree;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Queue - BFS)
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * Approach #2 (Recursion)
     */
    public int maxDepth2(TreeNode root) {
        return helper(root);
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(helper(root.left), helper(root.right));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.createBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.maxDepth1(root));
        System.out.println(solution.maxDepth2(root));
    }
}
