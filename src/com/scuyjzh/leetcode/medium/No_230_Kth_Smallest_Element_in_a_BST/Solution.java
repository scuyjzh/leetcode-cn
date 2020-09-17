package com.scuyjzh.leetcode.medium.No_230_Kth_Smallest_Element_in_a_BST;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            k--;
            if (k == 0) {
                break;
            }
            cur = cur.right;
        }
        return cur.val;
    }
}
