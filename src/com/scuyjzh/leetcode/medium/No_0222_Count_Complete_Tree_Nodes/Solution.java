package com.scuyjzh.leetcode.medium.No_0222_Count_Complete_Tree_Nodes;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        // 利用移位计算 2 ^ left 和 2 ^ right
        // 1.left == right，说明左子树一定是满二叉树
        // 2.left != right，说明右子树一定是满二叉树
        if (left == right) {
            return countNodes(root.right) + (1 << left);
        } else {
            return countNodes(root.left) + (1 << right);
        }
    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6]");
        System.out.println(solution.countNodes(root));
    }
}
