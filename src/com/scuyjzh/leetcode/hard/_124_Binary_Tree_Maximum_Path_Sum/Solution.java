package com.scuyjzh.leetcode.hard._124_Binary_Tree_Maximum_Path_Sum;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    int maxValue;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxValue = root.val;
        helper(root);
        return maxValue;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        /* 由左孩子开始的最大路径和 */
        int left = helper(root.left);
        /* 由右孩子开始的最大路径和 */
        int right = helper(root.right);
        /* 计算以root为最高点的最大路径和，并和原先的最大值比较 */
        maxValue = Math.max(maxValue, Math.max(left, 0) + Math.max(right, 0) + root.val);
        /* 返回以root为起点的单向路径的最大路径和 */
        return Math.max(root.val, Math.max(root.val + left, root.val + right));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxPathSum(TreeNode.createBinaryTree("[-10,9,20,null,null,15,7]")));
    }
}
