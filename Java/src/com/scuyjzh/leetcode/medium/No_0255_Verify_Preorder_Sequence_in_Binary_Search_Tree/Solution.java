package com.scuyjzh.leetcode.medium.No_0255_Verify_Preorder_Sequence_in_Binary_Search_Tree;

import java.util.*;

/**
 * 255. 验证前序遍历序列二叉搜索树
 *
 * 给定一个整数数组，你需要验证它是否是一个二叉搜索树正确的先序遍历序
 * 列。
 * 你可以假定该序列中的数都是不相同的。
 * 进阶挑战：
 * 您能否使用恒定的空间复杂度来完成此题？
 */
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        /*
         * 单调栈：
         * https://leetcode-cn.com/problems/verify-preorder-sequence-in-binary-search-tree/comments/
         */
        int pre = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < preorder.length; ++i) {
            if (preorder[i] < pre) {
                return false;
            }
            while (!stack.isEmpty() && preorder[i] > stack.peek()) {
                pre = stack.pop();
            }
            stack.push(preorder[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().verifyPreorder(new int[]{5, 2, 6, 1, 3}));
        System.out.println(new Solution().verifyPreorder(new int[]{5, 2, 1, 3, 6}));
    }
}
