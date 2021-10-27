package com.scuyjzh.leetcode.medium.No_0340_Longest_Substring_with_At_Most_K_Distinct_Characters;

import java.util.*;

/**
 * 340. 至多包含 K 个不同字符的最长子串
 *
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 */
class Solution {
    /**
     * 方法一：滑动窗口 + 哈希表
     *
     * • 时间复杂度：最好情况下是 O(N)，最坏情况下是 O(Nk)。
     * • 空间复杂度：哈希表的空间开销：O(k)。
     */
    public int lengthOfLongestSubstringKDistinct1(String s, int k) {
        /*
         * 想法：
         * 为了通过一次遍历解决这个问题，使用滑动窗口方法，使用两个指针 left 和 right 标记窗口的边界。
         *
         * 思路是将左右指针都设置为 0，然后向右移动 right 指针保证区间内含有不超过 k 个不同字符。当移
         * 动到含有 k + 1 个不同字符的时候，移动 left 指针直到区间内不含有超过 k + 1 个不同字符。
         *
         * 这个算法的基本思想是：在字符串上移动滑动窗口，保证窗口内有不超过 k 个不同字符，同时在每一步更
         * 新最大子串长度。
         *
         * 只有一个问题需要解决：如何移动左指针保证子串中只包含 k 个不同字符？
         * 可以使用一个哈希表，建立从字符到滑动窗口最右出现位置的映射，在任意时刻，哈希表不能包含
         * k + 1 个元素。
         */
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<>(k + 1);

        int ans = 1;

        while (right < n) {
            // add new character and move right pointer
            hashmap.put(s.charAt(right), right++);

            // sliding window contains k + 1 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                int delIdx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(delIdx));
                // move left pointer of the sliding window
                left = delIdx + 1;
            }

            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    /**
     * 方法二：滑动窗口 + 有序字典
     * <p>
     * • 时间复杂度：O(N) 因为有序字典的所有操作 insert/get/delete/popitem
     * (put/containsKey/remove) 都在常数时间内完成。
     * • 空间复杂度：O(k)，有序字典的空间开销，最多保存 k + 1 个元素。
     */
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        /*
         * 如何达到 O(N) 时间复杂度？
         * 方法 1 使用了标准的哈希表，不能够保证 O(N) 的复杂度。
         *
         * 为了达到 O(N) 的效率，可以使用一种数据结构，保证以下四种操作都可以在 O(1) 时间完成：
         *   • 插入键
         *   • 获取键 / 检查键是否存在
         *   • 删除键
         *   • 返回最先 / 最后插入的键值对
         *
         * 前三个操作通过标准的哈希表就可以实现，第四个操作使用链表可以实现。
         *
         * 使用有序字典结构，可以同时支持哈希表和链表操作，在 Java 中这个结构叫做 LinkedHashMap。
         */
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<>(k + 1);

        int ans = 1;

        while (right < n) {
            Character ch = s.charAt(right);
            // if character is already in the hashmap -
            // delete it, so that after insert it becomes
            // the rightmost element in the hashmap
            if (hashmap.containsKey(ch)) {
                hashmap.remove(ch);
            }
            hashmap.put(ch, right++);

            // sliding window contains k + 1 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
                hashmap.remove(leftmost.getKey());
                // move left pointer of the sliding window
                left = leftmost.getValue() + 1;
            }

            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct1("eceba", 2));
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct2("aa", 1));
    }
}
