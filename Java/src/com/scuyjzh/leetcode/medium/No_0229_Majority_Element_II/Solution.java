package com.scuyjzh.leetcode.medium.No_0229_Majority_Element_II;

import java.util.*;

/**
 * 229. 求众数 II
 * <p>
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 */
class Solution {
    /**
     * 方法：Boyer-Moore 投票算法
     * 时间复杂度：O(n)。Boyer-Moore 算法只对数组进行了一次遍历。
     * 空间复杂度：O(1)。Boyer-Moore 算法只需要常数级别的额外空间。
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // 最多有2个众数（出现超过 ⌊ n/3 ⌋ 次），初始化两个候选人
        int candidate1 = nums[0], candidate2 = nums[0];
        // 初始化计票
        int count1 = 0, count2 = 0;

        // 第1阶段 - 成对抵销
        for (int num : nums) {
            // 投票
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
            // 第1个候选人配对
            else if (count1 == 0) {
                candidate1 = num;
                count1++;
            }
            // 第2个候选人配对
            else if (count2 == 0) {
                candidate2 = num;
                count2++;
            }
            // 第3个竞争者出现
            else {
                count1--;
                count2--;
            }
        }

        // 第2阶段 - 计数, 数目要超过三分之一
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            res.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            res.add(candidate2);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2}));
    }
}
