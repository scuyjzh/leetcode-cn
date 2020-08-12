package com.scuyjzh.leetcode.easy.No_344_Reverse_String;

class Solution {
    /**
     * Approach #1 (Iterative Swapping Using Two Pointers)
     */
    public String reverseString1(String s) {
        char[] chs = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            chs[i] = (char) (chs[i] ^ chs[j]);
            chs[j] = (char) (chs[i] ^ chs[j]);
            chs[i] = (char) (chs[i] ^ chs[j]);
            i++;
            j--;
        }
        return new String(chs);
    }

    /**
     * Approach #2 (Iterative Swapping Using Two Pointers)
     */
    public String reverseString2(String s) {
        byte[] bytes = s.getBytes();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            bytes[i] = (byte) (bytes[i] ^ bytes[j]);
            bytes[j] = (byte) (bytes[i] ^ bytes[j]);
            bytes[i] = (byte) (bytes[i] ^ bytes[j]);
            i++;
            j--;
        }
        return new String(bytes);
    }

    /**
     * Approach #3 (Using Java Library)
     */
    public String reverseString4(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Approach #4 (Recursion - Divide and Conquer)
     */
    public String reverseString3(String s) {
        return helper(s);
    }

    private String helper(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        String leftStr = s.substring(0, length / 2);
        String rightStr = s.substring(length / 2, length);
        return helper(rightStr) + helper(leftStr);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseString1("hello"));
        System.out.println(solution.reverseString2("hello"));
    }
}
