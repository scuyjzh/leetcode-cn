package com.scuyjzh.leetcode.medium.No_0043_Multiply_Strings;

/**
 * 43. 字符串相乘
 * <p>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 */
class Solution {
    /**
     * 方法：做乘法
     * 时间复杂度：O(mn)，其中 m 和 n 分别是 num1 和 num2 的长度。需要计算 num1 的每一位和 num2 的每一位的乘积。
     * 空间复杂度：O(m+n)，其中 m 和 n 分别是 num1 和 num2 的长度。需要创建一个长度为 m+n 的数组存储乘积。
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int m = num1.length(), n = num2.length();
        // 结果最多为 m + n 位数
        int[] res = new int[m + n];
        // 从个位数开始逐位相乘
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // num1[i] 和 num2[j] 的乘积对应的就是 res[i+j] 和 res[i+j+1] 这两个位置
                int p1 = i + j, p2 = i + j + 1;
                // 叠加到 res 上
                int sum = mul + res[p2];
                res[p2] = sum % 10;
                res[p1] += sum / 10;
            }
        }

        // 结果前缀可能存的 0（未使用的位）
        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }
        // 将计算结果转化成字符串
        StringBuilder sb = new StringBuilder();
        for (; i < res.length; i++) {
            sb.append(res[i]);
        }

        return sb.toString().length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.multiply("123", "456"));
    }
}
