package com.scuyjzh.leetcode.medium.No_0333_Largest_BST_Subtree;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 333. 最大 BST 子树
 *
 * 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树
 * 的大小。其中，最大指的是子树节点数最多的。
 * 二叉搜索树（BST）中的所有节点都具备以下属性：
 *   • 左子树的值小于其父（根）节点的值。
 *   • 右子树的值大于其父（根）节点的值。
 * 注意:
 *   • 子树必须包含其所有后代。
 * 进阶:  你能想出 O(n) 时间复杂度的解法吗？
 */
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        return helper(root);
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isValidBST(root)) {
            return maxDepth(root);
        }
        int L = helper(root.left);
        int R = helper(root.right);
        return Math.max(L, R);
    }

    private boolean isValidBST(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, int lower, int upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        return dfs(root.left, lower, root.val) && dfs(root.right, root.val, upper);
    }

    private int maxDepth(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + dfs(root.left) + dfs(root.right);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestBSTSubtree(TreeNode.initBinaryTree("[10,5,15,1,8,null,7]")));
        System.out.println(new Solution().largestBSTSubtree(TreeNode.initBinaryTree("[4,2,7,2,3,5,null,2,null,null,null,null,null,1]")));
    }
}
