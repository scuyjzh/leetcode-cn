package com.scuyjzh.leetcode.easy.No_112_Path_Sum;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Stack - Postorder Traversal)
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root, pre = null;
        int SUM = 0;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.addLast(curr);
                SUM += curr.val;
                curr = curr.left;
            }
            curr = stack.peekLast();
            if (curr.left == null && curr.right == null && SUM == sum) {
                return true;
            }
            if (curr.right != null && curr.right != pre) {
                curr = curr.right;
            } else {
                pre = curr;
                stack.pollLast();
                SUM -= curr.val;
                curr = null;
            }
        }
        return false;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        return helper(root, sum);
    }

    private boolean helper(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return helper(root.left, sum - root.val) || helper(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.createBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,null,1,null,null,3]");
        System.out.println(solution.hasPathSum1(root, 18));
        System.out.println(solution.hasPathSum2(root, 22));
    }
}
