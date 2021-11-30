package com.scuyjzh.leetcode.medium.No_0397_Integer_Replacement;

import java.util.*;

/**
 * 397. 整数替换
 *
 * 给定一个正整数n ，你可以做如下操作：
 *   1.如果n是偶数，则用n / 2替换n 。
 *   2.如果n是奇数，则可以用n + 1或n - 1替换n 。
 * n变为 1 所需的最小替换次数是多少？
 */
class Solution {
    /**
     * 方法一：递归 + 记忆化搜索
     */
    Map<Integer, Integer> memo = new HashMap<>();

    public int integerReplacement1(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n == 1) {
            return 0;
        }
        if (!memo.containsKey(n)) {
            if (n % 2 == 0) {
                memo.put(n, 1 + dfs(n / 2));
            } else {
                memo.put(n, 2 + Math.min(dfs(n / 2), dfs(n / 2 + 1)));
            }
        }
        return memo.get(n);
    }

    /**
     * 方法二：贪心（位运算）
     */
    public int integerReplacement2(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/integer-replacement/solution/tong-ge-lai-shua-ti-la-yi-ti-san-jie-bao-u05f/
         * https://leetcode-cn.com/problems/integer-replacement/solution/gong-shui-san-xie-yi-ti-san-jie-dfsbfs-t-373h/
         */
        long num = n;
        int ans = 0;
        while (num > 1) {
            if ((num & 1) == 0) {
                num >>= 1;
            } else if ((num & 0b10) == 0 || num == 3) {
                num--;
            } else {
                num++;
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().integerReplacement1(8));
        System.out.println(new Solution().integerReplacement2(7));
        System.out.println(new Solution().integerReplacement2(4));
    }
}
