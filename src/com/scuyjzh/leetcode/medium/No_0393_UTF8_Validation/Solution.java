package com.scuyjzh.leetcode.medium.No_0393_UTF8_Validation;

/**
 * 393. UTF-8 编码验证
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * 1. 对于 1 字节的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 2. 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
 * 注意：
 * 输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 */
class Solution {
    public boolean validUtf8(int[] data) {
        int current = 0;
        for (int datum : data) {
            if (current > 0) {
                if ((datum & 0b11000000) != 0b10000000) {
                    return false;
                }
                current--;
                continue;
            }
            if ((datum & 0b10000000) == 0) {
                current = 0;
            } else if ((datum & 0b11100000) == 0b11000000) {
                current = 1;
            } else if ((datum & 0b11110000) == 0b11100000) {
                current = 2;
            } else if ((datum & 0b11111000) == 0b11110000) {
                current = 3;
            } else {
                return false;
            }
        }
        return current == 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 11000101 10000010 00000001
        System.out.println(solution.validUtf8(new int[]{197, 130, 1}));
        // 11101011 10001100 00000100
        System.out.println(solution.validUtf8(new int[]{235, 140, 4}));
    }
}
