package com.scuyjzh.leetcode.easy.No_104_Maximum_Depth_of_Binary_Tree;

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
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.remove();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public int maxDepth2(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(dfs(root.left), dfs(root.right));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.maxDepth1(root));
        System.out.println(solution.maxDepth2(root));
    }
}
