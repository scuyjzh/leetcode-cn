package com.scuyjzh.leetcode.hard.No_0248_Strobogrammatic_Number_III;

/**
 * 248. 中心对称数 III
 *
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或
 * 者上下颠倒地看）。
 * 写一个函数来计算范围在 [low, high] 之间中心对称数的个数。
 * 注意:
 * 由于范围可能很大，所以 low 和 high 都用字符串表示。
 */
class Solution {
    private final char[][] mapping = {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
    public int count = 0;

    public int strobogrammaticInRange(String low, String high) {
        int lo = low.length(), hi = high.length();
        // 获取长度在 [lo, hi] 区间的中心对称数
        for (int n = lo; n <= hi; ++n) {
            char[] chs = new char[n];
            findStrobogrammatic(chs, 0, chs.length - 1, low, high);
        }
        return count;
    }

    private void findStrobogrammatic(char[] chs, int lo, int hi, String low, String high) {
        // 递归出口，统计个数
        if (lo > hi) {
            // 如果数字长度为 1，则有效；否则第一个字符不能为 '0'
            if (chs.length == 1 || chs[0] != '0') {
                String str = String.valueOf(chs);
                // 判断范围是否在 [low, high] 之间
                if (compare(str, low) && compare(high, str)) {
                    count++;
                }
            }
            return;
        }
        // 遍历 lo 和 hi 的可能取值
        for (char[] map : mapping) {
            // 长度为 1 以及正中间数字不可以为 6 和 9
            if (lo == hi && map[0] != map[1]) {
                continue;
            }
            // 分别在两侧尝试添加 {'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}
            chs[lo] = map[0];
            chs[hi] = map[1];
            findStrobogrammatic(chs, lo + 1, hi - 1, low, high);
        }
    }

    private boolean compare(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return s1.compareTo(s2) >= 0;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().strobogrammaticInRange("50", "100"));
    }
}
