package com.scuyjzh.leetcode.hard.No_0030_Substring_with_Concatenation_of_All_Words;

import java.util.*;

/**
 * 30. 串联所有单词的子串
 * <p>
 * 给定一个字符串s和一些 长度相同 的单词words 。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑words中单词串联的顺序。
 */
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        // wordNum 为单词的个数
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        // wordLen为单词长度
        int wordLen = words[0].length();
        // allWords 用于记录words中单词出现的次数
        HashMap<String, Integer> allWords = new HashMap<>(wordNum);
        for (String word : words) {
            allWords.put(word, allWords.getOrDefault(word, 0) + 1);
        }
        // 遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; ++i) {
            // subWords 用于记录子串中（也就是滑动窗口中）单词出现的次数
            HashMap<String, Integer> subWords = new HashMap<>(wordNum);
            int index = i;
            // 遍历字符串，移动长度为 wordNum * wordLen 的滑动窗口
            while (index < i + wordNum * wordLen) {
                String curWord = s.substring(index, index + wordLen);
                // 当这个窗口内一旦出现不存在allWords中的单词
                // 或者这个单词在子串中出现的次数已经等于allWords中的次数（也就是再加入这个子串次数就要超出了）
                // 这个滑动窗口就不符合要求，直接break进入下一个滑动窗口的匹配
                if (!allWords.containsKey(curWord) || subWords.get(curWord) == (allWords.get(curWord))) {
                    break;
                }
                subWords.put(curWord, subWords.getOrDefault(curWord, 0) + 1);
                // 在当前滑动窗口中依次比较wordLen长度的单词
                index += wordLen;
            }
            // 一旦完全匹配上了，把滑动窗口的起始索引加入结果res中
            if (index == i + wordNum * wordLen) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        System.out.println(solution.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
    }
}
