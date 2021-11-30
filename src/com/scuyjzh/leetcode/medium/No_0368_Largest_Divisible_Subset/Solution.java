package com.scuyjzh.leetcode.medium.No_0368_Largest_Divisible_Subset;

import java.util.*;

/**
 * 368. 最大整除子集
 *
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的
 * 整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当
 * 满足：
 *   • answer[i] % answer[j] == 0 ，或
 *   • answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 */
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        /*
         * 前言：
         * 首先需要理解什么叫「整除子集」。根据题目的描述，如果一个所有元素互不相同的集合中的任意元素存在
         * 整除关系，就称为整除子集。为了得到「最大整除子集」，需要考虑如何从一个小的整除子集扩充成为
         * 更大的整除子集。
         *
         * 根据整除关系具有传递性，即如果 a∣b，并且 b∣c，那么 a∣c，可知：
         *   • 如果整数 a 是整除子集 S1 的最小整数 b 的约数（即 a∣b），那么可以将 a 添加到 S1 中得到一个更大的
         *     整除子集；
         *   • 如果整数 c 是整除子集 S2 的最小整数 d 的倍数（即 d∣c），那么可以将 c 添加到 S2 中得到一个更大的
         *     整除子集。
         * 这两点揭示了当前问题状态转移的特点，因此可以使用动态规划的方法求解。
         *
         * 动态规划：
         * 根据前言的分析，需要将输入数组 nums 按照升序排序，以便获得一个子集的最小整数或者最大整数。
         * 又根据动态规划的「无后效性」状态设计准则，需要将状态定义成「某个元素必须选择」。
         *
         * 一、状态定义：
         * dp[i] 表示在输入数组 nums 升序排列的前提下，以 nums[i] 为最大整数的「整除子集」的大小
         * （在这种定义下 nums[i] 必须被选择）。
         *
         * 二、状态转移方程：
         * 枚举 j=0…i−1 的所有整数 nums[j]，如果 nums[j] 能整除 nums[i]，说明 nums[i] 可以扩
         * 充在以 nums[j] 为最大整数的整除子集里成为一个更大的整除子集。
         *
         * 三、初始化：
         * 由于 nums[i] 必须被选择，因此对于任意 i=0…n−1，初始的时候 dp[i]=1，这里 n 是输入数组
         * 的长度。
         *
         * 四、输出：
         * 由于最大整除子集不一定包含 nums 中最大的整数，所以需要枚举所有的 dp[i]，选出最大整除子
         * 集的大小 maxSize，以及该最大子集中的最大整数 maxVal。按照如下方式倒推获得一个目标子集：
         *   1.倒序遍历数组 dp，直到找到 dp[i]=maxSize 为止，把此时对应的 nums[i] 加入结果集，此时
         *     maxVal=nums[i]；
         *   2.然后将 maxSize 的值减 1，继续倒序遍历找到 dp[i]=maxSize，且 nums[i] 能整除 maxVal 的 i 为止，
         *     将此时的 nums[i] 加入结果集，maxVal 更新为此时的 num[i]；
         *   3.重复上述操作，直到 maxSize 的值变成 0，此时的结果集即为一个目标子集。
         *
         * 下面用一个例子说明如何得到最大整除子集。假设输入数组为 [2,4,7,8,9,12,16,18]（已经有序），得到的动
         * 态规划表格如下：
         *   nums	2	4	7	8	9	12	16	20
         *   dp     1	2	1	3	1	3	4	3
         * 得到最大整除子集的做法如下：
         *   1.根据 dp 的计算结果，maxSize=4，maxVal=16，因此大小为 4 的最大整除子集包含的最大整数为 16；
         *   2.然后查找大小为 3 的最大整除子集，看到 8 和 12 对应的状态值都是 3，最大整除子集一定包含 8，
         *     这是因为 8∣16；
         *   3.然后查找大小为 2 的最大整除子集，看到 4 对应的状态值是 2，最大整除子集一定包含 4；
         *   4.然后查找大小为 1 的最大整除子集，看到 2 对应的状态值是 1，最大整除子集一定包含 2。
         * 通过这样的方式，就找到了满足条件的某个最大整除子集 [16,8,4,2]。
         */
        int len = nums.length;
        Arrays.sort(nums);

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = dp[0];
        for (int i = 1; i < len; ++i) {
            for (int j = 0; j < i; ++j) {
                // 题目中说「没有重复元素」很重要
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        for (int i = len - 1; i >= 0 && maxSize > 0; --i) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestDivisibleSubset(new int[]{1, 2, 3}));
        System.out.println(new Solution().largestDivisibleSubset(new int[]{1, 2, 4, 8}));
    }
}
