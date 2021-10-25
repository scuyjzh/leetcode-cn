# leetcode-cn

✏️📖 Solutions for [LeetCode(CN)](https://leetcode-cn.com/) Problems (inspired by [haoel's leetcode](https://github.com/haoel/leetcode))

(**Notes**: "🔒" means you need a premium subscription)

## Table of Contents

- [Tags](#Tags)
  * [Array](#Array)
  * [String](#String)
  * [Math](#Math)
  * [Tree](#Tree)
- [Problems](#Problems)

## Tags

### Array

#### 基础

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                               | 说明          |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------- | ------------- |
| 27   | [移除元素](https://leetcode-cn.com/problems/remove-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0027_Remove_Element/Solution.java) | 简单 | 数组 双指针                        |               |
| 26   | [删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0026_Remove_Duplicates_from_Sorted_Array/Solution.java) | 简单 | 数组 双指针                        |               |
| 80   | [删除有序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0080_Remove_Duplicates_from_Sorted_Array_II/Solution.java) | 中等 | 数组 双指针                        |               |
| 277  | [搜寻名人](https://leetcode-cn.com/problems/find-the-celebrity/) 🔒 | [Java](https://leetcode-cn.com/problems/find-the-celebrity/solution/) | 中等 | 贪心 图 双指针 交互                |               |
| 189  | [旋转数组](https://leetcode-cn.com/problems/rotate-array/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0189_Rotate_Array/Solution.java) | 中等 | 数组 数学 双指针                   |               |
| 41   | [缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0041_First_Missing_Positive/Solution.java) | 困难 | 数组 哈希表                        |               |
| 299  | [猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0299_Bulls_and_Cows/Solution.java) | 中等 | 哈希表 字符串 计数                 |               |
| 134  | [加油站](https://leetcode-cn.com/problems/gas-station/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0134_Gas_Station/Solution.java) | 中等 | 贪心 数组                          |               |
| 118  | [杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0118_Pascals_Triangle/Solution.java) | 简单 | 数组 动态规划                      | 很少考        |
| 119  | [杨辉三角 II](https://leetcode-cn.com/problems/pascals-triangle-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0119_Pascals_Triangle_II/Solution.java) | 简单 | 数组 动态规划                      | 很少考        |
| 169  | [多数元素](https://leetcode-cn.com/problems/majority-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0169_Majority_Element/Solution.java) | 简单 | 数组 哈希表 分治 计数 排序         | 很少考        |
| 229  | [求众数 II](https://leetcode-cn.com/problems/majority-element-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0229_Majority_Element_II/Solution.java) | 中等 | 数组 哈希表 计数 排序              | 很少考        |
| 274  | [H 指数](https://leetcode-cn.com/problems/h-index/)          | [Java](./src/com/scuyjzh/leetcode/medium/No_0274_H_Index/Solution.java) | 中等 | 数组 计数排序 排序                 |               |
| 275  | [H 指数 II](https://leetcode-cn.com/problems/h-index-ii/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0275_H_Index_II/Solution.java) | 中等 | 数组 二分查找                      | Binary Search |
| 243  | [最短单词距离](https://leetcode-cn.com/problems/shortest-word-distance/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0243_Shortest_Word_Distance/Solution.java) | 简单 | 数组 字符串                        |               |
| 244  | [最短单词距离 II](https://leetcode-cn.com/problems/shortest-word-distance-ii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0244_Shortest_Word_Distance_II/WordDistance.java) | 中等 | 设计 数组 哈希表 双指针 字符串     |               |
| 245  | [最短单词距离 III](https://leetcode-cn.com/problems/shortest-word-distance-iii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0245_Shortest_Word_Distance_III/Solution.java) | 中等 | 数组 字符串                        |               |
| 217  | [存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0217_Contains_Duplicate/Solution.java) | 简单 | 数组 哈希表 排序                   |               |
| 219  | [存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0219_Contains_Duplicate_II/Solution.java) | 简单 | 数组 哈希表 滑动窗口               | 很少考        |
| 220  | [存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0220_Contains_Duplicate_III/Solution.java) | 中等 | 数组 桶排序 有序集合 排序 滑动窗口 | 很少考        |
| 55   | [跳跃游戏](https://leetcode-cn.com/problems/jump-game/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0055_Jump_Game/Solution.java) | 中等 | 贪心 数组 动态规划                 |               |
| 45   | [跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0045_Jump_Game_II/Solution.java) | 中等 | 贪心 数组 动态规划                 |               |
| 121  | [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0121_Best_Time_to_Buy_and_Sell_Stock/Solution.java) | 简单 | 数组 动态规划                      |               |
| 122  | [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0122_Best_Time_to_Buy_and_Sell_Stock_II/Solution.java) | 中等 | 贪心 数组 动态规划                 |               |
| 123  | [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0123_Best_Time_to_Buy_and_Sell_Stock_III/Solution.java) | 困难 | 数组 动态规划                      |               |
| 188  | [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0188_Best_Time_to_Buy_and_Sell_Stock_IV/Solution.java) | 困难 | 数组 动态规划                      |               |
| 309  | [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown/Solution.java) | 中等 | 数组 动态规划                      |               |
| 714  | [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee/Solution.java) | 中等 | 贪心 数组 动态规划                 |               |
| 11   | [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0011_Container_With_Most_Water/Solution.java) | 中等 | 贪心 数组 双指针                   |               |
| 42   | [接雨水](https://leetcode-cn.com/problems/trapping-rain-water/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0042_Trapping_Rain_Water/Solution.java) | 困难 | 栈 数组 双指针 动态规划 单调栈     |               |
| 334  | [递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0334_Increasing_Triplet_Subsequence/Solution.java) | 中等 | 贪心 数组                          |               |
| 128  | [最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0128_Longest_Consecutive_Sequence/Solution.java) | 中等 | 并查集 数组 哈希表                 |               |
| 164  | [最大间距](https://leetcode-cn.com/problems/maximum-gap/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0164_Maximum_Gap/Solution.java) | 困难 | 数组 桶排序 基数排序 排序          | Bucket        |
| 287  | [寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0287_Find_the_Duplicate_Number/Solution.java) | 中等 | 位运算 数组 双指针 二分查找        |               |
| 135  | [分发糖果](https://leetcode-cn.com/problems/candy/)          | [Java](./src/com/scuyjzh/leetcode/hard/No_0135_Candy/Solution.java) | 困难 | 贪心 数组                          | 很少考        |
| 330  | [按要求补齐数组](https://leetcode-cn.com/problems/patching-array/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0330_Patching_Array/Solution.java) | 困难 | 贪心 数组                          | 很少考        |

#### 提高

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                 | 说明   |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------------------- | ------ |
| 4    | [寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0004_Median_of_Two_Sorted_Arrays/Solution.java) | 困难 | 数组 二分查找 分治                                   | 很少考 |
| 321  | [拼接最大数](https://leetcode-cn.com/problems/create-maximum-number/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0321_Create_Maximum_Number/Solution.java) | 困难 | 栈 贪心 单调栈                                       | 很少考 |
| 327  | [区间和的个数](https://leetcode-cn.com/problems/count-of-range-sum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0327_Count_of_Range_Sum/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |        |
| 289  | [生命游戏](https://leetcode-cn.com/problems/game-of-life/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0289_Game_of_Life/Solution.java) | 中等 | 数组 矩阵 模拟                                       |        |

#### Interval

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                 | 说明    |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------ | ------- |
| 57   | [插入区间](https://leetcode-cn.com/problems/insert-interval/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0057_Insert_Interval/Solution.java) | 中等 | 数组                                 |         |
| 56   | [合并区间](https://leetcode-cn.com/problems/merge-intervals/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0056_Merge_Intervals/Solution.java) | 中等 | 数组 排序                            |         |
| 252  | [会议室](https://leetcode-cn.com/problems/meeting-rooms/) 🔒  | [Java](./src/com/scuyjzh/leetcode/easy/No_0252_Meeting_Rooms/Solution.java) | 简单 | 数组 排序                            |         |
| 253  | [会议室 II](https://leetcode-cn.com/problems/meeting-rooms-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0253_Meeting_Rooms_II/Solution.java) | 中等 | 贪心 数组 双指针 排序 堆（优先队列） |         |
| 352  | [将数据流变为多个不相交区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0352_Data_Stream_as_Disjoint_Intervals/SummaryRanges.java) | 困难 | 设计 二分查找 有序集合               | TreeMap |

#### Counter

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                 | 说明 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------------------- | ---- |
| 239  | [滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0239_Sliding_Window_Maximum/Solution.java) | 困难 | 队列 数组 滑动窗口 单调队列 堆（优先队列）           |      |
| 295  | [数据流的中位数](https://leetcode-cn.com/problems/find-median-from-data-stream/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0295_Find_Median_from_Data_Stream/MedianFinder.java) | 困难 | 设计 双指针 数据流 排序 堆（优先队列）               |      |
| 53   | [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0053_Maximum_Subarray/Solution.java) | 简单 | 数组 分治 动态规划                                   |      |
| 325  | [和等于 k 的最长子数组长度 ](https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0325_Maximum_Size_Subarray_Sum_Equals_k/Solution.java) | 中等 | 数组 哈希表                                          |      |
| 209  | [长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0209_Minimum_Size_Subarray_Sum/Solution.java) | 中等 | 数组 二分查找 前缀和 滑动窗口                        |      |
| 238  | [除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0238_Product_of_Array_Except_Self/Solution.java) | 中等 | 数组 前缀和                                          |      |
| 152  | [乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0152_Maximum_Product_Subarray/Solution.java) | 中等 | 数组 动态规划                                        |      |
| 228  | [汇总区间](https://leetcode-cn.com/problems/summary-ranges/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0228_Summary_Ranges/Solution.java) | 简单 | 数组                                                 |      |
| 163  | [缺失的区间](https://leetcode-cn.com/problems/missing-ranges/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0163_Missing_Ranges/Solution.java) | 简单 | 数组                                                 |      |
| 88   | [合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0088_Merge_Sorted_Array/Solution.java) | 简单 | 数组 双指针 排序                                     |      |
| 75   | [颜色分类](https://leetcode-cn.com/problems/sort-colors/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0075_Sort_Colors/Solution.java) | 中等 | 数组 双指针 排序                                     |      |
| 283  | [移动零](https://leetcode-cn.com/problems/move-zeroes/)      | [Java](./src/com/scuyjzh/leetcode/easy/No_0283_Move_Zeroes/Solution.java) | 简单 | 数组 双指针                                          |      |
| 376  | [摆动序列](https://leetcode-cn.com/problems/wiggle-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0376_Wiggle_Subsequence/Solution.java) | 中等 | 贪心 数组 动态规划                                   |      |
| 280  | [摆动排序](https://leetcode-cn.com/problems/wiggle-sort/) 🔒  | [Java](./src/com/scuyjzh/leetcode/medium/No_0280_Wiggle_Sort/Solution.java) | 中等 | 贪心 数组 排序                                       |      |
| 324  | [摆动排序 II](https://leetcode-cn.com/problems/wiggle-sort-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0324_Wiggle_Sort_II/Solution.java) | 中等 | 数组 分治 快速排序 排序                              |      |
| 278  | [第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/) | [Java](https://leetcode-cn.com/problems/first-bad-version/solution/) | 简单 | 二分查找 交互                                        |      |
| 35   | [搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0035_Search_Insert_Position/Solution.java) | 简单 | 数组 二分查找                                        |      |
| 33   | [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0033_Search_in_Rotated_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |      |
| 81   | [搜索旋转排序数组 II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0081_Search_in_Rotated_Sorted_Array_II/Solution.java) | 中等 | 数组 二分查找                                        |      |
| 153  | [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0153_Find_Minimum_in_Rotated_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |      |
| 154  | [寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0154_Find_Minimum_in_Rotated_Sorted_Array_II/Solution.java) | 困难 | 数组 二分查找                                        |      |
| 162  | [寻找峰值](https://leetcode-cn.com/problems/find-peak-element/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0162_Find_Peak_Element/Solution.java) | 中等 | 数组 二分查找                                        |      |
| 374  | [猜数字大小](https://leetcode-cn.com/problems/guess-number-higher-or-lower/) | [Java](https://leetcode-cn.com/problems/guess-number-higher-or-lower/solution/) | 简单 | 二分查找 交互                                        |      |
| 34   | [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0034_Find_First_and_Last_Position_of_Element_in_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |      |
| 349  | [两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0349_Intersection_of_Two_Arrays/Solution.java) | 简单 | 数组 哈希表 双指针 二分查找 排序                     |      |
| 350  | [两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0350_Intersection_of_Two_Arrays_II/Solution.java) | 简单 | 数组 哈希表 双指针 二分查找 排序                     |      |
| 315  | [计算右侧小于当前元素的个数](https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0315_Count_of_Smaller_Numbers_After_Self/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |      |
| 300  | [最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0300_Longest_Increasing_Subsequence/Solution.java) | 中等 | 数组 二分查找 动态规划                               |      |
| 354  | [俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0354_Russian_Doll_Envelopes/Solution.java) | 困难 | 数组 二分查找 动态规划 排序                          |      |

### String

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                        | 说明   |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------------- | ------ |
| 28   | [实现 strStr()](https://leetcode-cn.com/problems/implement-strstr/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0028_strStr/Solution.java) | 简单 | 双指针 字符串 字符串匹配                    |        |
| 14   | [最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0014_Longest_Common_Prefix/Solution.java) | 简单 | 字符串                                      |        |
| 58   | [最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0058_Length_of_Last_Word/Solution.java) | 简单 | 字符串                                      |        |
| 387  | [字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0387_First_Unique_Character_in_a_String/Solution.java) | 简单 | 队列 哈希表 字符串 计数                     |        |
| 383  | [赎金信](https://leetcode-cn.com/problems/ransom-note/)      | [Java](./src/com/scuyjzh/leetcode/easy/No_0383_Ransom_Note/Solution.java) | 简单 | 哈希表 字符串 计数                          |        |
| 344  | [反转字符串](https://leetcode-cn.com/problems/reverse-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0344_Reverse_String/Solution.java) | 简单 | 递归 双指针 字符串                          |        |
| 151  | [翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0151_Reverse_Words_in_a_String/Solution.java) | 中等 | 双指针 字符串                               |        |
| 186  | [翻转字符串里的单词 II](https://leetcode-cn.com/problems/reverse-words-in-a-string-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0186_Reverse_Words_in_a_String_II/Solution.java) | 中等 | 双指针 字符串                               |        |
| 345  | [反转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0345_Reverse_Vowels_of_a_String/Solution.java) | 简单 | 双指针 字符串                               |        |
| 205  | [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0205_Isomorphic_Strings/Solution.java) | 简单 | 哈希表 字符串                               |        |
| 293  | [翻转游戏](https://leetcode-cn.com/problems/flip-game/) 🔒    | [Java](./src/com/scuyjzh/leetcode/easy/No_0293_Flip_Game/Solution.java) | 简单 | 字符串                                      |        |
| 294  | [翻转游戏 II](https://leetcode-cn.com/problems/flip-game-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0294_Flip_Game_II/Solution.java) | 中等 | 记忆化搜索 数学 动态规划 回溯 博弈          |        |
| 290  | [单词规律](https://leetcode-cn.com/problems/word-pattern/)   | [Java](./src/com/scuyjzh/leetcode/easy/No_0290_Word_Pattern/Solution.java) | 简单 | 哈希表 字符串                               |        |
| 242  | [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0242_Valid_Anagram/Solution.java) | 简单 | 哈希表 字符串 排序                          |        |
| 49   | [字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0049_Group_Anagrams/Solution.java) | 中等 | 哈希表 字符串 排序                          |        |
| 249  | [移位字符串分组](https://leetcode-cn.com/problems/group-shifted-strings/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0249_Group_Shifted_Strings/Solution.java) | 中等 | 数组 哈希表 字符串                          |        |
| 87   | [扰乱字符串](https://leetcode-cn.com/problems/scramble-string/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0087_Scramble_String/Solution.java) | 困难 | 字符串 动态规划                             |        |
| 179  | [最大数](https://leetcode-cn.com/problems/largest-number/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0179_Largest_Number/Solution.java) | 中等 | 贪心 字符串 排序                            |        |
| 6    | [Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0006_ZigZag_Conversion/Solution.java) | 中等 | 字符串                                      | 很少考 |
| 161  | [相隔为 1 的编辑距离](https://leetcode-cn.com/problems/one-edit-distance/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0161_One_Edit_Distance/Solution.java) | 中等 | 双指针 字符串                               | 很少考 |
| 38   | [外观数列](https://leetcode-cn.com/problems/count-and-say/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0038_Count_and_Say/Solution.java) | 中等 | 字符串                                      |        |
| 358  | [K 距离间隔重排字符串](https://leetcode-cn.com/problems/rearrange-string-k-distance-apart/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0358_Rearrange_String_k_Distance_Apart/Solution.java) | 困难 | 贪心 哈希表 字符串 计数 排序 堆（优先队列） |        |
| 316  | [去除重复字母](https://leetcode-cn.com/problems/remove-duplicate-letters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0316_Remove_Duplicate_Letters/Solution.java) | 中等 | 栈 贪心 字符串 单调栈                       |        |
| 271  | [字符串的编码与解码](https://leetcode-cn.com/problems/encode-and-decode-strings/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0271_Encode_and_Decode_Strings/Codec.java) | 中等 | 设计 数组 字符串                            |        |
| 168  | [Excel表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0168_Excel_Sheet_Column_Title/Solution.java) | 简单 | 数学 字符串                                 |        |
| 171  | [Excel 表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0171_Excel_Sheet_Column_Number/Solution.java) | 简单 | 数学 字符串                                 |        |
| 13   | [罗马数字转整数](https://leetcode-cn.com/problems/roman-to-integer/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0013_Roman_to_Integer/Solution.java) | 简单 | 哈希表 数学 字符串                          |        |
| 12   | [整数转罗马数字](https://leetcode-cn.com/problems/integer-to-roman/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0012_Integer_to_Roman/Solution.java) | 中等 | 哈希表 数学 字符串                          |        |
| 273  | [整数转换英文表示](https://leetcode-cn.com/problems/integer-to-english-words/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0273_Integer_to_English_Words/Solution.java) | 困难 | 递归 数学 字符串                            |        |

### Math

| #    | 题目                                                         | 题解                                                         | 难度 | 标签 | 说明 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---- | ---- |
| 7    | [整数反转](https://leetcode-cn.com/problems/reverse-integer/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0007_Reverse_Integer/Solution.java) | 简单 | 数学 |      |

### Tree

#### Preorder

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                | 说明           |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ----------------------------------- | -------------- |
| 226  | [翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0226_Invert_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树 | preorder + BFS |

#### BFS

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                              | 说明      |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | --------------------------------- | --------- |
| 235  | [二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree/Solution.java) | 简单 | 树 深度优先搜索 二叉搜索树 二叉树 | preorder  |
| 236  | [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0236_Lowest_Common_Ancestor_of_a_Binary_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉树            | postorder |

#### 重要程度

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                | 说明 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ----------------------------------- | ---- |
| 116  | [填充每个节点的下一个右侧节点指针](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0116_Populating_Next_Right_Pointers_in_Each_Node/Solution.java) | 中等 | 树 深度优先搜索 广度优先搜索 二叉树 | 重要 |
| 117  | [填充每个节点的下一个右侧节点指针 II](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0117_Populating_Next_Right_Pointers_in_Each_Node_II/Solution.java) | 中等 | 树 深度优先搜索 广度优先搜索 二叉树 | 重要 |

## Problems

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------------------- |
| 767  | [重构字符串](https://leetcode-cn.com/problems/reorganize-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0767_Reorganize_String/Solution.java) | 中等 | 贪心 哈希表 字符串 计数 排序 堆（优先队列）          |
| 714  | [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 402  | [移掉 K 位数字](https://leetcode-cn.com/problems/remove-k-digits/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0402_Remove_K_Digits/Solution.java) | 中等 | 栈 贪心 字符串 单调栈                                |
| 387  | [字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0387_First_Unique_Character_in_a_String/Solution.java) | 简单 | 队列 哈希表 字符串 计数                              |
| 383  | [赎金信](https://leetcode-cn.com/problems/ransom-note/)      | [Java](./src/com/scuyjzh/leetcode/easy/No_0383_Ransom_Note/Solution.java) | 简单 | 哈希表 字符串 计数                                   |
| 376  | [摆动序列](https://leetcode-cn.com/problems/wiggle-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0376_Wiggle_Subsequence/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 374  | [猜数字大小](https://leetcode-cn.com/problems/guess-number-higher-or-lower/) | [Java](https://leetcode-cn.com/problems/guess-number-higher-or-lower/solution/) | 简单 | 二分查找 交互                                        |
| 358  | [K 距离间隔重排字符串](https://leetcode-cn.com/problems/rearrange-string-k-distance-apart/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0358_Rearrange_String_k_Distance_Apart/Solution.java) | 困难 | 贪心 哈希表 字符串 计数 排序 堆（优先队列）          |
| 354  | [俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0354_Russian_Doll_Envelopes/Solution.java) | 困难 | 数组 二分查找 动态规划 排序                          |
| 352  | [将数据流变为多个不相交区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0352_Data_Stream_as_Disjoint_Intervals/SummaryRanges.java) | 困难 | 设计 二分查找 有序集合                               |
| 350  | [两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0350_Intersection_of_Two_Arrays_II/Solution.java) | 简单 | 数组 哈希表 双指针 二分查找 排序                     |
| 349  | [两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0349_Intersection_of_Two_Arrays/Solution.java) | 简单 | 数组 哈希表 双指针 二分查找 排序                     |
| 345  | [反转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0345_Reverse_Vowels_of_a_String/Solution.java) | 简单 | 双指针 字符串                                        |
| 344  | [反转字符串](https://leetcode-cn.com/problems/reverse-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0344_Reverse_String/Solution.java) | 简单 | 递归 双指针 字符串                                   |
| 334  | [递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0334_Increasing_Triplet_Subsequence/Solution.java) | 中等 | 贪心 数组                                            |
| 330  | [按要求补齐数组](https://leetcode-cn.com/problems/patching-array/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0330_Patching_Array/Solution.java) | 困难 | 贪心 数组                                            |
| 327  | [区间和的个数](https://leetcode-cn.com/problems/count-of-range-sum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0327_Count_of_Range_Sum/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |
| 325  | [和等于 k 的最长子数组长度 ](https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0325_Maximum_Size_Subarray_Sum_Equals_k/Solution.java) | 中等 | 数组 哈希表                                          |
| 324  | [摆动排序 II](https://leetcode-cn.com/problems/wiggle-sort-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0324_Wiggle_Sort_II/Solution.java) | 中等 | 数组 分治 快速排序 排序                              |
| 321  | [拼接最大数](https://leetcode-cn.com/problems/create-maximum-number/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0321_Create_Maximum_Number/Solution.java) | 困难 | 栈 贪心 单调栈                                       |
| 316  | [去除重复字母](https://leetcode-cn.com/problems/remove-duplicate-letters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0316_Remove_Duplicate_Letters/Solution.java) | 中等 | 栈 贪心 字符串 单调栈                                |
| 315  | [计算右侧小于当前元素的个数](https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0315_Count_of_Smaller_Numbers_After_Self/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |
| 309  | [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown/Solution.java) | 中等 | 数组 动态规划                                        |
| 303  | [区域和检索 - 数组不可变](https://leetcode-cn.com/problems/range-sum-query-immutable/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0303_Range_Sum_Query_Immutable/NumArray.java) | 简单 | 设计 数组 前缀和                                     |
| 300  | [最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0300_Longest_Increasing_Subsequence/Solution.java) | 中等 | 数组 二分查找 动态规划                               |
| 299  | [猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0299_Bulls_and_Cows/Solution.java) | 中等 | 哈希表 字符串 计数                                   |
| 295  | [数据流的中位数](https://leetcode-cn.com/problems/find-median-from-data-stream/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0295_Find_Median_from_Data_Stream/MedianFinder.java) | 困难 | 设计 双指针 数据流 排序 堆（优先队列）               |
| 294  | [翻转游戏 II](https://leetcode-cn.com/problems/flip-game-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0294_Flip_Game_II/Solution.java) | 中等 | 记忆化搜索 数学 动态规划 回溯 博弈                   |
| 293  | [翻转游戏](https://leetcode-cn.com/problems/flip-game/) 🔒    | [Java](./src/com/scuyjzh/leetcode/easy/No_0293_Flip_Game/Solution.java) | 简单 | 字符串                                               |
| 290  | [单词规律](https://leetcode-cn.com/problems/word-pattern/)   | [Java](./src/com/scuyjzh/leetcode/easy/No_0290_Word_Pattern/Solution.java) | 简单 | 哈希表 字符串                                        |
| 289  | [生命游戏](https://leetcode-cn.com/problems/game-of-life/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0289_Game_of_Life/Solution.java) | 中等 | 数组 矩阵 模拟                                       |
| 287  | [寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0287_Find_the_Duplicate_Number/Solution.java) | 中等 | 位运算 数组 双指针 二分查找                          |
| 283  | [移动零](https://leetcode-cn.com/problems/move-zeroes/)      | [Java](./src/com/scuyjzh/leetcode/easy/No_0283_Move_Zeroes/Solution.java) | 简单 | 数组 双指针                                          |
| 280  | [摆动排序](https://leetcode-cn.com/problems/wiggle-sort/) 🔒  | [Java](./src/com/scuyjzh/leetcode/medium/No_0280_Wiggle_Sort/Solution.java) | 中等 | 贪心 数组 排序                                       |
| 278  | [第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/) | [Java](https://leetcode-cn.com/problems/first-bad-version/solution/) | 简单 | 二分查找 交互                                        |
| 277  | [搜寻名人](https://leetcode-cn.com/problems/find-the-celebrity/) 🔒 | [Java](https://leetcode.jp/leetcode-277-find-the-celebrity-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/) | 中等 | 贪心 图 双指针 交互                                  |
| 275  | [H 指数 II](https://leetcode-cn.com/problems/h-index-ii/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0275_H_Index_II/Solution.java) | 中等 | 数组 二分查找                                        |
| 274  | [H 指数](https://leetcode-cn.com/problems/h-index/)          | [Java](./src/com/scuyjzh/leetcode/medium/No_0274_H_Index/Solution.java) | 中等 | 数组 计数排序 排序                                   |
| 273  | [整数转换英文表示](https://leetcode-cn.com/problems/integer-to-english-words/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0273_Integer_to_English_Words/Solution.java) | 困难 | 递归 数学 字符串                                     |
| 271  | [字符串的编码与解码](https://leetcode-cn.com/problems/encode-and-decode-strings/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0271_Encode_and_Decode_Strings/Codec.java) | 中等 | 设计 数组 字符串                                     |
| 252  | [会议室](https://leetcode-cn.com/problems/meeting-rooms/) 🔒  | [Java](./src/com/scuyjzh/leetcode/easy/No_0252_Meeting_Rooms/Solution.java) | 简单 | 数组 排序                                            |
| 253  | [会议室 II](https://leetcode-cn.com/problems/meeting-rooms-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0253_Meeting_Rooms_II/Solution.java) | 中等 | 贪心 数组 双指针 排序 堆（优先队列）                 |
| 249  | [移位字符串分组](https://leetcode-cn.com/problems/group-shifted-strings/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0249_Group_Shifted_Strings/Solution.java) | 中等 | 数组 哈希表 字符串                                   |
| 245  | [最短单词距离 III](https://leetcode-cn.com/problems/shortest-word-distance-iii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0245_Shortest_Word_Distance_III/Solution.java) | 中等 | 数组 字符串                                          |
| 244  | [最短单词距离 II](https://leetcode-cn.com/problems/shortest-word-distance-ii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0244_Shortest_Word_Distance_II/WordDistance.java) | 中等 | 设计 数组 哈希表 双指针 字符串                       |
| 243  | [最短单词距离](https://leetcode-cn.com/problems/shortest-word-distance/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0243_Shortest_Word_Distance/Solution.java) | 简单 | 数组 字符串                                          |
| 242  | [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0242_Valid_Anagram/Solution.java) | 简单 | 哈希表 字符串 排序                                   |
| 239  | [滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0239_Sliding_Window_Maximum/Solution.java) | 困难 | 队列 数组 滑动窗口 单调队列 堆（优先队列）           |
| 238  | [除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0238_Product_of_Array_Except_Self/Solution.java) | 中等 | 数组 前缀和                                          |
| 236  | [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0236_Lowest_Common_Ancestor_of_a_Binary_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉树                               |
| 235  | [二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree/Solution.java) | 简单 | 树 深度优先搜索 二叉搜索树 二叉树                    |
| 229  | [求众数 II](https://leetcode-cn.com/problems/majority-element-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0229_Majority_Element_II/Solution.java) | 中等 | 数组 哈希表 计数 排序                                |
| 228  | [汇总区间](https://leetcode-cn.com/problems/summary-ranges/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0228_Summary_Ranges/Solution.java) | 简单 | 数组                                                 |
| 226  | [翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0226_Invert_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树                  |
| 220  | [存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0220_Contains_Duplicate_III/Solution.java) | 中等 | 数组 桶排序 有序集合 排序 滑动窗口                   |
| 219  | [存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0219_Contains_Duplicate_II/Solution.java) | 简单 | 数组 哈希表 滑动窗口                                 |
| 217  | [存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0217_Contains_Duplicate/Solution.java) | 简单 | 数组 哈希表 排序                                     |
| 209  | [长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0209_Minimum_Size_Subarray_Sum/Solution.java) | 中等 | 数组 二分查找 前缀和 滑动窗口                        |
| 205  | [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0205_Isomorphic_Strings/Solution.java) | 简单 | 哈希表 字符串                                        |
| 189  | [旋转数组](https://leetcode-cn.com/problems/rotate-array/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0189_Rotate_Array/Solution.java) | 中等 | 数组 数学 双指针                                     |
| 188  | [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0188_Best_Time_to_Buy_and_Sell_Stock_IV/Solution.java) | 困难 | 数组 动态规划                                        |
| 186  | [翻转字符串里的单词 II](https://leetcode-cn.com/problems/reverse-words-in-a-string-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0186_Reverse_Words_in_a_String_II/Solution.java) | 中等 | 双指针 字符串                                        |
| 179  | [最大数](https://leetcode-cn.com/problems/largest-number/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0179_Largest_Number/Solution.java) | 中等 | 贪心 字符串 排序                                     |
| 171  | [Excel 表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0171_Excel_Sheet_Column_Number/Solution.java) | 简单 | 数学 字符串                                          |
| 169  | [多数元素](https://leetcode-cn.com/problems/majority-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0169_Majority_Element/Solution.java) | 简单 | 数组 哈希表 分治 计数 排序                           |
| 168  | [Excel表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0168_Excel_Sheet_Column_Title/Solution.java) | 简单 | 数学 字符串                                          |
| 164  | [最大间距](https://leetcode-cn.com/problems/maximum-gap/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0164_Maximum_Gap/Solution.java) | 困难 | 数组 桶排序 基数排序 排序                            |
| 163  | [缺失的区间](https://leetcode-cn.com/problems/missing-ranges/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0163_Missing_Ranges/Solution.java) | 简单 | 数组                                                 |
| 162  | [寻找峰值](https://leetcode-cn.com/problems/find-peak-element/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0162_Find_Peak_Element/Solution.java) | 中等 | 数组 二分查找                                        |
| 161  | [相隔为 1 的编辑距离](https://leetcode-cn.com/problems/one-edit-distance/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0161_One_Edit_Distance/Solution.java) | 中等 | 双指针 字符串                                        |
| 154  | [寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0154_Find_Minimum_in_Rotated_Sorted_Array_II/Solution.java) | 困难 | 数组 二分查找                                        |
| 153  | [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0153_Find_Minimum_in_Rotated_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |
| 152  | [乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0152_Maximum_Product_Subarray/Solution.java) | 中等 | 数组 动态规划                                        |
| 151  | [翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0151_Reverse_Words_in_a_String/Solution.java) | 中等 | 双指针 字符串                                        |
| 142  | [环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0142_Linked_List_Cycle_II/Solution.java) | 中等 | 哈希表 链表 双指针                                   |
| 141  | [环形链表](https://leetcode-cn.com/problems/linked-list-cycle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0141_Linked_List_Cycle/Solution.java) | 简单 | 哈希表 链表 双指针                                   |
| 135  | [分发糖果](https://leetcode-cn.com/problems/candy/)          | [Java](./src/com/scuyjzh/leetcode/hard/No_0135_Candy/Solution.java) | 困难 | 贪心 数组                                            |
| 134  | [加油站](https://leetcode-cn.com/problems/gas-station/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0134_Gas_Station/Solution.java) | 中等 | 贪心 数组                                            |
| 128  | [最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0128_Longest_Consecutive_Sequence/Solution.java) | 中等 | 并查集 数组 哈希表                                   |
| 123  | [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0123_Best_Time_to_Buy_and_Sell_Stock_III/Solution.java) | 困难 | 数组 动态规划                                        |
| 122  | [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0122_Best_Time_to_Buy_and_Sell_Stock_II/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 121  | [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0121_Best_Time_to_Buy_and_Sell_Stock/Solution.java) | 简单 | 数组 动态规划                                        |
| 119  | [杨辉三角 II](https://leetcode-cn.com/problems/pascals-triangle-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0119_Pascals_Triangle_II/Solution.java) | 简单 | 数组 动态规划                                        |
| 118  | [杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0118_Pascals_Triangle/Solution.java) | 简单 | 数组 动态规划                                        |
| 117  | [填充每个节点的下一个右侧节点指针 II](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0117_Populating_Next_Right_Pointers_in_Each_Node_II/Solution.java) | 中等 | 树 深度优先搜索 广度优先搜索 二叉树                  |
| 116  | [填充每个节点的下一个右侧节点指针](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0116_Populating_Next_Right_Pointers_in_Each_Node/Solution.java) | 中等 | 树 深度优先搜索 广度优先搜索 二叉树                  |
| 90   | [子集 II](https://leetcode-cn.com/problems/subsets-ii/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0090_Subsets_II/Solution.java) | 中等 | 位运算 数组 回溯                                     |
| 88   | [合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0088_Merge_Sorted_Array/Solution.java) | 简单 | 数组 双指针 排序                                     |
| 87   | [扰乱字符串](https://leetcode-cn.com/problems/scramble-string/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0087_Scramble_String/Solution.java) | 困难 | 字符串 动态规划                                      |
| 81   | [搜索旋转排序数组 II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0081_Search_in_Rotated_Sorted_Array_II/Solution.java) | 中等 | 数组 二分查找                                        |
| 80   | [删除有序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0080_Remove_Duplicates_from_Sorted_Array_II/Solution.java) | 中等 | 数组 双指针                                          |
| 78   | [子集](https://leetcode-cn.com/problems/subsets/)            | [Java](./src/com/scuyjzh/leetcode/medium/No_0078_Subsets/Solution.java) | 中等 | 位运算 数组 回溯                                     |
| 77   | [组合](https://leetcode-cn.com/problems/combinations/)       | [Java](./src/com/scuyjzh/leetcode/medium/No_0077_Combinations/Solution.java) | 中等 | 数组 回溯                                            |
| 75   | [颜色分类](https://leetcode-cn.com/problems/sort-colors/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0075_Sort_Colors/Solution.java) | 中等 | 数组 双指针 排序                                     |
| 58   | [最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0058_Length_of_Last_Word/Solution.java) | 简单 | 字符串                                               |
| 57   | [插入区间](https://leetcode-cn.com/problems/insert-interval/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0057_Insert_Interval/Solution.java) | 中等 | 数组                                                 |
| 56   | [合并区间](https://leetcode-cn.com/problems/merge-intervals/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0056_Merge_Intervals/Solution.java) | 中等 | 数组 排序                                            |
| 55   | [跳跃游戏](https://leetcode-cn.com/problems/jump-game/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0055_Jump_Game/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 53   | [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0053_Maximum_Subarray/Solution.java) | 简单 | 数组 分治 动态规划                                   |
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
