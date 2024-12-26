package com.scuyjzh.leetcode.medium.No_0424_Longest_Repeating_Character_Replacement;

/**
 * 424. 替换后的最长重复字符
 *
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换
 * 成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复
 * 字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过 10^4。
 */
class Solution {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        if (len <= 1) {
            return len;
        }

        char[] charArray = s.toCharArray();

        // 频数数组，记录每个字符出现的次数
        int[] freq = new int[26];
        // historyCharMax 保存滑动窗口内相同字母出现次数的 历史 最大值
        int historyCharMax = 0;
        // left 表示窗口左边界，right 表示窗口右边界
        int left = 0, right = 0;
        // [left, right) 内最多替换 k 个字符可以得到只有一种字符的子串
        while (right < len) {
            // 在这里维护 historyCharMax，因为每一次右边界读入一个字符，字符频数增加，才会使得 historyCharMax 增加
            historyCharMax = Math.max(historyCharMax, ++freq[charArray[right] - 'A']);
            ++right;

            // 通过判断窗口大小 (right−left+1) 是否大于 historyCharMax+K 来决定窗口是否做滑动，否则窗口就扩张
            // 窗口滑动：++left，++right
            // 窗口扩张：left 不变，++right
            if (right - left > historyCharMax + k) {
                // 说明此时 k 不够用
                // 把其它不是最多出现的字符替换以后，都不能填满这个滑动的窗口，这个时候须要考虑左边界向右移动
                // 移出滑动窗口的时候，频数数组须要相应地做减法
                --freq[charArray[left] - 'A'];
                ++left;
            }
        }
        return right - left;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().characterReplacement("AABABBA", 1));
    }
}
