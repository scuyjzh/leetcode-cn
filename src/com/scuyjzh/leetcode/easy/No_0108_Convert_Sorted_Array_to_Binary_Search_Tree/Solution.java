package com.scuyjzh.leetcode.easy.No_0108_Convert_Sorted_Array_to_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * @author scuyjzh
 * @date 2020/9/18 17:42
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, lo, mid - 1);
        root.right = dfs(nums, mid + 1, hi);
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.sortedArrayToBST(new int[]{1, 2, 3}));
    }
}
