package com.scuyjzh.leetcode.medium.No_0049_Group_Anagrams;

import java.util.*;

/**
 * 49. 字母异位词分组
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词指字母相同，但排列不同的字符串。
 */
class Solution {
    /**
     * 方法一：排序
     * 时间复杂度：O(NKlogK)，其中 N 是 strs 中的字符串的数量，K 是 strs 中的字符串的的最大长度。需要遍历 N 个字符串，对于每个字符串，需要 O(KlogK) 的时间进行排序以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(NKlogK)。
     * 空间复杂度：O(NK)，其中 N 是 strs 中的字符串的数量，K 是 strs 中的字符串的的最大长度。需要用哈希表存储全部字符串。
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        /*
         * 前言：
         * 两个字符串互为字母异位词，当且仅当两个字符串包含的字母相同。同一组字母异位词中的字符串具备相同
         * 点，可以使用相同点作为一组字母异位词的标志，使用哈希表存储每一组字母异位词，哈希表的键为一组字
         * 母异位词的标志，哈希表的值为一组字母异位词列表。
         *
         * 遍历每个字符串，对于每个字符串，得到该字符串所在的一组字母异位词的标志，将当前字符串加入该组字
         * 母异位词的列表中。遍历全部字符串之后，哈希表中的每个键值对即为一组字母异位词。
         *
         * 以下的两种方法分别使用排序和计数作为哈希表的键。
         */
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 由于互为字母异位词的两个字符串包含的字母相同
            char[] array = str.toCharArray();
            // 因此对两个字符串分别进行排序之后得到的字符串一定是相同的
            Arrays.sort(array);
            // 故可以将排序之后的字符串作为哈希表的键
            String key = String.valueOf(array);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 方法二：计数
     * 时间复杂度：O(N(K+∣Σ∣))，其中 N 是 strs 中的字符串的数量，K 是 strs 中的字符串的的最大长度，Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=26。需要遍历 N 个字符串，对于每个字符串，需要 O(K) 的时间计算每个字母出现的次数，O(∣Σ∣) 的时间生成哈希表的键，以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(N(K+∣Σ∣))。
     * 空间复杂度：O(N(K+∣Σ∣))，其中 N 是 strs 中的字符串的数量，K 是 strs 中的字符串的的最大长度，Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=26。需要用哈希表存储全部字符串，而记录每个字符串中每个字母出现次数的数组需要的空间为 O(∣Σ∣) ，在渐进意义下小于 O(N(K+∣Σ∣))，可以忽略不计。
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        /*
         * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同
         * 的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
         *
         * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 26 的数组记录每个字母出现的次数。
         */
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 对于每个字符串，使用长度为 26 的数组记录每个字母出现的次数
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; ++i) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; ++i) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println(new Solution().groupAnagrams1(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(new Solution().groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}