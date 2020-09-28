package com.scuyjzh.leetcode.hard.No_0099_Recover_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 99. 恢复二叉搜索树
 * 二叉搜索树中的两个结点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * @author scuyjzh
 * @date 2020/9/18 15:01
 */
class Solution {
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.poll();
            if (firstNode == null && pre.val > cur.val) {
                firstNode = pre;
            }
            if (firstNode != null && pre.val > cur.val) {
                secondNode = cur;
            }
            pre = cur;
            cur = cur.right;
        }
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[3,4,1,null,2]");
        solution.recoverTree(root);
    }
}
