package com.scuyjzh.leetcode.medium.No_0236_Lowest_Common_Ancestor_of_a_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        // 在左子树
        boolean inLeft = dfs(root.left, p, q);
        // 在右子树
        boolean inRight = dfs(root.right, p, q);
        // 是当前节点
        boolean isCurrentNode = root.val == p.val || root.val == q.val;
        boolean existed = (inLeft && inRight) || (isCurrentNode && (inLeft || inRight));
        if (existed) {
            ans = root;
        }
        return inLeft || inRight || isCurrentNode;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]");
        TreeNode p = new TreeNode(9);
        TreeNode q = new TreeNode(11);
        System.out.println(solution.lowestCommonAncestor(root, p, q).val);
    }
}
