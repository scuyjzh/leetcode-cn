package com.scuyjzh.leetcode.medium.No_0038_Count_and_Say;

/**
 * 38. 外观数列
 * <p>
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 *   • countAndSay(1) = "1"
 *   • countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 */
class Solution {
    public String countAndSay(int n) {
        String res = "1";
        // 特判
        if (n == 1) {
            return res;
        }
        // 第1层循环，循环中的第i次将生成 n=i+1 时的外观数列
        for (int i = 1; i < n; ++i) {
            char target = res.charAt(0);
            int count = 0;
            StringBuilder sb = new StringBuilder();
            // 第2层循环，循环中的第j次将扫描元素 res.charAt(j)
            for (int j = 0; j < res.length(); ++j) {
                if (res.charAt(j) != target) {
                    sb.append(count).append(target);
                    // 出现新的数字，重置 target 和 count
                    target = res.charAt(j);
                    count = 1;
                } else {
                    count++;
                }
            }
            // 边界处理
            res = sb.append(count).append(target).toString();
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countAndSay(1));
        System.out.println(solution.countAndSay(2));
        System.out.println(solution.countAndSay(3));
        System.out.println(solution.countAndSay(4));
        System.out.println(solution.countAndSay(5));
    }
}
