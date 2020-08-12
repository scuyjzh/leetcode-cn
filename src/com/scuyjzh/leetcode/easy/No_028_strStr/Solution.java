package com.scuyjzh.leetcode.easy.No_028_strStr;

/**
 * @author scuyjzh
 * @data 2020/6/22 4:24
 */
class Solution {
    /**
     * Sunday algorithm
     */
    public int strStr(String haystack, String needle) {
        int hayLen = haystack.length();
        int nLen = needle.length();

        //haystack串的游标索引
        int i = 0;
        // needle串的游标索引
        int j = 0;

        // haystack剩余字符少于needle串时跳过比较
        while (i <= hayLen - nLen) {
            // 将needle串与haystack串中参与比较的子串进行逐个字符比对
            while (j < nLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }

            // 如果j等于needle串的长度说明此时匹配成功，可以直接返回此时主串的游标索引
            if (j == nLen) {
                return i;
            }

            // 不匹配时计算需要跳过的字符数，移动主串游标i
            if (i < hayLen - nLen) {
                // 对照字符在needle串存在，则需要跳过的字符数为从对照字符在needle串中最后出现的位置起剩余的字符个数
                // 不存在则跳过的字符数为needle串长度+1，也就是代码nLen-(-1)的情况
                i += (nLen - lastIndex(needle, haystack.charAt(i + nLen)));
            } else {
                return -1;
            }
            // 每次比较之后将needle游标置为0
            j = 0;
        }

        return -1;
    }

    public int lastIndex(String str, char ch) {
        // 从后往前检索
        for (int j = str.length() - 1; j >= 0; j--) {
            if (str.charAt(j) == ch) {
                return j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String haystack = "HERE IS AN EXAMPLE.";
        String needle = "EXAMPLE";
        System.out.println(solution.strStr(haystack, needle));
    }
}
