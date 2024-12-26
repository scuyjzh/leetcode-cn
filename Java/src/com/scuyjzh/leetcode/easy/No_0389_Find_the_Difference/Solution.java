package com.scuyjzh.leetcode.easy.No_0389_Find_the_Difference;

/**
 * 389. 找不同
 *
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 */
class Solution {
    /**
     * 方法一：计数
     */
    public char findTheDifference1(String s, String t) {
        int[] cnt = new int[26];
        // 首先遍历字符串 s，对其中的每个字符都将计数值加 1
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            cnt[ch - 'a']++;
        }
        // 然后遍历字符串 t，对其中的每个字符都将计数值减 1
        for (int i = 0; i < t.length(); ++i) {
            char ch = t.charAt(i);
            cnt[ch - 'a']--;
            // 当发现某个字符计数值为负数时，说明该字符在字符串 t 中出现的次数大于在字符串 s 中出现的次数
            if (cnt[ch - 'a'] < 0) {
                // 因此该字符为被添加的字符
                return ch;
            }
        }
        return ' ';
    }

    /**
     * 方法二：求和
     */
    public char findTheDifference2(String s, String t) {
        int as = 0, at = 0;
        // 将字符串 s 中每个字符的 ASCII 码的值求和，得到 as
        for (int i = 0; i < s.length(); ++i) {
            as += s.charAt(i);
        }
        // 对字符串 t 同样的方法得到 at
        for (int i = 0; i < t.length(); ++i) {
            at += t.charAt(i);
        }
        // 两者的差值 at-as 即代表了被添加的字符
        return (char) (at - as);
    }

    /**
     * 方法三：位运算
     */
    public char findTheDifference3(String s, String t) {
        /*
         * 如何才能做到线性时间复杂度和常数空间复杂度呢？
         *
         * 如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符。类似于「136. 只出现一
         * 次的数字」，使用位运算的技巧解决本题。
         *
         * 对于这道题，可使用异或运算 ⊕。异或运算有以下三个性质。
         *   1.任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。
         *   2.任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
         *   3.异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
         */
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            ret ^= t.charAt(i);
        }
        return (char) ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findTheDifference1("abcd", "abcde"));
        System.out.println(new Solution().findTheDifference2("a", "aa"));
        System.out.println(new Solution().findTheDifference3("ae", "aea"));
    }
}
