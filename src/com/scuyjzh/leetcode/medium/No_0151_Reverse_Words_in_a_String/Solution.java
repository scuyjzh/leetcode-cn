package com.scuyjzh.leetcode.medium.No_0151_Reverse_Words_in_a_String;

import java.util.*;

/**
 * 151. 翻转字符串里的单词
 * <p>
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * 说明：
 *   • 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 *   • 翻转后单词间应当仅用一个空格分隔。
 *   • 翻转后的字符串中不应包含额外的空格。
 * 进阶：
 *   • 请尝试使用 O(1) 额外空间复杂度的原地解法。
 */
class Solution {
    /**
     * 方法一：使用语言特性
     * 时间复杂度：O(N)，其中 N 为输入字符串的长度。
     * 空间复杂度：O(N)，用来存储字符串分割之后的结果。
     */
    public String reverseWords1(String s) {
        /*
         * 思路和算法：
         * 很多语言对字符串提供了 split（拆分），reverse（翻转）和 join（连接）等方法，因此可以简
         * 单的调用内置的 API 完成操作：
         *   1.使用 split 将字符串按空格分割成字符串数组；
         *   2.使用 reverse 将字符串数组进行反转；
         *   3.使用 join 方法将字符串数组拼成一个字符串。
         */
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * 方法二：自行编写对应的函数
     * 时间复杂度：O(N)，其中 N 为输入字符串的长度。
     * 空间复杂度：Java 和 Python 的方法需要 O(N) 的空间来存储字符串，而 C++ 方法只需要 O(1) 的额外空间来存放若干变量。
     */
    public String reverseWords2(String s) {
        /*
         * 思路和算法：
         * 也可以不使用语言中的 API，而是自己编写对应的函数。在不同语言中，这些函数实现是不一样的，主
         * 要的差别是有些语言的字符串不可变（如 Java 和 Python)，有些语言的字符串可变（如 C++)。
         *
         * 对于字符串不可变的语言，首先得把字符串转化成其他可变的数据结构，同时还需要在转化的过程中去除空
         * 格。
         */
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    private StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb;
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    private void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    /**
     * 方法三：双端队列
     * 时间复杂度：O(N)，其中 N 为输入字符串的长度。
     * 空间复杂度：O(N)，双端队列存储单词需要 O(N) 的空间。
     */
    public String reverseWords3(String s) {
        /*
         * 思路和算法：
         * 由于双端队列支持从队列头部插入的方法，因此可以沿着字符串一个一个单词处理，然后将单词压入队
         * 列的头部，再将队列转成字符串即可。
         */
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        Deque<String> d = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords1("the sky is blue"));
        System.out.println(new Solution().reverseWords2("  hello world  "));
        System.out.println(new Solution().reverseWords1("a good   example"));
        System.out.println(new Solution().reverseWords1("  Bob    Loves  Alice   "));
        System.out.println(new Solution().reverseWords1("Alice does not even like bob"));
    }
}