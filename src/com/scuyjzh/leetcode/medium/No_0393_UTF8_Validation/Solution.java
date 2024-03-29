package com.scuyjzh.leetcode.medium.No_0393_UTF8_Validation;

/**
 * 393. UTF-8 编码验证
 *
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 *   1.对于 1 字节的字符，字节的第一位设为 0 ，后面 7 位为这个符号的
 *     unicode 码。
 *   2.对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1
 *     位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进
 *     制位，全部为这个符号的 unicode 码。
 *
 * 这是 UTF-8 编码的工作方式：
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 *
 * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
 *
 * 注意：
 * 输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着
 * 每个整数只表示 1 字节的数据。
 */
class Solution {
    public boolean validUtf8(int[] data) {
        int i = 0;
        while (i < data.length) {
            // 0x80: 10000000，也可以用 mark = 1 << 7 代替
            int mark = 0x80;
            int count = 0;

            while ((mark & data[i]) != 0) {
                mark >>= 1;
                ++count;
            }
            ++i;

            int k = i + count - 1;
            if (count == 1 || count > 4 || k > data.length) {
                return false;
            }

            while (i < k) {
                if ((data[i] & 0x80) == 0) {
                    return false;
                }
                ++i;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 11000101 10000010 00000001
        System.out.println(solution.validUtf8(new int[]{197, 130, 1}));
        // 11101011 10001100 00000100
        System.out.println(solution.validUtf8(new int[]{235, 140, 4}));
    }
}
