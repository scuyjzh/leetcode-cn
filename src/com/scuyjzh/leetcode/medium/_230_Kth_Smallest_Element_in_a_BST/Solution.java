package com.scuyjzh.leetcode.medium._230_Kth_Smallest_Element_in_a_BST;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) {
                break;
            }
            curr = curr.right;
        }
        return curr.val;
    }
}
