package com.scuyjzh.lcci._01_01;

/**
 * 判断字符是否唯一
 *
 * @author scuyjzh
 * @date 2020/6/30 14:09
 */
class Solution {
    public boolean isUnique(String astr) {
        // 由于ASCII码字符个数为128个，因此可以使用
        // 两个64位的long变量来存储是否出现某个字符
        long low64 = 0;
        long high64 = 0;

        for (char c : astr.toCharArray()) {
            if (c >= 64) {
                // 如果>=64，先统一左移64位
                long bitIndex = 1L << (c - 64);
                if ((high64 & bitIndex) != 0) {
                    // 如果这一位已经是 1 ，那么进行与操作得到结果不是 0
                    return false;
                }
                // 进行或运算，结果是把对应位置为 1
                high64 |= bitIndex;
            } else {
                long bitIndex = 1L << c;
                if ((low64 & bitIndex) != 0) {
                    return false;
                }
                low64 |= bitIndex;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.isUnique("leetcode");
        solution.isUnique("abc");
    }
}

