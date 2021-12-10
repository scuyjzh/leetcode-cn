package com.scuyjzh.leetcode.medium.No_0320_Generalized_Abbreviation;

import java.util.*;

/**
 * 320. 列举单词的全部缩写
 *
 * 单词的 广义缩写词 可以通过下述步骤构造：先取任意数量的不重叠的子字
 * 符串，再用它们各自的长度进行替换。例如，"abcde" 可以缩写为
 * "a3e"（"bcd" 变为 "3" ），"1bcd1"（"a" 和 "e" 都变为
 * "1"），"23"（"ab" 变为 "2" ，"cde" 变为 "3" ）。
 * 给你一个字符串 word ，返回一个由所有可能 广义缩写词 组成的列表。按
 * 任意顺序 返回答案。
 */
class Solution {
    int n;
    String word;
    List<String> res;

    public List<String> generateAbbreviations(String word) {
        /*
         * 对于一个长度为 n 的单次有多少种缩写？答案是 2^n 种，因为每个字符可以被缩写或者不被缩写，都将导致
         * 不同的缩写。
         *
         * 回溯算法穷举了问题一系列可能的部分候选串。抽象地说，部分候选串可以被看作是一棵潜在的搜索树的节
         * 点。每一个部分候选串都是某些候选串的父节点，树的叶子节点就是部分候选串没法继续扩展的结果。
         *
         * 部分候选串可以被扩展应该是以下两种情况之一：
         *   1.保留下一个字符
         *   2.将下一个字符缩写
         *
         * 按深度优先的顺序扩展所有的潜在候选串。当在搜索树中遇到一个叶子节点的时候开始回溯。搜索树中
         * 所有的叶子节点都是有效的缩写并被放到全局的列表中，以便最后返回。
         */
        this.n = word.length();
        this.word = word;
        this.res = new ArrayList<>();

        // i is the current position
        // k is the count of consecutive abbreviated characters
        // abbr is the current generalized abbreviation
        StringBuilder abbr = new StringBuilder();
        backtrack(0, 0, abbr);
        return res;
    }

    private void backtrack(int i, int k, StringBuilder abbr) {
        // keep the length of abbr
        int len = abbr.length();
        if (i == n) {
            if (k != 0) {
                // append the last k if non zero
                abbr.append(k);
            }
            res.add(abbr.toString());
        } else {
            // the branch that word.charAt(i) is abbreviated
            backtrack(i + 1, k + 1, abbr);

            // the branch that word.charAt(i) is kept
            if (k != 0) {
                abbr.append(k);
            }
            abbr.append(word.charAt(i));
            backtrack(i + 1, 0, abbr);
        }
        // reset abbr to the original state
        abbr.setLength(len);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateAbbreviations("word"));
        System.out.println(new Solution().generateAbbreviations("a"));
    }
}
