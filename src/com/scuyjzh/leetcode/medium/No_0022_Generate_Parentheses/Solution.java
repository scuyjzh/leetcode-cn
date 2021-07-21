package com.scuyjzh.leetcode.medium.No_0022_Generate_Parentheses;

import java.util.*;

/**
 * 22. 括号生成
 * <p>
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
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

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * 方法二：回溯法
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        StringBuilder path = new StringBuilder();
        backtrack(path, n, n, res);
        return res;
    }


    /**
     * @param path  从根结点到任意结点的路径
     * @param left  左括号还有几个可以使用
     * @param right 右括号还有几个可以使用
     * @param res   结果集
     */
    private void backtrack(StringBuilder path, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            // path.toString() 生成了一个新的字符串，相当于做了一次拷贝
            res.add(path.toString());
            return;
        }

        // 剪枝（左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            path.append("(");
            backtrack(path, left - 1, right, res);
            path.deleteCharAt(path.length() - 1);
        }

        if (right > 0) {
            path.append(")");
            backtrack(path, left, right - 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    /**
     * 方法三：动态规划
     */
    public List<String> generateParenthesis3(int n) {
        /*
         * 思路：
         * 当清楚所有 i<n 时括号的可能生成排列后，对于 i=n 的情况，考虑整个括号排列中最左边的括号。
         * 它一定是一个左括号，那么它一定可以和它对应的右括号组成一组完整的括号 "( )"，此时认为这一组是相比 n-1 增加进来的括号。
         * 那么，剩下 n-1 组括号有可能在哪呢？
         * 剩下的括号要么在这一组新增的括号内部，要么在这一组新增括号的外部（右侧）。
         * 既然知道了 i<n 的情况，就可以对所有情况进行遍历：
         * "(" + 【i=p时所有括号的排列组合】 + ")" + 【i=q时所有括号的排列组合】
         * 其中 p + q = n-1，且 p、q 均为非负整数。
         * 事实上，当上述 p 从 0 取到 n-1，q 从 n-1 取到 0 后，所有情况就遍历完了。
         */
        if (n == 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new LinkedList<>();
        // 0 组括号时记为空
        res.add(new LinkedList<>(Collections.singletonList("")));
        // 1 组括号只有一种情况
        res.add(new LinkedList<>(Collections.singletonList("()")));
        // 开始计算 i 组括号时的括号组合
        for (int i = 2; i <= n; ++i) {
            List<String> combinations = new LinkedList<>();
            // 开始遍历 p、q ，其中p+q=i-1, j 作为索引
            for (int j = 0; j < i; ++j) {
                // p = j 时的括号组合情况
                List<String> str1 = res.get(j);
                // q = (i-1) - j 时的括号组合情况
                List<String> str2 = res.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        String str = "(" + s1 + ")" + s2;
                        // 把所有可能的情况添加到 combinations 中
                        combinations.add(str);
                    }
                }
            }
            // combinations这个list就是i组括号的所有情况，添加到res中，继续求解i=i+1的情况
            res.add(combinations);
        }
        return res.get(n);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis1(3));
        System.out.println(solution.generateParenthesis2(3));
        System.out.println(solution.generateParenthesis3(3));
    }
}
