package com.scuyjzh.leetcode.medium.No_0366_Find_Leaves_of_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 366. 寻找二叉树的叶子节点
 *
 * 给你一棵二叉树，请按以下要求的顺序收集它的全部节点：
 * 1.依次从左到右，每次收集并删除所有的叶子节点
 * 2.重复如上过程直到整棵树为空
 */
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        // 根据子树的层高对节点进行分组
        helper(root, results);
        return results;
    }

    private int helper(TreeNode root, List<List<Integer>> results) {
        if (root == null) {
            return -1;
        }
        int height = 1 + Math.max(helper(root.left, results), helper(root.right, results));
        if (results.size() < height + 1) {
            results.add(new ArrayList<>());
        }
        results.get(height).add(root.val);
        return height;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findLeaves(TreeNode.initBinaryTree("[1,2,3,4,5]")));
    }
}
