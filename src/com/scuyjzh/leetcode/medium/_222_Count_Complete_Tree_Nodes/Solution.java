package com.scuyjzh.leetcode.medium._222_Count_Complete_Tree_Nodes;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    public int countNodes(TreeNode root) {
        int hLeft = 0, hRight = 0;
        TreeNode leftNode = root, rightNode = root;
        while (leftNode != null) {
            hLeft++;
            leftNode = leftNode.left;
        }
        while (rightNode != null) {
            hRight++;
            rightNode = rightNode.right;
        }
        if (hLeft == hRight) {
            return (1 << hLeft) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.createBinaryTree("[1,2,3,4,5,6,null]");
        System.out.println(solution.countNodes(root));
    }
}
