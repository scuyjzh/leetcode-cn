package com.scuyjzh.leetcode.medium.No_106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Iteration)
     */
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        // deal with edge case(s)
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }

        // build a map of the indices of the values as they appear in the inorder array
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        // initialize the stack of tree nodes
        Deque<TreeNode> stack = new ArrayDeque<>();
        int value = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(value);
        stack.push(root);

        // for all remaining values...
        for (int i = postorder.length - 2; i >= 0; --i) {
            // create a node
            value = postorder[i];
            TreeNode node = new TreeNode(value);
            if (map.get(value) > map.get(stack.peek().val)) {
                // the new node is on the right of the last node,
                // so it must be its right child (that's the way postorder works)
                stack.peek().right = node;
            } else {
                // the new node is on the left of the last node,
                // so it must be the left child of either the last node
                // or one of the last node's ancestors.
                // pop the stack until we either run out of ancestors
                // or the node at the top of the stack is to the left of the new node
                TreeNode parent = null;
                while (!stack.isEmpty() && map.get(value) < map.get(stack.peek().val)) {
                    parent = stack.pop();
                }
                parent.left = node;
            }
            // record the last node
            stack.push(node);
        }

        return root;
    }

    /**
     * Approach #2 (Recursion)
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(postorder.length - 1, 0, inorder.length - 1, postorder, inorder, map);
    }

    private TreeNode helper(int postRoot, int inStart, int inEnd, int[] postorder, int[] inorder, Map<Integer, Integer> map) {
        if (postRoot < 0 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postRoot]);
        // Index of current root in inorder
        int inRoot = map.get(root.val);
        root.left = helper(postRoot - (inEnd - inRoot) - 1, inStart, inRoot - 1, postorder, inorder, map);
        root.right = helper(postRoot - 1, inRoot + 1, inEnd, postorder, inorder, map);
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] inorder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        int[] postorder = new int[]{7, 4, 2, 5, 8, 6, 3, 1};
        System.out.println(solution.buildTree1(inorder, postorder));
        System.out.println(solution.buildTree2(inorder, postorder));
    }
}
