package com.scuyjzh.leetcode.medium.No_0012_Integer_to_Roman;

/**
 * 12. 整数转罗马数字
 *
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *   字符          数值
 *   I             1
 *   V             5
 *   X             10
 *   L             50
 *   C             100
 *   D             500
 *   M             1000
 * 例如，罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即
 * 为X+II。27 写做XXVII, 即为XX+V+II。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如
 * 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数
 * 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只
 * 适用于以下六种情况：
 *   • I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 *   • X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 *   • C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给你一个整数，将其转为罗马数字。
 * 提示：
 *   • 1 <= num <= 3999
 */
class Solution {
    /**
     * 方法一：模拟
     *
     * • 时间复杂度：O(1)。由于 valueSymbols 长度是固定的，且这 13 字符中的每个字符的出现次数均不会超
     *   过 3，因此循环次数有一个确定的上限。对于本题给出的数据范围，循环次数不会超过 15 次。
     * • 空间复杂度：O(1)。
     */
    public String intToRoman1(int num) {
        /*
         * 前言：
         * 罗马数字符号
         * 罗马数字由 7 个不同的单字母符号组成，每个符号对应一个具体的数值。此外，减法规则（如问题描述中所
         * 述）给出了额外的 6 个复合符号。这给了总共 13 个独特的符号（每个符号由 1 个或 2 个字母组成）。
         *   字符          数值
         *   I             1
         *   V             5
         *   X             10
         *   L             50
         *   C             100
         *   D             500
         *   M             1000
         *   CM            900
         *   CD            400
         *   XC            90
         *   XL            40
         *   IX            9
         *   IV            4
         *
         * 罗马数字的唯一表示法
         * 用来确定罗马数字的规则是：对于罗马数字从左到右的每一位，选择尽可能大的符号值。对于 140，最
         * 大可以选择的符号值为 C=100。接下来，对于剩余的数字 40，最大可以选择的符号值为 XL=40。因此，
         * 140 的对应的罗马数字为 C+XL=CXL。
         *
         * 思路：
         * 根据罗马数字的唯一表示法，为了表示一个给定的整数 num，寻找不超过 num 的最大符号值，将 num
         * 减去该符号值，然后继续寻找不超过 num 的最大符号值，将该符号拼接在上一个找到的符号之后，循环直至
         * num 为 0。最后得到的字符串即为 num 的罗马数字表示。
         *
         * 编程时，可以建立一个数值-符号对的列表 valueSymbols，按数值从大到小排列。遍历 valueSymbols 中的每
         * 个数值-符号对，若当前数值 value 不超过 num，则从 num 中不断减去 value，直至 num 小于 value，然后遍
         * 历下一个数值-符号对。若遍历中 num 为 0 则跳出循环。
         */
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    /**
     * 方法二：硬编码数字
     *
     * • 时间复杂度：O(1)。计算量与输入数字的大小无关。
     * • 空间复杂度：O(1)。
     */
    public String intToRoman2(int num) {
        /*
         * 思路：
         * 回顾前言中列出的这 13 个符号，可以发现：
         *   • 千位数字只能由 M 表示；
         *   • 百位数字只能由 C，CD，D 和 CM 表示；
         *   • 十位数字只能由 X，XL，L 和 XC 表示；
         *   • 个位数字只能由 I，IV，V 和 IX 表示。
         *
         * 这恰好把这 13 个符号分为四组，且组与组之间没有公共的符号。因此，整数 num 的十进制表示中的每一个
         * 数字都是可以单独处理的。
         *
         * 进一步地，可以计算出每个数字在每个位上的表示形式，整理成一张硬编码表。其中 0 对应的是空字符串。
         *
         * 利用模运算和除法运算，可以得到 num 每个位上的数字：
         *   thousands_digit = num / 1000
         *   hundreds_digit = (num % 1000) / 100
         *   tens_digit = (num % 100) / 10
         *   ones_digit = num % 10
         *
         * 最后，根据 num 每个位上的数字，在硬编码表中查找对应的罗马字符，并将结果拼接在一起，即为 num 对
         * 应的罗马数字。
         */
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().intToRoman1(3));
        System.out.println(new Solution().intToRoman1(4));
        System.out.println(new Solution().intToRoman1(9));
        System.out.println(new Solution().intToRoman2(58));
        System.out.println(new Solution().intToRoman2(1994));
    }
}
