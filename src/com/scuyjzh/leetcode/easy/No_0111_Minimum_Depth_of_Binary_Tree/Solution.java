package com.scuyjzh.leetcode.easy.No_0111_Minimum_Depth_of_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration by Level Order Traversal - BFS)
     */
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.remove();
                // 第一个访问到的叶子就是最小深度的结点
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
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
    public int minDepth2(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树的最小深度
        int left = dfs(root.left);
        // 右子树的最小深度
        int right = dfs(root.right);
        // 1.如果left和right都为0，返回1即可
        // 2.如果left和right只有一个为0，说明只有一个孩子结点，只需要返回此孩子结点的最小深度+1即可
        // 3.如果left和right都不为0，说明左右孩子都不为空，只需要返回最小深度的+1即可
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.minDepth1(root));
        System.out.println(solution.minDepth2(root));
    }
}
