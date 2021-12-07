package com.scuyjzh.leetcode.medium.No_0314_Binary_Tree_Vertical_Order_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 314. 二叉树的垂直遍历
 *
 * 给你一个二叉树的根节点，返回其节点按 垂直方向（从上到下，逐列）遍历的结
 * 果。
 * 如果两个节点在同一行和列，那么顺序则为 从左到右。
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // 主要思想是利用层序遍历
        // 因为是要求垂直输出结果，所以假设根节点的初始位置是 0，那么左边向下逐层减 1，右边向下逐层加 1
        if (root == null) {
            return new ArrayList<>();
        }
        // 存放当前位置的结果集
        Map<Integer, List<Integer>> res = new TreeMap<>();
        // 存放当前节点的位置
        Map<TreeNode, Integer> nodeMap = new HashMap<>();
        nodeMap.put(root, 0);
        // 初始化队列同时将第一层节点加入队列中，即根节点
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        // 层序遍历
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int pos = nodeMap.get(node);
            // 如果当前位置还没有存储元素的结果集，则初始化 value，并添加元素
            res.computeIfAbsent(pos, key -> new ArrayList<>()).add(node.val);
            // 左边向下逐层减 1
            if (node.left != null) {
                queue.offer(node.left);
                nodeMap.put(node.left, pos - 1);
            }
            // 右边向下逐层加 1
            if (node.right != null) {
                queue.offer(node.right);
                nodeMap.put(node.right, pos + 1);
            }
        }
        return new ArrayList<>(res.values());
    }

    public static void main(String[] args) {
        System.out.println(new Solution().verticalOrder(TreeNode.initBinaryTree("[3,9,20,null,null,15,7]")));
        System.out.println(new Solution().verticalOrder(TreeNode.initBinaryTree("[3,9,8,4,0,1,7]")));
        System.out.println(new Solution().verticalOrder(TreeNode.initBinaryTree("[3,9,8,4,0,1,7,null,null,null,2,5]")));
        System.out.println(new Solution().verticalOrder(TreeNode.initBinaryTree("[]")));
    }
}
