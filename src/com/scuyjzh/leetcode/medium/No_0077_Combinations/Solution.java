package com.scuyjzh.leetcode.medium.No_0077_Combinations;

import java.util.*;

/**
 * 77. 组合
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 */
class Solution {
    /**
     * 方法一：根据搜索起点画出二叉树（回溯 + 剪枝）
     */
    public List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }

        LinkedList<Integer> path = new LinkedList<>();
        // 从 1 开始是题目的设定
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, LinkedList<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点，在深度优先遍历的过程中剪枝，避免不必要的遍历
        // 由归纳可知：搜索起点的上界 + 接下来要选择的元素个数 - 1 = n
        // 其中，接下来要选择的元素个数 = k - path.size()，整理得到：
        // 搜索起点的上界 = n - (k - path.size()) + 1
        for (int i = begin; i <= n - (k - path.size()) + 1; ++i) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 调试语句 ①
            System.out.println("  递归之前 => " + path);

            // 下一轮搜索，设置的搜索起点要加 1，因为组合数里不允许出现重复的元素
            dfs(n, k, i + 1, path, res);

            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
            // 调试语句 ②
            System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 方法二：按照每一个数选与不选画出二叉树
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }

        // 为了防止底层动态数组扩容，初始化的时候传入最大长度
        Deque<Integer> path = new ArrayDeque<>(k);
        // 可以按照每一个数选与不选画出二叉树，二叉树最多 n 层
        dfs(1, n, k, path, res);
        return res;
    }

    private void dfs(int begin, int n, int k, Deque<Integer> path, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 剪枝，基础版本的递归终止条件：if (begin == n + 1) {
        if (begin > n - k + 1) {
            return;
        }

        // 不选当前考虑的数 begin，直接递归到下一层
        dfs(begin + 1, n, k, path, res);

        // 选择当前考虑的数 begin，递归到下一层的时候 k - 1，这里 k 表示还需要选多少个数
        path.addLast(begin);
        dfs(begin + 1, n, k - 1, path, res);

        // 深度优先遍历有回头的过程，因此需要撤销选择
        path.removeLast();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("输出 => " + solution.combine1(4, 2));
        System.out.println("输出 => " + solution.combine2(4, 2));
    }
}
