package com.scuyjzh.leetcode.easy.No_0217_Contains_Duplicate;

import java.util.*;

/**
 * 217. 存在重复元素
 * <p>
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 */
class Solution {
    /**
     * 方法一：排序
     * 时间复杂度：O(NlogN)，其中 N 为数组的长度。需要对数组进行排序。
     * 空间复杂度：O(logN)，其中 N 为数组的长度。注意在这里应当考虑递归调用栈的深度。
     */
    public boolean containsDuplicate1(int[] nums) {
        // 在对数字从小到大排序之后，数组的重复元素一定出现在相邻位置中
        Arrays.sort(nums);
        int n = nums.length;
        // 扫描已排序的数组，每次判断相邻的两个元素是否相等，如果相等则说明存在重复的元素
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法二：哈希表
     * 时间复杂度：O(N)，其中 N 为数组的长度。
     * 空间复杂度：O(N)，其中 N 为数组的长度。
     */
    public boolean containsDuplicate2(int[] nums) {
        // 对于数组中每个元素，将它插入到哈希表中
        Set<Integer> set = new HashSet<>();
        // 如果插入一个元素时发现该元素已经存在于哈希表中，则说明存在重复的元素
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().containsDuplicate1(new int[]{1, 2, 3, 1}));
        System.out.println(new Solution().containsDuplicate2(new int[]{1, 2, 3, 4}));
    }
}
