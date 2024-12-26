package com.scuyjzh.leetcode.medium.No_1358_Number_of_Substrings_Containing_All_Three_Characters;

/**
 * 1358. 包含所有三种字符的子字符串数目
 *
 * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
 *
 * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 */
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        int[] freq = new int[3];

        int res = 0;
        // 子字符串的左右边界
        int left = 0, right = 0;
        int count = 0;
        // 循环不变量：子串 [left, right] 中 a,b,c 都 至少 出现过一次
        while (right < n) {
            int rChIndex = chs[right] - 'a';
            if (freq[rChIndex] == 0) {
                ++count;
            }
            ++freq[rChIndex];

            // 右边界 right 遍历到子字符串刚好满足 a,b,c 都至少出现过一次的位置
            while (left < right && count == 3) {
                // 从右边界 right 开始到字符串 s 结尾位置所组成的子串都满足条件，
                // 计算可以组成子串的个数 n-right，作为对答案的贡献累加到 res
                // n-1-right+1 -> n-right
                res += n - right;
                // 向右不断移动左边界 left，如果子字符串依然满足 a,b,c 都至少出现一次，累加贡献到 res
                int lChIndex = chs[left] - 'a';
                --freq[lChIndex];
                if (freq[lChIndex] == 0) {
                    --count;
                }
                ++left;
            }

            // 当子字符串不满足 a,b,c 都至少出现一次时，停止移动 left，向右移动 right，寻找下一次满足条件的右边界
            // 重复以上步骤，直到 right 遍历到字符串 s 结尾
            ++right;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numberOfSubstrings("abcabc"));
    }
}
