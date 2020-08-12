package com.scuyjzh.leetcode.easy.No_110_Balanced_Binary_Tree;

class Solution {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            if (Math.abs(height(root.left) - height(root.right)) <= 1) {
                return isBalanced(root.left) && isBalanced(root.right);
            } else {
                return false;
            }
        }
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }
}
