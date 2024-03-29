package com.scuyjzh.leetcode.medium.No_0117_Populating_Next_Right_Pointers_in_Each_Node_II;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 *
 * 给定一个二叉树
 *   struct Node {
 *     int val;
 *     Node *left;
 *     Node *right;
 *     Node *next;
 *   }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到
 * 下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 进阶：
 *   • 你只能使用常量级额外空间。
 *   • 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
class Solution {
    /**
     * 方法一：层序遍历
     *
     * • 时间复杂度：O(N)。需要遍历这棵树上所有的点，时间复杂度为 O(N)。
     * • 空间复杂度：O(N)。即队列的空间代价。
     */
    public TreeNode connect1(TreeNode root) {
        /*
         * 这道题希望把二叉树各个层的点组织成链表，一个非常直观的思路是层次遍历。树的层次遍历基于广度
         * 优先搜索，它按照层的顺序遍历二叉树，在遍历第 i 层前，一定会遍历完第 i−1 层。
         *
         * 算法如下：初始化一个队列 q，将根节点放入队列中。当队列不为空的时候，记录当前队列大小为 n，从队
         * 列中以此取出 n 个元素并通过这 n 个元素拓展新节点。如此循环，直到队列为空。
         *
         * 这样做可以保证每次遍历的 n 个点都是同一层的。可以在遍历每一层的时候修改这一层节点的 next 指
         * 针，这样就可以把每一层都组织成链表。
         */
        if (root == null) {
            return null;
        }
        // 初始化队列同时将第一层节点加入队列中，即根节点
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {
            // 记录当前队列大小
            int size = queue.size();
            TreeNode last = null;
            // 遍历这一层的所有节点
            for (int i = 0; i < size; ++i) {
                // 从队首取出元素
                TreeNode node = queue.poll();
                // 拓展下一层节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 连接
                if (i != 0) {
                    last.next = node;
                }
                last = node;
            }
        }
        return root;
    }

    /**
     * 方法二：使用已建立的 next 指针
     *
     * • 时间复杂度：O(N)。分析同「方法一」。
     * • 空间复杂度：O(1)。
     */
    TreeNode last = null, nextStart = null;

    public TreeNode connect2(TreeNode root) {
        /*
         * 因为必须处理树上的所有节点，所以无法降低时间复杂度，但是可以尝试降低空间复杂度。
         *
         * 在方法一中，因为对树的结构一无所知，所以使用队列保证有序访问同一层的所有节点，并建立它们之间的
         * 连接。然而不难发现：一旦在某层的节点之间建立了 next 指针，那这层节点实际上形成了一个链表。因此，
         * 如果先去建立某一层的 next 指针，再去遍历这一层，就无需再使用队列了。
         *
         * 基于该想法，提出降低空间复杂度的思路：如果第 i 层节点之间已经建立 next 指针，就可以通过 next 指针
         * 访问该层的所有节点，同时对于每个第 i 层的节点，又可以通过它的 left 和 right 指针知道其第 i+1 层
         * 的孩子节点是什么，所以遍历过程中就能够按顺序为第 i+1 层节点建立 next 指针。
         *
         * 具体来说：
         *   • 从根节点开始。因为第 0 层只有一个节点，不需要处理。可以在上一层为下一层建立 next 指针。该方
         *     法最重要的一点是：位于第 x 层时为第 x+1 层建立 next 指针。一旦完成这些连接操作，移至第 x+
         *     1 层为第 x+2 层建立 next 指针。
         *   • 当遍历到某层节点时，该层节点的 next 指针已经建立。这样就不需要队列从而节省空间。每次只要知
         *     道下一层的最左边的节点，就可以从该节点开始，像遍历链表一样遍历该层的所有节点。
         */
        if (root == null) {
            return null;
        }
        TreeNode start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (TreeNode p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    private void handle(TreeNode p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().connect1(TreeNode.initBinaryTree("[1,2,3,4,5,null,7]")));
        System.out.println(new Solution().connect2(TreeNode.initBinaryTree("[1,2,3,4,5,null,7]")));
    }
}
