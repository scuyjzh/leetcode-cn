package com.scuyjzh.leetcode.easy.No_0543_Diameter_of_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 543. 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意
 * 两个节点路径长度中的最大值。这条路径可能穿过也可能不穿过根节点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两节点之间的路径长度是以它们之间边的数目表示。
 */
class Solution {
    private int ans = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        /*
         * 首先知道一条路径的长度为该路径经过的节点数减一，所以求直径（即求路径长度的最大值）等效于求
         * 路径经过节点数的最大值减一。
         *
         * 而任意一条路径均可以被看作由某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接得到。
         *
         * 假设知道对于该节点的左儿子向下遍历经过最多的节点数 L （即以左儿子为根的子树的深度） 和其右
         * 儿子向下遍历经过最多的节点数 R （即以右儿子为根的子树的深度），那么以该节点为起点的路径经过节点
         * 数的最大值即为 L+R+1 。
         *
         * 记节点 node 为起点的路径经过节点数的最大值为 d_node，那么二叉树的直径就是所有节点 d_node 的最大
         * 值减一。
         *
         * 最后的算法流程为：定义一个递归函数 depth(node) 计算 d_node，函数返回该节点为根的子树的深
         * 度。先递归调用左儿子和右儿子求得它们为根的子树的深度 L 和 R ，则该节点为根的子树的深度即为
         *                                    max(L,R)+1
         * 该节点的 d_node 值为
         *                                      L+R+1
         *
         * 递归搜索每个节点并设一个全局变量 ans 记录 d_node 的最大值，最后返回 ans-1 即为树的直径。
         */
        depth(root);
        // 一条路径的长度为该路径经过的节点数减一
        return ans - 1;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左儿子为根的子树的深度
        int left = depth(node.left);
        // 右儿子为根的子树的深度
        int right = depth(node.right);
        // 计算 d_node 即 L+R+1，并更新 ans
        ans = Math.max(ans, left + right + 1);
        // 返回该节点为根的子树的深度
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().diameterOfBinaryTree(TreeNode.initBinaryTree("[1,2,3,4,5]")));
    }
}
