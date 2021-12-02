package com.scuyjzh.leetcode.easy.No_0094_Binary_Tree_Inorder_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 *
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 */
class Solution {
    /**
     * 方法一：递归
     *
     * • 时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访
     *   问一次。
     * • 空间复杂度：O(n)。空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到
     *   O(n) 的级别。
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        /*
         * 首先需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，而
         * 在访问左子树或者右子树的时候按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具有
         * 递归的性质，可以直接用递归函数来模拟这一过程。
         *
         * 定义 inorder(root) 表示当前遍历到 root 节点的答案，那么按照定义，只要递归调用
         * inorder(root.left) 来遍历 root 节点的左子树，然后将 root 节点的值加入答案，再递归调用
         * inorder(root.right) 来遍历 root 节点的右子树即可，递归终止的条件为碰到空节点。
         */
        List<Integer> list = new LinkedList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * 方法二：迭代
     *
     * • 时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访
     *   问一次。
     * • 空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级
     *   别。
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        /*
         * 方法一的递归函数也可以用迭代的方式实现，两种方式是等价的，区别在于递归的时候隐式地维护了一
         * 个栈，而在迭代的时候需要显式地将这个栈模拟出来，其他都相同。
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
                stack.push(cur);
                cur = cur.left;
            }
            // 当前节点为空，说明左边走到头了，从栈中弹出节点并输出（与前序遍历唯一不同的地方）
            cur = stack.pop();
            list.add(cur.val);
            // 然后转向右子树节点，继续上面整个过程
            cur = cur.right;
        }
        return list;
    }

    /**
     * 方法三：Morris 遍历
     *
     * • 时间复杂度：O(n)，其中 n 是二叉树的节点数。没有左子树的节点只被访问一次，有左子树的节点被
     *   访问两次。
     * • 空间复杂度：O(1)。只操作已经存在的指针（树的空闲指针），因此只需要常数的额外空间。
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        /*
         * Morris 遍历算法是另一种遍历二叉树的方法，它能将非递归的中序遍历空间复杂度降为 O(1)。
         * Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 cur）：
         *   1.如果 cur 无左孩子，先将 cur 的值加入答案数组，再访问 cur 的右孩子，即 cur = cur.right。
         *   2.如果 cur 有左孩子，则找到 cur 左子树上最右的节点（即左子树中序遍历的最后一个节点，cur 在中序遍历
         *     中的前驱节点），记为 pre。根据 pre 的右孩子是否为空，进行如下操作。
         *       ○ 如果 pre 的右孩子为空，则将其右孩子指向 cur，然后访问 cur 的左孩子，即 cur = cur.left。
         *       ○ 如果 pre 的右孩子不为空，则此时其右孩子指向 cur，说明已经遍历完 cur 的左子树，
         *         将 pre 的右孩子置空，将 cur 的值加入答案数组，然后访问 cur 的右孩子，即 cur = cur.right。
         *   3.重复上述操作，直至访问完整棵树。
         *
         * 其实整个过程就多做一步：假设当前遍历到的节点为 cur，将 cur 的左子树中最右边的节点的右孩子指向 cur
         * ，这样在左子树遍历完成后通过这个指向走回了 cur，且能通过这个指向知晓已经遍历完成了左子
         * 树，而不用再通过栈来维护，省去了栈的空间复杂度。
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
                //     当前节点更新为当前节点的左孩子，继续遍历左子树。
                if (pre.right == null) {
                    // 找到当前节点在中序遍历下的前驱节点
                    pre.right = cur;
                    // 当前节点更新为当前节点的左孩子，继续遍历左子树
                    cur = cur.left;
                }
                // 2.b)如果前驱节点的右孩子为当前节点（第二次遍历到当前节点，说明左子树已经访问完了），将它的右孩子重新设为空（恢复树的形状）。
                //     将当前节点加入答案（这是与前序遍历唯一一点不同）。当前节点更新为当前节点的右孩子。
                if (pre.right == cur) {
                    // 恢复树的形状
                    pre.right = null;
                    // 将当前节点加入答案（这是与前序遍历唯一一点不同）
                    list.add(cur.val);
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
        System.out.println(solution.inorderTraversal1(root));
        System.out.println(solution.inorderTraversal2(root));
        System.out.println(solution.inorderTraversal3(root));
    }
}
