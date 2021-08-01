package com.scuyjzh.leetcode.easy.No_0014_Longest_Common_Prefix;

/**
 * 14. 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
class Solution {
    /**
     * 方法一：横向扫描
     * 时间复杂度：O(mn)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
     * 空间复杂度：O(1)。使用的额外空间复杂度为常数。
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        // 依次遍历字符串数组中的每个字符串，对于每个遍历到的字符串，更新最长公共前缀
        // 当遍历完所有的字符串以后，即可得到字符串数组中的最长公共前缀
        for (int i = 1; i < count; ++i) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            // 如果在尚未遍历完所有的字符串时，最长公共前缀已经是空串，则最长公共前缀一定是空串
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 方法二：纵向扫描
     * 时间复杂度：O(mn)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
     * 空间复杂度：O(1)。使用的额外空间复杂度为常数。
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        // 从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同
        // 如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀
        for (int i = 0; i < length; ++i) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; ++j) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix1(new String[]{"frankfurt", "franklin", "frankenstein"}));
        System.out.println(solution.longestCommonPrefix2(new String[]{"frankfurt", "franklin", "frankenstein"}));
    }
}