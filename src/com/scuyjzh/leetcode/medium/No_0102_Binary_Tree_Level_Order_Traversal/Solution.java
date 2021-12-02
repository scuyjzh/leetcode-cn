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
     * 方法：广度优先搜索
     *
     * • 时间复杂度：记树上所有节点的个数为 n。每个点进队出队各一次，故渐进时间复杂度为 O(n)。
     * • 空间复杂度：队列中元素的个数不超过 n 个，故渐进空间复杂度为 O(n)。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,null,6]");
        System.out.println(solution.levelOrder(root));
    }
}
