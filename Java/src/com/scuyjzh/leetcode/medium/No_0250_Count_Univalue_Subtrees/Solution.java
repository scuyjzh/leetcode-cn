package com.scuyjzh.leetcode.medium.No_0250_Count_Univalue_Subtrees;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 250. 统计同值子树
 *
 * 给定一个二叉树，统计该二叉树数值相同的子树个数。
 * 同值子树是指该子树的所有节点都拥有相同的数值。
 */
class Solution {
    private int ans = 0;

    private boolean isSame(TreeNode root) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/count-univalue-subtrees/solution/java-di-gui-by-npe_tle/
         */
        if (root == null) {
            return true;
        }
        boolean isLeftOk = root.left == null || isSame(root.left) && root.val == root.left.val;
        boolean isRightOk = root.right == null || isSame(root.right) && root.val == root.right.val;
        if (isLeftOk && isRightOk) {
            ans++;
            return true;
        }
        return false;
    }

    public int countUnivalSubtrees(TreeNode root) {
        isSame(root);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countUnivalSubtrees(TreeNode.initBinaryTree("[5,1,5,5,5,null,5]")));
    }
}
