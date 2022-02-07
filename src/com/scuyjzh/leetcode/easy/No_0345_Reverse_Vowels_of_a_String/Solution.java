package com.scuyjzh.leetcode.easy.No_0345_Reverse_Vowels_of_a_String;

/**
 * 345. 反转字符串中的元音字母
 *
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        // 使用两个指针 i 和 j 对字符串相向地进行遍历
        // 指针 i 初始时指向字符串 s 的首位，指针 j 初始时指向字符串 s 的末位
        int i = 0, j = n - 1;
        while (i < j) {
            // 在遍历的过程中，不停地将 i 向右移动，直到 i 指向一个元音字母（或者超出字符串的边界范围）
            while (i < n && !isVowel(arr[i])) {
                ++i;
            }
            // 同时，不停地将 j 向左移动，直到 j 指向一个元音字母
            while (j > 0 && !isVowel(arr[j])) {
                --j;
            }
            // 此时，如果 i<j，那么交换 i 和 j 指向的元音字母，否则说明所有的元音字母均已遍历过，就可以退出遍历的过程
            if (i < j) {
                swap(arr, i, j);
                ++i;
                --j;
            }
        }
        return new String(arr);
    }

    private boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseVowels("hello"));
        System.out.println(new Solution().reverseVowels("leetcode"));
    }
}
