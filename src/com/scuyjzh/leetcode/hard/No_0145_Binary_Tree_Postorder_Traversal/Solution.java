package com.scuyjzh.leetcode.hard.No_0145_Binary_Tree_Postorder_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration with Stack)
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.element();
            if (cur.right == null || cur.right == pre) {
                list.add(cur.val);
                stack.pop();
                // 记录上一个访问的节点
                // 用于判断“访问根节点之前，右子树是否已访问过”
                pre = cur;
                // 表示不需要转向，继续弹栈
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * Approach #2 (Iteration with Stack)
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // 前序遍历顺序为：根 -> 左 -> 右
        // 后序遍历顺序为：左 -> 右 -> 根
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            // 将节点插入链表的头部
            // 链表：右 -> 左 -> 根
            list.addFirst(cur.val);
            // 将遍历的顺序由从左到右修改为从右到左
            // 链表：左 -> 右 -> 根
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return list;
    }

    /**
     * Approach #3 (Recursion - DFS)
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        traversal(root, list);
        return list;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traversal(root.left, list);
        traversal(root.right, list);
        list.add(root.val);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,null,1]");
        System.out.println(solution.postorderTraversal1(root));
        System.out.println(solution.postorderTraversal2(root));
        System.out.println(solution.postorderTraversal3(root));
    }
}
