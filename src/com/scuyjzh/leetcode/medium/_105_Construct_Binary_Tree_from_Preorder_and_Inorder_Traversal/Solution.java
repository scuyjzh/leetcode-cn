package com.scuyjzh.leetcode.medium._105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration)
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        // deal with edge case(s)
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        // build a map of the indices of the values as they appear in the inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        // initialize the stack of tree nodes
        Stack<TreeNode> stack = new Stack<>();
        int value = preorder[0];
        TreeNode root = new TreeNode(value);
        stack.push(root);

        // for all remaining values...
        for (int i = 1; i < preorder.length; ++i) {
            // create a node
            value = preorder[i];
            TreeNode node = new TreeNode(value);
            if (map.get(value) < map.get(stack.peek().val)) {
                // the new node is on the left of the last node,
                // so it must be its left child (that's the way preorder works)
                stack.peek().left = node;
            } else {
                // the new node is on the right of the last node,
                // so it must be the right child of either the last node
                // or one of the last node's ancestors.
                // pop the stack until we either run out of ancestors
                // or the node at the top of the stack is to the right of the new node
                TreeNode parent = null;
                while (!stack.empty() && map.get(value) > map.get(stack.peek().val)) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }

        return root;
    }

    /**
     * Approach #2 (Recursion)
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(0, 0, inorder.length - 1, preorder, inorder, map);
    }

    private TreeNode helper(int preRoot, int inStart, int inEnd, int[] preorder, int[] inorder, Map<Integer, Integer> map) {
        if (preRoot >= preorder.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preRoot]);
        // Index of current root in inorder
        int inRoot = map.get(root.val);
        root.left = helper(preRoot + 1, inStart, inRoot - 1, preorder, inorder, map);
        root.right = helper(preRoot + (inRoot - inStart) + 1, inRoot + 1, inEnd, preorder, inorder, map);
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] preorder = new int[]{1, 2, 4, 5, 3, 6};
        int[] inorder = new int[]{4, 2, 5, 1, 3, 6};
        System.out.println(solution.buildTree1(preorder, inorder));
        System.out.println(solution.buildTree2(preorder, inorder));
    }
}
