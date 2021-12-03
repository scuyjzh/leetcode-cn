package com.scuyjzh.leetcode.medium.No_0199_Binary_Tree_Right_Side_View;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 199. 二叉树的右视图
 *
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到
 * 底部的顺序，返回从右侧所能看到的节点值。
 */
class Solution {
    /**
     * 方法一：广度优先搜索
     */
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        // 定义队列
        Deque<TreeNode> queue = new ArrayDeque<>();
        // 将根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 当前队列大小，即是当前层的节点个数
            int size = queue.size();
            // 遍历当前层的所有节点
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                // 将当前层的左右孩子入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 将当前层的最后一个节点放入结果列表
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    /**
     * 方法二：深度优先搜索
     */
    public List<Integer> rightSideView2(TreeNode root) {
        // 定义结果列表
        List<Integer> res = new ArrayList<>();
        // 从根节点开始访问，层数是 0
        dfs(0, root, res);
        return res;
    }

    private void dfs(int level, TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        // 结果列表长度等于当前层数时，把当前节点加入结果列表
        // 此时可以保证当前节点为当前层最右边的节点
        if (level == res.size()) {
            res.add(root.val);
        }
        // 按照「根结点 -> 右子树 -> 左子树」的顺序访问，就可以保证每层都是最先访问最右边的节点
        // （与前序遍历「根结点 -> 左子树 -> 右子树」的顺序正好相反，前序遍历每层最先访问的是最左边的节点）
        dfs(level + 1, root.right, res);
        dfs(level + 1, root.left, res);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rightSideView1(TreeNode.initBinaryTree("[1,2,3,null,5,null,4]")));
        System.out.println(new Solution().rightSideView2(TreeNode.initBinaryTree("[1,null,3]")));
    }
}
