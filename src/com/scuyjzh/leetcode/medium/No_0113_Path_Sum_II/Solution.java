package com.scuyjzh.leetcode.medium.No_0113_Path_Sum_II;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 113. 路径总和 II
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根
 * 节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 */
class Solution {
    /**
     * 方法一：深度优先搜索 + 回溯
     */
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
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

    /**
     * 方法二：深度优先搜索（后序遍历）
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
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
                // 每走一步，就将结点加入到路径中
                path.add(cur.val);
                // 累加当前已走路径的和
                curSum += cur.val;
                cur = cur.left;
            }
            // 在出栈前，将栈顶视作实际的根结点，并检查其右子树是否不存在或已被访问
            cur = stack.peek();
            if (cur.left == null && cur.right == null && curSum == sum) {
                // path 全局只有一份，必须做拷贝
                list.add(new ArrayList<>(path));
            }
            if (cur.right == null || cur.right == pre) {
                // 如果不存在右子树或右子树已被访问，那么可以访问根结点，将其弹出栈
                stack.pop();
                // 减去出栈的值
                curSum -= cur.val;
                // 移除路径中的出栈结点
                path.remove(path.size() - 1);
                // 记录上一个访问的结点，用于判断“访问根结点之前，右子树是否已访问过”
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
     * 方法三：广度优先搜索
     */
    public List<List<Integer>> pathSum3(TreeNode root, int targetSum) {
        /*
         * 也可以采用广度优先搜索的方式，遍历这棵树。当遍历到叶子节点，且此时路径和恰为目标和时，
         * 就找到了一条满足条件的路径。
         *
         * 为了节省空间，使用哈希表记录树中的每一个节点的父节点。每次找到一个满足条件的节点，就从
         * 该节点出发不断向父节点迭代，即可还原出从根节点到当前节点的路径。
         */
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) {
            return list;
        }

        // 使用哈希表记录树中的每一个节点的父节点
        Map<TreeNode, TreeNode> map = new HashMap<>();

        Deque<TreeNode> queueNode = new ArrayDeque<>();
        Deque<Integer> queueSum = new ArrayDeque<>();
        queueNode.offer(root);
        queueSum.offer(root.val);

        while (!queueNode.isEmpty()) {
            TreeNode cur = queueNode.poll();
            int curSum = queueSum.poll();

            if (cur.left == null && cur.right == null) {
                if (curSum == targetSum) {
                    // 找到一个满足条件的节点，就从该节点出发不断向父节点迭代，即可还原出从根节点到当前节点的路径
                    getPath(cur, list, map);
                }
            } else {
                if (cur.left != null) {
                    map.put(cur.left, cur);
                    queueNode.offer(cur.left);
                    queueSum.offer(cur.left.val + curSum);
                }
                if (cur.right != null) {
                    map.put(cur.right, cur);
                    queueNode.offer(cur.right);
                    queueSum.offer(cur.right.val + curSum);
                }
            }
        }

        return list;
    }

    private void getPath(TreeNode node, List<List<Integer>> list, Map<TreeNode, TreeNode> map) {
        LinkedList<Integer> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.val);
            node = map.get(node);
        }
        list.add(new LinkedList<>(path));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,5,1]");
        System.out.println(solution.pathSum1(root, 22));
        System.out.println(solution.pathSum2(root, 22));
        System.out.println(solution.pathSum3(root, 22));
    }
}
