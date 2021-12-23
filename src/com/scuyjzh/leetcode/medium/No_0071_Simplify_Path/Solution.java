package com.scuyjzh.leetcode.medium.No_0071_Simplify_Path;

import java.util.*;

/**
 * 71. 简化路径
 *
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径
 * （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 *
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个
 * 点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂
 * 相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜
 * 杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文
 * 件/目录名称。
 *
 * 请注意，返回的 规范路径 必须遵循下述格式：
 *   • 始终以斜杠 '/' 开头。
 *   • 两个目录名之间必须只有一个斜杠 '/' 。
 *   • 最后一个目录名（如果存在）不能 以 '/' 结尾。
 *   • 此外，路径仅包含从根目录到目标文件或目录的路径上的目录
 *     （即，不含 '.' 或 '..'）。
 *
 * 返回简化后得到的 规范路径 。
 */
class Solution {
    /**
     * 方法一：栈
     */
    public String simplifyPath1(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String dir : path.split("/")) {
            if ("".equals(dir) || ".".equals(dir)) {
                continue;
            } else if ("..".equals(dir)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }
        return "/" + String.join("/", stack);
    }

    /**
     * 方法二：数组模拟栈
     */
    public String simplifyPath2(String path) {
        String[] dirs = path.split("/");
        String[] stack = new String[dirs.length - 1];
        int ptr = 0;
        for (String dir : dirs) {
            if ("".equals(dir) || ".".equals(dir)) {
                continue;
            } else if ("..".equals(dir)) {
                if (ptr > 0) {
                    --ptr;
                }
            } else {
                stack[ptr++] = dir;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ptr; ++i) {
            res.append("/");
            res.append(stack[i]);
        }
        return res.length() == 0 ? "/" : res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().simplifyPath1("/home/"));
        System.out.println(new Solution().simplifyPath1("/../"));
        System.out.println(new Solution().simplifyPath2("/home//foo/"));
        System.out.println(new Solution().simplifyPath2("/a/./b/../../c/"));
    }
}
