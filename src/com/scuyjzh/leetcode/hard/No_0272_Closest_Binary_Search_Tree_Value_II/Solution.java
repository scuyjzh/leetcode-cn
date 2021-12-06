package com.scuyjzh.leetcode.hard.No_0272_Closest_Binary_Search_Tree_Value_II;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 272. 最接近的二叉搜索树值 II
 *
 * 给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接
 * 近目标值 target 的 k 个值。
 * 注意：
 *   • 给定的目标值 target 是一个浮点数
 *   • 你可以默认 k 值永远是有效的，即 k ≤ 总结点数
 *   • 题目保证该二叉搜索树中只会存在一种k 个值集合最接近目标值
 */
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new LinkedList<>();
        // 使用中序遍历递归搜索
        dfs(root, target, k, res);
        return res;
    }

    private void dfs(TreeNode root, double target, int k, List<Integer> res) {
        if (root == null) {
            return;
        }
        dfs(root.left, target, k, res);
        // 当结果列表元素个数小于 k，直接添加
        if (res.size() < k) {
            res.add(root.val);
        }
        // 等于 k 时，就计算结果列表首位元素与 target 的差值，并和当前节点的计算结果进行比较
        else {
            // 若大于当前节点与 target 的差值，则移除首位，然后添加当前节点，保持结果列表元素个数为 k
            if (Math.abs(res.get(0) - target) > Math.abs(root.val - target)) {
                res.remove(0);
                res.add(root.val);
            }
            // 反之则返回，无需再遍历后面的节点
            else {
                return;
            }
        }
        dfs(root.right, target, k, res);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().closestKValues(TreeNode.initBinaryTree("[4,2,5,1,3]"), 3.714286, 2));
    }
}
