package com.scuyjzh.leetcode.medium.No_094_Binary_Tree_Inorder_Traversal;

import java.util.*;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    /**
     * Approach #1 (Iteration with Stack - DFS)
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    /**
     * Approach #2 (Iteration by Morris Traversal)
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root, pre;
        while (cur != null) {
            // 左子树为空，输出当前节点，并将其右孩子作为当前节点
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                // 左子树不为空
                pre = cur.left;
                // 找到前驱节点，即左子树中的最右节点
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 如果前驱节点的右孩子为空，则将它的右孩子设置为当前节点
                // 并将当前节点更新为当前节点的左孩子
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                // 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状，斩断线索）
                // 并输出当前节点，且将当前节点更新为当前节点的右孩子
                if (pre.right == cur) {
                    pre.right = null;
                    list.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    /**
     * Approach #3 (Recursion - DFS)
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        traversal(root, list);
        return list;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree(
                "[6,2,7,1,4,null,9,null,null,3,5,null,null,8]");
        System.out.println(solution.inorderTraversal1(root));
        System.out.println(solution.inorderTraversal2(root));
        System.out.println(solution.inorderTraversal3(root));
    }
}
