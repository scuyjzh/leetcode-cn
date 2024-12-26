package com.scuyjzh.leetcode.medium.No_0388_Longest_Absolute_File_Path;

import java.util.*;

/**
 * 388. 文件的最长绝对路径
 *
 * https://leetcode-cn.com/problems/longest-absolute-file-path/
 */
class Solution {
    public int lengthLongestPath(String input) {
        String[] files = input.split("\n");
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        // 根目录
        stack.push(0);
        for (String file : files) {
            int depth = 1;
            int k = 0;
            while (k < file.length() && file.charAt(k) == '\t') {
                ++depth;
                ++k;
            }
            int curLength = file.length() - k;
            while (stack.size() > depth) {
                stack.pop();
            }
            if (file.indexOf('.') >= 0) {
                res = Math.max(res, stack.peek() + curLength);
            } else {
                stack.push(stack.peek() + curLength + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(new Solution().lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
        System.out.println(new Solution().lengthLongestPath("a"));
        System.out.println(new Solution().lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt"));
    }
}
