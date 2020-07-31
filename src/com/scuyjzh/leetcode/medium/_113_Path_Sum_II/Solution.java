package com.scuyjzh.leetcode.medium._113_Path_Sum_II;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Stack - Postorder Traversal)
     */
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root, pre = null;
        int SUM = 0;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.addLast(curr);
                path.add(curr.val);
                SUM += curr.val;
                curr = curr.left;
            }
            curr = stack.peekLast();
            if (curr.right != null && curr.right != pre) {
                curr = curr.right;
                continue;
            }
            if (curr.left == null && curr.right == null && SUM == sum) {
                list.add(new ArrayList<>(path));
            }
            pre = curr;
            stack.pollLast();
            path.remove(path.size() - 1);
            SUM -= curr.val;
            curr = null;
        }
        return list;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) {
            return list;
        }
        List<Integer> path = new ArrayList<Integer>();
        helper(root, sum, path, list);
        return list;
    }

    private void helper(TreeNode root, int sum, List<Integer> path, List<List<Integer>> list) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            list.add(new ArrayList<>(path));
        } else {
            helper(root.left, sum - root.val, path, list);
            helper(root.right, sum - root.val, path, list);
        }
        // back-tracking
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.createBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,5,1]");
        System.out.println(solution.pathSum1(root, 22));
        System.out.println(solution.pathSum2(root, 22));
    }
}
