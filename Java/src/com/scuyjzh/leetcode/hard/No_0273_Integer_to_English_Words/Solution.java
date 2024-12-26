package com.scuyjzh.leetcode.hard.No_0273_Integer_to_English_Words;

/**
 * 273. 整数转换英文表示
 *
 * 将非负整数 num 转换为其对应的英文表示。
 */
class Solution {
    String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = {"", "Thousand", "Million", "Billion"};

    /**
     * 方法一：递归
     *
     * • 时间复杂度：O(1)。非负整数 nums 按照 3 位一组划分最多有 4 组，分别得到每一组的英文表示，然
     *   后拼接得到整数 num 的英文表示，时间复杂度是常数。
     * • 空间复杂度：O(1)。空间复杂度主要取决于存储英文表示的字符串和递归调用栈，英文表示的长度可
     *   以看成常数，递归调用栈不会超过 3 层。
     */
    public String numberToWords1(int num) {
        /*
         * 由于非负整数 num 的最大值为 2^31-1，因此最多有 10 位数。将整数转换成英文表示中，将数字按照 3 位一
         * 组划分，将每一组的英文表示拼接之后即可得到整数 num 的英文表示。
         *
         * 每一组最多有 3 位数，可以使用递归的方式得到每一组的英文表示。根据数字所在的范围，具体做法如下：
         *   • 小于 20 的数可以直接得到其英文表示；
         *   • 大于等于 20 且小于 100 的数首先将十位转换成英文表示，然后对个位递归地转换成英文表示；
         *   • 大于等于 100 的数首先将百位转换成英文表示，然后对其余部分（十位和个位）递归地转换成英文表
         *     示。
         *
         * 从高到低的每一组的单位依次是 10^9、10^6、10^3、1，除了最低组以外，每一组都有对应的表示单位的词，分
         * 别是 “Billion"、“Million"、“Thousand"。
         *
         * 得到每一组的英文表示后，需要对每一组加上对应的表示单位的词，然后拼接得到整数 num 的英文表示。
         *
         * 具体实现中需要注意以下两点：
         *   • 只有非零的组的英文表示才会拼接到整数 num 的英文表示中；
         *   • 如果 num=0，则不适用上述做法，而是直接返回 “Zero"。
         */
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 3, unit = 1000000000; i >= 0; --i, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                StringBuffer curr = new StringBuffer();
                recursion(curr, curNum);
                curr.append(thousands[i]).append(" ");
                sb.append(curr);
            }
        }
        return sb.toString().trim();
    }

    private void recursion(StringBuffer curr, int num) {
        if (num == 0) {
            return;
        } else if (num < 10) {
            curr.append(singles[num]).append(" ");
        } else if (num < 20) {
            curr.append(teens[num - 10]).append(" ");
        } else if (num < 100) {
            curr.append(tens[num / 10]).append(" ");
            recursion(curr, num % 10);
        } else {
            curr.append(singles[num / 100]).append(" Hundred ");
            recursion(curr, num % 100);
        }
    }

    /**
     * 方法二：迭代
     *
     * • 时间复杂度：O(1)。
     * • 空间复杂度：O(1)。
     */
    public String numberToWords2(int num) {
        /*
         * 也可以使用迭代的方式得到每一组的英文表示。由于每一组最多有 3 位数，因此依次得到百位、十位、个位
         * 上的数字，生成该组的英文表示，注意只有非零位才会被添加到英文表示中。
         */
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 3, unit = 1000000000; i >= 0; --i, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                sb.append(toEnglish(curNum)).append(thousands[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    private String toEnglish(int num) {
        StringBuilder curr = new StringBuilder();
        int hundred = num / 100;
        num %= 100;
        if (hundred != 0) {
            curr.append(singles[hundred]).append(" Hundred ");
        }
        int ten = num / 10;
        if (ten >= 2) {
            curr.append(tens[ten]).append(" ");
            num %= 10;
        }
        if (num > 0 && num < 10) {
            curr.append(singles[num]).append(" ");
        } else if (num >= 10) {
            curr.append(teens[num - 10]).append(" ");
        }
        return curr.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numberToWords1(1234567891));
        System.out.println(new Solution().numberToWords1(1234567));
        System.out.println(new Solution().numberToWords2(12345));
        System.out.println(new Solution().numberToWords2(123));
    }
}
