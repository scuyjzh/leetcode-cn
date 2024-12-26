package com.scuyjzh.leetcode.hard.No_0358_Rearrange_String_k_Distance_Apart;

import java.util.*;

/**
 * 358. K 距离间隔重排字符串
 * <p>
 * 给你一个非空的字符串 s 和一个整数 k，你要将这个字符串中的字母进行重
 * 新排列，使得重排后的字符串中相同字母的位置间隔距离至少为 k。
 * 所有输入的字符串都由小写字母组成，如果找不到距离至少为 k 的重排结
 * 果，请返回一个空字符串 ""。
 */
class Solution {
    public String rearrangeString(String s, int k) {
        // 解题思路参考「力扣」767. 重构字符串 贪心 的解法
        if (s == null) {
            return "";
        }
        if (k <= 1) {
            return s;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        PriorityQueue<Character> heap = new PriorityQueue<>((o1, o2) -> countMap.get(o2) - countMap.get(o1));
        for (int i = 0; i < s.length(); ++i) {
            countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        heap.addAll(countMap.keySet());
        // 利用队列queue存储未到达指定距离k前的字符
        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            char ch = heap.poll();
            sb.append(ch);
            countMap.put(ch, countMap.get(ch) - 1);
            queue.add(ch);
            // 保证每两个相同的字符间隔都大于等于k
            if (queue.size() == k) {
                ch = queue.poll();
                if (countMap.get(ch) > 0) {
                    heap.add(ch);
                }
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rearrangeString("aabbcc", 3));
        System.out.println(new Solution().rearrangeString("aaabc", 3));
        System.out.println(new Solution().rearrangeString("aaadbbcc", 2));
    }
}
