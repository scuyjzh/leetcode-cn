package com.scuyjzh.leetcode.easy.No_0270_Closest_Binary_Search_Tree_Value;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 270. 最接近的二叉搜索树值
 *
 * 给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找
 * 到最接近目标值 target 的数值。
 * 注意：
 *   • 给定的目标值 target 是一个浮点数
 *   • 题目保证在该二叉搜索树中只会存在一个最接近目标值的数
 */
class Solution {
    public int closestValue1(TreeNode root, double target) {
        /*
         * 最简单的方法就是构建中序序列，然后使用内置的 min 函数在升序数组中找到最接近的元素。
         * 如果最接近的元素的索引远小于树的高度，那么可以进行优化。
         * 首先，可以一边遍历树一边搜索最接近的值。
         * 第二，可以在找到了最接近的值以后立即停止遍历。若目标值位于两个有序数组元素之间 nums[i]
         * <=target<nums[i+1]，则说明找到了最接近的元素。
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 初始化一个空栈和设 pre 为一个很小的数字
        long pre = Long.MIN_VALUE;
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            // 为了要迭代构建一个中序序列，要尽可能的左移并将节点添加到栈中
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 弹出栈顶元素
            cur = stack.pop();
            // 若目标值在 pre 和 cur.val 之间，则最接近的元素在这两个元素之间
            if (pre <= target && target < cur.val) {
                return Math.abs(pre - target) < Math.abs(cur.val - target) ? (int) pre : cur.val;
            }
            // 设置 pre = cur.val，且向右走一步
            pre = cur.val;
            cur = cur.right;
        }
        return (int) pre;
    }

    double minDelta = Double.MAX_VALUE;
    int res = Integer.MIN_VALUE;

    public int closestValue2(TreeNode root, double target) {
        return dfs(root, target);
    }

    private int dfs(TreeNode root, double target) {
        if (root == null) {
            return res;
        }
        // 计算当前节点值和 target 的距离
        double curDelta = Math.abs(root.val - target);
        // 如果当前距离小于最小值，则替换（此处是同时发生替换）
        if (curDelta <= minDelta) {
            res = root.val;
            minDelta = curDelta;
        }
        // 二分搜索，利用二叉搜索树的条件：目标值大于当前节点值则去右子树继续下一轮的递归，否则去左子树
        res = root.val > target ? dfs(root.left, target) : dfs(root.right, target);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().closestValue1(TreeNode.initBinaryTree("[4,2,5,1,3]"), 3.714286));
        System.out.println(new Solution().closestValue2(TreeNode.initBinaryTree("[4,2,5,1,3]"), 3.714286));
    }
}
