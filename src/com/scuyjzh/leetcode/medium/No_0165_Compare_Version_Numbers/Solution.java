package com.scuyjzh.leetcode.medium.No_0165_Compare_Version_Numbers;

/**
 * 165. 比较版本号
 *
 * 给你两个版本号 version1 和 version2 ，请你比较它们。
 *
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号
 * 由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修
 * 订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修
 * 订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
 *
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号
 * 时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订
 * 号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为
 * 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相
 * 同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 *
 * 返回规则如下：
 *   • 如果 version1 > version2 返回 1，
 *   • 如果 version1 < version2 返回 -1，
 *   • 除此之外返回 0。
 */
class Solution {
    /**
     * 方法：字符串分割
     *
     * • 时间复杂度：O(n+m)（或 O(max(n,m))，这是等价的），其中 n 是字符串 version1 的长度，m 是
     *   字符串 version2 的长度。
     * • 空间复杂度：O(n+m)，需要 O(n+m) 的空间存储分割后的修订号列表。
     */
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int x, y;
        for (int i = 0; i < v1.length || i < v2.length; ++i) {
            x = 0;
            y = 0;
            if (i < v1.length) {
                x = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                y = Integer.parseInt(v2[i]);
            }
            if (x > y) {
                return 1;
            }
            if (x < y) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().compareVersion("1.01", "1.001"));
        System.out.println(new Solution().compareVersion("1.0", "1.0.0"));
        System.out.println(new Solution().compareVersion("0.1", "1.1"));
        System.out.println(new Solution().compareVersion("1.0.1", "1"));
        System.out.println(new Solution().compareVersion("7.5.2.4", "7.5.3"));
    }
}
