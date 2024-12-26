package com.scuyjzh.leetcode.easy.No_0257_Binary_Tree_Paths;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 257. 二叉树的所有路径
 *
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子
 * 节点的路径。
 * 叶子节点 是指没有子节点的节点。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public List<String> binaryTreePaths1(TreeNode root) {
        /*
         * 最直观的方法是使用深度优先搜索。在深度优先搜索遍历二叉树时，需要考虑当前的节点以及它的孩子
         * 节点。
         *   • 如果当前节点不是叶子节点，则在当前的路径末尾添加该节点，并继续递归遍历该节点的每一个孩子节
         *     点。
         *   • 如果当前节点是叶子节点，则在当前路径末尾添加该节点后就得到了一条从根节点到叶子节点的路
         *     径，将该路径加入到答案即可。
         * 如此，当遍历完整棵二叉树以后就得到了所有从根节点到叶子节点的路径。
         */
        List<String> paths = new LinkedList<>();
        dfs(root, "", paths);
        return paths;
    }

    private void dfs(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuilder sb = new StringBuilder(path);
            sb.append(root.val);
            // 当前节点是叶子节点
            if (root.left == null && root.right == null) {
                // 把路径加入到答案中
                paths.add(sb.toString());
            }
            // 当前节点不是叶子节点，继续递归遍历
            else {
                sb.append("->");
                dfs(root.left, sb.toString(), paths);
                dfs(root.right, sb.toString(), paths);
            }
        }
    }

    /**
     * 方法二：广度优先搜索
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        /*
         * 也可以用广度优先搜索来实现。维护一个队列，存储节点以及根到该节点的路径。一开始这个队列
         * 里只有根节点。在每一步迭代中，取出队列中的首节点，如果它是叶子节点，则将它对应的路径加入到
         * 答案中。如果它不是叶子节点，则将它的所有孩子节点加入到队列的末尾。当队列为空时广度优先搜索结
         * 束，即能得到答案。
         */
        List<String> paths = new LinkedList<>();
        if (root == null) {
            return paths;
        }

        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<String> pathQueue = new ArrayDeque<>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            // 当前节点是叶子节点
            if (node.left == null && node.right == null) {
                // 把路径加入到答案中
                paths.add(path);
            }
            // 当前节点不是叶子节点
            else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    pathQueue.offer(path + "->" + node.left.val);
                }

                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    pathQueue.offer(path + "->" + node.right.val);
                }
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,null,6]");
        System.out.println(new Solution().binaryTreePaths1(root));
        System.out.println(new Solution().binaryTreePaths2(root));
    }
}
