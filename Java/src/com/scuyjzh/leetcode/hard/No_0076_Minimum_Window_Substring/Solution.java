package com.scuyjzh.leetcode.hard.No_0076_Minimum_Window_Substring;

/**
 * 76. 最小覆盖子串
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小
 * 子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 */
class Solution {
    public String minWindow(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        // 字典 need 表示当前滑动窗口中需要的各元素的数量
        int[] need = new int[128];
        // 一开始滑动窗口为空，用 T 中元素初始化 need 字典。
        // 当滑动窗口扩展或者收缩的时候，去维护这个 need 字典。
        // 例如，当滑动窗口包含某个元素，就让 need 中这个元素的数量减 1，代表所需元素减少了 1 个；
        // 当滑动窗口移除某个元素，就让 need 中这个元素的数量加 1，代表所需元素增加了 1 个。
        // 比如当 need 等于 {'A':-2,'C':1} 时，表示当前滑动窗口中有 2 个 'A' 是多余的，同时还需要 1 个 'C'。
        // 这么做的目的就是为了步骤 2 中，排除不必要的元素，数量为负的就是不必要的元素，而数量为 0 表示刚刚好。
        for (int i = 0; i < t.length(); ++i) {
            ++need[tc[i]];
        }

        // 用 l,r 表示滑动窗口的左边界和右边界，通过改变 l,r 来扩展和收缩滑动窗口
        int l = 0, r = 0;
        // 维护一个额外的变量 needCnt 来记录所需元素的总数量
        int needCnt = t.length();
        // 维护滑动窗口大小
        int windowSize = Integer.MAX_VALUE;
        // 记录窗口起始位置
        int start = 0;
        while (r < s.length()) {
            char ch = sc[r];
            // 步骤 1：不断右移 r 使滑动窗口增大，直到窗口包含了 T 中所有元素
            if (need[ch] > 0) {
                // 当碰到一个所需元素 ch，不仅 need[ch] 的数量减少 1，同时 needCnt 也要减少 1
                --needCnt;
            }
            --need[ch];
            // 此时 need 中所有元素的数量都小于等于 0，且 needCnt 也为 0
            if (needCnt == 0) {
                // 步骤 2：不断右移 l 收缩滑动窗口，直到碰到一个必须包含的元素 A（即 need[A] 等于 0，表示数量刚好为 0）
                while (l < r && need[sc[l]] < 0) {
                    ++need[sc[l]];
                    ++l;
                }
                // 此时记录长度并更新窗口大小和窗口起始位置
                if (r - l + 1 < windowSize) {
                    windowSize = r - l + 1;
                    start = l;
                }
                // 步骤 3：l 右移一个位置，开始寻找下一个满足条件的滑动窗口（继续从步骤 1 开始执行）
                ++need[sc[l]];
                ++l;
                ++needCnt;
            }
            // 不断右移 r 使滑动窗口增大
            ++r;
        }

        return windowSize == Integer.MAX_VALUE ? "" : s.substring(start, start + windowSize);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(new Solution().minWindow("a", "a"));
        System.out.println(new Solution().minWindow("a", "aa"));
    }
}
