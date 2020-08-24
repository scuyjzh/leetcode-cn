package com.scuyjzh.lcof.No_03;

import java.util.*;

/**
 * 数组中重复的数字
 *
 * @author scuyjzh
 * @date 2020/6/30 10:50
 */
class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }
}
