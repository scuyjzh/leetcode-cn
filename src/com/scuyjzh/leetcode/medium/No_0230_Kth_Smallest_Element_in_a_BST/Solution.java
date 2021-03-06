package com.scuyjzh.leetcode.medium.No_0230_Kth_Smallest_Element_in_a_BST;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

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
