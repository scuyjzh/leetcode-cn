package com.scuyjzh.leetcode.easy.No_0226_Invert_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 226. 翻转二叉树
 *
 * 翻转一棵二叉树。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public TreeNode invertTree1(TreeNode root) {
        /*
         * 思路与算法：
         * 这是一道很经典的二叉树问题。显然，从根节点开始，递归地对树进行遍历，并从叶子节点先开始翻
         * 转。如果当前遍历到的节点 root 的左右两棵子树都已经翻转，那么只需要交换两棵子树的位置，即可完
         * 成以 root 为根节点的整棵子树的翻转。
         */
        return dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        // 递归函数的终止条件，当节点为 null 时直接返回
        if (root == null) {
            return null;
        }
        // 如果当前节点不为 null，那么先将其左右子树进行翻转，然后交换左右子树
        TreeNode right = dfs(root.right);
        TreeNode left = dfs(root.left);
        root.left = right;
        root.right = left;
        // 返回值为完成了翻转后的当前节点
        return root;
    }

    /**
     * 方法二：广度优先搜索
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每次都从队列中拿一个节点，并交换这个节点的左右子树
            TreeNode cur = queue.poll();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            // 如果当前节点的左子树不为空，则放入队列等待后续处理
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            // 如果当前节点的右子树不为空，则放入队列等待后续处理
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        // 返回处理完的根节点
        return root;
    }

    public static void main(String[] args) {
        new Solution().invertTree1(TreeNode.initBinaryTree("[1,2,3,4,5,null,6]"));
        new Solution().invertTree2(TreeNode.initBinaryTree("[1,2,3,4,5,null,6]"));
    }
}
