package com.scuyjzh.leetcode.medium.No_0767_Reorganize_String;

import java.util.*;

/**
 * 767. 重构字符串
 * <p>
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符
 * 不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 */
class Solution {
    /*
     * 前言：
     * 这道题是典型的使用贪心思想的题。重构字符串时，需要根据每个字母在字符串中出现的次数处理每个字母
     * 放置的位置。如果出现次数最多的字母可以在重新排布之后不相邻，则可以重新排布字母使得相邻的字母都
     * 不相同。如果出现次数最多的字母过多，则无法重新排布字母使得相邻的字母都不相同。
     *
     * 假设字符串的长度为 n，如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现多少次？
     *
     * 当 n 是偶数时，有 n/2 个偶数下标和 n/2 个奇数下标，因此每个字母的出现次数都不能超过 n/2 次，否则
     * 出现次数最多的字母一定会出现相邻。
     *
     * 当 n 是奇数时，由于共有 (n+1)/2 个偶数下标，因此每个字母的出现次数都不能超过 (n+1)/2 次，否则出
     * 现次数最多的字母一定会出现相邻。
     *
     * 由于当 n 是偶数时，在整数除法下满足 n/2 和 (n+1)/2 相等，因此可以合并 n 是偶数与 n 是奇数的情况：
     * 如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现 (n+1)/2 次。
     *
     * 因此首先遍历字符串并统计每个字母的出现次数，如果存在一个字母的出现次数大于 (n+1)/2，则无法重新
     * 排布字母使得相邻的字母都不相同，返回空字符串。如果所有字母的出现次数都不超过 (n+1)/2，则考虑如
     * 何重新排布字母。
     *
     * 以下提供两种使用贪心的方法，分别基于最大堆和计数。
     */

