package com.scuyjzh.leetcode.medium.No_0113_Path_Sum_II;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration by Postorder Traversal)
     */
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, pre = null;
        int SUM = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.addLast(cur);
                path.add(cur.val);
                SUM += cur.val;
                cur = cur.left;
            }
            cur = stack.peekLast();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }
            if (cur.left == null && cur.right == null && SUM == sum) {
                list.add(new ArrayList<>(path));
            }
            pre = cur;
            stack.pollLast();
            path.remove(path.size() - 1);
            SUM -= cur.val;
            cur = null;
        }
        return list;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> path = new ArrayList<>();
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
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,5,1]");
        System.out.println(solution.pathSum1(root, 22));
        System.out.println(solution.pathSum2(root, 22));
    }
}
