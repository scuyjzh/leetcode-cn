package com.scuyjzh.leetcode.hard.No_0127_Word_Ladder;

import java.util.*;

/**
 * 127. 单词接龙
 *
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按
 * 下述规格形成的序列：
 *   • 序列中第一个单词是 beginWord 。
 *   • 序列中最后一个单词是 endWord 。
 *   • 每次转换只能改变一个字母。
 *   • 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到
 * 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在
 * 这样的转换序列，返回 0。
 */
class Solution {
    private Set<String> wordSet;
    private Queue<String> queue;
    private Set<String> visited;

    /**
     * 方法一：广度优先遍历
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);

        // 第 2 步：图的广度优先遍历，必须使用队列和表示是否已访问的 visited 哈希表
        queue = new ArrayDeque<>();
        queue.offer(beginWord);
        visited = new HashSet<>();
        visited.add(beginWord);

        // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
        int step = 1;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; ++i) {
                // 依次遍历当前队列中的单词
                String currentWord = queue.poll();
                // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                if (changeWordEveryOneLetter(currentWord, endWord)) {
                    return step + 1;
                }
            }
            ++step;
        }
        return 0;
    }

    /**
     * 尝试对 currentWord 修改每一个字符，看是否能与 endWord 匹配
     */
    private boolean changeWordEveryOneLetter(String currentWord, String endWord) {
        char[] charArray = currentWord.toCharArray();
        for (int i = 0; i < currentWord.length(); ++i) {
            // 先保存，然后恢复
            char originChar = charArray[i];
            for (char ch = 'a'; ch <= 'z'; ++ch) {
                if (ch == originChar) {
                    continue;
                }
                charArray[i] = ch;
                String nextWord = String.valueOf(charArray);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) {
                        return true;
                    }
                    if (!visited.contains(nextWord)) {
                        queue.offer(nextWord);
                        // 注意：添加到队列以后，必须马上标记为已经访问
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复
            charArray[i] = originChar;
        }
        return false;
    }

    /**
     * 方法二：双向广度优先遍历
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        // 第 2 步：已经访问过的 word 添加到 visited 哈希表里
        visited = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }

            // 逻辑到这里，保证 beginVisited 是相对较小的集合，nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                if (changeWordEveryOneLetter(word, endVisited, nextLevelVisited)) {
                    return step + 1;
                }
            }

            // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
            beginVisited = nextLevelVisited;
            ++step;
        }
        return 0;
    }

    /**
     * 尝试对 word 修改每一个字符，看看是不是能落在 endVisited 中，扩展得到的新的 word 添加到 nextLevelVisited 里
     */
    private boolean changeWordEveryOneLetter(String word, Set<String> endVisited, Set<String> nextLevelVisited) {
        char[] charArray = word.toCharArray();
        for (int i = 0; i < word.length(); ++i) {
            char originChar = charArray[i];
            for (char ch = 'a'; ch <= 'z'; ++ch) {
                if (originChar == ch) {
                    continue;
                }
                charArray[i] = ch;
                String nextWord = String.valueOf(charArray);
                if (wordSet.contains(nextWord)) {
                    if (endVisited.contains(nextWord)) {
                        return true;
                    }
                    if (!visited.contains(nextWord)) {
                        nextLevelVisited.add(nextWord);
                        // 标记为已访问
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复，下次再用
            charArray[i] = originChar;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().ladderLength1("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(new Solution().ladderLength2("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }
}
