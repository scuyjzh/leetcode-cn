package com.scuyjzh.leetcode.medium.No_0022_Generate_Parentheses;

import java.util.*;

/**
 * 22. 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能
 * 的并且 有效的 括号组合。
 *
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 */
class Solution {
    /**
     * 方法一：深度优先遍历
     */
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        String path = "";
        // 执行深度优先遍历，搜索可能的结果
        dfs(path, n, n, res);
        return res;
    }

    /**
     * @param path        当前递归得到的结果（从根结点到任意结点的路径）
     * @param leftRemain  左括号剩余数量
     * @param rightRemain 右括号剩余数量
     * @param res         结果集
     */
    private void dfs(String path, int leftRemain, int rightRemain, List<String> res) {
        // 在递归终止的时候，直接把它添加到结果集即可
        if (leftRemain == 0 && rightRemain == 0) {
            res.add(path);
            return;
        }

        // 可以生出左枝叶（即生成左括号）的条件：左括号剩余数量（严格）大于 0
        if (leftRemain > 0) {
            // 因为每一次递归，都传入新的字符串变量（即拼接后的变量），并没有改变 path 状态变量，所以无需回溯
            dfs(path + "(", leftRemain - 1, rightRemain, res);
        }

        // 可以生出右枝叶（即生成右括号）的条件：左括号剩余数量（严格）小于 右括号剩余数量
        if (leftRemain < rightRemain) {
            // 因为每一次递归，都传入新的字符串变量（即拼接后的变量），并没有改变 path 状态变量，所以无需回溯
            dfs(path + ")", leftRemain, rightRemain - 1, res);
        }
    }

    /**
     * 方法二：深度优先遍历 + 回溯
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        StringBuilder path = new StringBuilder();
        // 执行深度优先遍历，搜索可能的结果
        dfs(path, n, n, res);
        return res;
    }

    /**
     * @param path        当前递归得到的结果（从根结点到任意结点的路径）
     * @param leftRemain  左括号还有几个可以使用
     * @param rightRemain 右括号还有几个可以使用
     * @param res         结果集
     */
    private void dfs(StringBuilder path, int leftRemain, int rightRemain, List<String> res) {
        // 递归终止条件
        if (leftRemain == 0 && rightRemain == 0) {
            // path.toString() 生成了一个新的字符串，相当于做了一次拷贝，因为全程使用一份状态变量
            res.add(path.toString());
            return;
        }

        // 可以生出左枝叶（即生成左括号）的条件：左括号剩余数量（严格）大于 0
        if (leftRemain > 0) {
            // 生成左括号
            path.append("(");
            // 继续递归生成新括号
            dfs(path, leftRemain - 1, rightRemain, res);
            // 由于递归之前改动了 path 状态变量（全程只使用这一份状态变量），因此退出递归后需要做回撤，即「撤销选择」、「恢复现场」
            path.deleteCharAt(path.length() - 1);
        }

        // 可以生出右枝叶（即生成右括号）的条件：左括号剩余数量（严格）小于 右括号剩余数量
        if (leftRemain < rightRemain) {
            // 生成右括号
            path.append(")");
            // 继续递归生成新括号
            dfs(path, leftRemain, rightRemain - 1, res);
            // 由于递归之前改动了 path 状态变量（全程只使用这一份状态变量），因此退出递归后需要做回撤，即「撤销选择」、「恢复现场」
            path.deleteCharAt(path.length() - 1);
        }
    }

    /**
     * 方法三：动态规划
     */
    public List<String> generateParenthesis3(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/generate-parentheses/solution/zui-jian-dan-yi-dong-de-dong-tai-gui-hua-bu-lun-da/
         */
        if (n == 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new LinkedList<>();
        // 0 组括号时记为空
        res.add(new LinkedList<>(Collections.singletonList("")));
        // 1 组括号只有一种情况
        res.add(new LinkedList<>(Collections.singletonList("()")));
        // 开始计算 i 组括号时的括号组合（i 从 2 开始）
        for (int k = 2; k <= n; ++k) {
            List<String> combinations = new LinkedList<>();
            // 开始遍历 p、q ，其中 p+q = k-1, i 作为索引
            for (int i = 0; i < k; ++i) {
                // p = j 时的括号组合情况
                List<String> str1 = res.get(i);
                // q = (k-1) - i 时的括号组合情况
                List<String> str2 = res.get(k - 1 - i);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        String str = "(" + s1 + ")" + s2;
                        // 把所有可能的情况添加到 combinations 中
                        combinations.add(str);
                    }
                }
            }
            // combinations这个列表就是 i 组括号的所有情况，添加到 res 中，继续求解 k=k+1 的情况
            res.add(combinations);
        }
        return res.get(n);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis1(3));
        System.out.println(new Solution().generateParenthesis2(2));
        System.out.println(new Solution().generateParenthesis3(1));
    }
}
