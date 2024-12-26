package com.scuyjzh.leetcode.medium.No_0098_Validate_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 98. 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 *   • 节点的左子树只包含 小于 当前节点的数。
 *   • 节点的右子树只包含 大于 当前节点的数。
 *   • 所有左子树和右子树自身必须也是二叉搜索树。
 */
class Solution {
    /**
     * 方法一: 递归
     */
    public boolean isValidBST1(TreeNode root) {
        /*
         * 要解决这道题首先要了解二叉搜索树有什么性质可以利用，由题目给出的信息可以知道：如
         * 果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值；若它的右子树不空，则右子
         * 树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树。
         *
         * 这启示设计一个递归函数 helper(root, lower, upper) 来递归判断，函数表示考虑以 root 为根的
         * 子树，判断子树中所有节点的值是否都在 (l,r) 的范围内（注意是开区间）。如果 root 节点的值 val 不
         * 在 (l,r) 的范围内说明不满足条件直接返回，否则要继续递归调用检查它的左右子树是否满足，如果都
         * 满足才说明这是一棵二叉搜索树。
         *
         * 那么根据二叉搜索树的性质，在递归调用左子树时，需要把上界 upper 改为 root.val，即调用
         * helper(root.left, lower, root.val)，因为左子树里所有节点的值均小于它的根节点的值。同理递归调
         * 用右子树时，需要把下界 lower 改为 root.val，即调用 helper(root.right, root.val,
         * upper)。
         *
         * 函数递归调用的入口为 helper(root, -inf, +inf)， inf 表示一个无穷大的值。
         */
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        return helper(root.left, lower, root.val) && helper(root.right, root.val, upper);
    }

    /**
     * 方法二：中序遍历
     */
    public boolean isValidBST2(TreeNode root) {
        /*
         * 基于方法一中提及的性质，可以进一步知道二叉搜索树「中序遍历」得到的值构成的序列一定是升序
         * 的，这启示在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。如果
         * 均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是，下面的代码使用栈来模拟中序遍历的
         * 过程。
         *
         * 中序遍历是二叉树的一种遍历方式，它先遍历左子树，再遍历根节点，最后遍历右子树。而二叉搜索树
         * 保证了左子树的节点的值均小于根节点的值，根节点的值均小于右子树的值，因此中序遍历以后得到的
         * 序列一定是升序序列。
         */
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.isEmpty()) {
            // 不断往左子树方向走，每走一次就将当前节点保存到栈中
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 当前节点为空，说明左边走到头了，从栈中弹出节点并输出
            cur = stack.pop();
            // 如果当前节点的值小于等于上一个节点的值，说明不是二叉搜索树
            if (pre != null && cur.val <= pre.val) {
                return false;
            }
            // 更新上一个节点
            pre = cur;
            // 然后转向右子树节点，继续上面整个过程
            cur = cur.right;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValidBST1(TreeNode.initBinaryTree("[2,1,3]")));
        System.out.println(new Solution().isValidBST2(TreeNode.initBinaryTree("[5,1,4,null,null,3,6]")));
    }
}
