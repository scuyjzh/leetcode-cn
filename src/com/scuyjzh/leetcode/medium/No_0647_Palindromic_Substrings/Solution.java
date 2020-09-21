package com.scuyjzh.leetcode.medium.No_0647_Palindromic_Substrings;

/**
 * @author scuyjzh
 * @date 2020/8/19 23:33
 */
class Solution {
    /**
     * 方法一：暴力匹配
     * 时间复杂度：O(N^3)，这里 N 是字符串的长度，枚举字符串的左边界、右边界，然后继续验证子串是否是回文子串，这三种操作都与 N 相关
     * 空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关
     */
    public int countSubstrings1(String s) {
        int len = s.length();
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组，这一步非必需
        char[] charArray = s.toCharArray();
        int ans = 0;

        // 枚举所有长度严格大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (validPalindromic(charArray, i, j)) {
                    ans++;
                }
            }
        }
        return ans;
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
     * 空间复杂度：O(N^2)
     */
    public int countSubstrings2(String s) {
        int len = s.length();
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();
        int ans = 0;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                // 边界条件：j - 1 - (i + 1) + 1 < 2
                if (charArray[i] == charArray[j] && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 方法三：中心扩散
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     */
    public int countSubstrings3(String s) {
        int n = s.length(), ans = 0;
        // 长度为 n 的字符串会生成 2n-1 组回文中心 [l_i, r_i]
        for (int i = 0; i < 2 * n - 1; ++i) {
            // r_i = l_i + (i mod 2)
            int l = i / 2, r = l + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 方法四：Manacher 算法
     * 时间复杂度：O(N)，Manacher 算法只有在遇到还未匹配的位置时才进行匹配，已经匹配过的位置不再匹配，因此对于字符串 S 的每一个位置，都只进行一次匹配，算法的复杂度为 O(N)
     * 空间复杂度：O(N)，这里 p 数组的长度为 N
     */
    public int countSubstrings4(String s) {
        int len = s.length();
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

        int ans = 0;
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
            // (以 i 为中心向两边能扩散的步数 + 1) / 2 = 以 i 为中心的回文串个数
            ans += (p[i] + 1) / 2;
        }
        return ans;
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
        System.out.println(solution.countSubstrings1("madam"));
        System.out.println(solution.countSubstrings2("madam"));
        System.out.println(solution.countSubstrings3("madam"));
        System.out.println(solution.countSubstrings4("madam"));
        System.out.println(solution.countSubstrings1("revive"));
        System.out.println(solution.countSubstrings2("revive"));
        System.out.println(solution.countSubstrings3("revive"));
        System.out.println(solution.countSubstrings4("revive"));
    }
}
