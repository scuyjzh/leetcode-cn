package com.scuyjzh.leetcode.easy.No_0345_Reverse_Vowels_of_a_String;

/**
 * 345. 反转字符串中的元音字母
 * <p>
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 */
class Solution {
    /**
     * 方法：双指针
     * 时间复杂度：O(N)，其中 N 是字符串 s 的长度。在最坏的情况下，两个指针各遍历整个字符串一次。
     * 空间复杂度：O(1) 或 O(N)，取决于使用的语言中字符串类型的性质。如果字符串是可修改的，那么可以直接在字符串上使用双指针进行交换，空间复杂度为 O(1)，否则需要使用 O(N) 的空间将字符串临时转换为可以修改的数据结构（例如数组），空间复杂度为 O(N)。
     */
    public String reverseVowels(String s) {
        /*
         * 思路与算法：
         * 可以使用两个指针 i 和 j 对字符串相向地进行遍历。
         *
         * 具体地，指针 i 初始时指向字符串 s 的首位，指针 j 初始时指向字符串 s 的末位。在遍历的过程中，不
         * 停地将 i 向右移动，直到 i 指向一个元音字母（或者超出字符串的边界范围）；同时，不停地将 j 向左
         * 移动，直到 j 指向一个元音字母。此时，如果 i<j，那么交换 i 和 j 指向的元音字母，否则说明所有的
         * 元音字母均已遍历过，就可以退出遍历的过程。
         */
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < n && !isVowel(arr[i])) {
                ++i;
            }
            while (j > 0 && !isVowel(arr[j])) {
                --j;
            }
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