package com.scuyjzh.leetcode.easy.No_0617_Merge_Two_Binary_Trees;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 617. 合并二叉树
 *
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树
 * 的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那
 * 么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作
 * 为新二叉树的节点。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        /*
         * 可以使用深度优先搜索合并两个二叉树。从根节点开始同时遍历两个二叉树，并将对应的节点进行合并。
         *
         * 两个二叉树的对应节点可能存在以下三种情况，对于每种情况使用不同的合并方式。
         *   • 如果两个二叉树的对应节点都为空，则合并后的二叉树的对应节点也为空；
         *   • 如果两个二叉树的对应节点只有一个为空，则合并后的二叉树的对应节点为其中的非空节点；
         *   • 如果两个二叉树的对应节点都不为空，则合并后的二叉树的对应节点的值为两个二叉树的对应节点的值
         *     之和，此时需要显性合并两个节点。
         *
         * 对一个节点进行合并之后，还要对该节点的左右子树分别进行合并。这是一个递归的过程。
         */
        return dfs(root1, root2);
    }

    private TreeNode dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = dfs(root1.left, root2.left);
        merged.right = dfs(root1.right, root2.right);
        return merged;
    }

    /**
     * 方法二：广度优先搜索
     */
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode merged = new TreeNode(root1.val + root2.val);
        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<TreeNode> queue1 = new ArrayDeque<>();
        Deque<TreeNode> queue2 = new ArrayDeque<>();
        queue.offer(merged);
        queue1.offer(root1);
        queue2.offer(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    queue.offer(left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                } else if (left1 != null) {
                    node.left = left1;
                } else if (left2 != null) {
                    node.left = left2;
                }
            }
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    queue.offer(right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }
        return merged;
    }

    public static void main(String[] args) {
        TreeNode root1 = new Solution().mergeTrees1(TreeNode.initBinaryTree("[1,3,2,5]"), TreeNode.initBinaryTree("[2,1,3,null,4,7]"));
        TreeNode root2 = new Solution().mergeTrees2(TreeNode.initBinaryTree("[1,3,2,5]"), TreeNode.initBinaryTree("[2,1,3,null,4,7]"));
        System.out.println(root1.val);
        System.out.println(root2.val);
    }
}
