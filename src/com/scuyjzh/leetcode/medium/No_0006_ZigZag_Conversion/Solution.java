package com.scuyjzh.leetcode.medium.No_0006_ZigZag_Conversion;

import java.util.*;

/**
 * 6. Z 字形变换
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右
 * 进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *   P   A   H   N
 *   A P L S I I G
 *   Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比
 * 如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 *   string convert(string s, int numRows);
 */
class Solution {
    /**
     * 方法：按行排序
     * 时间复杂度：O(n)，其中 n==len(s)
     * 空间复杂度：O(n)
     */
    public String convert(String s, int numRows) {
        /*
         * 思路：
         * 通过从左向右迭代字符串，可以轻松地确定字符位于 Z 字形图案中的哪一行。
         *
         * 算法：
         * 可以使用 min(numRows, len(s)) 个列表来表示 Z 字形图案中的非空行。
         * 从左到右迭代 s，将每个字符添加到合适的行。使用当前行和当前方向这两个变量对合适的行进行跟
         * 踪。
         * 只有当向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
         */
        if (numRows < 2) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); ++i) {
            rows.add(new StringBuilder());
        }

        // curRow 变量表示当前行
        int curRow = 0;
        // goingDown 变量表示当前方向
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 当向上移动到最上面的行或向下移动到最下面的行时，当前方向发生改变
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            // 根据当前方向来更新当前字符 c 对应的行索引
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
        System.out.println(new Solution().convert("PAYPALISHIRING", 4));
        System.out.println(new Solution().convert("A", 1));
    }
}
