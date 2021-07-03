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
          正则表达式：^[+\\-]?\\d+（Java中需用双斜杠表示转义）
          ^   表示匹配字符串开头，此处匹配的就是 '+'、'-' 号
          []  匹配方括号内的任意字符，比如 [0-9] 就是匹配数字字符 0 - 9 中的一个
          ?   匹配之前的字符出现 >=0 次，这里用 ? 是因为 '+' 号可以省略
          \\d 匹配数字（0 - 9）
          +   匹配之前的字符出现 >=1 次，\\d+ 则匹配一串数字
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

    /**
     * 方法三：逐字匹配
     */
    public int myAtoi3(String str) {
        int len = str.length();
        // str.charAt(i) 方法回去检查下标的合法性，一般先转换成字符数组
        char[] charArray = str.toCharArray();

        // 1、去除前导空格
        int index = 0;
        while (index < len && charArray[index] == ' ') {
            index++;
        }

        // 2、如果已经遍历完成（针对极端用例 "      "）
        if (index == len) {
            return 0;
        }

        // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        int sign = 1;
        char firstChar = charArray[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // 4、将后续出现的数字字符进行转换（题目规定不能使用 long 类型）
        int res = 0;
        while (index < len) {
            char currChar = charArray[index];
            // 4.1 先判断不合法的情况
            if (currChar < '0' || currChar > '9') {
                break;
            }

            // 题目规定环境只能存储 32 位大小的有符号整数，因此，需要提前判断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            res = res * 10 + sign * (currChar - '0');
            index++;
        }
        return res;
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

        System.out.println(solution.myAtoi3("42"));
        System.out.println(solution.myAtoi3("   -42"));
        System.out.println(solution.myAtoi3("4193 with words"));
        System.out.println(solution.myAtoi3("words and 987"));
        System.out.println(solution.myAtoi3("-91283472332"));
    }
}
