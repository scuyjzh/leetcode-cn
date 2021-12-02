package com.scuyjzh.leetcode.easy.No_0144_Binary_Tree_Preorder_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 144. 二叉树的前序遍历
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
class Solution {
    /**
     * 方法一：递归
     *
     * • 时间复杂度：O(n)，其中 n 是二叉树的节点数。每一个节点恰好被遍历一次。
     * • 空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为 O(log n)，最坏情况下树呈现链状，为
     *   O(n)。
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        /*
         * 首先需要了解什么是二叉树的前序遍历：按照访问根节点——左子树——右子树的方式遍历这棵树，而
         * 在访问左子树或者右子树的时候，按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具
         * 有递归的性质，可以直接用递归函数来模拟这一过程。
         *
         * 定义 preorder(root) 表示当前遍历到 root 节点的答案。按照定义，只要首先将 root 节点的值加
         * 入答案，然后递归调用 preorder(root.left) 来遍历 root 节点的左子树，最后递归调用
         * preorder(root.right) 来遍历 root 节点的右子树即可，递归终止的条件为碰到空节点。
         */
        List<Integer> list = new LinkedList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    /**
     * 方法二：迭代
     *
     * • 时间复杂度：O(n)，其中 n 是二叉树的节点数。每一个节点恰好被遍历一次。
     * • 空间复杂度：O(n)，为迭代过程中显式栈的开销，平均情况下为 O(log n)，最坏情况下树呈现链状，
     *   为 O(n)。
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        /*
         * 也可以用迭代的方式实现方法一的递归函数，两种方式是等价的，区别在于递归的时候隐式地维护了一
         * 个栈，而在迭代的时候需要显式地将这个栈模拟出来，其余的实现与细节都相同。
         */
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 不断往左子树方向走，每走一次就将当前节点保存到栈中
            while (cur != null) {
                // 每次遍历到左子节点，立即输出即可（与中序遍历唯一不同的地方）
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            // 弹出访问过的左子节点
            cur = stack.pop();
            // 转向到右子节点
            cur = cur.right;
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
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        // 由于栈是“先进后出”的顺序，所以入栈时先将右子树入栈，这样使得前序遍历结果为 “根->左->右”的顺序
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 先把根节点压入栈中
        stack.push(root);
        while (!stack.isEmpty()) {
            // 根节点值加入到结果中
            TreeNode cur = stack.pop();
            list.add(cur.val);
            // 右子树入栈
            if (cur.right != null) {
                stack.push(cur.right);
            }
            // 左子树入栈
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return list;
    }

    /**
     * 方法四：Morris 遍历
     *
     * • 时间复杂度：O(n)，其中 n 是二叉树的节点数。没有左子树的节点只被访问一次，有左子树的节点被
     *   访问两次。
     * • 空间复杂度：O(1)。只操作已经存在的指针（树的空闲指针），因此只需要常数的额外空间。
     */
    public List<Integer> preorderTraversal4(TreeNode root) {
        /*
         * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其前序遍历规则总结如下：
         *   1.新建临时节点，令该节点为 cur；
         *   2.如果当前节点的左子节点为空，将当前节点加入答案，并遍历当前节点的右子节点；
         *   3.如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点：
         *       ○ 如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点。然后将当前节点加入答
         *         案，并将前驱节点的右子节点更新为当前节点。当前节点更新为当前节点的左子节点。
         *       ○ 如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。当前节点更新为当前节点的
         *         右子节点。
         *   4.重复步骤 2 和步骤 3，直到遍历结束。
         */
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root, pre;
        while (cur != null) {
            // 1.如果当前节点没有左孩子，将当前节点加入答案并将其右孩子作为当前节点
            if (cur.left == null) {
                // 将当前节点加入答案
                list.add(cur.val);
                // 将当前节点更新为右孩子
                cur = cur.right;
            }
            // 2.如果当前节点有左孩子，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点
            else {
                // pre 节点就是当前 cur 节点向左走一步，然后一直向右走至无法走为止
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    // 退出循环的条件是：
                    // (1) pre.right == null，第一次遍历到当前节点，执行 2.a
                    // (2) pre.right == cur，第二次遍历到当前节点，执行 2.b
                    pre = pre.right;
                }
                // 2.a)如果前驱节点的右孩子为空，将它的右孩子设置为当前节点（第一次遍历到当前节点）。
                //     将当前节点加入答案（这是与中序遍历唯一一点不同）。当前节点更新为当前节点的左孩子，继续遍历左子树。
                if (pre.right == null) {
                    // 找到当前节点在中序遍历下的前驱节点
                    pre.right = cur;
                    // 将当前节点加入答案（这是与中序遍历唯一一点不同）
                    list.add(cur.val);
                    // 将当前节点更新为左孩子，继续遍历左子树
                    cur = cur.left;
                }
                // 2.b)如果前驱节点的右孩子为当前节点（第二次遍历到当前节点，说明左子树已经访问完了），将它的右孩子重新设为空（恢复树的形状）。
                //     当前节点更新为当前节点的右孩子。
                if (pre.right == cur) {
                    // 恢复树的形状
                    pre.right = null;
                    // 当前节点更新为当前节点的右孩子
                    cur = cur.right;
                }
            }
            // 3.重复以上1、2直到当前节点为空
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,null,6]");
        System.out.println(solution.preorderTraversal1(root));
        System.out.println(solution.preorderTraversal2(root));
        System.out.println(solution.preorderTraversal3(root));
        System.out.println(solution.preorderTraversal4(root));
    }
}
