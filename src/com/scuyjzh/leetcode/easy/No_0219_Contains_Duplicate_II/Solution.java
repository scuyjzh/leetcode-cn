package com.scuyjzh.leetcode.easy.No_0219_Contains_Duplicate_II;

import java.util.*;

/**
 * 219. 存在重复元素 II
 * <p>
 * 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，使得nums [i] = nums [j]，并且 i 和 j的差的 绝对值 至多为 k。
 */
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 维护一个哈希表，始终包含最多 k 个元素，当出现重复值时则说明在 k 距离内存在重复元素
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
    }
}
