package com.scuyjzh.leetcode.easy.No_0387_First_Unique_Character_in_a_String;

import java.util.*;

/**
 * 387. 字符串中的第一个唯一字符
 * <p>
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 */
class Solution {
    /**
     * 方法一：使用哈希表存储频数
     * 时间复杂度：O(N)，其中 N 是字符串 s 的长度。需要进行两次遍历。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。需要 O(∣Σ∣) 的空间存储哈希映射。
     */
    public int firstUniqChar1(String s) {
        /*
         * 思路与算法：
         * 可以对字符串进行两次遍历。
         *
         * 在第一次遍历时，使用哈希映射统计出字符串中每个字符出现的次数。在第二次遍历时，只要遍历
         * 到了一个只出现一次的字符，那么就返回它的索引，否则在遍历结束后返回 −1。
         */
        Map<Character, Integer> frequency = new HashMap<>(26);
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法二：使用哈希表存储索引
     * 时间复杂度：O(N)，其中 N 是字符串 s 的长度。第一次遍历字符串的时间复杂度为 O(N)，第二次遍历哈希映射的时间复杂度为 O(∣Σ∣)，由于 s 包含的字符种类数一定小于 s 的长度，因此 O(∣Σ∣) 在渐进意义下小于 O(N)，可以忽略。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。需要 O(∣Σ∣) 的空间存储哈希映射。
     */
    public int firstUniqChar2(String s) {
        /*
         * 思路与算法：
         * 可以对方法一进行修改，使得第二次遍历的对象从字符串变为哈希映射。
         *
         * 具体地，对于哈希映射中的每一个键值对，键表示一个字符，值表示它的首次出现的索引（如果该字符只出
         * 现一次）或者 −1（如果该字符出现多次）。当第一次遍历字符串时，设当前遍历到的字符为 c，如果 c
         * 不在哈希映射中，就将 c 与它的索引作为一个键值对加入哈希映射中，否则将 c 在哈希映射中对应
         * 的值修改为 −1。
         *
         * 在第一次遍历结束后，只需要再遍历一次哈希映射中的所有值，找出其中不为 −1 的最小值，即为第一
         * 个不重复字符的索引。如果哈希映射中的所有值均为 −1，就返回 −1。
         */
        Map<Character, Integer> position = new HashMap<>(26);
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (position.containsKey(ch)) {
                position.put(ch, -1);
            } else {
                position.put(ch, i);
            }
        }
        int first = n;
        for (Map.Entry<Character, Integer> entry : position.entrySet()) {
            int pos = entry.getValue();
            if (pos != -1 && pos < first) {
                first = pos;
            }
        }
        if (first == n) {
            first = -1;
        }
        return first;
    }

    /**
     * 方法三：队列
     * 时间复杂度：O(N)，其中 N 是字符串 s 的长度。遍历字符串的时间复杂度为 O(N)，而在遍历的过程中还维护了一个队列，由于每一个字符最多只会被放入和弹出队列最多各一次，因此维护队列的总时间复杂度为 O(∣Σ∣)，由于 s 包含的字符种类数一定小于 s 的长度，因此 O(∣Σ∣) 在渐进意义下小于 O(N)，可以忽略。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。需要 O(∣Σ∣) 的空间存储哈希映射以及队列。
     */
    public int firstUniqChar3(String s) {
        /*
         * 思路与算法：
         * 也可以借助队列找到第一个不重复的字符。队列具有「先进先出」的性质，因此很适合用来找出第一个
         * 满足某个条件的元素。
         *
         * 具体地，使用与方法二相同的哈希映射，并且使用一个额外的队列，按照顺序存储每一个字符以及它们
         * 第一次出现的位置。当对字符串进行遍历时，设当前遍历到的字符为 c，如果 c 不在哈希映射中，
         * 就将 c 与它的索引作为一个二元组放入队尾，否则就需要检查队列中的元素是否都满足「只出现一次」
         * 的要求，即不断地根据哈希映射中存储的值（是否为 −1）选择弹出队首的元素，直到队首元素「真
         * 的」只出现了一次或者队列为空。
         *
         * 在遍历完成后，如果队列为空，说明没有不重复的字符，返回 −1，否则队首的元素即为第一个不重复的字
         * 符以及其索引的二元组。
         *
         * 小贴士：
         * 在维护队列时，使用了「延迟删除」这一技巧。也就是说，即使队列中有一些字符出现了超过一次，但
         * 它只要不位于队首，那么就不会对答案造成影响，也就可以不用去删除它。只有当它前面的所有字符被
         * 移出队列，它成为队首时，才需要将它移除。
         */
        Map<Character, Integer> position = new HashMap<>(26);
        Queue<Pair> queue = new LinkedList<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (!position.containsKey(ch)) {
                position.put(ch, i);
                queue.offer(new Pair(ch, i));
            } else {
                position.put(ch, -1);
                while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.poll().pos;
    }

    class Pair {
        char ch;
        int pos;

        Pair(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().firstUniqChar1("leetcode"));
        System.out.println(new Solution().firstUniqChar2("loveleetcode"));
        System.out.println(new Solution().firstUniqChar3("loveleetcode"));
    }
}