    /**
     * 方法一：基于最大堆的贪心
     * • 时间复杂度：O(nlog∣Σ∣+∣Σ∣)，其中 n 是字符串的长度，Σ 是字符集，在本题中字符集为所有小写字
     *   母，∣Σ∣=26。
     * • 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=26。
     */
    public String reorganizeString1(String s) {
        /*
         * 维护最大堆存储字母，堆顶元素为出现次数最多的字母。首先统计每个字母的出现次数，然后将出现次数大
         * 于 0 的字母加入最大堆。
         *
         * 当最大堆的元素个数大于 1 时，每次从最大堆取出两个字母，拼接到重构的字符串，然后将两个字母的出现
         * 次数分别减 1，并将剩余出现次数大于 0 的字母重新加入最大堆。由于最大堆中的元素都是不同的，因此取
         * 出的两个字母一定也是不同的，将两个不同的字母拼接到重构的字符串，可以确保相邻的字母都不相同。
         *
         * 如果最大堆变成空，则已经完成字符串的重构。如果最大堆剩下 1 个元素，则取出最后一个字母，拼接到重
         * 构的字符串。
         *
         * 对于长度为 n 的字符串，共有 n/2 次每次从最大堆取出两个字母的操作，当 n 是奇数时，还有一次从最大堆
         * 取出一个字母的操作，因此重构的字符串的长度一定是 n。
         *
         * 当 n 是奇数时，是否可能出现重构的字符串的最后两个字母相同的情况？如果最后一个字母在整个字符串中
         * 至少出现了 2 次，则在最后一次从最大堆取出两个字母时，该字母会先被选出，因此不会成为重构的字符串
         * 的倒数第二个字母，也不可能出现重构的字符串最后两个字母相同的情况。
         *
         * 因此，在重构字符串可行的情况下，基于最大堆的贪心可以确保得到正确答案。
         */
        if (s.length() < 2) {
            return s;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            char ch = s.charAt(i);
            counts[ch - 'a']++;
            maxCount = Math.max(maxCount, counts[ch - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> queue = new PriorityQueue<>((letter1, letter2) -> counts[letter2 - 'a'] - counts[letter1 - 'a']);
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            if (counts[ch - 'a'] > 0) {
                queue.offer(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * 方法二：基于计数的贪心
     * • 时间复杂度：O(n+∣Σ∣)，其中 n 是字符串的长度，Σ 是字符集，在本题中字符集为所有小写字母，
     *   ∣Σ∣=26。
     *   遍历字符串并统计每个字母的出现次数，时间复杂度是 O(n)。
     *   重构字符串需要进行 n 次放置字母的操作，并遍历每个字母得到出现次数，时间复杂度是 O(n+∣Σ∣)
     *   。
     *   总时间复杂度是 O(n+∣Σ∣)。
     * • 空间复杂度：O(∣Σ∣)，其中 n 是字符串的长度，Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=
     *   26。这里不计算存储最终答案字符串需要的空间（以及由于语言特性，在构造字符串时需要的额外缓存
     *   空间），空间复杂度主要取决于统计每个字母出现次数的数组和优先队列。空间复杂度主要取决于统计
     *   每个字母出现次数的数组。
     */
    public String reorganizeString2(String s) {
        /*
         * 首先统计每个字母的出现次数，然后根据每个字母的出现次数重构字符串。
         *
         * 当 n 是奇数且出现最多的字母的出现次数是 (n+1)/2 时，出现次数最多的字母必须全部放置在偶数下标，
         * 否则一定会出现相邻的字母相同的情况。其余情况下，每个字母放置在偶数下标或者奇数下标都是可行的。
         *
         * 维护偶数下标 evenIndex 和奇数下标 oddIndex，初始值分别为 0 和 1。遍历每个字母，根据每个字母的出现
         * 次数判断字母应该放置在偶数下标还是奇数下标。
         *
         * 首先考虑是否可以放置在奇数下标。根据上述分析可知，只要字母的出现次数不超过字符串的长度的一半
         * （即出现次数小于或等于 n/2），就可以放置在奇数下标，只有当字母的出现次数超过字符串的长度的一半
         * 时，才必须放置在偶数下标。字母的出现次数超过字符串的长度的一半只可能发生在 n 是奇数的情况下，且
         * 最多只有一个字母的出现次数会超过字符串的长度的一半。
         *
         * 因此通过如下操作在重构的字符串中放置字母。
         *   • 如果字母的出现次数大于 0 且小于或等于 n/2，且 oddIndex 没有超出数组下标范围，则将字母放置在
         *     oddIndex，然后将 oddIndex 的值加 2。
         *   • 如果字母的出现次数大于 n/2，或 oddIndex 超出数组下标范围，则将字母放置在 evenIndex，然后将
         *     evenIndex 的值加 2。
         *
         * 如果一个字母出现了多次，则重复上述操作，直到该字母全部放置完毕。
         *
         * 那么上述做法是否可以确保相邻的字母都不相同？考虑以下三种情况。
         *   • 如果 n 是奇数且存在一个字母的出现次数为 (n+1)/2，则该字母全部被放置在偶数下标，其余的
         *     (n−1)/2 个字母都被放置在奇数下标，因此相邻的字母一定不相同。
         *   • 如果同一个字母全部被放置在奇数下标或全部被放置在偶数下标，则该字母不可能在相邻的下标出现。
         *   • 如果同一个字母先被放置在奇数下标直到奇数下标超出数组下标范围，然后被放置在偶数下标，由于该
         *     字母的出现次数不会超过 n/2，因此该字母的最小奇数下标与最大偶数下标之差不小于 3，不可能在相
         *     邻的下标出现。
         */
        if (s.length() < 2) {
            return s;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            char ch = s.charAt(i);
            counts[ch - 'a']++;
            maxCount = Math.max(maxCount, counts[ch - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; ++i) {
            char ch = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = ch;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = ch;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reorganizeString1("aab"));
        System.out.println(new Solution().reorganizeString2("aaab"));
    }
}
