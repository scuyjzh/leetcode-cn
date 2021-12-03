package com.scuyjzh.leetcode.medium.No_0103_Binary_Tree_Zigzag_Level_Order_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从
 * 右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
class Solution {
    /**
     * 方法一：广度优先遍历
     */
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        /*
         * 此题是「102. 二叉树的层序遍历」的变种，最后输出的要求有所变化，要求按层数的奇偶来决定每一层
         * 的输出顺序。规定二叉树的根节点为第 0 层，如果当前层数是偶数，从左至右输出当前层的节点值，否则，
         * 从右至左输出当前层的节点值。
         *
         * 依然可以沿用第 102 题的思想，修改广度优先搜索，对树进行逐层遍历，用队列维护当前层的所有元
         * 素，当队列不为空的时候，求得当前队列的长度 size，每次从队列中取出 size 个元素进行拓展，然后进行下
         * 一次迭代。
         *
         * 为了满足题目要求的返回值为「先从左往右，再从右往左」交替输出的锯齿形，可以利用「双端队列」
         * 的数据结构来维护当前层节点值输出的顺序。
         *
         * 双端队列是一个可以在队列任意一端插入元素的队列。在广度优先搜索遍历当前层节点拓展下一层节点的
         * 时候仍然从左往右按顺序拓展，但是对当前层节点的存储维护一个变量 isOrderLeft 记录是从左至右
         * 还是从右至左的：
         *   • 如果从左至右，每次将被遍历到的元素插入至双端队列的末尾。
         *   • 如果从右至左，每次将被遍历到的元素插入至双端队列的头部。
         *
         * 当遍历结束的时候就得到了答案数组。
         */
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean isOrderLeft = true;
        while (!queue.isEmpty()) {
            Deque<Integer> level = new LinkedList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (isOrderLeft) {
                    level.offerLast(cur.val);
                } else {
                    level.offerFirst(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            list.add(new LinkedList<>(level));
            isOrderLeft = !isOrderLeft;
        }
        return list;
    }

    /**
     * 方法二：深度优先搜索（递归）
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        // 用来存放最终结果
        List<List<Integer>> res = new LinkedList<>();
        dfs(0, root, res);
        return res;
    }

    private void dfs(int level, TreeNode root, List<List<Integer>> res) {
        // 假设 res 是 [ [2,3] [1] ]，level 是 2，就再插入一个空 list 放到 res 中
        if (res.size() <= level) {
            res.add(new LinkedList<>());
        }
        // 将当前节点的值加入到 res 中，level 代表当前层
        if (level % 2 == 0) {
            // 偶数行插入到链表尾部
            res.get(level).add(root.val);
        } else {
            // 奇数行采用前插法
            res.get(level).add(0, root.val);
        }
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
        System.out.println(solution.zigzagLevelOrder1(root));
        System.out.println(solution.zigzagLevelOrder2(root));
    }
}
