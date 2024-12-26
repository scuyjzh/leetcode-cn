package com.scuyjzh.leetcode.medium.No_0394_Decode_String;

import java.util.*;

/**
 * 394. 字符串解码
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string
 * 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入
 * 的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k
 * ，例如不会出现像 3a 或 2[4] 的输入。
 */
class Solution {
    public String decodeString(String s) {
        StringBuilder decoded = new StringBuilder();
        int digit = 0;
        Deque<Integer> digitStack = new ArrayDeque<>();
        Deque<String> decodeStack = new ArrayDeque<>();
        for (Character ch : s.toCharArray()) {
            if (ch == '[') {
                digitStack.push(digit);
                decodeStack.push(decoded.toString());
                digit = 0;
                decoded = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder tmp = new StringBuilder();
                int curDigit = digitStack.pop();
                for (int i = 0; i < curDigit; ++i) {
                    tmp.append(decoded);
                }
                decoded = new StringBuilder(decodeStack.pop() + tmp);
            } else if (Character.isDigit(ch)) {
                digit = digit * 10 + Integer.parseInt(ch + "");
            } else {
                decoded.append(ch);
            }
        }
        return decoded.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().decodeString("3[a]2[bc]"));
        System.out.println(new Solution().decodeString("3[a2[c]]"));
        System.out.println(new Solution().decodeString("2[abc]3[cd]ef"));
        System.out.println(new Solution().decodeString("abc3[cd]xyz"));
    }
}
