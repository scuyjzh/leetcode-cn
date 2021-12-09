package com.scuyjzh.leetcode.medium.No_0017_Letter_Combinations_of_a_Phone_Number;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案
 * 可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字
 * 母。
 */
class Solution {
    private final String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        /*
         * 首先使用哈希表存储每个数字对应的所有可能的字母，然后进行回溯操作。
         *
         * 回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列
         * 是不完整的）。该字符串初始为空。每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的
         * 字母，并将其中的一个字母插入到已有的字母排列后面，然后继续处理电话号码的后一位数字，直到处理完
         * 电话号码中的所有数字，即得到一个完整的字母排列。然后进行回退操作，遍历其余的字母排列。
         *
         * 回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。在这道题中，由于每个数
         * 字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。
         */
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        StringBuilder path = new StringBuilder();
        backtrack(digits, 0, path, res);
        return res;
    }

    private void backtrack(String digits, int index, StringBuilder path, List<String> res) {
        if (path.length() == digits.length()) {
            res.add(path.toString());
            return;
        }
        String letters = map[digits.charAt(index) - '0'];
        for (char letter : letters.toCharArray()) {
            path.append(letter);
            backtrack(digits, index + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().letterCombinations("23"));
        System.out.println(new Solution().letterCombinations("2"));
    }
}
