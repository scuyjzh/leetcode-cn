package com.scuyjzh.leetcode.structure;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode createBinaryTree(String str) {
        if ("[]".equals(str)) {
            return null;
        }
        str = str.substring(1, str.length() - 1);
        String[] split = str.split(",");
        int len = split.length;
        TreeNode[] treeNodes = new TreeNode[len];
        for (int i = 0; i < len; ++i) {
            if (!"null".equals(split[i])) {
                treeNodes[i] = new TreeNode(Integer.valueOf(split[i]));
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
