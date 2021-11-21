package com.scuyjzh.leetcode.hard.No_0032_Longest_Valid_Parentheses;

import java.util.*;

/**
 * 32. 最长有效括号
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连
 * 续）括号子串的长度。
 */
class Solution {
    /**
     * 方法一：动态规划
     *
     * • 时间复杂度：O(n)，其中 n 为字符串的长度。只需遍历整个字符串一次，即可将 dp 数组求出
     *   来。
     * • 空间复杂度：O(n)。需要一个大小为 n 的 dp 数组。
     */
    public int longestValidParentheses1(String s) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/
         */
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int len = s.length();
        char[] chs = s.toCharArray();
        // 状态数组，其中第 i 个元素表示以下标为 i 的字符结尾的最长有效子字符串的长度
        int[] dp = new int[len];
        // i 从 1 开始遍历，一是因为单括号肯定无效，二是防止 i - 1 索引越界
        for (int i = 1; i < len; ++i) {
            // 当前字符 s[i] == '(' 时，s[i] 无法和其之前的元素组成有效的括号对，所以 dp[i] = 0；
            // 当前字符 s[i] == ')' 时，再根据前面的元素来判断是否有有效括号对。
            if (chs[i] == ')') {
                // 上一个字符 s[i−1] 是左括号 '('
                if (chs[i - 1] == '(') {
                    if (i < 2) {
                        // 开头处
                        dp[i] = 2;
                    } else {
                        // 非开头处，有效括号长度增加 2
                        dp[i] = dp[i - 2] + 2;
                    }
                }
                // 上一个字符 s[i−1] 是右括号 ')'
                else {
                    // 由于上一个字符是右括号，所以前面需要有和当前字符 s[i]（也是右括号 ')'） 组成有效括号对的左括号 '(' ，即形如 (...(...))
                    // 因此要求 s[i−1] 位置必须是有效的括号对（即 dp[i-1] > 0），否则 s[i] 无法和前面的字符组成有效括号对
                    if (dp[i - 1] > 0) {
                        // 找到和 s[i] 配对的位置（即 i − dp[i−1] − 1），并判断其是否为左括号 '('
                        int lastSingleIndex = i - dp[i - 1] - 1;
                        if (lastSingleIndex >= 0 && chs[lastSingleIndex] == '(') {
                            // 有效括号长度增加 2
                            dp[i] = dp[i - 1] + 2;
                            // 和 s[i] 配对的左括号 '(' 之前可能还存在有效括号对
                            if (lastSingleIndex - 1 >= 0) {
                                dp[i] = dp[i] + dp[lastSingleIndex - 1];
                            }
                        }
                    }
                }
            }
            // 结果是：max(dp[i])
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 方法二：栈
     *
     * • 时间复杂度：O(n)，n 是给定字符串的长度。只需要遍历字符串一次即可。
     * • 空间复杂度：O(n)。栈的大小在最坏情况下会达到 n，因此空间复杂度为 O(n) 。
     */
    public int longestValidParentheses2(String s) {
        /*
         * 思路：
         * 撇开方法一提及的动态规划方法，相信大多数人对于这题的第一直觉是找到每个可能的子串后判断它的有效
         * 性，但这样的时间复杂度会达到 O(n^3)，无法通过所有测试用例。但是通过栈，可以在遍历给定字符串
         * 的过程中去判断到目前为止扫描的子串的有效性，同时能得到最长有效括号的长度。
         *
         * 具体做法是始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这
         * 样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：
         *   • 对于遇到的每个 ‘(’ ，将它的下标放入栈中
         *   • 对于遇到的每个 ‘)’ ，先弹出栈顶元素表示匹配了当前右括号：
         *       ○ 如果栈为空，说明当前的右括号为没有被匹配的右括号，将其下标放入栈中来更新之前
         *         提到的「最后一个没有被匹配的右括号的下标」
         *       ○ 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长
         *         度」
         *
         * 从前往后遍历字符串并更新答案即可。
         *
         * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候会将其放入栈中，这样就不满足提及的
         * 「最后一个没有被匹配的右括号的下标」，为了保持统一，在一开始的时候往栈中放入一个值为 −1 的
         * 元素。
         */
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 可以理解成：在字符串 s 之前添加一个右括号 ‘)’ ，下标为 -1，并将其入栈
        stack.push(-1);

        for (int i = 0; i < s.length(); ++i) {

            if (s.charAt(i) == '(') {
                // 对于遇到的每个 ‘(’ ，将它的下标放入栈中
                stack.push(i);
            } else {
                // 对于遇到的每个 ‘)’ ，先弹出栈顶元素表示匹配了当前右括号
                stack.pop();
                // 栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
                if (!stack.isEmpty()) {
                    res = Math.max(res, i - stack.peek());
                }
                // 栈为空，说明当前的右括号为没有被匹配的右括号，将其下标入栈
                else {
                    stack.push(i);
                }
            }
        }
        return res;
    }

    /**
     * 方法三：栈 + 模拟
     *
     * • 时间复杂度：O(n)，n 是给定字符串的长度。
     * • 空间复杂度：O(n)。栈的大小在最坏情况下会达到 n，因此空间复杂度为 O(n) 。
     */
    public int longestValidParentheses3(String s) {
        /*
         * 思路：
         * 用栈模拟一遍，将所有匹配到的括号的位置全部置 1。
         * 例如：
         *   - "()(()" 的 mark 为 [1, 1, 0, 1, 1]
         *   - ")()((())" 的 mark 为 [0, 1, 1, 0, 1, 1, 1, 1]
         * 经上述处理后, 该问题就转化为寻找最长连续的 1 的长度。
         */
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        char[] chs = s.toCharArray();
        int[] mark = new int[len];
        for (int i = 0; i < len; ++i) {
            if (chs[i] == '(') {
                stack.push(i);
            } else {
                // 右括号判断是否匹配，合法的括号下标标为 1
                if (!stack.isEmpty() && chs[stack.peek()] == '(') {
                    mark[stack.pop()] = 1;
                    mark[i] = 1;
                }
            }
        }

        int maxLen = 0;
        int curLen = 0;
        // 求最长连续的 1 的长度
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

    public static void main(String[] args) {
        System.out.println(new Solution().longestValidParentheses1("(()"));
        System.out.println(new Solution().longestValidParentheses2(")()())"));
        System.out.println(new Solution().longestValidParentheses3(")(())"));
    }
}
