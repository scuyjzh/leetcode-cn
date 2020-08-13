package com.scuyjzh.leetcode.medium.No_103_Binary_Tree_Zigzag_Level_Order_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration with BFS)
     */
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean zigzag = false;
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            LinkedList<Integer> subList = new LinkedList<>();
            for (int i = 0; i < levelNum; ++i) {
                TreeNode curr = queue.poll();
                if (zigzag) {
                    subList.addFirst(curr.val);
                } else {
                    subList.add(curr.val);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            list.add(subList);
            zigzag = !zigzag;
        }
        return list;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        helper(list, root, 0);
        return list;
    }

    private void helper(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level >= list.size()) {
            list.add(new LinkedList<Integer>());
        }
        if (level % 2 == 0) {
            list.get(level).add(root.val);
        } else {
            list.get(level).add(0, root.val);
        }
        helper(list, root.left, level + 1);
        helper(list, root.right, level + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.zigzagLevelOrder1(root));
        System.out.println(solution.zigzagLevelOrder2(root));
    }
}
