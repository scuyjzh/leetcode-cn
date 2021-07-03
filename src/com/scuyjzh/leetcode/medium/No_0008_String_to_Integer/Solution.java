package com.scuyjzh.leetcode.medium.No_0008_String_to_Integer;

import java.util.*;
import java.util.regex.*;

/**
 * 8. 字符串转换整数 (atoi)
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 */
class Solution {
    class Automaton {
        public int sign = 1;
        public long ans = 0;
        private String state = "start";
        /*
          用下面的表格来表示自动机：
                      ' '     +/-     number      other
          start       start   signed  in_number   end
          signed      end     end     in_number   end
          in_number   end     end     in_number   end
          end         end     end     end         end
         */
        private Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};

        public void get(char c) {
            state = table.get(state)[get_col(c)];
            if ("in_number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }
    }

    /**
     * 方法一：自动机
     * 时间复杂度：O(n)，其中 n 为字符串的长度。我们只需要依次处理所有的字符，处理每个字符需要的时间为 O(1)。
     * 空间复杂度：O(1)。自动机的状态只需要常数空间存储。
     */
    public int myAtoi1(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

    /**
     * 方法二：正则表达式
     */
    public int myAtoi2(String str) {
        // 清空字符串开头和末尾的空格（这是trim方法功能，事实上只需清空开头的空格）
        // 注意：trim()不能去除全角空格，只能去除半角空格
        str = str.trim();

        /*
          Java正则表达式：^[+\\-]?\\d+
          ^   表示匹配字符串开头，此处匹配的就是 '+'、'-' 号
          []  表示匹配其中包含的任一字符，比如[0-9]就是匹配数字字符 0 - 9 中的一个
          ?   表示前面一个字符出现零次或者一次，这里用 ? 是因为 '+' 号可以省略
          \\d 表示数字 0 - 9 范围
          +   表示前面一个字符出现一次或者多次，\\d+ 合一起就能匹配一连串数字了
         */
        Pattern p = Pattern.compile("^[+-]?\\d+");
        Matcher m = p.matcher(str);
        int value = 0;
        // 判断是否能匹配到
        if (m.find()) {
            // 字符串转整数，溢出异常捕获
            try {
                value = Integer.parseInt(str.substring(m.start(), m.end()));
            } catch (Exception e) {
                // 判断正负
                value = str.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        return value;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myAtoi1("42"));
        System.out.println(solution.myAtoi1("   -42"));
        System.out.println(solution.myAtoi1("4193 with words"));
        System.out.println(solution.myAtoi1("words and 987"));
        System.out.println(solution.myAtoi1("-91283472332"));

        System.out.println(solution.myAtoi2("42"));
        System.out.println(solution.myAtoi2("   -42"));
        System.out.println(solution.myAtoi2("4193 with words"));
        System.out.println(solution.myAtoi2("words and 987"));
        System.out.println(solution.myAtoi2("-91283472332"));
    }
}
