package com.scuyjzh.leetcode.medium.No_1382_Balance_a_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 1382. 将二叉搜索树变平衡
 *
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应
 * 该与原来的树有着相同的节点值。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，就称
 * 这棵二叉搜索树是 平衡的 。
 * 如果有多种构造方法，请你返回任意一种。
 */
class Solution {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortList = new LinkedList<>();
        // 中序遍历构造有序链表
        inOrder(root, sortList);
        // 有序链表构造平衡二叉树
        return buildTree(sortList, 0, sortList.size() - 1);
    }

    private void inOrder(TreeNode root, List<Integer> sortList) {
        if (root != null) {
            inOrder(root.left, sortList);
            sortList.add(root.val);
            inOrder(root.right, sortList);
        }
    }

    private TreeNode buildTree(List<Integer> sortList, int start, int end) {
        if (start > end) {
            return null;
        }
        // 中间节点为root
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(sortList.get(mid));
        // 递归构造左右子树
        root.left = buildTree(sortList, start, mid - 1);
        root.right = buildTree(sortList, mid + 1, end);
        // 返回root
        return root;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().balanceBST(TreeNode.initBinaryTree("[1,null,2,null,3,null,4,null,null]")));
    }
}
