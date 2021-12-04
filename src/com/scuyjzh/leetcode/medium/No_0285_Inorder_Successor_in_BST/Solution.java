package com.scuyjzh.leetcode.medium.No_0285_Inorder_Successor_in_BST;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 285. 二叉搜索树中的中序后继
 *
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后
 * 继。如果节点没有中序后继，请返回 null 。
 * 节点p的后继是值比p.val大的节点中键值最小的节点。
 */
class Solution {
    /**
     * 方法：中序遍历
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == p.val) {
                break;
            } else if (cur.val > p.val) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // 右子树不需要压栈，因为右子树的父节点一定小于当前节点
                cur = cur.right;
            }
        }
        if (cur.right != null) {
            // 存在右子树就选择右子树最左边的节点
            TreeNode node = cur.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            // 否则就是栈顶节点
            return stack.isEmpty() ? null : stack.pop();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().inorderSuccessor(TreeNode.initBinaryTree("[2,1,3]"), new TreeNode(1)));
    }
}
