package com.scuyjzh.leetcode.medium.No_0299_Bulls_and_Cows;

/**
 * 299. 猜数字游戏
 * <p>
 * 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
 * 1.你写出一个秘密数字，并请朋友猜这个数字是多少。
 * 2.朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
 * 3.朋友根据提示继续猜，直到猜出秘密数字。
 * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛。
 * • xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
 * • yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。
 */
class Solution {
    /**
     * 方法一：两次遍历
     */
    public String getHint1(String secret, String guess) {
        int bulls = 0;
        // secretMiss表示未命中，但在secret字符串中统计该字符
        int[] secretMiss = new int[10];
        // guessMiss表示未命中，但在guess字符串中统计该字符
        int[] guessMiss = new int[10];

        // 按照上述方法遍历，各自统计
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                secretMiss[secret.charAt(i) - '0']++;
                guessMiss[guess.charAt(i) - '0']++;
            }
        }
        // bull的数量应是hit数组之和
        // cow的数量取secretMiss和guessMiss相同位置上较小的数值（因为不能重复计算，所以只能一对一匹配缺失的）
        int cows = 0;
        for (int i = 0; i < 10; ++i) {
            cows = cows + Math.min(secretMiss[i], guessMiss[i]);
        }
        return bulls + "A" + cows + "B";
    }

    /**
     * 方法二：一次遍历
     */
    public String getHint2(String secret, String guess) {
        int[] numbers = new int[10];
        int bulls = 0, cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                // 当前数小于 0, 说明之前在 guess 中出现过, 和 secret 当前的数匹配
                if (numbers[secret.charAt(i) - '0']++ < 0) {
                    cows++;
                }
                // 当前数大于 0, 说明之前在 secret 中出现过, 和 guess 当前的数匹配
                if (numbers[guess.charAt(i) - '0']-- > 0) {
                    cows++;
                }
            }
        }
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getHint1("1123", "0111"));
        System.out.println(new Solution().getHint2("1123", "0111"));
    }
}
