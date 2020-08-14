package com.scuyjzh.leetcode.medium.No_098_Validate_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration with Stack)
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && cur.val <= pre.val) {
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }

    /**
     * Approach #2 (Recursion)
     */
    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val <= minVal || root.val >= maxVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val)
                && isValidBST(root.right, root.val, maxVal);
    }
}
