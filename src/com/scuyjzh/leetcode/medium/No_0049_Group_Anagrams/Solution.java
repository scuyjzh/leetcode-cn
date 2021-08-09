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
     * 时间复杂度：O(nk*log k)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。需要遍历 n 个字符串，对于每个字符串，需要 O(k*log k) 的时间进行排序以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(nk*log k)。
     * 空间复杂度：O(nk)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。需要用哈希表存储全部字符串。
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(strs.length);
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
     * 时间复杂度：O(n(k+∣Σ∣))，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度，Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=26。需要遍历 n 个字符串，对于每个字符串，需要 O(k) 的时间计算每个字母出现的次数，O(∣Σ∣) 的时间生成哈希表的键，以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(n(k+∣Σ∣))。
     * 空间复杂度：O(n(k+∣Σ∣))，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度，Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=26。需要用哈希表存储全部字符串，而记录每个字符串中每个字母出现次数的数组需要的空间为 O(∣Σ∣) ，在渐进意义下小于 O(n(k+∣Σ∣))，可以忽略不计。
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(strs.length);
        for (String str : strs) {
            // 对于每个字符串，使用长度为 26 的数组记录每个字母出现的次数
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
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
        Solution solution = new Solution();
        System.out.println(solution.groupAnagrams1(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(solution.groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
