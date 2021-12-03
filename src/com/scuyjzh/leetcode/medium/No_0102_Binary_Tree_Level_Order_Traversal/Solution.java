package com.scuyjzh.leetcode.medium.No_0102_Binary_Tree_Level_Order_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从
 * 左到右访问所有节点）。
 */
class Solution {
    /**
     * 方法一：广度优先搜索
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        /*
         * 可以用一种巧妙的方法修改广度优先搜索：
         *   • 首先根元素入队
         *   • 当队列不为空的时候
         *       ○ 求当前队列的长度 s_i
         *       ○ 依次从队列中取 s_i 个元素进行拓展，然后进入下一次迭代
         * 它和普通广度优先搜索的区别在于，普通广度优先搜索每次只取一个元素拓展，而这里每次取 s_i 个元素。在
         * 上述过程中的第 i 次迭代就得到了二叉树的第 i 层的 s_i 个元素。
         */
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) {
            return list;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        // 将根节点放入队列中，然后不断遍历队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 获取当前队列的长度，这个长度相当于当前这一层的节点个数
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            list.add(level);
        }
        return list;
    }

    /**
     * 方法二：深度优先搜索（递归）
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
        // 假设 res 是 [ [1] [2,3] ]，level 是 3，就再插入一个空 list 放到 res 中
        if (res.size() < level) {
            res.add(new LinkedList<>());
        }
        // 将当前节点的值加入到 res 中，level 代表当前层，假设 level 是 3，节点值是 99
        // res 是 [ [1] [2,3] [4] ]，加入后 res 就变为 [ [1] [2,3] [4,99] ]
        res.get(level - 1).add(root.val);
        // 递归处理左子树和右子树，同时将层数 level 加 1
        if (root.left != null) {
            dfs(level + 1, root.left, res);
        }
        if (root.right != null) {
            dfs(level + 1, root.right, res);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.levelOrder1(root));
        System.out.println(solution.levelOrder2(root));
    }
}
