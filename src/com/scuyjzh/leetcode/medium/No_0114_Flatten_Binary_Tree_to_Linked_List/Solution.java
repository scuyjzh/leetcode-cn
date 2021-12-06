package com.scuyjzh.leetcode.medium.No_0114_Flatten_Binary_Tree_to_Linked_List;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 114. 二叉树展开为链表
 *
 * 给你二叉树的根节点 root ，请你将它展开为一个单链表：
 *   • 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向
 *     链表中下一个节点，而左子指针始终为 null 。
 *   • 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
class Solution {
    /**
     * 方法一：前序遍历
     */
    public void flatten1(TreeNode root) {
        /*
         * 将二叉树展开为单链表之后，单链表中的节点顺序即为二叉树的前序遍历访问各节点的顺序。因此，可以对
         * 二叉树进行前序遍历，获得各节点被访问到的顺序。由于将二叉树展开为链表之后会破坏二叉树的结构，因
         * 此在前序遍历结束之后更新每个节点的左右子节点的信息，将二叉树展开为单链表。
         *
         * 前序遍历可以通过递归或者迭代的方式实现。以下代码通过迭代实现前序遍历。
         */
        List<TreeNode> list = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        // 对二叉树进行前序遍历，获得各节点被访问到的顺序
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                list.add(cur);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        // 更新每个节点的左右子节点的信息，将二叉树展开为单链表
        int size = list.size();
        for (int i = 1; i < size; ++i) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    /**
     * 方法二：前序遍历和展开同步进行
     */
    public void flatten2(TreeNode root) {
        /*
         * 使用方法一的前序遍历，由于将节点展开之后会破坏二叉树的结构而丢失子节点的信息，因此前序遍历和展
         * 开为单链表分成了两步。能不能在不丢失子节点的信息的情况下，将前序遍历和展开为单链表同时进行？
         *
         * 之所以会在破坏二叉树的结构之后丢失子节点的信息，是因为在对左子树进行遍历时，没有存储右子节点的
         * 信息，在遍历完左子树之后才获得右子节点的信息。只要对前序遍历进行修改，在遍历左子树之前就获得左
         * 右子节点的信息，并存入栈内，子节点的信息就不会丢失，就可以将前序遍历和展开为单链表同时进行。
         *
         * 该做法不适用于递归实现的前序遍历，只适用于迭代实现的前序遍历。修改后的前序遍历的具体做法是，每
         * 次从栈内弹出一个节点作为当前访问的节点，获得该节点的子节点，如果子节点不为空，则依次将右子节点
         * 和左子节点压入栈内（注意入栈顺序）。
         *
         * 展开为单链表的做法是，维护上一个访问的节点 pre，每次访问一个节点时，令当前访问的节点为
         * cur，将 pre 的左子节点设为 null 以及将 pre 的右子节点设为 cur，然后将 cur 赋值给
         * pre，进入下一个节点的访问，直到遍历结束。需要注意的是，初始时 pre 为 null，只有在 pre
         * 不为 null 时才能对 pre 的左右子节点进行更新。
         */
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // 维护上一个访问的节点 pre
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            // 每次访问一个节点时，令当前访问的节点为 cur
            TreeNode cur = stack.pop();
            // 初始时 pre 为 null，只有在 pre 不为 null 时才能对 pre 的左右子节点进行更新
            if (pre != null) {
                // 将 pre 的左子节点设为 null
                pre.left = null;
                // 将 pre 的右子节点设为 cur
                pre.right = cur;
            }
            // 如果子节点不为空，依次将右子节点和左子节点压入栈内（注意入栈顺序）
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            // 将 cur 赋值给 pre
            pre = cur;
        }
    }

    /**
     * 方法三：寻找前驱节点
     */
    public void flatten3(TreeNode root) {
        TreeNode cur = root;
        /*
         * 前两种方法都借助前序遍历，前序遍历过程中需要使用栈存储节点。有没有空间复杂度是 O(1) 的做法呢？
         *
         * 注意到前序遍历访问各节点的顺序是根节点、左子树、右子树。如果一个节点的左子节点为空，则该节点不
         * 需要进行展开操作。如果一个节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，该
         * 节点的右子节点被访问。该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点
         * 的前驱节点。因此，问题转化成寻找当前节点的前驱节点。
         *
         * 具体做法是，对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，
         * 将当前节点的右子节点赋给前驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，并
         * 将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处
         * 理结束。
         */
        while (cur != null) {
            // 对于当前节点，如果其左子节点不为空
            if (cur.left != null) {
                TreeNode left = cur.left;
                // 在其左子树中找到最右边的节点，作为前驱节点
                TreeNode pre = left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将当前节点的右子节点赋给前驱节点的右子节点
                pre.right = cur.right;
                // 然后将当前节点的左子节点赋给当前节点的右子节点
                cur.right = left;
                // 并将当前节点的左子节点设为空
                cur.left = null;
            }
            // 继续处理链表中的下一个节点
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        new Solution().flatten1(TreeNode.initBinaryTree("[1,2,5,3,4,null,6]"));
        new Solution().flatten2(TreeNode.initBinaryTree("[1,2,5,3,4,null,6]"));
        new Solution().flatten3(TreeNode.initBinaryTree("[1,2,5,3,4,null,6]"));
    }
}
