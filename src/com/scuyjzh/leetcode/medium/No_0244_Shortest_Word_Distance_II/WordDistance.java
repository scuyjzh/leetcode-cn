package com.scuyjzh.leetcode.medium.No_0244_Shortest_Word_Distance_II;

import java.util.*;

/**
 * 244. 最短单词距离 II
 * <p>
 * 请设计一个类，使该类的构造函数能够接收一个单词列表。
 * 然后再实现一个方法，该方法能够分别接收两个单词 word1 和 word2，并返回列表中这两个单词之间的最短距离。
 * 您的方法将被以不同的参数调用 多次。
 */
class WordDistance {
    HashMap<String, List<Integer>> map;

    public WordDistance(String[] words) {
        map = new HashMap<>();
        // 统计每个单词出现的下标存入哈希表中
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            if (map.containsKey(w)) {
                map.get(w).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(w, list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> idx1 = map.get(word1);
        List<Integer> idx2 = map.get(word2);
        int res = Integer.MAX_VALUE;
        // 初始化两个指针 i 和 j 来指向下标列表中的某个下标
        int i = 0, j = 0;
        while (i < idx1.size() && j < idx2.size()) {
            // 更新结果
            res = Math.min(Math.abs(idx1.get(i) - idx2.get(j)), res);
            // 比较两个下标列表中的数字，将数字较小的列表的指针向后移动一位，直至其中一个列表遍历完成
            if (idx1.get(i) < idx2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        WordDistance wd = new WordDistance(new String[]{"practice", "makes", "practice", "coding", "makes", "makes", "coding"});
        System.out.println(wd.shortest("makes", "coding"));
        System.out.println(wd.shortest("practice", "makes"));
    }
}
