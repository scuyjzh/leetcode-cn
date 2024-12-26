package com.scuyjzh.leetcode.easy.No_0100_Same_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 100. 相同的树
 *
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相
 * 同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        /*
         * 如果两个二叉树都为空，则两个二叉树相同。如果两个二叉树中有且只有一个为空，则两个二叉树一定不相
         * 同。
         *
         * 如果两个二叉树都不为空，那么首先判断它们的根节点的值是否相同，若不相同则两个二叉树一定不同，若
         * 相同，再分别判断两个二叉树的左子树是否相同以及右子树是否相同。这是一个递归的过程，因此可以使用
         * 深度优先搜索，递归地判断两个二叉树是否相同。
         */
        return dfs(p, q);
    }

    private boolean dfs(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return dfs(p.left, q.left) && dfs(p.right, q.right);
    }

    /**
     * 方法二：广度优先搜索
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();
            if (p == null && q == null) {
                continue;
            }
            if ((p == null || q == null) || p.val != q.val) {
                return false;
            }
            queue.offer(p.left);
            queue.offer(q.left);

            queue.offer(p.right);
            queue.offer(q.right);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode p = TreeNode.initBinaryTree("[1,2,3,4,5,null,6]");
        TreeNode q = TreeNode.initBinaryTree("[1,2,3,4,5,null,6]");
        System.out.println(solution.isSameTree1(p, q));
        System.out.println(solution.isSameTree2(p, q));
    }
}
