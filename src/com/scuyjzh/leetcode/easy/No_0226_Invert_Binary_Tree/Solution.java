package com.scuyjzh.leetcode.easy.No_0226_Invert_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 226. 翻转二叉树
 * <p>
 * 翻转一棵二叉树。
 */
class Solution {
    /**
     * 方法一：递归
     * 时间复杂度：O(N)，其中 N 为二叉树节点的数目。会遍历二叉树中的每一个节点，对每个节点而言，在常数时间内交换其两棵子树。
     * 空间复杂度：O(N)。使用的空间由递归栈的深度决定，它等于当前节点在二叉树中的高度。在平均情况下，二叉树的高度与节点个数为对数关系，即 O(logN)。而在最坏情况下，树形成链状，空间复杂度为 O(N)。
     */
    public TreeNode invertTree1(TreeNode root) {
        /*
         * 思路与算法：
         * 这是一道很经典的二叉树问题。显然，从根节点开始，递归地对树进行遍历，并从叶子节点先开始翻转。
         * 如果当前遍历到的节点 root 的左右两棵子树都已经翻转，那么只需要交换两棵子树的位置，即可完成
         * 以 root 为根节点的整棵子树的翻转。
         */
        return invert(root);
    }

    private TreeNode invert(TreeNode root) {
        // 递归函数的终止条件，当节点为 null 时直接返回
        if (root == null) {
            return null;
        }
        // 如果当前结点不为 null，那么先将其左右子树进行翻转，然后交换左右子树
        TreeNode right = invert(root.right);
        TreeNode left = invert(root.left);
        root.left = right;
        root.right = left;
        // 返回值为完成了翻转后的当前结点
        return root;
    }

    /**
     * 方法二：迭代
     * 时间复杂度：O(N)，其中 N 为二叉树节点的数目。树中的每个节点都需要入队列/出队列一次。
     * 空间复杂度：O(N)。最坏的情况下队列会包含所有的叶子节点，完全二叉树的叶子节点数是 n/2 个。
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 将二叉树中的结点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每次都从队列中拿一个结点，并交换这个结点的左右子树
            TreeNode cur = queue.remove();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            // 如果当前结点的左子树不为空，则放入队列等待后续处理
            if (cur.left != null) {
                queue.add(cur.left);
            }
            // 如果当前结点的右子树不为空，则放入队列等待后续处理
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        // 返回处理完的根结点
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,7]");
        new Solution().invertTree1(root);
        new Solution().invertTree2(root);
    }
}