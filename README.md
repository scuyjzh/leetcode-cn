# leetcode-cn

✏️📖 Solutions for [LeetCode(CN)](https://leetcode-cn.com/) Problems (inspired by [haoel's leetcode](https://github.com/haoel/leetcode))

(**Notes**: "🔒" means you need a premium subscription)

## Table of Contents

- [Tags](#Tags)
  * [Array](#Array)
  * [String](#String)
  * [Math](#Math)
- [Problems](#Problems)

## Tags

### Array

#### 基础

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                               |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------- |
| 27   | [移除元素](https://leetcode-cn.com/problems/remove-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0027_Remove_Element/Solution.java) | 简单 | 数组 双指针                        |
| 26   | [删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0026_Remove_Duplicates_from_Sorted_Array/Solution.java) | 简单 | 数组 双指针                        |
| 80   | [删除有序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0080_Remove_Duplicates_from_Sorted_Array_II/Solution.java) | 中等 | 数组 双指针                        |
| 277  | [搜寻名人](https://leetcode-cn.com/problems/find-the-celebrity/) 🔒 | [Java](https://leetcode-cn.com/problems/find-the-celebrity/solution/) | 中等 | 贪心 图 双指针 交互                |
| 189  | [旋转数组](https://leetcode-cn.com/problems/rotate-array/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0189_Rotate_Array/Solution.java) | 中等 | 数组 数学 双指针                   |
| 41   | [缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0041_First_Missing_Positive/Solution.java) | 困难 | 数组 哈希表                        |
| 299  | [猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0299_Bulls_and_Cows/Solution.java) | 中等 | 哈希表 字符串 计数                 |
| 134  | [加油站](https://leetcode-cn.com/problems/gas-station/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0134_Gas_Station/Solution.java) | 中等 | 贪心 数组                          |
| 118  | [杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0118_Pascals_Triangle/Solution.java) | 简单 | 数组 动态规划                      |
| 119  | [杨辉三角 II](https://leetcode-cn.com/problems/pascals-triangle-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0119_Pascals_Triangle_II/Solution.java) | 简单 | 数组 动态规划                      |
| 169  | [多数元素](https://leetcode-cn.com/problems/majority-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0169_Majority_Element/Solution.java) | 简单 | 数组 哈希表 分治 计数 排序         |
| 229  | [求众数 II](https://leetcode-cn.com/problems/majority-element-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0229_Majority_Element_II/Solution.java) | 中等 | 数组 哈希表 计数 排序              |
| 274  | [H 指数](https://leetcode-cn.com/problems/h-index/)          | [Java](./src/com/scuyjzh/leetcode/medium/No_0274_H_Index/Solution.java) | 中等 | 数组 计数排序 排序                 |
| 275  | [H 指数 II](https://leetcode-cn.com/problems/h-index-ii/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0275_H_Index_II/Solution.java) | 中等 | 数组 二分查找                      |
| 243  | [最短单词距离](https://leetcode-cn.com/problems/shortest-word-distance/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0243_Shortest_Word_Distance/Solution.java) | 简单 | 数组 字符串                        |
| 244  | [最短单词距离 II](https://leetcode-cn.com/problems/shortest-word-distance-ii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0244_Shortest_Word_Distance_II/WordDistance.java) | 中等 | 设计 数组 哈希表 双指针 字符串     |
| 245  | [最短单词距离 III](https://leetcode-cn.com/problems/shortest-word-distance-iii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0245_Shortest_Word_Distance_III/Solution.java) | 中等 | 数组 字符串                        |
| 217  | [存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0217_Contains_Duplicate/Solution.java) | 简单 | 数组 哈希表 排序                   |
| 219  | [存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0219_Contains_Duplicate_II/Solution.java) | 简单 | 数组 哈希表 滑动窗口               |
| 220  | [存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0220_Contains_Duplicate_III/Solution.java) | 中等 | 数组 桶排序 有序集合 排序 滑动窗口 |
| 55   | [跳跃游戏](https://leetcode-cn.com/problems/jump-game/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0055_Jump_Game/Solution.java) | 中等 | 贪心 数组 动态规划                 |
| 45   | [跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0045_Jump_Game_II/Solution.java) | 中等 | 贪心 数组 动态规划                 |
| 121  | [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0121_Best_Time_to_Buy_and_Sell_Stock/Solution.java) | 简单 | 数组 动态规划                      |
| 122  | [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0122_Best_Time_to_Buy_and_Sell_Stock_II/Solution.java) | 简单 | 贪心 数组 动态规划                 |
| 123  | [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0123_Best_Time_to_Buy_and_Sell_Stock_III/Solution.java) | 困难 | 数组 动态规划                      |
| 188  | [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0188_Best_Time_to_Buy_and_Sell_Stock_IV/Solution.java) | 困难 | 数组 动态规划                      |
| 309  | [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown/Solution.java) | 中等 | 数组 动态规划                      |
| 714  | [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee/Solution.java) | 中等 | 贪心 数组 动态规划                 |
| 11   | [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0011_Container_With_Most_Water/Solution.java) | 中等 | 贪心 数组 双指针                   |
| 42   | [接雨水](https://leetcode-cn.com/problems/trapping-rain-water/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0042_Trapping_Rain_Water/Solution.java) | 困难 | 栈 数组 双指针 动态规划 单调栈     |
| 334  | [递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0334_Increasing_Triplet_Subsequence/Solution.java) | 中等 | 贪心 数组                          |
| 128  | [最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0128_Longest_Consecutive_Sequence/Solution.java) | 中等 | 并查集 数组 哈希表                 |
| 164  | [最大间距](https://leetcode-cn.com/problems/maximum-gap/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0164_Maximum_Gap/Solution.java) | 困难 | 数组 桶排序 基数排序 排序          |
| 287  | [寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0287_Find_the_Duplicate_Number/Solution.java) | 中等 | 位运算 数组 双指针 二分查找        |
| 135  | [分发糖果](https://leetcode-cn.com/problems/candy/)          | [Java](./src/com/scuyjzh/leetcode/hard/No_0135_Candy/Solution.java) | 困难 | 贪心 数组                          |
| 330  | [按要求补齐数组](https://leetcode-cn.com/problems/patching-array/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0330_Patching_Array/Solution.java) | 困难 | 贪心 数组                          |

#### 提高

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------------------- |
| 4    | [寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0004_Median_of_Two_Sorted_Arrays/Solution.java) | 困难 | 数组 二分查找 分治                                   |
| 321  | [拼接最大数](https://leetcode-cn.com/problems/create-maximum-number/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0321_Create_Maximum_Number/Solution.java) | 困难 | 栈 贪心 单调栈                                       |
| 327  | [区间和的个数](https://leetcode-cn.com/problems/count-of-range-sum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0327_Count_of_Range_Sum/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |
| 289  | [生命游戏](https://leetcode-cn.com/problems/game-of-life/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0289_Game_of_Life/Solution.java) | 中等 | 数组 矩阵 模拟                                       |

### String

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                   |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------- |
| 3    | [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0003_Longest_Substring_Without_Repeating_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口 |
| 5    | [最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0005_Longest_Palindromic_Substring/Solution.java) | 中等 | 字符串 动态规划        |
| 6    | [Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0006_ZigZag_Conversion/Solution.java) | 中等 | 字符串                 |

### Math

| #    | 题目                                                  | 题解                                                         | 难度 | 标签      |
| ---- | ----------------------------------------------------- | ------------------------------------------------------------ | ---- | --------- |
| 50   | [Pow(x, n)](https://leetcode-cn.com/problems/powx-n/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0050_Pow/Solution.java) | 中等 | 递归 数学 |

## Problems

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------------------- |
| 714  | [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 402  | [移掉 K 位数字](https://leetcode-cn.com/problems/remove-k-digits/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0402_Remove_K_Digits/Solution.java) | 中等 | 栈 贪心 字符串 单调栈                                |
| 334  | [递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0334_Increasing_Triplet_Subsequence/Solution.java) | 中等 | 贪心 数组                                            |
| 330  | [按要求补齐数组](https://leetcode-cn.com/problems/patching-array/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0330_Patching_Array/Solution.java) | 困难 | 贪心 数组                                            |
| 327  | [区间和的个数](https://leetcode-cn.com/problems/count-of-range-sum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0327_Count_of_Range_Sum/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |
| 321  | [拼接最大数](https://leetcode-cn.com/problems/create-maximum-number/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0321_Create_Maximum_Number/Solution.java) | 困难 | 栈 贪心 单调栈                                       |
| 309  | [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown/Solution.java) | 中等 | 数组 动态规划                                        |
| 303  | [区域和检索 - 数组不可变](https://leetcode-cn.com/problems/range-sum-query-immutable/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0303_Range_Sum_Query_Immutable/NumArray.java) | 简单 | 设计 数组 前缀和                                     |
| 299  | [猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0299_Bulls_and_Cows/Solution.java) | 中等 | 哈希表 字符串 计数                                   |
| 289  | [生命游戏](https://leetcode-cn.com/problems/game-of-life/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0289_Game_of_Life/Solution.java) | 中等 | 数组 矩阵 模拟                                       |
| 287  | [寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0287_Find_the_Duplicate_Number/Solution.java) | 中等 | 位运算 数组 双指针 二分查找                          |
| 277  | [搜寻名人](https://leetcode-cn.com/problems/find-the-celebrity/) 🔒 | [Java](https://leetcode.jp/leetcode-277-find-the-celebrity-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/) | 中等 | 贪心 图 双指针 交互                                  |
| 275  | [H 指数 II](https://leetcode-cn.com/problems/h-index-ii/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0275_H_Index_II/Solution.java) | 中等 | 数组 二分查找                                        |
| 274  | [H 指数](https://leetcode-cn.com/problems/h-index/)          | [Java](./src/com/scuyjzh/leetcode/medium/No_0274_H_Index/Solution.java) | 中等 | 数组 计数排序 排序                                   |
| 245  | [最短单词距离 III](https://leetcode-cn.com/problems/shortest-word-distance-iii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0245_Shortest_Word_Distance_III/Solution.java) | 中等 | 数组 字符串                                          |
| 244  | [最短单词距离 II](https://leetcode-cn.com/problems/shortest-word-distance-ii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0244_Shortest_Word_Distance_II/WordDistance.java) | 中等 | 设计 数组 哈希表 双指针 字符串                       |
| 243  | [最短单词距离](https://leetcode-cn.com/problems/shortest-word-distance/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0243_Shortest_Word_Distance/Solution.java) | 简单 | 数组 字符串                                          |
| 229  | [求众数 II](https://leetcode-cn.com/problems/majority-element-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0229_Majority_Element_II/Solution.java) | 中等 | 数组 哈希表 计数 排序                                |
| 220  | [存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0220_Contains_Duplicate_III/Solution.java) | 中等 | 数组 桶排序 有序集合 排序 滑动窗口                   |
| 219  | [存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0219_Contains_Duplicate_II/Solution.java) | 简单 | 数组 哈希表 滑动窗口                                 |
| 217  | [存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0217_Contains_Duplicate/Solution.java) | 简单 | 数组 哈希表 排序                                     |
| 189  | [旋转数组](https://leetcode-cn.com/problems/rotate-array/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0189_Rotate_Array/Solution.java) | 中等 | 数组 数学 双指针                                     |
| 188  | [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0188_Best_Time_to_Buy_and_Sell_Stock_IV/Solution.java) | 困难 | 数组 动态规划                                        |
| 169  | [多数元素](https://leetcode-cn.com/problems/majority-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0169_Majority_Element/Solution.java) | 简单 | 数组 哈希表 分治 计数 排序                           |
| 164  | [最大间距](https://leetcode-cn.com/problems/maximum-gap/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0164_Maximum_Gap/Solution.java) | 困难 | 数组 桶排序 基数排序 排序                            |
| 142  | [环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0142_Linked_List_Cycle_II/Solution.java) | 中等 | 哈希表 链表 双指针                                   |
| 141  | [环形链表](https://leetcode-cn.com/problems/linked-list-cycle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0141_Linked_List_Cycle/Solution.java) | 简单 | 哈希表 链表 双指针                                   |
| 135  | [分发糖果](https://leetcode-cn.com/problems/candy/)          | [Java](./src/com/scuyjzh/leetcode/hard/No_0135_Candy/Solution.java) | 困难 | 贪心 数组                                            |
| 134  | [加油站](https://leetcode-cn.com/problems/gas-station/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0134_Gas_Station/Solution.java) | 中等 | 贪心 数组                                            |
| 128  | [最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0128_Longest_Consecutive_Sequence/Solution.java) | 中等 | 并查集 数组 哈希表                                   |
| 123  | [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0123_Best_Time_to_Buy_and_Sell_Stock_III/Solution.java) | 困难 | 数组 动态规划                                        |
| 122  | [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0122_Best_Time_to_Buy_and_Sell_Stock_II/Solution.java) | 简单 | 贪心 数组 动态规划                                   |
| 121  | [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0121_Best_Time_to_Buy_and_Sell_Stock/Solution.java) | 简单 | 数组 动态规划                                        |
| 119  | [杨辉三角 II](https://leetcode-cn.com/problems/pascals-triangle-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0119_Pascals_Triangle_II/Solution.java) | 简单 | 数组 动态规划                                        |
| 118  | [杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0118_Pascals_Triangle/Solution.java) | 简单 | 数组 动态规划                                        |
| 90   | [子集 II](https://leetcode-cn.com/problems/subsets-ii/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0090_Subsets_II/Solution.java) | 中等 | 位运算 数组 回溯                                     |
| 80   | [删除有序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0080_Remove_Duplicates_from_Sorted_Array_II/Solution.java) | 中等 | 数组 双指针                                          |
| 78   | [子集](https://leetcode-cn.com/problems/subsets/)            | [Java](./src/com/scuyjzh/leetcode/medium/No_0078_Subsets/Solution.java) | 中等 | 位运算 数组 回溯                                     |
| 77   | [组合](https://leetcode-cn.com/problems/combinations/)       | [Java](./src/com/scuyjzh/leetcode/medium/No_0077_Combinations/Solution.java) | 中等 | 数组 回溯                                            |
| 55   | [跳跃游戏](https://leetcode-cn.com/problems/jump-game/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0055_Jump_Game/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 52   | [N皇后 II](https://leetcode-cn.com/problems/n-queens-ii/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0052_N_Queens_II/Solution.java) | 困难 | 回溯                                                 |
| 51   | [N 皇后](https://leetcode-cn.com/problems/n-queens/)         | [Java](./src/com/scuyjzh/leetcode/hard/No_0051_N_Queens/Solution.java) | 困难 | 数组 回溯                                            |
| 50   | [Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)        | [Java](./src/com/scuyjzh/leetcode/medium/No_0050_Pow/Solution.java) | 中等 | 递归 数学                                            |
| 49   | [字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0049_Group_Anagrams/Solution.java) | 中等 | 哈希表 字符串 排序                                   |
| 48   | [旋转图像](https://leetcode-cn.com/problems/rotate-image/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0048_Rotate_Image/Solution.java) | 中等 | 数组 数学 矩阵                                       |
| 47   | [全排列 II](https://leetcode-cn.com/problems/permutations-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0047_Permutations_II/Solution.java) | 中等 | 数组 回溯                                            |
| 46   | [全排列](https://leetcode-cn.com/problems/permutations/)     | [Java](./src/com/scuyjzh/leetcode/medium/No_0046_Permutations/Solution.java) | 中等 | 数组 回溯                                            |
| 45   | [跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0045_Jump_Game_II/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 44   | [通配符匹配](https://leetcode-cn.com/problems/wildcard-matching/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0044_Wildcard_Matching/Solution.java) | 困难 | 贪心 递归 字符串 动态规划                            |
| 43   | [字符串相乘](https://leetcode-cn.com/problems/multiply-strings/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0043_Multiply_Strings/Solution.java) | 中等 | 数学 字符串 模拟                                     |
| 42   | [接雨水](https://leetcode-cn.com/problems/trapping-rain-water/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0042_Trapping_Rain_Water/Solution.java) | 困难 | 栈 数组 双指针 动态规划 单调栈                       |
| 41   | [缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0041_First_Missing_Positive/Solution.java) | 困难 | 数组 哈希表                                          |
| 40   | [组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0040_Combination_Sum_II/Solution.java) | 中等 | 数组 回溯                                            |
| 39   | [组合总和](https://leetcode-cn.com/problems/combination-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0039_Combination_Sum/Solution.java) | 中等 | 数组 回溯                                            |
| 38   | [外观数列](https://leetcode-cn.com/problems/count-and-say/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0038_Count_and_Say/Solution.java) | 中等 | 字符串                                               |
| 37   | [解数独](https://leetcode-cn.com/problems/sudoku-solver/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0037_Sudoku_Solver/Solution.java) | 困难 | 数组 回溯 矩阵                                       |
| 36   | [有效的数独](https://leetcode-cn.com/problems/valid-sudoku/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0036_Valid_Sudoku/Solution.java) | 中等 | 数组 哈希表 矩阵                                     |
| 35   | [搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0035_Search_Insert_Position/Solution.java) | 简单 | 数组 二分查找                                        |
| 34   | [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0034_Find_First_and_Last_Position_of_Element_in_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |
| 33   | [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0033_Search_in_Rotated_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |
| 32   | [最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0032_Longest_Valid_Parentheses/Solution.java) | 困难 | 栈 字符串 动态规划                                   |
| 31   | [下一个排列](https://leetcode-cn.com/problems/next-permutation/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0031_Next_Permutation/Solution.java) | 中等 | 数组 双指针                                          |
| 30   | [串联所有单词的子串](https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0030_Substring_with_Concatenation_of_All_Words/Solution.java) | 困难 | 哈希表 字符串 滑动窗口                               |
| 29   | [两数相除](https://leetcode-cn.com/problems/divide-two-integers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0029_Divide_Two_Integers/Solution.java) | 中等 | 位运算 数学                                          |
| 28   | [实现 strStr()](https://leetcode-cn.com/problems/implement-strstr/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0028_strStr/Solution.java) | 简单 | 双指针 字符串 字符串匹配                             |
| 27   | [移除元素](https://leetcode-cn.com/problems/remove-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0027_Remove_Element/Solution.java) | 简单 | 数组 双指针                                          |
| 26   | [删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0026_Remove_Duplicates_from_Sorted_Array/Solution.java) | 简单 | 数组 双指针                                          |
| 25   | [K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0025_Reverse_Nodes_in_kGroup/Solution.java) | 困难 | 递归 链表                                            |
| 24   | [两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0024_Swap_Nodes_in_Pairs/Solution.java) | 中等 | 递归 链表                                            |
| 23   | [合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0023_Merge_k_Sorted_Lists/Solution.java) | 困难 | 链表 分治 堆（优先队列） 归并排序                    |
| 22   | [括号生成](https://leetcode-cn.com/problems/generate-parentheses/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0022_Generate_Parentheses/Solution.java) | 中等 | 字符串 动态规划 回溯                                 |
| 21   | [合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0021_Merge_Two_Sorted_Lists/Solution.java) | 简单 | 递归 链表                                            |
| 20   | [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0020_Valid_Parentheses/Solution.java) | 简单 | 栈 字符串                                            |
| 19   | [删除链表的倒数第 N 个结点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0019_Remove_Nth_Node_From_End_of_List/Solution.java) | 中等 | 链表 双指针                                          |
| 18   | [四数之和](https://leetcode-cn.com/problems/4sum/)           | [Java](./src/com/scuyjzh/leetcode/medium/No_0018_4Sum/Solution.java) | 中等 | 数组 双指针 排序                                     |
| 17   | [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0017_Letter_Combinations_of_a_Phone_Number/Solution.java) | 中等 | 哈希表 字符串 回溯                                   |
| 16   | [最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0016_3Sum_Closest/Solution.java) | 中等 | 数组 双指针 排序                                     |
| 15   | [三数之和](https://leetcode-cn.com/problems/3sum/)           | [Java](./src/com/scuyjzh/leetcode/medium/No_0015_3Sum/Solution.java) | 中等 | 数组 双指针 排序                                     |
| 14   | [最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0014_Longest_Common_Prefix/Solution.java) | 简单 | 字符串                                               |
| 13   | [罗马数字转整数](https://leetcode-cn.com/problems/roman-to-integer/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0013_Roman_to_Integer/Solution.java) | 简单 | 哈希表 数学 字符串                                   |
| 12   | [整数转罗马数字](https://leetcode-cn.com/problems/integer-to-roman/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0012_Integer_to_Roman/Solution.java) | 中等 | 哈希表 数学 字符串                                   |
| 11   | [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0011_Container_With_Most_Water/Solution.java) | 中等 | 贪心 数组 双指针                                     |
| 10   | [正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0010_Regular_Expression_Matching/Solution.java) | 困难 | 递归 字符串 动态规划                                 |
| 9    | [回文数](https://leetcode-cn.com/problems/palindrome-number/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0009_Palindrome_Number/Solution.java) | 简单 | 数学                                                 |
| 8    | [字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0008_String_to_Integer/Solution.java) | 中等 | 字符串                                               |
| 7    | [整数反转](https://leetcode-cn.com/problems/reverse-integer/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0007_Reverse_Integer/Solution.java) | 简单 | 数学                                                 |
| 6    | [Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0006_ZigZag_Conversion/Solution.java) | 中等 | 字符串                                               |
| 5    | [最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0005_Longest_Palindromic_Substring/Solution.java) | 中等 | 字符串 动态规划                                      |
| 4    | [寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0004_Median_of_Two_Sorted_Arrays/Solution.java) | 困难 | 数组 二分查找 分治                                   |
| 3    | [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0003_Longest_Substring_Without_Repeating_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口                               |
| 2    | [两数相加](https://leetcode-cn.com/problems/add-two-numbers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0002_Add_Two_Numbers/Solution.java) | 中等 | 递归 链表 数学                                       |
| 1    | [两数之和](https://leetcode-cn.com/problems/two-sum/)        | [Java](./src/com/scuyjzh/leetcode/easy/No_0001_Two_Sum/Solution.java) | 简单 | 数组 哈希表                                          |
