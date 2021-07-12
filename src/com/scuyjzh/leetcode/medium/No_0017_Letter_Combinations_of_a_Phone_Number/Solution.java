package com.scuyjzh.leetcode.medium.No_0017_Letter_Combinations_of_a_Phone_Number;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 * <p>
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
class Solution {
    private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private StringBuilder combination = new StringBuilder();

    private List<String> combinations = new ArrayList<>();

    /**
     * 方法一：回溯
     * 时间复杂度：O(3^m × 4^n)，其中 m 是输入中对应 3 个字母的数字个数（包括数字 2、3、4、5、6、8），n 是输入中对应 4 个字母的数字个数（包括数字 7、9），m+n 是输入数字的总个数。当输入包含 m 个对应 3 个字母的数字和 n 个对应 4 个字母的数字时，不同的字母组合一共有 3^m × 4^n 种，需要遍历每一种字母组合。
     * 空间复杂度：O(m+n)，其中 m 是输入中对应 3 个字母的数字个数，n 是输入中对应 4 个字母的数字个数，m+n 是输入数字的总个数。除了返回值以外，空间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，哈希表的大小与输入无关，可以看成常数，递归调用层数最大为 m+n 。
     */
    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        backtrack(digits, 0);
        return combinations;
    }

    private void backtrack(String digits, int index) {
        if (combination.length() == digits.length()) {
            combinations.add(combination.toString());
            return;
        }
        // 电话号码数字与对应数组索引差值为 2
        String val = map[digits.charAt(index) - '2'];
        for (char ch : val.toCharArray()) {
            combination.append(ch);
            backtrack(digits, index + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
    }

    /**
     * 方法二：队列
     */
    public List<String> letterCombinations2(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }

        HashMap<Character, String[]> map = new HashMap<Character, String[]>(8) {{
            put('2', new String[]{"a", "b", "c"});
            put('3', new String[]{"d", "e", "f"});
            put('4', new String[]{"g", "h", "i"});
            put('5', new String[]{"j", "k", "l"});
            put('6', new String[]{"m", "n", "o"});
            put('7', new String[]{"p", "q", "r", "s"});
            put('8', new String[]{"t", "u", "v"});
            put('9', new String[]{"w", "x", "y", "z"});
        }};

        // 定义一个队列来存储所有的组合结果
        Queue<String> queue = new LinkedList<>();
        // 遍历Digits，取到对应号码对应的字母数组
        for (int i = 0; i < digits.length(); ++i) {
            String[] letters = map.get(digits.charAt(i));
            if (queue.size() == 0) {
                // 初始定义的queue一定是空的，所以这时候把第一个号码的字母放入队列
                Collections.addAll(queue, letters);
            } else {
                // 对于后面的字母，将queue中元素出队然后拼接后重新进入队列
                // 重要：此处需记录本次需要出队的元素数量
                int queueLength = queue.size();
                for (int j = 0; j < queueLength; ++j) {
                    // 依次将队列头元素出队列
                    String s = queue.poll();
                    for (String letter : letters) {
                        // 将出来的队列元素和电话号码对应的字母依次进行拼接并添加进队列
                        queue.add(s + letter);
                    }
                }
            }
        }
        combinations.addAll(queue);
        return combinations;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations1("23"));
        System.out.println(solution.letterCombinations2("23"));
    }
}
