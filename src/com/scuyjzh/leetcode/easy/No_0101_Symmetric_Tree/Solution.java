package com.scuyjzh.leetcode.easy.No_0101_Symmetric_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 101. 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public boolean isSymmetric1(TreeNode root) {
        /*
         * 如果一个树的左子树与右子树镜像对称，那么这个树是对称的。
         * 因此，该问题可以转化为：两个树在什么情况下互为镜像？
         * 如果同时满足下面的条件，两个树互为镜像：
         *   • 它们的两个根节点具有相同的值
         *   • 每个树的右子树都与另一个树的左子树镜像对称
         *
         * 可以实现这样一个递归函数，通过「同步移动」两个指针的方法来遍历这棵树，p 指针和 q 指针一开始
         * 都指向这棵树的根，随后 p 右移时，q 左移，p 左移时，q 右移。每次检查当前 p 和 q 节点的值是否相等，如
         * 果相等再判断左右子树是否对称。
         */
        if (root == null) {
            return true;
        }
        // 调用递归函数，比较左子树和右子树
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        // 递归的终止条件是：
        // 1.两个节点都为空
        // 2.两个节点中有一个为空
        // 3.两个节点的值不相等
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        // 再递归比较：
        // 1.左节点的左孩子和右节点的右孩子
        // 2.左节点的右孩子和右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    /**
     * 方法二：广度优先搜索
     */
    public boolean isSymmetric2(TreeNode root) {
        /*
         * 「方法一」中用递归的方法实现了对称性的判断，那么如何用迭代的方法实现呢？首先引入一个队
         * 列，这是把递归程序改写成迭代程序的常用方法。初始化时把根节点入队两次。每次提取两个节点并比
         * 较它们的值（队列中每两个连续的节点应该是相等的，而且它们的子树互为镜像），然后将两个节点的左右
         * 子节点按相反的顺序插入队列中。当队列为空时，或者检测到树不对称（即从队列中取出两个不相等的
         * 连续节点）时，该算法结束。
         */
        if (root == null) {
            return true;
        }

        // 用队列保存节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点的左右孩子放到队列中
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            // 从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            // 如果两个节点都为空就继续循环
            if (left == null && right == null) {
                continue;
            }
            // 两者有一个为空或节点值不相等就返回 false
            if ((left == null || right == null) || (left.val != right.val)) {
                return false;
            }
            // 将左节点的左孩子和右节点的右孩子放入队列
            queue.offer(left.left);
            queue.offer(right.right);
            // 将左节点的右孩子和右节点的左孩子放入队列
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root1 = TreeNode.initBinaryTree("[1,2,2,3,4,4,3]");
        TreeNode root2 = TreeNode.initBinaryTree("[1,2,2,null,3,null,3]");
        System.out.println(solution.isSymmetric1(root1));
        System.out.println(solution.isSymmetric2(root2));
    }
}
