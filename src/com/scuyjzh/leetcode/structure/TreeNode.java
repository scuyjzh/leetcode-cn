package com.scuyjzh.leetcode.structure;

/**
 * Definition for a binary tree node.
 *
 * @author scuyjzh
 * @version 1.0
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode initBinaryTree(String str) {
        if ("[]".equals(str)) {
            return null;
        }
        str = str.substring(1, str.length() - 1);
        String[] split = str.split(",");
        int len = split.length;
        TreeNode[] treeNodes = new TreeNode[len];
        for (int i = 0; i < len; ++i) {
            if (!"null".equals(split[i])) {
                treeNodes[i] = new TreeNode(Integer.parseInt(split[i]));
            }
        }
        for (int i = 0; i < len; ++i) {
            if (treeNodes[i] != null) {
                int leftIndex = 2 * i + 1;
                if (leftIndex < len) {
                    treeNodes[i].left = treeNodes[leftIndex];
                }
                int rightIndex = leftIndex + 1;
                if (rightIndex < len) {
                    treeNodes[i].right = treeNodes[rightIndex];
                }
            }
        }
        return treeNodes[0];
    }
}
