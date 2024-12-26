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
        /*
         * 如果一个字符串可以组成一个回文串，那么：(1) 如果它的长度为偶数，那么每个字符都必须出现偶数次；
         * (2) 如果它的长度为奇数，那么除了一个字符出现奇数次以外，其它的字符都必须出现偶数次。因此可以总结
         * 得到，如果一个字符串可以组成一个回文串，那么出现奇数次的字符的数量不能超过 1。
         *
         * 由于字符串中出现的字符的 ASCII 码在 0 到 127 之间，因此可以枚举所有的字符，对于每一个字符 c，
         * 找出它在字符串中出现的次数 ct，如果 ct 为奇数，那么将计数器 count 的值增加 1。如果在某一个
         * 时刻 count 的值大于 1，那么说明至少有两个字符出现了奇数次，因此字符串就不能组成一个回文串。如果
         * 在枚举完成后，count 的值仍然小于等于 1，那么字符串就可以组成一个回文串。
         */
        int count = 0;
        for (char i = 0; i < 128 && count <= 1; ++i) {
            int ct = 0;
            for (int j = 0; j < s.length(); ++j) {
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
        /*
         * 可以使用映射表（map）帮助统计字符串中每个字符出现的次数。映射表中的键（key）存放字
         * 符，值（value）存放字符出现的次数。
         *
         * 对字符串进行遍历，并得到字符串对应的映射表。随后遍历映射表，如果发现超过一个字符出现了奇数
         * 次，那么字符串就不可以组成一个回文串。
         */
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
        /*
         * 因为不同的字符数目最多只有 128 个，因此可以用长度为 128 的数组代替映射表。
         */
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
        /*
         * 方法三中有两次循环，第一次循环统计每个字符出现的次数，第二次循环统计出现奇数次的字符数目 count
         * 。可以把两个循环合二为一，只保留第一个循环，并在此循环中实时计算 count。
         *
         * 在对字符串进行遍历时，每一步更新了当前字符 c 出现的次数后，如果字符 c 出现了偶数次，就把
         * count 减一（这说明字符 c 在这一步前出现了奇数次），否则就把 count 加一。在遍历完整个字符串后，
         * count 就表示字符串中出现奇数次的字符的数目。
         */
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
        /*
         * 方法四也可以使用集合而不是数组来实现。
         *
         * 集合里存放出现了奇数次的字符。在对字符串进行遍历时，如果字符 c 在集合中，就把它删除，否则就把它
         * 添加进集合中。在遍历完整个字符串后，集合的大小就表示字符串中出现奇数次的字符的数目。
         */
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
