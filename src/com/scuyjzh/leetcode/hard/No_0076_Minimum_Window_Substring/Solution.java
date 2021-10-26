package com.scuyjzh.leetcode.hard.No_0076_Minimum_Window_Substring;

/**
 * 76. 最小覆盖子串
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小
 * 子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 */
class Solution {
    public String minWindow(String s, String t) {
        // 用 T 中元素初始化 need 字典
        int[] need = new int[128];
        for (int i = 0; i < t.length(); ++i) {
            need[t.charAt(i)]++;
        }

        int l = 0, r = 0, size = Integer.MAX_VALUE, needCnt = t.length(), start = 0;
        while (r < s.length()) {
            char ch = s.charAt(r);
            if (need[ch] > 0) {
                needCnt--;
            }
            need[ch]--;
            // 步骤一：不断右移 r 使滑动窗口增大，直到窗口包含了 T 中所有元素，此时 need 中所有元素的数量都小于等于 0，且 needCnt 也为 0
            if (needCnt == 0) {
                // 步骤二：不断右移 l 使滑动窗口缩小，直到碰到一个必须包含的元素 A，此时记录长度并更新结果
                while (l < r && need[s.charAt(l)] < 0) {
                    need[s.charAt(l)]++;
                    l++;
                }
                if (r - l + 1 < size) {
                    size = r - l + 1;
                    start = l;
                }
                // 步骤三：l 右移一个位置，开始寻找下一个满足条件的滑动窗口
                need[s.charAt(l)]++;
                l++;
                needCnt++;
            }
            r++;
        }

        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(new Solution().minWindow("a", "a"));
        System.out.println(new Solution().minWindow("a", "aa"));
    }
}
