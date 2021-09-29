package com.scuyjzh.leetcode.easy.No_0344_Reverse_String;

/**
 * 344. 反转字符串
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 */
class Solution {
    /**
     * 方法：双指针
     * 时间复杂度：O(N)，其中 N 为字符数组的长度。一共执行了 N/2 次的交换。
     * 空间复杂度：O(1)。只使用了常数空间来存放若干变量。
     */
    public void reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

    public static void main(String[] args) {
        char[] s = "hello".toCharArray();
        new Solution().reverseString(s);
        System.out.println(s);
    }
}