package com.scuyjzh.leetcode.easy.No_235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration)
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p,
                                          TreeNode q) {
        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        // Start from the root node of the tree
        TreeNode node = root;

        // Traverse the tree
        while (root != null) {
            // Value of ancestor/parent node.
            int parentVal = node.val;

            if (pVal < parentVal && qVal < parentVal) {
                // 如果根节点的值比p.val和q.val都大，则它们的最低公共祖先节点在根节点的左子树中
                node = node.left;
            } else if (pVal > parentVal && qVal > parentVal) {
                // 如果根节点的值比p.val和q.val都小，则它们的最低公共祖先节点在根节点的右子树中
                node = node.right;
            } else {
                // 如果根节点的值处于p.val和q.val之间，则根节点就是它们的最低公共祖先节点
                return node;
            }
        }
        return null;
    }

    /**
     * Approach #2 (Recursion)
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p,
                                          TreeNode q) {
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        // Value of current node or parent node.
        int parentVal = root.val;

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return helper(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return helper(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }
}
