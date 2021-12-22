package com.scuyjzh.leetcode.hard.No_0126_Word_Ladder_II;

import java.util.*;

/**
 * 126. 单词接龙 II
 *
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表
 * 示此过程的 转换序列 是形式上像 beginWord -> s_1 -> s_2 -> ... -> s_k
 * 这样的单词序列，并满足：
 *   • 每对相邻的单词之间仅有单个字母不同。
 *   • 转换过程中的每个单词 s_i（1 <= i <= k）必须是字典 wordList
 *     中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 *   • s_k == endWord
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你
 * 找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在
 * 这样的转换序列，返回一个空列表。每个序列都应该以单词列表
 * [beginWord, s_1, s_2, ..., s_k] 的形式返回。
 */
class Solution {
    private List<List<String>> res;
    private Set<String> wordSet;
    private Map<String, Integer> steps;
    private Map<String, Set<String>> from;

    private String beginWord;
    private String endWord;
    private int wl;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        res = new ArrayList<>();
        // 因为需要快速判断扩展出的单词是否在 wordList 里，因此需要将 wordList 存入哈希表
        wordSet = new HashSet<>(wordList);
        // 特殊用例判断
        if (!wordSet.contains(endWord)) {
            return res;
        }
        // 因为从 beginWord 开始扩展，因此 wordSet 里一定不可以有 beginWord
        wordSet.remove(beginWord);

        // 第 1 步：广度优先遍历构建图
        // 为了避免记录不需要的边，需要记录扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先遍历的第几层
        // steps 记录了扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先遍历的第几层
        steps = new HashMap<>();
        steps.put(beginWord, 0);
        // from 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，这些单词可以变换到 key ，它们是一对多关系，dfs 的时候会用到
        from = new HashMap<>();

        this.beginWord = beginWord;
        this.endWord = endWord;
        wl = beginWord.length();

        boolean found = bfs();

        // 第 2 步：深度优先遍历找到所有解，从 endWord 恢复到 beginWord ，所以每次尝试操作 path 列表的头部
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            dfs(path, endWord);
        }

        return res;
    }

    private boolean bfs() {
        int step = 0;
        boolean found = false;

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            ++step;
            int currSize = queue.size();
            for (int i = 0; i < currSize; ++i) {
                String currWord = queue.poll();
                char[] charArray = currWord.toCharArray();
                for (int j = 0; j < wl; ++j) {
                    char originChar = charArray[j];
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        // 将每一位逐个替换成 26 个英文小写字母
                        charArray[j] = ch;
                        String nextWord = String.valueOf(charArray);

                        // 注意：这几行代码的逻辑先后顺序
                        if (steps.containsKey(nextWord) && steps.get(nextWord) == step) {
                            from.get(nextWord).add(currWord);
                        }

                        if (!wordSet.contains(nextWord)) {
                            continue;
                        }
                        // 剪枝，如果从一个单词扩展出来的单词以前遍历过，距离一定更远
                        // 为了避免搜索到已经遍历到，且距离更远的单词，需要将它从 wordSet 中删除
                        wordSet.remove(nextWord);

                        // 这一层扩展出的单词进入队列
                        queue.offer(nextWord);

                        // 记录 nextWord 从 currWord 而来
                        from.putIfAbsent(nextWord, new HashSet<>());
                        from.get(nextWord).add(currWord);
                        // 记录 nextWord 的 step
                        steps.put(nextWord, step);
                        if (nextWord.equals(endWord)) {
                            // 注意：由于有多条路径到达 endWord 找到以后不能立即退出，只需要设置 found = true 即可
                            found = true;
                        }
                    }
                    charArray[j] = originChar;
                }
            }
            if (found) {
                break;
            }
        }
        return found;
    }

    private void dfs(Deque<String> path, String currWord) {
        if (currWord.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String precursor : from.get(currWord)) {
            path.addFirst(precursor);
            dfs(path, precursor);
            path.removeFirst();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }
}
