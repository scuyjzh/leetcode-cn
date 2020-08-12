package com.scuyjzh.leetcode.medium.No_071_Simplify_Path;

import java.util.*;

class Solution {
    /**
     * Approach #1 (Array)
     */
    public String simplifyPath1(String path) {
        String[] dir = path.split("/");
        String[] stack = new String[dir.length - 1];
        int ptr = 0;
        for (int i = 0; i < dir.length; ++i) {
            if (dir[i].equals("") || dir[i].equals(".")) {
                continue;
            } else if (dir[i].equals("..")) {
                if (ptr > 0) {
                    ptr--;
                }
            } else {
                stack[ptr++] = dir[i];
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ptr; i++) {
            result.append("/");
            result.append(stack[i]);
        }
        return result.length() == 0 ? "/" : result.toString();
    }

    /**
     * Approach #2 (Stack)
     */
    public String simplifyPath2(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String s : path.split("/")) {
            if (s.equals("") || s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else {
                stack.addLast(s);
            }
        }
        return "/" + String.join("/", stack);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.simplifyPath1("/abc/..."));
        System.out.println(solution.simplifyPath2("/a/b/c"));
    }
}
