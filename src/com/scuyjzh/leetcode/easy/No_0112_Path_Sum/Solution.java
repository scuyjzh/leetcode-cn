package com.scuyjzh.leetcode.easy.No_0112_Path_Sum;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 112. 路径总和
 *
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum ，判断该
 * 树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于
 * 目标和targetSum 。
 * 叶子节点 是指没有子节点的节点。
 */
class Solution {
    /**
     * 方法一：广度优先搜索
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        /*
         * 注意到本题的要求是，询问是否有从「根节点」到某个「叶子节点」经过的路径上的节点之和等于目标和。
         * 核心思想是对树进行一次遍历，在遍历时记录从根节点到当前节点的路径和，以防止重复计算。
         *     需要特别注意的是，给定的 root 可能为空。
         *
         * 首先可以想到使用广度优先搜索的方式，记录从根节点到当前节点的路径和，以防止重复计算。
         * 这样使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可。
         */
        if (root == null) {
            return false;
        }
        Deque<TreeNode> queNode = new ArrayDeque<>();
        Deque<Integer> queVal = new ArrayDeque<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode cur = queNode.poll();
            int curSum = queVal.poll();
            if (cur.left == null && cur.right == null) {
                if (curSum == sum) {
                    return true;
                }
                continue;
            }
            if (cur.left != null) {
                queNode.offer(cur.left);
                queVal.offer(cur.left.val + curSum);
            }
            if (cur.right != null) {
                queNode.offer(cur.right);
                queVal.offer(cur.right.val + curSum);
            }
        }
        return false;
    }

    /**
     * 方法二：深度优先搜索（后序遍历）
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        // 记录当前累计的和
        int curSum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                // 累加当前已走路径的和
                curSum += cur.val;
                cur = cur.left;
            }
            // 在出栈前，将栈顶视作实际的根结点，并检查其右子树是否不存在或已被访问
            cur = stack.peek();
            if (cur.left == null && cur.right == null && curSum == sum) {
                // 找到路径
                return true;
            }
            if (cur.right == null || cur.right == pre) {
                // 如果不存在右子树或右子树已被访问，那么可以访问根结点，将其弹出栈
                stack.pop();
                // 减去出栈的值
                curSum -= cur.val;
                // 记录上一个访问的结点，用于判断“访问根结点之前，右子树是否已访问过”
                pre = cur;
                // 表示不需要转向，继续弹栈
                cur = null;
            } else {
                // 如果右子树存在且还未被访问过，就转向其右子树
                cur = cur.right;
            }
        }
        return false;
    }

    /**
     * 方法三：深度优先搜索（递归）
     */
    public boolean hasPathSum3(TreeNode root, int sum) {
        /*
         * 观察要求完成的函数，可以归纳出它的功能：询问是否存在从当前节点 root 到叶子节点的路径，
         * 满足其路径和为 sum。
         *
         * 假定从根节点到当前节点的值之和为 val，可以将这个大问题转化为一个小问题：是否存在从当前节
         * 点的子节点到叶子的路径，满足其路径和为 sum - val。
         *
         * 不难发现这满足递归的性质，若当前节点就是叶子节点，那么直接判断 sum 是否等于 val 即可（因
         * 为路径和已经确定，就是当前节点的值，只需要判断该路径和是否满足条件）。若当前节点不是叶子节
         * 点，只需要递归地询问它的子节点是否能满足条件即可。
         */
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return dfs(root.left, sum - root.val) || dfs(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,null,1]");
        System.out.println(solution.hasPathSum1(root, 18));
        System.out.println(solution.hasPathSum2(root, 22));
        System.out.println(solution.hasPathSum3(root, 27));
    }
}
