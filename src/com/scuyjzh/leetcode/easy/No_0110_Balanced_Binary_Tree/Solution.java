package com.scuyjzh.leetcode.easy.No_0110_Balanced_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
class Solution {
    /**
     * 方法一：自顶向下的递归
     */
    public boolean isBalanced1(TreeNode root) {
        /*
         * 这道题中的平衡二叉树的定义是：二叉树的每个节点的左右子树的高度差的绝对值不超过 1，则二叉树是平
         * 衡二叉树。根据定义，一棵二叉树是平衡二叉树，当且仅当其所有子树也都是平衡二叉树，因此可以使用递
         * 归的方式判断二叉树是不是平衡二叉树，递归的顺序可以是自顶向下或者自底向上。
         *
         * 定义函数 height，用于计算二叉树中的任意一个节点 p 的高度。
         *
         * 有了计算节点高度的函数，即可判断二叉树是否平衡。具体做法类似于二叉树的前序遍历，即对于当前遍历
         * 到的节点，首先计算左右子树的高度，如果左右子树的高度差是否不超过 1，再分别递归地遍历左右子节
         * 点，并判断左子树和右子树是否平衡。这是一个自顶向下的递归的过程。
         */
        return helper(root);
    }

    private boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && helper(root.left) && helper(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * 方法二：自底向上的递归
     */
    public boolean isBalanced2(TreeNode root) {
        /*
         * 方法一由于是自顶向下递归，因此对于同一个节点，函数 height 会被重复调用，导致时间复杂度较高。如
         * 果使用自底向上的做法，则对于每个节点，函数 height 只会被调用一次。
         *
         * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断
         * 以当前节点为根的子树是否平衡。如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返
         * 回 −1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
         */
        return balanced(root) != -1;
    }

    private int balanced(TreeNode root) {
        // 递归终止条件：
        // 1.当越过叶子节点时，返回高度 0
        // 2.当左（右）子树高度 left == -1（right == -1） 时，代表此子树的 左（右）子树 不是平衡树，因此直接返回 -1
        if (root == null) {
            return 0;
        }
        int leftHeight, rightHeight;
        if ((leftHeight = balanced(root.left)) == -1
                || (rightHeight = balanced(root.right)) == -1
                || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        // 递归返回值：
        // 1.当节点 root 左/右子树的高度差 ≤ 1：则返回以节点 root 为根节点的子树的最大高度，即节点 root 的左右子树中最大高度加 1 (max(left, right) + 1)
        // 2.当节点 root 左/右子树的高度差 > 1：则返回 -1，代表此子树不是平衡树
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,2,3,4,4,3,8,7,6,5,5,6,7,8]");
        System.out.println(solution.isBalanced1(root));
        System.out.println(solution.isBalanced2(root));
    }
}
