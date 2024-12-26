package com.scuyjzh.leetcode.medium.No_0249_Group_Shifted_Strings;

import java.util.*;

/**
 * 249. 移位字符串分组
 * <p>
 * 给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中
 * 每个字母都变为其在字母表中后续的字母，比如："abc" -> "bcd"。这
 * 样，可以持续进行 “移位” 操作，从而生成如下移位序列：
 *   "abc" -> "bcd" -> ... -> "xyz"
 * 给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作
 * 规律的组合进行分组并返回。
 */
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String key = hash(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private String hash(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            sb.append("#");
            int shift = (ch - str.charAt(0) + 26) % 26;
            sb.append(shift);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
    }
}