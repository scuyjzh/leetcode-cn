package com.scuyjzh.leetcode.hard.No_0032_Longest_Valid_Parentheses;

import java.util.*;

/**
 * 32. 最长有效括号
 * <p>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
class Solution {
    /**
     * 方法一：栈模拟
     * 时间复杂度：O(n)，n 是给定字符串的长度。
     * 空间复杂度：O(n)。栈的大小在最坏情况下会达到 n，因此空间复杂度为 O(n) 。
     */
    public int longestValidParentheses1(String s) {
        /*
         * 思路：
         * 用栈模拟一遍，将所有匹配到的括号的位置全部置 1。
         * 例如：
         * - "()(()" 的 mark 为 [1, 1, 0, 1, 1]
         * - ")()((())" 的 mark 为 [0, 1, 1, 0, 1, 1, 1, 1]
         * 经上述处理后, 该问题就转化为寻找最长连续的 1 的长度。
         */
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        char[] array = s.toCharArray();
        int[] mark = new int[len];
        for (int i = 0; i < len; ++i) {
            if (array[i] == '(') {
                stack.push(i);
            } else {
                // 右括号判断是否匹配，合法的括号下标标为 1
                if (!stack.isEmpty() && array[stack.peek()] == '(') {
                    mark[stack.pop()] = 1;
                    mark[i] = 1;
                }
            }
        }
        int maxLen = 0;
        int curLen = 0;
        // 求最长连续的1的长度
        for (int i : mark) {
            if (i == 1) {
                curLen += 1;
                maxLen = Math.max(maxLen, curLen);
            } else {
                curLen = 0;
            }
        }
        return maxLen;
    }

    /**
     * 方法二：栈
     * 时间复杂度：O(n)，n 是给定字符串的长度。我们只需要遍历字符串一次即可。
     * 空间复杂度：O(n)。栈的大小在最坏情况下会达到 n，因此空间复杂度为 O(n) 。
     */
    public int longestValidParentheses2(String s) {
        /*
         * 思路：
         * 通过栈，可以在遍历给定字符串的过程中去判断到目前为止扫描的子串的有效性，同时能得到最长有效括号的长度。
         * 具体做法是始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：
         * - 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
         * - 对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
         *   • 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
         *   • 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
         * 从前往后遍历字符串并更新答案即可。
         * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，
         * 为了保持统一，在一开始的时候往栈中放入一个值为 −1 的元素。
         */
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                // 左括号的下标入栈
                stack.push(i);
            } else {
                // 栈顶的左括号被匹配
                stack.pop();
                if (!stack.isEmpty()) {
                    // 栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
                    res = Math.max(res, i - stack.peek());
                } else {
                    // 栈为空，说明当前的右括号为没有被匹配的右括号，将其下标入栈
                    stack.push(i);
                }
            }
        }
        return res;
    }

    /**
     * 方法三：动态规划
     * 时间复杂度：O(n)，其中 n 为字符串的长度。我们只需遍历整个字符串一次，即可将 dp 数组求出来。
     * 空间复杂度：O(n)。我们需要一个大小为 n 的 dp 数组。
     */
    public int longestValidParentheses3(String s) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/
         */
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        char[] array = s.toCharArray();
        int[] dp = new int[len];
        int res = 0;
        for (int i = 1; i < len; ++i) {
            if (array[i] == ')') {
                if (array[i - 1] == '(') {
                    dp[i] = 2;
                    if (i - 2 >= 0) {
                        dp[i] = dp[i] + dp[i - 2];
                    }
                } else if (dp[i - 1] > 0) {
                    if ((i - dp[i - 1] - 1) >= 0 && array[i - dp[i - 1] - 1] == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if (i - dp[i - 1] - 2 >= 0) {
                            dp[i] = dp[i] + dp[i - dp[i - 1] - 2];
                        }
                    }
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses1("(()"));
        System.out.println(solution.longestValidParentheses2(")()())"));
        System.out.println(solution.longestValidParentheses3(")(())"));
    }
}
