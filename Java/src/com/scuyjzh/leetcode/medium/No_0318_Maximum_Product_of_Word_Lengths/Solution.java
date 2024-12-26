package com.scuyjzh.leetcode.medium.No_0318_Maximum_Product_of_Word_Lengths;

import java.util.*;

/**
 * 318. 最大单词长度乘积
 *
 * 给你一个字符串数组 words ，找出并返回 length(words[i]) *
 * length(words[j]) 的最大值，并且这两个单词不含有公共字母。如果不存
 * 在这样的两个单词，返回 0 。
 */
class Solution {
    /**
     * 方法一：位运算
     */
    public int maxProduct1(String[] words) {
        /*
         * 如何将判断两个单词是否有公共字母的时间复杂度降低到 O(1)？
         *
         * 可以使用位运算预处理每个单词，通过位运算操作判断两个单词是否有公共字母。由于单词只包含小写字
         * 母，共有 26 个小写字母，因此可以使用位掩码的最低 26 位分别表示每个字母是否在这个单词中出现。将 a
         * 到 z 分别记为第 0 个字母到第 25 个字母，则位掩码的从低到高的第 i 位是 1 当且仅当第 i 个字母在这个单词
         * 中，其中 0≤i≤25。
         *
         * 用数组 masks 记录每个单词的位掩码表示。计算数组 masks 之后，判断第 i 个单词和第 j 个单词是否有公共
         * 字母可以通过判断 masks[i] & masks[j] 是否等于 0 实现，当且仅当 masks[i] & masks[j]=0 时第 i 个单词和
         * 第 j 个单词没有公共字母，此时使用这两个单词的长度乘积更新最大单词长度乘积。
         */
        int[] masks = new int[words.length];
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            for (int j = 0; j < word.length(); ++j) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int ans = 0;
        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if ((masks[i] & masks[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：位运算优化
     */
    public int maxProduct2(String[] words) {
        /*
         * 方法一需要对数组 words 中的每个单词计算位掩码，如果数组 words 中存在由相同的字母组成的不同单词，
         * 则会造成不必要的重复计算。例如单词 meet 和 met 包含的字母相同，只是字母的出现次数和单词长度不
         * 同，因此这两个单词的位掩码表示也相同。由于判断两个单词是否有公共字母是通过判断两个单词的位掩码
         * 的按位与运算实现，因此在位掩码相同的情况下，单词的长度不会影响是否有公共字母，当两个位掩码的按
         * 位与运算等于 0 时，为了得到最大单词长度乘积，这两个位掩码对应的单词长度应该尽可能大。根据上述分
         * 析可知，如果有多个单词的位掩码相同，则只需要记录该位掩码对应的最大单词长度即可。
         *
         * 可以使用哈希表记录每个位掩码对应的最大单词长度，然后遍历哈希表中的每一对位掩码，如果这一对位掩
         * 码的按位与运算等于 0，则用这一对位掩码对应的长度乘积更新最大单词长度乘积。
         *
         * 由于每个单词的位掩码都不等于 0，任何一个不等于 0 的数和自身做按位与运算的结果一定不等于 0，因此
         * 当一对位掩码的按位与运算等于 0 时，这两个位掩码一定是不同的，对应的单词也一定是不同的。
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            int wordLength = word.length();
            for (int i = 0; i < wordLength; ++i) {
                mask |= 1 << (word.charAt(i) - 'a');
            }
            if (wordLength > map.getOrDefault(mask, 0)) {
                map.put(mask, wordLength);
            }
        }

        int ans = 0;
        for (int mask1 : map.keySet()) {
            for (int mask2 : map.keySet()) {
                if ((mask1 & mask2) == 0) {
                    ans = Math.max(ans, map.get(mask1) * map.get(mask2));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct1(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
        System.out.println(new Solution().maxProduct2(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}));
    }
}
