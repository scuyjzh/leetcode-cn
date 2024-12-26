package com.scuyjzh.leetcode.medium.No_2055_Plates_Between_Candles;

import java.util.Arrays;

/**
 * 2055. 蜡烛之间的盘子
 *
 * https://leetcode-cn.com/problems/plates-between-candles/
 */
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // '*' 前缀和数组
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            sum[i] = chars[i - 1] == '*' ? sum[i - 1] + 1 : sum[i - 1];
        }

        // 缓存每个位置前后距离最近的 '|' 的索引
        int[] before = new int[n], after = new int[n];
        for (int i = 0, pre = -1; i < n; ++i) {
            if (chars[i] == '|') {
                pre = i;
            }
            before[i] = pre;
        }
        for (int i = n - 1, next = n; i >= 0; --i) {
            if (chars[i] == '|') {
                next = i;
            }
            after[i] = next;
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int[] query = queries[i];
            int left = after[query[0]], right = before[query[1]];
            if (left >= right) {
                answer[i] = 0;
            } else {
                // 前缀和与 '|' 的索引是错位的
                answer[i] = sum[right + 1] - sum[left + 1];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}})));
        System.out.println(Arrays.toString(new Solution().platesBetweenCandles("***|**|*****|**||**|*", new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
    }
}
