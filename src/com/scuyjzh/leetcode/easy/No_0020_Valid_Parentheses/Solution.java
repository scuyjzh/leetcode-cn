package com.scuyjzh.leetcode.easy.No_0020_Valid_Parentheses;

import java.util.*;

/**
 * 20. 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 1.左括号必须用相同类型的右括号闭合。
 * 2.左括号必须以正确的顺序闭合。
 */
class Solution {
    /**
     * 方法：栈
     * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。
     * 空间复杂度：O(n+∣Σ∣)，其中 Σ 表示字符集，本题中字符串只包含 6 种括号，∣Σ∣=6。栈中的字符数量为 O(n)，而哈希表使用的空间为 O(∣Σ∣)，相加即可得到总空间复杂度。
     */
    public boolean isValid(String s) {
        // 注意到有效字符串的长度一定为偶数
        if (s.length() % 2 == 1) {
            return false;
        }
        // 使用哈希表存储每一种括号。哈希表的键为右括号，值为相同类型的左括号
        Map<Character, Character> pairs = new HashMap<Character, Character>(3) {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        // 判断括号的有效性可以使用「栈」这一数据结构来解决
        Deque<Character> stack = new LinkedList<>();
        // 遍历给定的字符串 s。当遇到一个左括号时，会期望在后续的遍历中，有一个相同类型的右括号将其闭合
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            // 当遇到一个右括号时，需要将一个相同类型的左括号闭合。此时，可以取出栈顶的左括号并判断它们是否是相同类型的括号
            if (pairs.containsKey(ch)) {
                // 如果不是相同的类型，或者栈中并没有左括号，那么字符串 s 无效，返回 False
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                    return false;
                }
                stack.pop();
            } else {
                // 由于后遇到的左括号要先闭合，因此可以将这个左括号放入栈顶
                stack.push(ch);
            }
        }
        // 在遍历结束后，如果栈中没有左括号，说明将字符串 s 中的所有左括号闭合，返回 True，否则返回 False
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("[]()"));
    }
}