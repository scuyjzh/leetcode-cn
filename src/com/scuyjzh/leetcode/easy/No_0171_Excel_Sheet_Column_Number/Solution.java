package com.scuyjzh.leetcode.easy.No_0171_Excel_Sheet_Column_Number;

/**
 * 171. Excel 表列序号
 * <p>
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名
 * 称对应的列序号。
 * 例如，
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 */
class Solution {
    public int titleToNumber(String columnTitle) {
        int number = 0;
        int multiple = 1;
        for (int i = columnTitle.length() - 1; i >= 0; --i) {
            int k = columnTitle.charAt(i) - 'A' + 1;
            number += k * multiple;
            multiple *= 26;
        }
        return number;
    }
}
