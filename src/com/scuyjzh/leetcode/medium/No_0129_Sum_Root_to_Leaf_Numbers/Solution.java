package com.scuyjzh.leetcode.medium.No_0129_Sum_Root_to_Leaf_Numbers;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 129. 求根节点到叶节点数字之和
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之
 * 间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *   • 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public int sumNumbers1(TreeNode root) {
        /*
         * 这道题中，二叉树的每条从根节点到叶子节点的路径都代表一个数字。其实，每个节点都对应一个数字，等
         * 于其父节点对应的数字乘以 10 再加上该节点的值（这里假设根节点的父节点对应的数字是 0）。只要计算出
         * 每个叶子节点对应的数字，然后计算所有叶子节点对应的数字之和，即可得到结果。可以通过深度优先搜索
         * 和广度优先搜索实现。
         *
         * 深度优先搜索是很直观的做法。从根节点开始，遍历每个节点，如果遇到叶子节点，则将叶子节点对应的数
         * 字加到数字之和。如果当前节点不是叶子节点，则计算其子节点对应的数字，然后对子节点递归遍历。
         */
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

    /**
     * 方法二：广度优先搜索
     */
    public int sumNumbers2(TreeNode root) {
        /*
         * 使用广度优先搜索，需要维护两个队列，分别存储节点和节点对应的数字。
         * 初始时，将根节点和根节点的值分别加入两个队列。每次从两个队列分别取出一个节点和一个数字，进行如
         * 下操作：
         *   • 如果当前节点是叶子节点，则将其对应的数字加到数字之和；
         *   • 如果当前节点不是叶子节点，则获得当前节点的非空子节点，并根据当前节点对应的数字和子节点的值
         *     计算子节点对应的数字，然后将子节点和子节点对应的数字分别加入两个队列。
         * 搜索结束后，即可得到所有叶子节点对应的数字之和。
         */
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<Integer> numQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        int sum = 0;
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            int tmpSum = numQueue.poll();
            if (cur.left == null && cur.right == null) {
                // 如果当前节点是叶子节点，则将其对应的数字加到数字之和
                sum += tmpSum;
            } else {
                // 如果当前节点不是叶子节点，则获得当前节点的非空子节点
                if (cur.right != null) {
                    // 并根据当前节点对应的数字和子节点的值计算子节点对应的数字
                    // 然后将子节点和子节点对应的数字分别加入两个队列
                    nodeQueue.offer(cur.right);
                    numQueue.offer(tmpSum * 10 + cur.right.val);
                }
                if (cur.left != null) {
                    nodeQueue.offer(cur.left);
                    numQueue.offer(tmpSum * 10 + cur.left.val);
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.sumNumbers1(TreeNode.initBinaryTree("[1,2,3]")));
        System.out.println(solution.sumNumbers2(TreeNode.initBinaryTree("[4,9,0,5,1]")));
    }
}
