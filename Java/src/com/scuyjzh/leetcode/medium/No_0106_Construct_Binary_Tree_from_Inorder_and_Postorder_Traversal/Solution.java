package com.scuyjzh.leetcode.medium.No_0106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /*
         * 首先解决这道题需要明确给定一棵二叉树，是如何对其进行中序遍历与后序遍历的：
         *   • 中序遍历的顺序是每次遍历左孩子，再遍历根节点，最后遍历右孩子。
         *   • 后序遍历的顺序是每次遍历左孩子，再遍历右孩子，最后遍历根节点。
         *
         * 因此根据上文所述，可以发现后序遍历的数组最后一个元素代表的即为根节点。知道这个性质后，
         * 可以利用已知的根节点信息在中序遍历的数组中找到根节点所在的下标，然后根据其将中序遍历的数组分成
         * 左右两部分，左边部分即左子树，右边部分为右子树，针对每个部分可以用同样的方法继续递归下去构造。
         *
         * 为了高效查找根节点元素在中序遍历数组中的下标，选择创建哈希表来存储中序序列，即建立一个
         * （元素，下标）键值对的哈希表。
         */
        int inLen = inorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; ++i) {
            map.put(inorder[i], i);
        }
        return helper(postorder.length - 1, 0, inLen - 1, postorder, map);
    }

    private TreeNode helper(int postRoot, int inStart, int inEnd, int[] postorder, Map<Integer, Integer> map) {
        if (postRoot < 0 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postRoot]);
        // 在中序遍历中定位根节点
        int inRoot = map.get(root.val);
        root.left = helper(postRoot - (inEnd - inRoot) - 1, inStart, inRoot - 1, postorder, map);
        root.right = helper(postRoot - 1, inRoot + 1, inEnd, postorder, map);
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        System.out.println(new Solution().buildTree(inorder, postorder));
    }
}
