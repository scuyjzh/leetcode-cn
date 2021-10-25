package com.scuyjzh.leetcode.easy.No_0013_Roman_to_Integer;

/**
 * 13. 罗马数字转整数
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
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 */
class Solution {
    /**
     * 方法：模拟
     *
     * • 时间复杂度：O(n)，其中 n 是字符串 s 的长度。
     * • 空间复杂度：O(1)。
     */
    public int romanToInt(String s) {
        /*
         * 思路：
         * 通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视
         * 作一个单独的值，累加每个字符对应的数值即可。
         * 例如 XXVII 可视作 X+X+V+I+I=10+10+5+1+1=27。
         *
         * 若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个
         * 字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。
         * 例如 XIV 可视作 X−I+V=10−1+5=14。
         */
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); ++i) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().romanToInt("III"));
        System.out.println(new Solution().romanToInt("IV"));
        System.out.println(new Solution().romanToInt("IX"));
        System.out.println(new Solution().romanToInt("LVIII"));
    }
}
