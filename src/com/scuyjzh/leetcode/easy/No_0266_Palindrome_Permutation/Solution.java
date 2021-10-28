package com.scuyjzh.leetcode.easy.No_0266_Palindrome_Permutation;

import java.util.*;

/**
 * 266. 回文排列
 *
 * 给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回
 * 文字符串。
 */
class Solution {
    /**
     * 方法一：穷举
     *
     * • 时间复杂度：O(128∗∣S∣)。枚举了 128 个字符，每次枚举需要遍历整个字符串，因此时间复杂度
     *   为 O(128∗∣S∣)。
     * • 空间复杂度：O(1)。
     */
    public boolean canPermutePalindrome1(String s) {
        int count = 0;
        for (char i = 0; i < 128 && count <= 1; ++i) {
            int ct = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == i) {
                    ct++;
                }
            }
            count += ct % 2;
        }
        return count <= 1;
    }

    /**
     * 方法二：基于哈希的映射表（HashMap）
     *
     * • 时间复杂度：O(∣S∣)。需要遍历整个字符串以及映射表，但映射表的大小一定不会大于字符串的长
     *   度，因此时间复杂度为 O(∣S∣)。
     * • 空间复杂度：O(min{∣S∣,128})。在最坏的情况下，字符串中的每一个字符都不相同，但不同的字符数
     *   目最多只有 128 个。
     */
    public boolean canPermutePalindrome2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = 0;
        for (char key : map.keySet()) {
            count += map.get(key) % 2;
        }
        return count <= 1;
    }

    /**
     * 方法三：数组
     *
     * • 时间复杂度：O(max{∣S∣,128})。需要遍历整个字符串以及数组，但数组的大小和字符串长度的关
     *   系未知，因此时间复杂度为 O(max{∣S∣,128})。
     * • 空间复杂度：O(128)。数组的长度为 128。
     */
    public boolean canPermutePalindrome3(String s) {
        int[] map = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            map[s.charAt(i)]++;
        }
        int count = 0;
        for (int key = 0; key < map.length && count <= 1; ++key) {
            count += map[key] % 2;
        }
        return count <= 1;
    }

    /**
     * 方法四：数组+单次循环
     *
     * • 时间复杂度：O(∣S∣)。只需要遍历一次字符串。
     * • 空间复杂度：O(128)。数组的长度为 128。
     */
    public boolean canPermutePalindrome4(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0) {
                count--;
            } else {
                count++;
            }
        }
        return count <= 1;
    }

    /**
     * 方法五：集合
     *
     * • 时间复杂度：O(∣S∣)。只需要遍历一次字符串。
     * • 空间复杂度：O(min{∣S∣,128})。在最坏的情况下，字符串中的每一个字符都不相同，但不同的字符数
     *   目最多只有 128 个。
     */
    public boolean canPermutePalindrome5(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            if (!set.add(s.charAt(i))) {
                set.remove(s.charAt(i));
            }
        }
        return set.size() <= 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canPermutePalindrome1("code"));
        System.out.println(new Solution().canPermutePalindrome2("aab"));
        System.out.println(new Solution().canPermutePalindrome3("aab"));
        System.out.println(new Solution().canPermutePalindrome4("carerac"));
        System.out.println(new Solution().canPermutePalindrome5("carerac"));
    }
}
