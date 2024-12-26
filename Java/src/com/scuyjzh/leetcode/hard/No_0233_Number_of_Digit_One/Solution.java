package com.scuyjzh.leetcode.hard.No_0233_Number_of_Digit_One;

/**
 * 233. 数字 1 的个数
 *
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个
 * 数。
 */
class Solution {
    public int countDigitOne(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/number-of-digit-one/solution/gong-shui-san-xie-jiang-shu-wei-dp-wen-t-c9oi/
         */
        String s = String.valueOf(n);
        int m = s.length();
        if (m == 1) {
            return n > 0 ? 1 : 0;
        }

        // 计算第 i 位前缀代表的数值，和后缀代表的数值
        // 例如 abcde 则有 ps[2] = ab; ss[2] = de
        int[] ps = new int[m], ss = new int[m];
        ps[m - 1] = Integer.parseInt(s.substring(0, m - 1));
        ss[0] = Integer.parseInt(s.substring(1));
        for (int i = 1; i < m - 1; ++i) {
            ps[i] = Integer.parseInt(s.substring(0, i));
            ss[i] = Integer.parseInt(s.substring(i + 1));
        }

        // 分情况讨论
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            // x 为当前位数值，len 为当前位后面长度为多少
            int x = s.charAt(i) - '0', len = m - i - 1;
            int prefix = ps[i], suffix = ss[i];
            int tot = 0;
            // 当前位前面的部分 < ab
            tot += prefix * Math.pow(10, len);
            // 当前位前面的部分 = ab
            if (x == 0) {
            } else if (x == 1) {
                tot += suffix + 1;
            } else {
                tot += Math.pow(10, len);
            }
            ans += tot;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countDigitOne(13));
        System.out.println(new Solution().countDigitOne(0));
    }
}
