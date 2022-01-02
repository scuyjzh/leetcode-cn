package com.scuyjzh.leetcode.medium.No_0260_Single_Number_III;

import java.util.*;

/**
 * 260. 只出现一次的数字 III
 *
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素
 * 均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 */
class Solution {
    /**
     * 方法一：哈希表
     *
     * • 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * • 空间复杂度：O(n)，即为哈希映射需要使用的空间。
     */
    public int[] singleNumber1(int[] nums) {
        // 可以使用一个哈希映射统计数组中每一个元素出现的次数
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int index = 0;
        // 在统计完成后，对哈希映射进行遍历，将所有只出现了一次的数放入答案中
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                ans[index++] = entry.getKey();
            }
        }
        return ans;
    }

    /**
     * 方法二：位运算
     *
     * • 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * • 空间复杂度：O(1)。
     */
    public int[] singleNumber2(int[] nums) {
        /*
         * 假设数组 nums 中只出现一次的元素分别是 x1 和 x2，如果把 nums 中的所有元素全部异或起来，得到结果 x，
         * 那么一定有：
         *                                     x=x1⊕x2
         * 其中 ⊕ 表示异或运算。这是因为 nums 中出现两次的元素都会因为异或运算的性质 a⊕b⊕b=a 抵消掉，那
         * 么最终的结果就只剩下 x1 和 x2 的异或和。
         *
         * x 显然不会等于 0，因为如果 x=0，那么说明 x1=x2，这样 x1 和 x2 就不是只出现一次的数字了。因此，
         * 可以使用位运算 x & -x 取出 x 的二进制表示中最低位那个 1，设其为第 l 位，那么 x1 和 x2 中的某一
         * 个数的二进制表示的第 l 位为 0，另一个数的二进制表示的第 l 位为 1。在这种情况下，x1⊕x2 的二进制表
         * 示的第 l 位才能为 1。
         *
         * 这样一来，就可以把 nums 中的所有元素分成两类，其中一类包含所有二进制表示的第 l 位为 0 的数，
         * 另一类包含所有二进制表示的第 l 位为 1 的数。可以发现：
         *   • 对于任意一个在数组 nums 中出现两次的元素，该元素的两次出现会被包含在同一类中；
         *   • 对于任意一个在数组 nums 中只出现了一次的元素，即 x1 和 x2，它们会被包含在不同类中。
         *
         * 因此，如果将每一类的元素全部异或起来，那么其中一类会得到 x1，另一类会得到 x2。这样就找
         * 出了这两个只出现一次的元素。
         */
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        // 防止溢出（Least Significant Bit 最低有效位）
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().singleNumber1(new int[]{1, 2, 1, 3, 2, 5})));
        System.out.println(Arrays.toString(new Solution().singleNumber2(new int[]{-1, 0})));
        System.out.println(Arrays.toString(new Solution().singleNumber2(new int[]{0, 1})));
    }
}
