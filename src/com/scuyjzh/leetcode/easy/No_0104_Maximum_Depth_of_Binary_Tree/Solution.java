package com.scuyjzh.leetcode.easy.No_0104_Maximum_Depth_of_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public int maxDepth1(TreeNode root) {
        /*
         * 如果知道了左子树和右子树的最大深度 l 和 r，那么该二叉树的最大深度即为
         *         max(l,r)+1
         * 而左子树和右子树的最大深度又可以以同样的方式进行计算。因此可以用「深度优先搜索」的方法来计
         * 算二叉树的最大深度。具体而言，在计算当前二叉树的最大深度时，可以先递归计算出其左子树和右子树的
         * 最大深度，然后在 O(1) 时间内计算出当前二叉树的最大深度。递归在访问到空节点时退出。
         */
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(dfs(root.left), dfs(root.right));
    }

    /**
     * 方法二：广度优先搜索
     */
    public int maxDepth2(TreeNode root) {
        /*
         * 也可以用「广度优先搜索」的方法来解决这道题目，但需要对其进行一些修改，此时广度优先
         * 搜索的队列里存放的是「当前层的所有节点」。每次拓展下一层的时候，不同于广度优先搜索的每次只从队
         * 列里拿出一个节点，需要将队列里的所有节点都拿出来进行拓展，这样能保证每次拓展完的时候队列里
         * 存放的是当前层的所有节点，即是一层一层地进行拓展，最后用一个变量 ans 来维护拓展的次数，
         * 该二叉树的最大深度即为 ans。
         */
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.maxDepth1(root));
        System.out.println(solution.maxDepth2(root));
    }
}
