package com.scuyjzh.leetcode.easy.No_0066_Plus_One;

import java.util.*;

/**
 * 66. 加一
 *
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加
 * 一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
class Solution {
    /**
     * 方法：找出最长的后缀 9
     *
     * • 时间复杂度：O(n)，其中 n 是数组 digits 的长度。
     * • 空间复杂度：O(1)。返回值不计入空间复杂度。
     */
    public int[] plusOne(int[] digits) {
        /*
         * 思路：
         * 当对数组 digits 加一时，只需要关注 digits 的末尾出现了多少个 9 即可。可以考虑如下的三种
         * 情况：
         *   • 如果 digits 的末尾没有 9，例如 [1,2,3]，那么直接将末尾的数加一，得到 [1,2,4] 并返回；
         *   • 如果 digits 的末尾有若干个 9，例如 [1,2,3,9,9]，那么只需要找出从末尾开始的第一个不为 9 的元
         *     素，即 3，将该元素加一，得到 [1,2,4,9,9]。随后将末尾的 9 全部置零，得到 [1,2,4,0,0] 并返回。
         *   • 如果 digits 的所有元素都是 9，例如 [9,9,9,9,9]，那么答案为 [1,0,0,0,0,0]。只需要构造一个长度
         *     比 digits 多 1 的新数组，将首元素置为 1，其余元素置为 0 即可。
         *
         * 算法：
         * 只需要对数组 digits 进行一次逆序遍历，找出第一个不为 9 的元素，将其加一并将后续所有元素置零即
         * 可。如果 digits 中所有的元素均为 9，那么对应着「思路」部分的第三种情况，需要返回一个新的数组。
         */
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{0})));
    }
}
