package com.scuyjzh.leetcode.easy.No_0925_Long_Pressed_Name;

/**
 * 925. 长按键入
 *
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按
 * 键可能会被长按，而字符可能被输入 1 次或多次。
 *
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字
 * （其中一些字符可能被长按），那么就返回 True。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public boolean isLongPressedName(String name, String typed) {
        // 使用两个下标 i,j 追踪 name 和 typed 的位置
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                // 当 name[i]=typed[j] 时，说明两个字符串存在一对匹配的字符，此时将 i,j 都加 1
                ++i;
                ++j;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                // 否则，如果 typed[j]=typed[j−1]，说明存在一次长按键入，此时只将 j 加 1
                ++j;
            } else {
                // 如果 typed 中存在一个字符，它两个条件均不满足，则应当直接返回 false
                return false;
            }
        }
        // 最后，如果 i=name.length，说明 name 的每个字符都被「匹配」了
        return i == name.length();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isLongPressedName("alex", "aaleex"));
        System.out.println(new Solution().isLongPressedName("saeed", "ssaaedd"));
        System.out.println(new Solution().isLongPressedName("leelee", "lleeelee"));
    }
}
