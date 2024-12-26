package com.scuyjzh.leetcode.easy.No_0169_Majority_Element;

import java.util.*;

/**
 * 169. 多数元素
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 进阶：
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
class Solution {
    /**
     * 方法一：哈希表
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。遍历数组 nums 一次，对于 nums 中的每一个元素，将其插入哈希表都只需要常数时间。
     * 空间复杂度：O(n)。哈希表最多包含 n - ⌊ n/2 ⌋ 个键值对，所以占用的空间为 O(n)。
     */
    public int majorityElement1(int[] nums) {
        /*
         * 思路：
         * 出现次数最多的元素大于 ⌊ n/2 ⌋ 次，所以可以用哈希表来快速统计每个元素出现的次数。
         *
         * 算法：
         * 使用哈希映射（HashMap）来存储每个元素以及出现的次数。对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。
         * 用一个循环遍历数组 nums 并将数组中的每个元素加入哈希映射中。在这之后，遍历哈希映射中的所有键值对，返回值最大的键。
         * 同样也可以在遍历数组 nums 时候使用打擂台的方法，维护最大的值，这样省去了最后对哈希映射的遍历。
         */
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    /**
     * 方法二：排序
     * 时间复杂度：O(n*log n)。将数组排序的时间复杂度为 O(n*log n)。
     * 空间复杂度：O(log n)。如果使用语言自带的排序算法，需要使用 O(log n) 的栈空间。如果自己编写堆排序，则只需要使用 O(1) 的额外空间。
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 方法三：Boyer-Moore 投票算法
     * 时间复杂度：O(n)。Boyer-Moore 算法只对数组进行了一次遍历。
     * 空间复杂度：O(1)。Boyer-Moore 算法只需要常数级别的额外空间。
     */
    public int majorityElement3(int[] nums) {
        int count = 0;
        int candidate = nums[0];
        // 思路：不同的两数相互抵消，最后剩下的肯定是多于一半的那个数
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().majorityElement1(new int[]{2, 2, 1, 1, 1, 2, 2}));
        System.out.println(new Solution().majorityElement2(new int[]{2, 2, 1, 1, 1, 2, 2}));
        System.out.println(new Solution().majorityElement3(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }
}
