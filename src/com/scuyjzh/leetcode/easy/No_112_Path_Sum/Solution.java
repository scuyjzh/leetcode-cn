package com.scuyjzh.leetcode.easy.No_112_Path_Sum;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Queue - BFS)
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }

    /**
     * Approach #2 (Iteration by postorder traversal)
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
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
     * Approach #3 (Recursion - DFS)
     */
    public boolean hasPathSum3(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return dfs(root.left, sum - root.val) || dfs(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,null,1,null,null,3]");
        System.out.println(solution.hasPathSum1(root, 18));
        System.out.println(solution.hasPathSum2(root, 22));
    }
}
