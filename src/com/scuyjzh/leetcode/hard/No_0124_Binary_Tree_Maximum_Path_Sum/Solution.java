package com.scuyjzh.leetcode.hard.No_0124_Binary_Tree_Maximum_Path_Sum;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 *
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意
 * 节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包
 * 含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        /*
         * 首先，考虑实现一个简化的函数 maxGain(node)，该函数计算二叉树中的一个节点的最大贡献值，具体而
         * 言，就是在以该节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大。
         *
         * 具体而言，该函数的计算如下。
         *   • 空节点的最大贡献值等于 0。
         *   • 非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和（对于叶节点而言，最大贡献值等于
         *     节点值）。
         *
         * 例如，考虑如下二叉树。
         *      -10
         *      / \
         *     9  20
         *       /  \
         *      15   7
         * 叶节点 9、15、7 的最大贡献值分别为 9、15、7。
         * 得到叶节点的最大贡献值之后，再计算非叶节点的最大贡献值。节点 20 的最大贡献值等于 20+
         * max(15,7)=35，节点 −10 的最大贡献值等于 −10+max(9,35)=25。
         *
         * 上述计算过程是递归的过程，因此，对根节点调用函数 maxGain，即可得到每个节点的最大贡献值。
         *
         * 根据函数 maxGain 得到每个节点的最大贡献值之后，如何得到二叉树的最大路径和？对于二叉树中的一个
         * 节点，该节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值，如果子节点的最大贡献
         * 值为正，则计入该节点的最大路径和，否则不计入该节点的最大路径和。维护一个全局变量 maxSum 存储最
         * 大路径和，在递归过程中更新 maxSum 的值，最后得到的 maxSum 的值即为二叉树中的最大路径和。
         */
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewPath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewPath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxPathSum(TreeNode.initBinaryTree("[-10,9,20,null,null,15,7]")));
    }
}
