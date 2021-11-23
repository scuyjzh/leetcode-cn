package com.scuyjzh.leetcode.medium.No_0008_String_to_Integer;

import java.util.*;
import java.util.regex.*;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32
 * 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数 myAtoi(string s) 的算法如下：
 *   • 读入字符串并丢弃无用的前导空格
 *   • 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字
 *     符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存
 *     在，则假定结果为正。
 *   • 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。
 *     字符串的其余部分将被忽略。
 *   • 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032"
 *     -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从
 *     步骤 2 开始）。
 *   • 如果整数数超过 32 位有符号整数范围 [−2^31, 2^31− 1] ，需
 *     要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31
 *     的整数应该被固定为 −2^31 ，大于 2^31− 1 的整数应该被固定为
 *     2^31− 1 。
 *   • 返回整数作为最终结果。
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
            state = table.get(state)[getCol(c)];
            if ("in_number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int getCol(char c) {
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
     */
    public int myAtoi1(String s) {
        Automaton automaton = new Automaton();
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(s.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

    /**
     * 方法二：正则表达式
     */
    public int myAtoi2(String s) {
        // 清空字符串开头和末尾的空格（这是trim方法功能，事实上只需清空开头的空格）
        // 注意：trim()不能去除全角空格，只能去除半角空格
        s = s.trim();

        /*
         * 正则表达式：^[+\\-]?\\d+（Java中需用双斜杠表示转义）
         *
         *   ^      表示匹配字符串开头，此处匹配的就是 '+'、'-' 号
         *   []     匹配方括号内的任意字符，比如 [0-9] 就是匹配数字字符 0-9 中的一个
         *   ?      匹配之前的字符出现 >=0 次，这里用 ? 是因为 '+' 号可以省略
         *   \\d    匹配数字 0-9
         *   +      匹配之前的字符出现 >=1 次，\\d+ 则匹配一串数字
         */
        Pattern p = Pattern.compile("^[+-]?\\d+");
        Matcher m = p.matcher(s);
        int value = 0;
        // 判断是否能匹配到
        if (m.find()) {
            // 字符串转整数，溢出异常捕获
            try {
                value = Integer.parseInt(s.substring(m.start(), m.end()));
            } catch (Exception e) {
                // 判断正负
                value = s.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        return value;
    }

    /**
     * 方法三：逐字匹配
     */
    public int myAtoi3(String str) {
        int len = str.length();

        // 1、去除前导空格
        int index = 0;
        while (index < len && str.charAt(index) == ' ') {
            index++;
        }

        // 2、如果已经遍历结束
        if (index == len) {
            return 0;
        }

        // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        int sign = 1;
        char firstChar = str.charAt(index);
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // 4、将后续出现的数字字符进行转换（题目规定不能使用 long 类型）
        int res = 0;
        while (index < len) {
            char currChar = str.charAt(index);
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
