package com.scuyjzh.leetcode.medium.No_005_Longest_Palindromic_Substring;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * @author scuyjzh
 * @date 2020/8/20 9:45
 */
class Solution {
    /**
     * 方法一：暴力匹配
     * 时间复杂度：O(N^3)，这里 N 是字符串的长度，枚举字符串的左边界、右边界，然后继续验证子串是否是回文子串，这三种操作都与 N 相关
     * 空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关
     */
    public String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组，这一步非必需
        char[] charArray = s.toCharArray();

        // 枚举所有长度严格大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 方法二：动态规划
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N^2)，二维 dp 问题，一个状态得用二维有序数对表示，因此空间复杂度是 O(N^2)
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();
        // 初始化的部分可以省去。因为只有一个字符的时候一定是回文，dp[i][i] 根本不会被其它状态值所参考
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        // 由于 dp[i][j] 参考它左下方的值：（1）先升序填列；（2）再升序填行
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // dp[i][j] = charArray[i] == charArray[j] && (j - i < 3 || dp[i + 1][j - 1]);
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 边界条件：j - 1 - (i + 1) + 1 < 2
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 方法三：中心扩散
     * 时间复杂度：O(N^2)，枚举“中心位置”时间复杂度为 O(N)，从“中心位置”扩散得到“回文子串”的时间复杂度为 O(N)，因此时间复杂度可以降到 O(N^2)
     * 空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关
     */
    public String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;

        char[] charArray = s.toCharArray();
        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < len - 1; i++) {
            // 奇数回文串
            int oddLen = expandAroundCenter(charArray, i, i);
            // 偶数回文串
            int evenLen = expandAroundCenter(charArray, i, i + 1);
            int culMaxLen = Math.max(oddLen, evenLen);
            if (culMaxLen > maxLen) {
                maxLen = culMaxLen;
                // 这一步要在纸上画图发现规律
                begin = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private int expandAroundCenter(char[] charArray, int left, int right) {
        // 当 left = right 的时候，回文中心是一个字符，回文串的长度是奇数
        // 当 right = left + 1 的时候，回文中心是一个空隙，回文串的长度是偶数
        int len = charArray.length;
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (charArray[i] == charArray[j]) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // 跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，
        // 回文串的长度是 j - i + 1 - 2 = j - i - 1
        return j - i - 1;
    }

    /**
     * 方法四：Manacher 算法
     * 时间复杂度：O(N)，Manacher 算法只有在遇到还未匹配的位置时才进行匹配，已经匹配过的位置不再匹配，因此对于字符串 S 的每一个位置，都只进行一次匹配，算法的复杂度为 O(N)
     * 空间复杂度：O(N)，这里 p 数组的长度为 N
     */
    public String longestPalindrome4(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 得到预处理字符串
        String str = addDividers(s, '#');
        // 新字符串的长度
        int sLen = 2 * len + 1;

        // p[i]：以预处理字符串下标 i 为中心的回文半径，即当前下标 i 向两边扩散能够扩散的最多的步数
        // p 是 palindromic 的首字符
        int[] p = new int[sLen];

        // 双指针，它们是一一对应的，须同时更新
        // maxRight：当前使用中心扩散法够走到的最右边下标
        int maxRight = 0;
        // center：表示与 maxRight 对应的回文中心的下标
        int center = 0;

        // 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        int maxLen = 1;
        // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
        int begin = 0;
        for (int i = 0; i < sLen; i++) {
            // 情况1：当 i >= maxRight 时，只能用中心扩散法逐个匹配
            // 情况2：当 i < maxRight 时：
            //   ① 当 p[mirror] < maxRight - 1 时，有 p[i] = p[mirror] < maxRight - 1
            //   ② 当 p[mirror] == maxRight - 1 时，有 p[i] 至少等于 maxRight - 1，需要从 maxRight 开始扩散
            //   ③ 当 p[mirror] > maxRight - 1 时，有 p[i] = maxRight - 1
            //   综合以上三种情况，p[i] = min(p[mirror], maxRight - 1)，然后尝试中心扩散
            if (i < maxRight) {
                // mirror 为 i 关于 center 的对称点下标
                // (mirror + i) / 2 = center
                int mirror = 2 * center - i;
                // 状态转移方程，如上所述
                p[i] = Math.min(maxRight - i, p[mirror]);
            }

            // 下一次尝试中心扩散的左右起点，更新 p[i] 的值
            int left = i - (1 + p[i]);
            int right = i + (1 + p[i]);

            // left >= 0 && right < sLen 保证不越界
            // str.charAt(left) == str.charAt(right) 表示可以扩散 1 次
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;
            }

            // 更新 maxRight，它是遍历过的 i 的 i + p[i] 的最大者
            // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if (i + p[i] > maxRight) {
                // maxRight 和 center 需要同时更新
                maxRight = i + p[i];
                center = i;
            }
            if (p[i] > maxLen) {
                // 记录最长回文子串的长度和相应它在原始字符串中的起点
                maxLen = p[i];
                begin = (i - maxLen) / 2;
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private String addDividers(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome1("madam"));
        System.out.println(solution.longestPalindrome2("madam"));
        System.out.println(solution.longestPalindrome3("madam"));
        System.out.println(solution.longestPalindrome4("madam"));
        System.out.println(solution.longestPalindrome1("revive"));
        System.out.println(solution.longestPalindrome2("revive"));
        System.out.println(solution.longestPalindrome3("revive"));
        System.out.println(solution.longestPalindrome4("revive"));
    }
}
