package com.scuyjzh.leetcode.medium.No_0173_Binary_Search_Tree_Iterator;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 173. 二叉搜索树迭代器
 *
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜
 * 索树（BST）的迭代器：
 *   • BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对
 *     象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初
 *     始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元
 *     素。
 *   • boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true
 *     ；否则返回 false 。
 *   • int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次
 * 调用将返回 BST 中的最小元素。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST
 * 的中序遍历中至少存在一个下一个数字。
 */
class BSTIterator {

    private TreeNode cur;
    private Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        /*
         * 根据二叉搜索树的性质，不难发现，原问题等价于对二叉搜索树进行中序遍历。因此，可以采取与「94.
         * 二叉树的中序遍历」类似的方法来解决这一问题。
         *
         * 可以直接对二叉搜索树做一次完全的递归遍历，获取中序遍历的全部结果并保存在数组中。随后，
         * 利用得到的数组本身来实现迭代器。
         *
         * 除了递归的方法外，还可以利用栈这一数据结构，通过迭代的方式对二叉树做中序遍历。此时，无
         * 需预先计算出中序遍历的全部结果，只需要实时维护当前栈的情况即可。
         */
        cur = root;
        stack = new ArrayDeque<>();
    }

    public int next() {
        // 不断往左子树方向走，每走一次就将当前节点保存到栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        // 当前节点为空，说明左边走到头了，从栈中弹出节点并输出
        cur = stack.pop();
        int ret = cur.val;
        // 然后转向右子树节点，继续上面整个过程
        cur = cur.right;
        return ret;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

    public static void main(String[] args) {
        BSTIterator bSTIterator = new BSTIterator(TreeNode.initBinaryTree("[7,3,15,null,null,9,20]"));
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
    }
}
