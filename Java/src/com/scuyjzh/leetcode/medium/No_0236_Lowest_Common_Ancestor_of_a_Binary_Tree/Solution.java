package com.scuyjzh.leetcode.medium.No_0236_Lowest_Common_Ancestor_of_a_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近
 * 公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 * （一个节点也可以是它自己的祖先）。”
 */
class Solution {
    /**
     * 方法：递归
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left_have = dfs(root.left, p, q);
        TreeNode right_have = dfs(root.right, p, q);
        if (left_have != null && right_have != null) {
            return root;
        } else {
            return left_have != null ? left_have : right_have;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.initBinaryTree("[3,5,1,6,2,0,8,null,null,7,4]");
        System.out.println(new Solution().lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1)).val);
        System.out.println(new Solution().lowestCommonAncestor(root, new TreeNode(5), new TreeNode(4)).val);
    }
}
