package com.scuyjzh.leetcode.medium.No_0179_Largest_Number;

import java.util.*;

/**
 * 179. 最大数
 * <p>
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之
 * 组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 */
class Solution {
    /**
     * 方法：排序
     * 时间复杂度：O(NlogNlogM)，其中 N 是给定序列的长度，M 是 32 位整数的最大值，每个数转化为字符串后的长度是 O(logM) 的数量级。
     * 空间复杂度：O(logN)，排序需要 O(logN) 的栈空间。
     */
    public String largestNumber(int[] nums) {
        /*
         * 思路与算法：
         * 要想组成最大的整数，一种直观的想法是把数值大的数放在高位。于是可以比较输入数组的每个元素的
         * 最高位，最高位相同的时候比较次高位，以此类推，完成排序，然后把它们拼接起来。这种排序方式对于输
         * 入数组 没有相同数字开头 的时候是有效的，例如 [45,56,81,76,123]。
         *
         * 下面考虑输入数组 有相同数字开头 的情况，例如 [4,42] 和 [4,45]。
         *   • 对于 [4,42]，比较 442>424，需要把 4 放在前面；
         *   • 对于 [4,45]，比较 445<454，需要把 45 放在前面。
         *
         * 因此需要比较两个数不同的拼接顺序的结果，进而决定它们在结果中的排列顺序。
         *
         * 由于需要拼接以后才能决定两个数在结果中的先后顺序，N 个数就有 N! 种拼接的可能，是不是需要先
         * 得到 N 个数的全排列以后，再选出最大的呢？答案是没有必要。上述排序规则满足传递性，两个元素比较就
         * 可以确定它们在排序以后的相对位置关系。下面证明这种排序规则的必要性和充分性。
         *
         * https://leetcode-cn.com/problems/largest-number/solution/zui-da-shu-by-leetcode-solution-sid5/
         */
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; ++i) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        // System.out.println(new Solution().largestNumber(new int[]{10, 2}));
        System.out.println(new Solution().largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(new Solution().largestNumber(new int[]{1}));
        System.out.println(new Solution().largestNumber(new int[]{10}));
    }
}
