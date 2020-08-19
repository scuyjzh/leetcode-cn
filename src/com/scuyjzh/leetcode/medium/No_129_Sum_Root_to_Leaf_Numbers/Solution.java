package com.scuyjzh.leetcode.medium.No_129_Sum_Root_to_Leaf_Numbers;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Deque - BFS)
     */
    public int sumNumbers1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> q = new LinkedList<>();
        Deque<Integer> sumq = new LinkedList<>();
        q.addLast(root);
        sumq.addLast(root.val);
        int sum = 0;
        while (!q.isEmpty()) {
            TreeNode cur = q.removeFirst();
            int tmpSum = sumq.removeFirst();
            if (cur.left == null && cur.right == null) {
                sum += tmpSum;
            } else {
                if (cur.right != null) {
                    q.addLast(cur.right);
                    sumq.addLast(tmpSum * 10 + cur.right.val);
                }
                if (cur.left != null) {
                    q.addLast(cur.left);
                    sumq.addLast(tmpSum * 10 + cur.left.val);
                }
            }
        }

        return sum;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public int sumNumbers2(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.left, sum) + dfs(root.right, sum);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.sumNumbers1(TreeNode.initBinaryTree("[4,9,0,5,1]")));
        System.out.println(solution.sumNumbers2(TreeNode.initBinaryTree("[4,9,0,5,1]")));
    }
}
