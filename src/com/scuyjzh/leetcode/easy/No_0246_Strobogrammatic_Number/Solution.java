package com.scuyjzh.leetcode.easy.No_0246_Strobogrammatic_Number;

import java.util.*;

/**
 * 246. 中心对称数
 *
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或
 * 者上下颠倒地看）。
 * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的
 * 形式来表达数字。
 */
class Solution {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            if (map.get(num.charAt(left)) == null || map.get(num.charAt(right)) == null) {
                return false;
            }
            if (map.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
