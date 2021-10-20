package com.scuyjzh.leetcode.medium.No_0316_Remove_Duplicate_Letters;

import java.util.*;

/**
 * 316. 去除重复字母
 * <p>
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现
 * 一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位
 * 置）。
 */
class Solution {
    /**
     * 方法：贪心 + 单调栈
     * <p>
     * • 时间复杂度：O(N)，其中 N 为字符串长度。代码中虽然有双重循环，但是每个字符至多只会入栈、出
     *   栈各一次。
     * • 空间复杂度：O(∣Σ∣)，其中 Σ 为字符集合，本题中字符均为小写字母，所以 ∣Σ∣=26。由于栈中的字
     *   符不能重复，因此栈中最多只能有 ∣Σ∣ 个字符，另外需要维护两个数组，分别记录每个字符是否出现在
     *   栈中以及每个字符最后一次出现的下标。
     */
    public String removeDuplicateLetters(String s) {
        int len = s.length();

        char[] charArray = s.toCharArray();
        // 需要记录每一个字符最后一次出现的下标
        int[] lastIndex = new int[26];
        for (int i = 0; i < len; ++i) {
            lastIndex[charArray[i] - 'a'] = i;
        }

        // 需要使用「栈」作为辅助的数据结构
        Deque<Character> stack = new ArrayDeque<>();
        // 判断当前读到的字母在栈中是否已经出现
        boolean[] visited = new boolean[26];
        for (int i = 0; i < len; ++i) {
            // 如果当前看到的字母已经在栈中，它一定不是某一段单调递增字母的最后一个字母；
            // 因此，如果遍历到栈中有的字符可以舍弃。
            if (visited[charArray[i] - 'a']) {
                continue;
            }

            while (!stack.isEmpty() && stack.peekLast() > charArray[i] && lastIndex[stack.peekLast() - 'a'] > i) {
                Character top = stack.removeLast();
                visited[top - 'a'] = false;
            }

            stack.addLast(charArray[i]);
            visited[charArray[i] - 'a'] = true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : stack) {
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateLetters("bcabc"));
        System.out.println(new Solution().removeDuplicateLetters("cbacdcbc"));
    }
}
