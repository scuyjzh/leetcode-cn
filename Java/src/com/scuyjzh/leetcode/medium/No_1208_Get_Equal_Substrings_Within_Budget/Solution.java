package com.scuyjzh.leetcode.medium.No_1208_Get_Equal_Substrings_Within_Budget;

/**
 * 1208. 尽可能使字符串相等
 *
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的
 * 开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小
 * 于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可
 * 以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 */
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        // 创建一个长度为 n 的数组 diff，其中 diff[i] 表示将 s[i] 变成 t[i] 的开销 |s[i]-t[i]|
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int res = 0;
        // 维护两个指针 left 和 right 表示数组 diff 的滑动窗口（子数组）的左右边界下标
        int left = 0, right = 0;
        // 维护滑动窗口的元素和 sum，初始值为 0
        int sum = 0;
        while (right < n) {
            // 将 diff[right] 的值加到 sum
            sum += diff[right];
            // 如果此时 sum>maxCost，则滑动窗口的元素和大于 maxCost
            while (sum > maxCost) {
                // 需要向右移动指针 left 并同时更新 sum 的值，直到 sum≤maxCost
                sum -= diff[left];
                ++left;
            }
            // 如果 sum≤maxCost，则滑动窗口的元素和不超过 maxCost，使用当前窗口的长度 right−left+1 更新最大窗口的长度
            res = Math.max(res, right - left + 1);
            // 不断右移 right 指针，重复上述步骤，直到 right 超出数组下标范围
            ++right;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().equalSubstring("abcd", "bcdf", 3));
    }
}
