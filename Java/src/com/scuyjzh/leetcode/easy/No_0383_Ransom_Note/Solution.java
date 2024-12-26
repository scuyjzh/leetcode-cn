package com.scuyjzh.leetcode.easy.No_0383_Ransom_Note;

/**
 * 383. 赎金信
 * <p>
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。
 * 如果可以构成，返回 true ；否则返回 false。
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
 * 提示：
 *   • 你可以假设两个字符串均只含有小写字母。
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        // 新建小写字母频次统计数组，0-25 分别代表 a-z
        int[] charCountRN = new int[26];
        int[] charCountM = new int[26];
        // 将 String 转化成 char[] 可以加速程序，因为 String.charAt() 每次调用都会检查下标是否越界
        char[] charArrayRN = ransomNote.toCharArray();
        char[] charArrayM = magazine.toCharArray();
        // 统计救赎信的各个字母出现次数
        for (char c : charArrayRN) {
            charCountRN[c - 'a']++;
        }
        // 统计杂志的各个字母出现次数
        for (char c : charArrayM) {
            charCountM[c - 'a']++;
        }
        // 对每个字母的出现次数进行比较
        for (int i = 0; i < 26; ++i) {
            if (charCountRN[i] > charCountM[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canConstruct("a", "b"));
        System.out.println(new Solution().canConstruct("aa", "ab"));
        System.out.println(new Solution().canConstruct("aa", "aab"));
    }
}