package com.scuyjzh.leetcode.easy.No_0111_Minimum_Depth_of_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public int minDepth1(TreeNode root) {
        /*
         * 首先可以想到使用深度优先搜索的方法，遍历整棵树，记录最小深度。
         * 对于每一个非叶子节点，只需要分别计算其左右子树的最小叶子节点深度。这样就将一个大问题转化为
         * 了小问题，可以递归地解决该问题。
         */
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 这道题递归条件里分为三种情况：
        // 1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回 1 即可
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 2.如果左孩子和由孩子其中一个为空，那么需要返回比较大的那个孩子的深度
        int m1 = dfs(root.left);
        int m2 = dfs(root.right);
        // 这里其中一个节点为空，说明 m1 和 m2 有一个必然为 0，所以可以返回 m1 + m2 + 1
        if (root.left == null || root.right == null) {
            return m1 + m2 + 1;
        }
        // 3.最后一种情况，也就是左右孩子都不为空，返回最小深度 +1 即可
        return Math.min(m1, m2) + 1;
    }

    /**
     * 方法二：广度优先搜索
     */
    public int minDepth2(TreeNode root) {
        /*
         * 同样，可以想到使用广度优先搜索的方法，遍历整棵树。
         * 当找到一个叶子节点时，直接返回这个叶子节点的深度。广度优先搜索的性质保证了最先搜索到的叶子
         * 节点的深度一定最小。
         */
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                // 第一个访问到的叶子就是最小深度的节点
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
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
        System.out.println(solution.minDepth1(root));
        System.out.println(solution.minDepth2(root));
    }
}
