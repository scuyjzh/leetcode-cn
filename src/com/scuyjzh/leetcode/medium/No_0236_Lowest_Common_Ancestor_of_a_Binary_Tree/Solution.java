package com.scuyjzh.leetcode.medium.No_0236_Lowest_Common_Ancestor_of_a_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 */
class Solution {
    /**
     * 方法：递归
     * 时间复杂度：O(N)，其中 N 是二叉树的节点数。二叉树的所有节点有且只会被访问一次，因此时间复杂度为 O(N)。
     * 空间复杂度：O(N)，其中 N 是二叉树的节点数。递归调用的栈深度取决于二叉树的高度，二叉树最坏情况下为一条链，此时高度为 N，因此空间复杂度为 O(N)。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = dfs(root.left, p, q);
        TreeNode right = dfs(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]");
        TreeNode p = new TreeNode(9);
        TreeNode q = new TreeNode(11);
        System.out.println(solution.lowestCommonAncestor(root, p, q).val);
    }
}