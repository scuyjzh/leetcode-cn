package com.scuyjzh.leetcode.easy.No_0145_Binary_Tree_Postorder_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 145. 二叉树的后序遍历
 *
 * 给定一个二叉树，返回它的 后序 遍历。
 */
class Solution {
    /**
     * 方法一：递归
     *
     * • 时间复杂度：O(n)，其中 n 是二叉树的节点数。每一个节点恰好被遍历一次。
     * • 空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为 O(log n)，最坏情况下树呈现链状，为
     *   O(n)。
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        /*
         * 首先需要了解什么是二叉树的后序遍历：按照访问左子树——右子树——根节点的方式遍历这棵树，而
         * 在访问左子树或者右子树的时候，按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具
         * 有递归的性质，可以直接用递归函数来模拟这一过程。
         *
         * 定义 postorder(root) 表示当前遍历到 root 节点的答案。按照定义，只要递归调用
         * postorder(root->left) 来遍历 root 节点的左子树，然后递归调用 postorder(root->right) 来遍历
         * root 节点的右子树，最后将 root 节点的值加入答案即可，递归终止的条件为碰到空节点。
         */
        List<Integer> list = new LinkedList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    /**
     * 方法二：迭代
     *
     * • 时间复杂度：O(n)，其中 n 是二叉树的节点数。每一个节点恰好被遍历一次。
     * • 空间复杂度：O(n)，为迭代过程中显式栈的开销，平均情况下为 O(log n)，最坏情况下树呈现链状，
     *   为 O(n)。
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        /*
         * 也可以用迭代的方式实现方法一的递归函数，两种方式是等价的，区别在于递归的时候隐式地维护了一
         * 个栈，而在迭代的时候需要显式地将这个栈模拟出来，其余的实现与细节都相同。
         */
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.isEmpty()) {
            // 不断往左子树方向走，每走一次就将当前节点保存到栈中
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
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
     * 方法三：迭代
     *
     * • 时间复杂度：O(n)，其中 n 是二叉树的节点数。每一个节点恰好被遍历一次。
     * • 空间复杂度：O(n)，为迭代过程中显式栈的开销，平均情况下为 O(log n)，最坏情况下树呈现链状，
     *   为 O(n)。
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
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
            // 将当前节点插入答案链表的头部
            // 链表：右 -> 左 -> 根
            list.addFirst(cur.val);
            // 将前序遍历的顺序由从左到右修改为从右到左
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,null,6]");
        System.out.println(solution.postorderTraversal1(root));
        System.out.println(solution.postorderTraversal2(root));
        System.out.println(solution.postorderTraversal3(root));
    }
}
