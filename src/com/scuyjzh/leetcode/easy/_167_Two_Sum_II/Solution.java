package com.scuyjzh.leetcode.easy._167_Two_Sum_II;

/**
 * @author scuyjzh
 * @data 2020/6/28 1:46
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }
}
