package com.scuyjzh.leetcode.easy.No_653_Two_Sum_IV_Input_is_a_BST;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    public boolean findTarget1(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            if (nums.get(i) + nums.get(j) == k) {
                return true;
            }
            if (nums.get(i) + nums.get(j) < k) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public boolean findTarget3(TreeNode root, int k) {
        return dfs(root, root, k);
    }

    public boolean dfs(TreeNode root, TreeNode cur, int k) {
        if (cur == null) {
            return false;
        }
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }

    public boolean search(TreeNode root, TreeNode cur, int value) {
        if (root == null) {
            return false;
        }
        return (root.val == value) && (root != cur) || (root.val < value) && search(root.right, cur, value) || (root.val > value) && search(root.left, cur, value);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.createBinaryTree("[50,35,58,30,40,57,60]");
        System.out.println(solution.findTarget1(root, 100));
        System.out.println(solution.findTarget2(root, 100));
        System.out.println(solution.findTarget3(root, 100));
    }
}
