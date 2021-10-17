package com.scuyjzh.leetcode.medium.No_0038_Count_and_Say;

/**
 * 38. 外观数列
 * <p>
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前
 * 一项的描述。
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 *   • countAndSay(1) = "1"
 *   • countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另
 *     一个数字字符串。
 * <p>
 * 前五项如下：
 *   1.     1
 *   2.     11
 *   3.     21
 *   4.     1211
 *   5.     111221
 *   第一项是数字 1
 *   描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 *   描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 *   描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 *   描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 * <p>
 * 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组
 * 都由连续的最多 相同字符 组成。然后对于每个组，先描述字符的数量，然
 * 后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的
 * 字符数量用数字替换，再将所有描述组连接起来。
 */
class Solution {
    /**
     * 方法：遍历生成
     * 时间复杂度：O(N×M)，其中 N 为给定的正整数，M 为生成的字符串中的最大长度。
     * 空间复杂度：O(M)。其中 M 为生成的字符串中的最大长度。
     */
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
        System.out.println(new Solution().countAndSay(1));
        System.out.println(new Solution().countAndSay(4));
    }
}
