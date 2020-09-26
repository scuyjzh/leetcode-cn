package com.scuyjzh.leetcode.medium.No_0113_Path_Sum_II;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * @author scuyjzh
 * @date 2020/9/24 17:51
 */
class Solution {
    /**
     * Approach #1 (Iteration by Postorder Traversal)
     */
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> path = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        // 记录当前累计的和
        int curSum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                // 每走一步，就将节点加入到路径中
                path.add(cur.val);
                // 累加当前已走路径的和
                curSum += cur.val;
                cur = cur.left;
            }
            // 在出栈前，将栈顶视作实际的根节点，并检查其右子树是否不存在或已被访问
            cur = stack.peek();
            if (cur.left == null && cur.right == null && curSum == sum) {
                // path 全局只有一份，必须做拷贝
                list.add(new ArrayList<>(path));
            }
            if (cur.right == null || cur.right == pre) {
                // 如果不存在右子树或右子树已被访问，那么可以访问根节点，将其弹出栈
                stack.pop();
                // 减去出栈的值
                curSum -= cur.val;
                // 移除路径中的出栈节点
                path.remove(path.size() - 1);
                // 记录上一个访问的节点，用于判断“访问根节点之前，右子树是否已访问过”
                pre = cur;
                // 表示不需要转向，继续弹栈
                cur = null;
            } else {
                // 如果右子树存在且还未被访问过，就转向其右子树
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path, list);
        return list;
    }

    private void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> list) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            list.add(new ArrayList<>(path));
        } else {
            dfs(root.left, sum - root.val, path, list);
            dfs(root.right, sum - root.val, path, list);
        }
        // back-tracking
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,5,1]");
        System.out.println(solution.pathSum1(root, 22));
        System.out.println(solution.pathSum2(root, 22));
    }
}
