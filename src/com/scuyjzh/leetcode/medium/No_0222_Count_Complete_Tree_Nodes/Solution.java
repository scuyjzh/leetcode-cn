package com.scuyjzh.leetcode.medium.No_0222_Count_Complete_Tree_Nodes;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 *
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满
 * 外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最
 * 左边的若干位置。若最底层为第 h 层，则该层包含 1~2^h个节点。
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你
 * 可以设计一个更快的算法吗？
 */
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLevel = countLevel(root.left);
        int rightLevel = countLevel(root.right);
        // 利用完全二叉树的性质可以得到：
        // 1.leftLevel == rightLevel，说明左子树一定是满二叉树
        // 2.leftLevel != rightLevel，说明右子树一定是满二叉树
        // 层数为 h 的满二叉树的节点个数为 2^h - 1
        int leftNodes, rightNodes;
        if (leftLevel == rightLevel) {
            // 利用移位计算 2 ^ leftLevel - 1
            leftNodes = (1 << leftLevel) - 1;
            rightNodes = countNodes(root.right);
        } else {
            // 利用移位计算 2 ^ rightLevel - 1
            leftNodes = countNodes(root.left);
            rightNodes = (1 << rightLevel) - 1;
        }
        return leftNodes + rightNodes + 1;
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
        System.out.println(new Solution().countNodes(TreeNode.initBinaryTree("[1,2,3,4,5,6]")));
    }
}
