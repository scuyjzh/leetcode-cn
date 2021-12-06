package com.scuyjzh.leetcode.medium.No_0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并
 * 返回其根节点。
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
         * 对于任意一颗树而言，前序遍历的形式总是
         *     [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
         * 即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是
         *     [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
         *
         * 只要在中序遍历中定位到根节点，那么就可以分别知道左子树和右子树中的节点数目。由于同一颗
         * 子树的前序遍历和中序遍历的长度显然是相同的，因此就可以对应到前序遍历的结果中，对上述形式中
         * 的所有左右括号进行定位。
         *
         * 这样一来，就知道了左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，
         * 就可以递归地对构造出左子树和右子树，再将这两颗子树接到根节点的左右位置。
         *
         * 在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，但这样
         * 做的时间复杂度较高。可以考虑使用哈希表来帮助快速地定位根节点。对于哈希映射中的每个键值
         * 对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。在构造二叉树的过程之前，可
         * 以对中序遍历的列表进行一遍扫描，就可以构造出这个哈希映射。在此后构造二叉树的过程中，就只需
         * 要 O(1) 的时间对根节点进行定位了。
         */
        int inLen = inorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; ++i) {
            map.put(inorder[i], i);
        }
        return helper(0, 0, inLen - 1, preorder, map);
    }

    private TreeNode helper(int preRoot, int inStart, int inEnd, int[] preorder, Map<Integer, Integer> map) {
        if (preRoot >= preorder.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preRoot]);
        // 在中序遍历中定位根节点
        int inRoot = map.get(root.val);
        root.left = helper(preRoot + 1, inStart, inRoot - 1, preorder, map);
        root.right = helper(preRoot + (inRoot - inStart) + 1, inRoot + 1, inEnd, preorder, map);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        System.out.println(new Solution().buildTree(preorder, inorder));
    }
}
