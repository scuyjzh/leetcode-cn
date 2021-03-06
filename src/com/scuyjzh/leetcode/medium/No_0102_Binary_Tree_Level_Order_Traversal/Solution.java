package com.scuyjzh.leetcode.medium.No_0102_Binary_Tree_Level_Order_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration with Queue - BFS)
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根结点放入队列中，然后不断遍历队列
        queue.add(root);
        while (!queue.isEmpty()) {
            // 获取当前队列的长度，这个长度相当于当前这一层的结点个数
            int size = queue.size();
            List<Integer> tmp = new LinkedList<>();
            while (size-- > 0) {
                TreeNode cur = queue.remove();
                tmp.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        // 用来存放最终结果
        List<List<Integer>> res = new LinkedList<>();
        dfs(1, root, res);
        return res;
    }

    private void dfs(int level, TreeNode root, List<List<Integer>> res) {
        // 假设res是[ [1] [2,3] ]，level是3，就再插入一个空list放到res中
        if (res.size() < level) {
            res.add(new LinkedList<>());
        }
        // 将当前结点的值加入到res中，level代表当前层，假设level是3，结点值是99
        // res是[ [1] [2,3] [4] ]，加入后res就变为 [ [1] [2,3] [4,99] ]
        res.get(level - 1).add(root.val);
        // 递归处理左子树和右子树，同时将层数level加1
        if (root.left != null) {
            dfs(level + 1, root.left, res);
        }
        if (root.right != null) {
            dfs(level + 1, root.right, res);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,null,99]");
        System.out.println(solution.levelOrder1(root));
        System.out.println(solution.levelOrder2(root));
    }
}
