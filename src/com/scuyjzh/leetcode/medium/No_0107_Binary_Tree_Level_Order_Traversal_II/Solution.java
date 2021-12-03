package com.scuyjzh.leetcode.medium.No_0107_Binary_Tree_Level_Order_Traversal_II;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 107. 二叉树的层序遍历 II
 *
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点
 * 所在层到根节点所在的层，逐层从左向右遍历）
 */
class Solution {
    /**
     * 方法一：广度优先搜索
     */
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        /*
         * 这道题和「102. 二叉树的层序遍历」相似，不同之处在于，第 102 题要求从上到下输出每一层的节点值，而
         * 这道题要求从下到上输出每一层的节点值。除了输出顺序不同以外，这两道题的思路是相同的，都可以使用
         * 广度优先搜索进行层次遍历。
         *
         * 树的层次遍历可以使用广度优先搜索实现。从根节点开始搜索，每次遍历同一层的全部节点，使用一个列表
         * 存储该层的节点值。
         *
         * 如果要求从上到下输出每一层的节点值，做法是很直观的，在遍历完一层节点之后，将存储该层节点值的列
         * 表添加到结果列表的尾部。这道题要求从下到上输出每一层的节点值，只要对上述操作稍作修改即可：在遍
         * 历完一层节点之后，将存储该层节点值的列表添加到结果列表的头部。
         *
         * 为了降低在结果列表的头部添加一层节点值的列表的时间复杂度，结果列表可以使用链表的结构，在链表头
         * 部添加一层节点值的列表的时间复杂度是 O(1)。在 Java 中，由于需要返回的 List 是一个接口，这里
         * 可以使用链表实现；而 C++ 或 Python 中，需要返回一个 vector 或 list，它不方便在头部插入元
         * 素（会增加时间开销），所以可以先用尾部插入的方法得到从上到下的层次遍历列表，然后再进行反
         * 转。
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
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 采用前插法
            list.add(0, level);
        }
        return list;
    }

    /**
     * 方法二：深度优先搜索（递归）
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        // 用来存放最终结果
        List<List<Integer>> res = new LinkedList<>();
        dfs(1, root, res);
        return res;
    }

    private void dfs(int level, TreeNode root, List<List<Integer>> res) {
        // 假设 res 是 [ [2,3] [1] ]，level 是 3，就再插入一个空 list 放到 res 中
        if (res.size() < level) {
            // 采用前插法，插入到 res 头部
            res.add(0, new LinkedList<>());
        }
        // 将当前节点的值加入到 res 中，level 代表当前层，假设 level 是 3，节点值是 99
        // res 是 [ [4] [2,3] [1] ]，加入后 res 就变为 [ [4,99] [2,3] [1] ]
        res.get(res.size() - level).add(root.val);
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
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,null,99]");
        System.out.println(solution.levelOrderBottom1(root));
        System.out.println(solution.levelOrderBottom2(root));
    }
}
