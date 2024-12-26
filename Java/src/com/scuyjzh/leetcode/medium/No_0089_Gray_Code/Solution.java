package com.scuyjzh.leetcode.medium.No_0089_Gray_Code;

import java.util.*;

/**
 * 89. 格雷编码
 *
 * n 位格雷码序列 是一个由 2^n 个整数组成的序列，其中：
 *     • 每个整数都在范围 [0, 2^n - 1] 内（含 0 和 2^n - 1）
 *     • 第一个整数是 0
 *     • 一个整数在序列中出现 不超过一次
 *     • 每对 相邻 整数的二进制表示 恰好一位不同 ，且
 *     • 第一个 和 最后一个 整数的二进制表示 恰好一位不同
 *
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 */
class Solution {
    List<Integer> res = new ArrayList<>();

    /**
     * 方法：回溯
     */
    public List<Integer> grayCode(int n) {
        // 000 -> 001 -> 011 -> 010 -> 110 -> 111 -> 101 -> 100
        backtrace(n, new StringBuffer(), new int[]{0, 1});
        return res;
    }

    public void backtrace(int n, StringBuffer sb, int[] nums) {
        // 判断条件，是否返回
        if (sb.length() == n) {
            // 二进制转换为十进制
            res.add(Integer.valueOf(sb.toString(), 2));
            return;
        }
        // 回溯第一个状态
        sb.append(nums[0]);
        // 注意数组 {0, 1}
        backtrace(n, sb, new int[]{0, 1});
        sb.deleteCharAt(sb.length() - 1);
        // 回溯第二个状态
        sb.append(nums[1]);
        // 注意数组，右子节点的 choice_list 要反过来 {1, 0}
        backtrace(n, sb, new int[]{1, 0});
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().grayCode(3));
        System.out.println(new Solution().grayCode(2));
        System.out.println(new Solution().grayCode(1));
    }
}
