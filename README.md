# leetcode-cn

✏️📖 Solutions for [LeetCode(CN)](https://leetcode-cn.com/) Problems.

(**Notes**: "🔒" means you need a premium subscription)

## Table of Contents

- [HOT 100](#hot-100)
- [Tags](#tags)
  * [Sliding Window & Two Pointers](#sliding-window--two-pointers)
  * [Array](#array)
  * [String](#string)
  * [Math](#math)
  * [Tree](#tree)
  * [Backtracking](#backtracking)
  * [Dynamic Programming](#dynamic-programming)
  * [LinkedList](#linkedlist)
  * [Binary Search](#binary-search)
  * [Matrix](#matrix)
  * [DFS & BFS](#dfs--bfs)
  * [Stack & PriorityQueue](#stack--priorityqueue)
  * [Bit Manipulation](#bit-manipulation)
  * [Graph](#graph)
  * [Trie](#trie)
  * [Design](#design)

## HOT 100

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                      |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | --------------------------------------------------------- |
| 1    | [两数之和](https://leetcode-cn.com/problems/two-sum/)        | [Java](./src/com/scuyjzh/leetcode/easy/No_0001_Two_Sum/Solution.java) | 简单 | 数组 哈希表                                               |
| 2    | [两数相加](https://leetcode-cn.com/problems/add-two-numbers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0002_Add_Two_Numbers/Solution.java) | 中等 | 递归 链表 数学                                            |
| 3    | [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0003_Longest_Substring_Without_Repeating_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口                                    |
| 4    | [寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0004_Median_of_Two_Sorted_Arrays/Solution.java) | 困难 | 数组 二分查找 分治                                        |
| 5    | [最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0005_Longest_Palindromic_Substring/Solution.java) | 中等 | 字符串 动态规划                                           |
| 10   | [正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0010_Regular_Expression_Matching/Solution.java) | 困难 | 递归 字符串 动态规划                                      |
| 11   | [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0011_Container_With_Most_Water/Solution.java) | 中等 | 贪心 数组 双指针                                          |
| 15   | [三数之和](https://leetcode-cn.com/problems/3sum/)           | [Java](./src/com/scuyjzh/leetcode/medium/No_0015_3Sum/Solution.java) | 中等 | 数组 双指针 排序                                          |
| 17   | [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0017_Letter_Combinations_of_a_Phone_Number/Solution.java) | 中等 | 哈希表 字符串 回溯                                        |
| 19   | [删除链表的倒数第 N 个节点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0019_Remove_Nth_Node_From_End_of_List/Solution.java) | 中等 | 链表 双指针                                               |
| 20   | [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0020_Valid_Parentheses/Solution.java) | 简单 | 栈 字符串                                                 |
| 21   | [合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0021_Merge_Two_Sorted_Lists/Solution.java) | 简单 | 递归 链表                                                 |
| 22   | [括号生成](https://leetcode-cn.com/problems/generate-parentheses/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0022_Generate_Parentheses/Solution.java) | 中等 | 字符串 动态规划 回溯                                      |
| 23   | [合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0023_Merge_k_Sorted_Lists/Solution.java) | 困难 | 链表 分治 堆（优先队列） 归并排序                         |
| 31   | [下一个排列](https://leetcode-cn.com/problems/next-permutation/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0031_Next_Permutation/Solution.java) | 中等 | 数组 双指针                                               |
| 32   | [最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0032_Longest_Valid_Parentheses/Solution.java) | 困难 | 栈 字符串 动态规划                                        |
| 33   | [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0033_Search_in_Rotated_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                             |
| 34   | [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0034_Find_First_and_Last_Position_of_Element_in_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                             |
| 39   | [组合总和](https://leetcode-cn.com/problems/combination-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0039_Combination_Sum/Solution.java) | 中等 | 数组 回溯                                                 |
| 42   | [接雨水](https://leetcode-cn.com/problems/trapping-rain-water/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0042_Trapping_Rain_Water/Solution.java) | 困难 | 栈 数组 双指针 动态规划 单调栈                            |
| 46   | [全排列](https://leetcode-cn.com/problems/permutations/)     | [Java](./src/com/scuyjzh/leetcode/medium/No_0046_Permutations/Solution.java) | 中等 | 数组 回溯                                                 |
| 48   | [旋转图像](https://leetcode-cn.com/problems/rotate-image/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0048_Rotate_Image/Solution.java) | 中等 | 数组 数学 矩阵                                            |
| 49   | [字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0049_Group_Anagrams/Solution.java) | 中等 | 哈希表 字符串 排序                                        |
| 53   | [最大子数组和](https://leetcode-cn.com/problems/maximum-subarray/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0053_Maximum_Subarray/Solution.java) | 简单 | 数组 分治 动态规划                                        |
| 55   | [跳跃游戏](https://leetcode-cn.com/problems/jump-game/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0055_Jump_Game/Solution.java) | 中等 | 贪心 数组 动态规划                                        |
| 56   | [合并区间](https://leetcode-cn.com/problems/merge-intervals/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0056_Merge_Intervals/Solution.java) | 中等 | 数组 排序                                                 |
| 62   | [不同路径](https://leetcode-cn.com/problems/unique-paths/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0062_Unique_Paths/Solution.java) | 中等 | 数学 动态规划 组合数学                                    |
| 64   | [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0064_Minimum_Path_Sum/Solution.java) | 中等 | 数组 动态规划 矩阵                                        |
| 70   | [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)  | [Java](./src/com/scuyjzh/leetcode/easy/No_0070_Climbing_Stairs/Solution.java) | 简单 | 记忆化搜索 数学 动态规划                                  |
| 72   | [编辑距离](https://leetcode-cn.com/problems/edit-distance/)  | [Java](./src/com/scuyjzh/leetcode/hard/No_0072_Edit_Distance/Solution.java) | 困难 | 字符串 动态规划                                           |
| 75   | [颜色分类](https://leetcode-cn.com/problems/sort-colors/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0075_Sort_Colors/Solution.java) | 中等 | 数组 双指针 排序                                          |
| 76   | [最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0076_Minimum_Window_Substring/Solution.java) | 困难 | 哈希表 字符串 滑动窗口                                    |
| 78   | [子集](https://leetcode-cn.com/problems/subsets/)            | [Java](./src/com/scuyjzh/leetcode/medium/No_0078_Subsets/Solution.java) | 中等 | 位运算 数组 回溯                                          |
| 79   | [单词搜索](https://leetcode-cn.com/problems/word-search/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0079_Word_Search/Solution.java) | 中等 | 数组 回溯 矩阵                                            |
| 84   | [柱状图中最大的矩形](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0084_Largest_Rectangle_in_Histogram/Solution.java) | 困难 | 栈 数组 单调栈                                            |
| 85   | [最大矩形](https://leetcode-cn.com/problems/maximal-rectangle/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0085_Maximal_Rectangle/Solution.java) | 困难 | 栈 数组 动态规划 矩阵 单调栈                              |
| 94   | [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0094_Binary_Tree_Inorder_Traversal/Solution.java) | 简单 | 栈 树 深度优先搜索 二叉树                                 |
| 96   | [不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0096_Unique_Binary_Search_Trees/Solution.java) | 中等 | 树 二叉搜索树 数学 动态规划 二叉树                        |
| 98   | [验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0098_Validate_Binary_Search_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉搜索树 二叉树                         |
| 101  | [对称二叉树](https://leetcode-cn.com/problems/symmetric-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0101_Symmetric_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树                       |
| 102  | [二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0102_Binary_Tree_Level_Order_Traversal/Solution.java) | 中等 | 树 广度优先搜索 二叉树                                    |
| 104  | [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0104_Maximum_Depth_of_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树                       |
| 105  | [从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal/Solution.java) | 中等 | 树 数组 哈希表 分治 二叉树                                |
| 114  | [二叉树展开为链表](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0114_Flatten_Binary_Tree_to_Linked_List/Solution.java) | 中等 | 栈 树 深度优先搜索 链表 二叉树                            |
| 121  | [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0121_Best_Time_to_Buy_and_Sell_Stock/Solution.java) | 简单 | 数组 动态规划                                             |
| 124  | [二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0124_Binary_Tree_Maximum_Path_Sum/Solution.java) | 困难 | 树 深度优先搜索 动态规划 二叉树                           |
| 128  | [最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0128_Longest_Consecutive_Sequence/Solution.java) | 中等 | 并查集 数组 哈希表                                        |
| 136  | [只出现一次的数字](https://leetcode-cn.com/problems/single-number/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0136_Single_Number/Solution.java) | 简单 | 位运算 数组                                               |
| 139  | [单词拆分](https://leetcode-cn.com/problems/word-break/)     | [Java](./src/com/scuyjzh/leetcode/medium/No_0139_Word_Break/Solution.java) | 中等 | 字典树 记忆化搜索 哈希表 字符串 动态规划                  |
| 141  | [环形链表](https://leetcode-cn.com/problems/linked-list-cycle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0141_Linked_List_Cycle/Solution.java) | 简单 | 哈希表 链表 双指针                                        |
| 142  | [环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0142_Linked_List_Cycle_II/Solution.java) | 中等 | 哈希表 链表 双指针                                        |
| 146  | [LRU 缓存](https://leetcode-cn.com/problems/lru-cache/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0146_LRU_Cache/LRUCache.java) | 中等 | 设计 哈希表 链表 双向链表                                 |
| 148  | [排序链表](https://leetcode-cn.com/problems/sort-list/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0148_Sort_List/Solution.java) | 中等 | 链表 双指针 分治 排序 归并排序                            |
| 152  | [乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0152_Maximum_Product_Subarray/Solution.java) | 中等 | 数组 动态规划                                             |
| 155  | [最小栈](https://leetcode-cn.com/problems/min-stack/)        | [Java](./src/com/scuyjzh/leetcode/easy/No_0155_Min_Stack/MinStack.java) | 简单 | 栈 设计                                                   |
| 160  | [相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0160_Intersection_of_Two_Linked_Lists/Solution.java) | 简单 | 哈希表 链表 双指针                                        |
| 169  | [多数元素](https://leetcode-cn.com/problems/majority-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0169_Majority_Element/Solution.java) | 简单 | 数组 哈希表 分治 计数 排序                                |
| 198  | [打家劫舍](https://leetcode-cn.com/problems/house-robber/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0198_House_Robber/Solution.java) | 中等 | 数组 动态规划                                             |
| 200  | [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0200_Number_of_Islands/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 并查集 数组 矩阵                |
| 206  | [反转链表](https://leetcode-cn.com/problems/reverse-linked-list/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0206_Reverse_Linked_List/Solution.java) | 简单 | 递归 链表                                                 |
| 207  | [课程表](https://leetcode-cn.com/problems/course-schedule/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0207_Course_Schedule/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 图 拓扑排序                     |
| 208  | [实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0208_Implement_Trie/Trie.java) | 中等 | 设计 字典树 哈希表 字符串                                 |
| 215  | [数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0215_Kth_Largest_Element_in_an_Array/Solution.java) | 中等 | 数组 分治 快速排序 排序 堆（优先队列）                    |
| 221  | [最大正方形](https://leetcode-cn.com/problems/maximal-square/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0221_Maximal_Square/Solution.java) | 中等 | 数组 动态规划 矩阵                                        |
| 226  | [翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0226_Invert_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树                       |
| 234  | [回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0234_Palindrome_Linked_List/Solution.java) | 简单 | 栈 递归 链表 双指针                                       |
| 236  | [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0236_Lowest_Common_Ancestor_of_a_Binary_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉树                                    |
| 238  | [除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0238_Product_of_Array_Except_Self/Solution.java) | 中等 | 数组 前缀和                                               |
| 239  | [滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0239_Sliding_Window_Maximum/Solution.java) | 困难 | 队列 数组 滑动窗口 单调队列 堆（优先队列）                |
| 240  | [搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0240_Search_a_2D_Matrix_II/Solution.java) | 中等 | 数组 二分查找 分治 矩阵                                   |
| 253  | [会议室 II](https://leetcode-cn.com/problems/meeting-rooms-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0253_Meeting_Rooms_II/Solution.java) | 中等 | 贪心 数组 双指针 排序 堆（优先队列）                      |
| 279  | [完全平方数](https://leetcode-cn.com/problems/perfect-squares/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0279_Perfect_Squares/Solution.java) | 中等 | 广度优先搜索 数学 动态规划                                |
| 283  | [移动零](https://leetcode-cn.com/problems/move-zeroes/)      | [Java](./src/com/scuyjzh/leetcode/easy/No_0283_Move_Zeroes/Solution.java) | 简单 | 数组 双指针                                               |
| 287  | [寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0287_Find_the_Duplicate_Number/Solution.java) | 中等 | 位运算 数组 双指针 二分查找                               |
| 297  | [二叉树的序列化与反序列化](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0297_Serialize_and_Deserialize_Binary_Tree/Codec.java) | 困难 | 树 深度优先搜索 二叉搜索树 设计 字符串 二叉树             |
| 300  | [最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0300_Longest_Increasing_Subsequence/Solution.java) | 中等 | 数组 二分查找 动态规划                                    |
| 301  | [删除无效的括号](https://leetcode-cn.com/problems/remove-invalid-parentheses/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0301_Remove_Invalid_Parentheses/Solution.java) | 困难 | 广度优先搜索 字符串 回溯                                  |
| 309  | [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown/Solution.java) | 中等 | 数组 动态规划                                             |
| 312  | [戳气球](https://leetcode-cn.com/problems/burst-balloons/)   | [Java](./src/com/scuyjzh/leetcode/hard/No_0312_Burst_Balloons/Solution.java) | 困难 | 数组 动态规划                                             |
| 322  | [零钱兑换](https://leetcode-cn.com/problems/coin-change/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0322_Coin_Change/Solution.java) | 中等 | 广度优先搜索 数组 动态规划                                |
| 337  | [打家劫舍 III](https://leetcode-cn.com/problems/house-robber-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0337_House_Robber_III/Solution.java) | 中等 | 树 深度优先搜索 动态规划 二叉树                           |
| 338  | [比特位计数](https://leetcode-cn.com/problems/counting-bits/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0338_Counting_Bits/Solution.java) | 简单 | 位运算 动态规划                                           |
| 347  | [前 K 个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0347_Top_K_Frequent_Elements/Solution.java) | 中等 | 数组 哈希表 分治 桶排序 计数 快速排序 排序 堆（优先队列） |
| 394  | [字符串解码](https://leetcode-cn.com/problems/decode-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0394_Decode_String/Solution.java) | 中等 | 栈 递归 字符串                                            |
| 399  | [除法求值](https://leetcode-cn.com/problems/evaluate-division/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0399_Evaluate_Division/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 并查集 图 数组 最短路           |
| 406  | [根据身高重建队列](https://leetcode-cn.com/problems/queue-reconstruction-by-height/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0406_Queue_Reconstruction_by_Height/Solution.java) | 中等 | 贪心 数组 排序                                            |
| 416  | [分割等和子集](https://leetcode-cn.com/problems/partition-equal-subset-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0416_Partition_Equal_Subset_Sum/Solution.java) | 中等 | 数组 动态规划                                             |
| 437  | [路径总和 III](https://leetcode-cn.com/problems/path-sum-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0437_Path_Sum_III/Solution.java) | 中等 | 树 深度优先搜索 二叉树                                    |
| 438  | [找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0438_Find_All_Anagrams_in_a_String/Solution.java) | 中等 | 哈希表 字符串 滑动窗口                                    |
| 448  | [找到所有数组中消失的数字](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0448_Find_All_Numbers_Disappeared_in_an_Array/Solution.java) | 简单 | 数组 哈希表                                               |
| 461  | [汉明距离](https://leetcode-cn.com/problems/hamming-distance/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0461_Hamming_Distance/Solution.java) | 简单 | 位运算                                                    |
| 494  | [目标和](https://leetcode-cn.com/problems/target-sum/)       | [Java](./src/com/scuyjzh/leetcode/medium/No_0494_Target_Sum/Solution.java) | 中等 | 数组 动态规划 回溯                                        |
| 538  | [把二叉搜索树转换为累加树](https://leetcode-cn.com/problems/convert-bst-to-greater-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0538_Convert_BST_to_Greater_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉搜索树 二叉树                         |
| 543  | [二叉树的直径](https://leetcode-cn.com/problems/diameter-of-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0543_Diameter_of_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 二叉树                                    |
| 560  | [和为 K 的子数组](https://leetcode-cn.com/problems/subarray-sum-equals-k/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0560_Subarray_Sum_Equals_K/Solution.java) | 中等 | 数组 哈希表 前缀和                                        |
| 581  | [最短无序连续子数组](https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0581_Shortest_Unsorted_Continuous_Subarray/Solution.java) | 中等 | 栈 贪心 数组 双指针 排序 单调栈                           |
| 617  | [合并二叉树](https://leetcode-cn.com/problems/merge-two-binary-trees/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0617_Merge_Two_Binary_Trees/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树                       |
| 621  | [任务调度器](https://leetcode-cn.com/problems/task-scheduler/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0621_Task_Scheduler/Solution.java) | 中等 | 贪心 数组 哈希表 计数 排序 堆（优先队列）                 |
| 739  | [每日温度](https://leetcode-cn.com/problems/daily-temperatures/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0739_Daily_Temperatures/Solution.java) | 中等 | 栈 数组 单调栈                                            |

## Tags

### Sliding Window & Two Pointers

#### 循环不变量

| #    | 题目                                                         | 题解                                                         | 难度 | 标签        |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ----------- |
| 26   | [删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0026_Remove_Duplicates_from_Sorted_Array/Solution.java) | 简单 | 数组 双指针 |
| 674  | [最长连续递增序列](https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0674_Longest_Continuous_Increasing_Subsequence/Solution.java) | 简单 | 数组        |
| 27   | [移除元素](https://leetcode-cn.com/problems/remove-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0027_Remove_Element/Solution.java) | 简单 | 数组 双指针 |
| 80   | [删除有序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0080_Remove_Duplicates_from_Sorted_Array_II/Solution.java) | 中等 | 数组 双指针 |
| 283  | [移动零](https://leetcode-cn.com/problems/move-zeroes/)      | [Java](./src/com/scuyjzh/leetcode/easy/No_0283_Move_Zeroes/Solution.java) | 简单 | 数组 双指针 |

#### 使用循环不变量写对代码

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                   |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | -------------------------------------- |
| 75   | [颜色分类](https://leetcode-cn.com/problems/sort-colors/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0075_Sort_Colors/Solution.java) | 中等 | 数组 双指针 排序                       |
| 215  | [数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0215_Kth_Largest_Element_in_an_Array/Solution.java) | 中等 | 数组 分治 快速排序 排序 堆（优先队列） |

#### 滑动窗口 1：同向交替移动的两个变量

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------ |
| 643  | [子数组最大平均数 I](https://leetcode-cn.com/problems/maximum-average-subarray-i/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0643_Maximum_Average_Subarray_I/Solution.java) | 简单 | 数组 滑动窗口                        |
| 1052 | [爱生气的书店老板](https://leetcode-cn.com/problems/grumpy-bookstore-owner/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1052_Grumpy_Bookstore_Owner/Solution.java) | 中等 | 数组 滑动窗口                        |
| 1423 | [可获得的最大点数](https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1423_Maximum_Points_You_Can_Obtain_from_Cards/Solution.java) | 中等 | 数组 前缀和 滑动窗口                 |
| 1456 | [定长子串中元音的最大数目](https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1456_Maximum_Number_of_Vowels_in_a_Substring_of_Given_Length/Solution.java) | 中等 | 字符串 滑动窗口                      |
| 1658 | [将 x 减到 0 的最小操作数](https://leetcode-cn.com/problems/minimum-operations-to-reduce-x-to-zero/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1658_Minimum_Operations_to_Reduce_X_to_Zero/Solution.java) | 中等 | 数组 哈希表 二分查找 前缀和 滑动窗口 |

#### 滑动窗口 2：不定长度的滑动窗口

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                          |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ----------------------------- |
| 76   | [最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0076_Minimum_Window_Substring/Solution.java) | 困难 | 哈希表 字符串 滑动窗口        |
| 424  | [替换后的最长重复字符](https://leetcode-cn.com/problems/longest-repeating-character-replacement/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0424_Longest_Repeating_Character_Replacement/Solution.java) | 中等 | 哈希表 字符串 滑动窗口        |
| 3    | [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0003_Longest_Substring_Without_Repeating_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口        |
| 209  | [长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0209_Minimum_Size_Subarray_Sum/Solution.java) | 中等 | 数组 二分查找 前缀和 滑动窗口 |
| 1695 | [删除子数组的最大得分](https://leetcode-cn.com/problems/maximum-erasure-value/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1695_Maximum_Erasure_Value/Solution.java) | 中等 | 数组 哈希表 滑动窗口          |
| 438  | [找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0438_Find_All_Anagrams_in_a_String/Solution.java) | 中等 | 哈希表 字符串 滑动窗口        |
| 567  | [字符串的排列](https://leetcode-cn.com/problems/permutation-in-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0567_Permutation_in_String/Solution.java) | 中等 | 哈希表 双指针 字符串 滑动窗口 |
| 487  | [最大连续1的个数 II](https://leetcode-cn.com/problems/max-consecutive-ones-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0487_Max_Consecutive_Ones_II/Solution.java) | 中等 | 数组 动态规划 滑动窗口        |
| 1004 | [最大连续1的个数 III](https://leetcode-cn.com/problems/max-consecutive-ones-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1004_Max_Consecutive_Ones_III/Solution.java) | 中等 | 数组 二分查找 前缀和 滑动窗口 |
| 1208 | [尽可能使字符串相等](https://leetcode-cn.com/problems/get-equal-substrings-within-budget/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1208_Get_Equal_Substrings_Within_Budget/Solution.java) | 中等 | 数组 二分查找 前缀和 滑动窗口 |
| 1493 | [删掉一个元素以后全为 1 的最长子数组](https://leetcode-cn.com/problems/longest-subarray-of-1s-after-deleting-one-element/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1493_Longest_Subarray_of_1_s_After_Deleting_One_Element/Solution.java) | 中等 | 数学 动态规划 滑动窗口        |
| 978  | [最长湍流子数组](https://leetcode-cn.com/problems/longest-turbulent-subarray/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0978_Longest_Turbulent_Subarray/Solution.java) | 中等 | 数组 动态规划 滑动窗口        |
| 1100 | [长度为 K 的无重复字符子串](https://leetcode-cn.com/problems/find-k-length-substrings-with-no-repeated-characters/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_1100_Find_K_Length_Substrings_With_No_Repeated_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口        |
| 1151 | [最少交换次数来组合所有的 1](https://leetcode-cn.com/problems/minimum-swaps-to-group-all-1s-together/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_1151_Minimum_Swaps_to_Group_All_1_s_Together/Solution.java) | 中等 | 数组 滑动窗口                 |
| 1176 | [健身计划评估](https://leetcode-cn.com/problems/diet-plan-performance/) | [Java](./src/com/scuyjzh/leetcode/easy/No_1176_Diet_Plan_Performance/Solution.java) | 简单 | 数组 滑动窗口                 |

#### 滑动窗口 3：计数问题选讲

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                      |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------- |
| 159  | [至多包含两个不同字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0159_Longest_Substring_with_At_Most_Two_Distinct_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口    |
| 340  | [至多包含 K 个不同字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0340_Longest_Substring_with_At_Most_K_Distinct_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口    |
| 795  | [区间子数组个数](https://leetcode-cn.com/problems/number-of-subarrays-with-bounded-maximum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0795_Number_of_Subarrays_with_Bounded_Maximum/Solution.java) | 中等 | 数组 双指针               |
| 992  | [K 个不同整数的子数组](https://leetcode-cn.com/problems/subarrays-with-k-different-integers/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0992_Subarrays_with_K_Different_Integers/Solution.java) | 困难 | 数组 哈希表 计数 滑动窗口 |
| 713  | [乘积小于K的子数组](https://leetcode-cn.com/problems/subarray-product-less-than-k/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0713_Subarray_Product_Less_Than_K/Solution.java) | 中等 | 数组 滑动窗口             |
| 904  | [水果成篮](https://leetcode-cn.com/problems/fruit-into-baskets/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0904_Fruit_Into_Baskets/Solution.java) | 中等 | 数组 哈希表 滑动窗口      |
| 1358 | [包含所有三种字符的子字符串数目](https://leetcode-cn.com/problems/number-of-substrings-containing-all-three-characters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1358_Number_of_Substrings_Containing_All_Three_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口    |
| 467  | [环绕字符串中唯一的子字符串](https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0467_Unique_Substrings_in_Wraparound_String/Solution.java) | 中等 | 字符串 动态规划           |
| 719  | [找出第 k 小的距离对](https://leetcode-cn.com/problems/find-k-th-smallest-pair-distance/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0719_Find_K_th_Smallest_Pair_Distance/Solution.java) | 困难 | 数组 双指针 二分查找 排序 |

#### 滑动窗口 4：使用数据结构维护窗口性质

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | --------------------------------------------------- |
| 239  | [滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0239_Sliding_Window_Maximum/Solution.java) | 困难 | 队列 数组 滑动窗口 单调队列 堆（优先队列）          |
| 480  | [滑动窗口中位数](https://leetcode-cn.com/problems/sliding-window-median/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0480_Sliding_Window_Median/Solution.java) | 困难 | 数组 哈希表 滑动窗口 堆（优先队列）                 |
| 220  | [存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0220_Contains_Duplicate_III/Solution.java) | 中等 | 数组 桶排序 有序集合 排序 滑动窗口                  |
| 1438 | [绝对差不超过限制的最长连续子数组](https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) | [Java](./src/com/scuyjzh/leetcode/medium/No_1438_Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_or_Equal_to_Limit/Solution.java) | 中等 | 队列 数组 有序集合 滑动窗口 单调队列 堆（优先队列） |

#### 链表中的双指针问题

| #    | 题目                                                         | 题解                                                         | 难度 | 标签               |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------ |
| 141  | [环形链表](https://leetcode-cn.com/problems/linked-list-cycle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0141_Linked_List_Cycle/Solution.java) | 简单 | 哈希表 链表 双指针 |
| 19   | [删除链表的倒数第 N 个节点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0019_Remove_Nth_Node_From_End_of_List/Solution.java) | 中等 | 链表 双指针        |
| 876  | [链表的中间结点](https://leetcode-cn.com/problems/middle-of-the-linked-list/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0876_Middle_of_the_Linked_List/Solution.java) | 简单 | 链表 双指针        |
| 142  | [环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0142_Linked_List_Cycle_II/Solution.java) | 中等 | 哈希表 链表 双指针 |
| 160  | [相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0160_Intersection_of_Two_Linked_Lists/Solution.java) | 简单 | 哈希表 链表 双指针 |

#### 双指针：相向交替移动的两个变量

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                     |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------- |
| 11   | [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0011_Container_With_Most_Water/Solution.java) | 中等 | 贪心 数组 双指针                         |
| 167  | [两数之和 II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0167_Two_Sum_II/Solution.java) | 中等 | 数组 双指针 二分查找                     |
| 15   | [三数之和](https://leetcode-cn.com/problems/3sum/)           | [Java](./src/com/scuyjzh/leetcode/medium/No_0015_3Sum/Solution.java) | 中等 | 数组 双指针 排序                         |
| 125  | [验证回文串](https://leetcode-cn.com/problems/valid-palindrome/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0125_Valid_Palindrome/Solution.java) | 简单 | 双指针 字符串                            |
| 344  | [反转字符串](https://leetcode-cn.com/problems/reverse-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0344_Reverse_String/Solution.java) | 简单 | 递归 双指针 字符串                       |
| 345  | [反转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0345_Reverse_Vowels_of_a_String/Solution.java) | 简单 | 双指针 字符串                            |
| 16   | [最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0016_3Sum_Closest/Solution.java) | 中等 | 数组 双指针 排序                         |
| 18   | [四数之和](https://leetcode-cn.com/problems/4sum/)           | [Java](./src/com/scuyjzh/leetcode/medium/No_0018_4Sum/Solution.java) | 中等 | 数组 双指针 排序                         |
| 42   | [接雨水](https://leetcode-cn.com/problems/trapping-rain-water/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0042_Trapping_Rain_Water/Solution.java) | 困难 | 栈 数组 双指针 动态规划 单调栈           |
| 658  | [找到 K 个最接近的元素](https://leetcode-cn.com/problems/find-k-closest-elements/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0658_Find_K_Closest_Elements/Solution.java) | 中等 | 数组 双指针 二分查找 排序 堆（优先队列） |
| 259  | [较小的三数之和](https://leetcode-cn.com/problems/3sum-smaller/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0259_3Sum_Smaller/Solution.java) | 中等 | 数组 双指针 二分查找 排序                |
| 845  | [数组中的最长山脉](https://leetcode-cn.com/problems/longest-mountain-in-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0845_Longest_Mountain_in_Array/Solution.java) | 中等 | 数组 双指针 动态规划 枚举                |
| 881  | [救生艇](https://leetcode-cn.com/problems/boats-to-save-people/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0881_Boats_to_Save_People/Solution.java) | 中等 | 贪心 数组 双指针 排序                    |
| 925  | [长按键入](https://leetcode-cn.com/problems/long-pressed-name/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0925_Long_Pressed_Name/Solution.java) | 简单 | 双指针 字符串                            |
| 977  | [有序数组的平方](https://leetcode-cn.com/problems/squares-of-a-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0977_Squares_of_a_Sorted_Array/Solution.java) | 简单 | 数组 双指针 排序                         |
| 1229 | [安排会议日程](https://leetcode-cn.com/problems/meeting-scheduler/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_1229_Meeting_Scheduler/Solution.java) | 中等 | 数组 双指针 排序                         |

### Array

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------------------- |
| 27   | [移除元素](https://leetcode-cn.com/problems/remove-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0027_Remove_Element/Solution.java) | 简单 | 数组 双指针                                          |
| 26   | [删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0026_Remove_Duplicates_from_Sorted_Array/Solution.java) | 简单 | 数组 双指针                                          |
| 80   | [删除有序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0080_Remove_Duplicates_from_Sorted_Array_II/Solution.java) | 中等 | 数组 双指针                                          |
| 277  | [搜寻名人](https://leetcode-cn.com/problems/find-the-celebrity/) 🔒 | [Java](https://leetcode-cn.com/problems/find-the-celebrity/solution/) | 中等 | 贪心 图 双指针 交互                                  |
| 189  | [旋转数组](https://leetcode-cn.com/problems/rotate-array/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0189_Rotate_Array/Solution.java) | 中等 | 数组 数学 双指针                                     |
| 41   | [缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0041_First_Missing_Positive/Solution.java) | 困难 | 数组 哈希表                                          |
| 299  | [猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0299_Bulls_and_Cows/Solution.java) | 中等 | 哈希表 字符串 计数                                   |
| 134  | [加油站](https://leetcode-cn.com/problems/gas-station/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0134_Gas_Station/Solution.java) | 中等 | 贪心 数组                                            |
| 118  | [杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0118_Pascals_Triangle/Solution.java) | 简单 | 数组 动态规划                                        |
| 119  | [杨辉三角 II](https://leetcode-cn.com/problems/pascals-triangle-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0119_Pascals_Triangle_II/Solution.java) | 简单 | 数组 动态规划                                        |
| 169  | [多数元素](https://leetcode-cn.com/problems/majority-element/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0169_Majority_Element/Solution.java) | 简单 | 数组 哈希表 分治 计数 排序                           |
| 229  | [求众数 II](https://leetcode-cn.com/problems/majority-element-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0229_Majority_Element_II/Solution.java) | 中等 | 数组 哈希表 计数 排序                                |
| 274  | [H 指数](https://leetcode-cn.com/problems/h-index/)          | [Java](./src/com/scuyjzh/leetcode/medium/No_0274_H_Index/Solution.java) | 中等 | 数组 计数排序 排序                                   |
| 275  | [H 指数 II](https://leetcode-cn.com/problems/h-index-ii/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0275_H_Index_II/Solution.java) | 中等 | 数组 二分查找                                        |
| 243  | [最短单词距离](https://leetcode-cn.com/problems/shortest-word-distance/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0243_Shortest_Word_Distance/Solution.java) | 简单 | 数组 字符串                                          |
| 244  | [最短单词距离 II](https://leetcode-cn.com/problems/shortest-word-distance-ii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0244_Shortest_Word_Distance_II/WordDistance.java) | 中等 | 设计 数组 哈希表 双指针 字符串                       |
| 245  | [最短单词距离 III](https://leetcode-cn.com/problems/shortest-word-distance-iii) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0245_Shortest_Word_Distance_III/Solution.java) | 中等 | 数组 字符串                                          |
| 217  | [存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0217_Contains_Duplicate/Solution.java) | 简单 | 数组 哈希表 排序                                     |
| 219  | [存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0219_Contains_Duplicate_II/Solution.java) | 简单 | 数组 哈希表 滑动窗口                                 |
| 220  | [存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0220_Contains_Duplicate_III/Solution.java) | 中等 | 数组 桶排序 有序集合 排序 滑动窗口                   |
| 55   | [跳跃游戏](https://leetcode-cn.com/problems/jump-game/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0055_Jump_Game/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 45   | [跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0045_Jump_Game_II/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 121  | [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0121_Best_Time_to_Buy_and_Sell_Stock/Solution.java) | 简单 | 数组 动态规划                                        |
| 122  | [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0122_Best_Time_to_Buy_and_Sell_Stock_II/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 123  | [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0123_Best_Time_to_Buy_and_Sell_Stock_III/Solution.java) | 困难 | 数组 动态规划                                        |
| 188  | [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0188_Best_Time_to_Buy_and_Sell_Stock_IV/Solution.java) | 困难 | 数组 动态规划                                        |
| 309  | [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown/Solution.java) | 中等 | 数组 动态规划                                        |
| 714  | [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 11   | [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0011_Container_With_Most_Water/Solution.java) | 中等 | 贪心 数组 双指针                                     |
| 42   | [接雨水](https://leetcode-cn.com/problems/trapping-rain-water/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0042_Trapping_Rain_Water/Solution.java) | 困难 | 栈 数组 双指针 动态规划 单调栈                       |
| 334  | [递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0334_Increasing_Triplet_Subsequence/Solution.java) | 中等 | 贪心 数组                                            |
| 128  | [最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0128_Longest_Consecutive_Sequence/Solution.java) | 中等 | 并查集 数组 哈希表                                   |
| 164  | [最大间距](https://leetcode-cn.com/problems/maximum-gap/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0164_Maximum_Gap/Solution.java) | 困难 | 数组 桶排序 基数排序 排序                            |
| 287  | [寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0287_Find_the_Duplicate_Number/Solution.java) | 中等 | 位运算 数组 双指针 二分查找                          |
| 135  | [分发糖果](https://leetcode-cn.com/problems/candy/)          | [Java](./src/com/scuyjzh/leetcode/hard/No_0135_Candy/Solution.java) | 困难 | 贪心 数组                                            |
| 330  | [按要求补齐数组](https://leetcode-cn.com/problems/patching-array/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0330_Patching_Array/Solution.java) | 困难 | 贪心 数组                                            |
| 4    | [寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0004_Median_of_Two_Sorted_Arrays/Solution.java) | 困难 | 数组 二分查找 分治                                   |
| 321  | [拼接最大数](https://leetcode-cn.com/problems/create-maximum-number/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0321_Create_Maximum_Number/Solution.java) | 困难 | 栈 贪心 单调栈                                       |
| 327  | [区间和的个数](https://leetcode-cn.com/problems/count-of-range-sum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0327_Count_of_Range_Sum/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |
| 289  | [生命游戏](https://leetcode-cn.com/problems/game-of-life/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0289_Game_of_Life/Solution.java) | 中等 | 数组 矩阵 模拟                                       |
| 57   | [插入区间](https://leetcode-cn.com/problems/insert-interval/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0057_Insert_Interval/Solution.java) | 中等 | 数组                                                 |
| 56   | [合并区间](https://leetcode-cn.com/problems/merge-intervals/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0056_Merge_Intervals/Solution.java) | 中等 | 数组 排序                                            |
| 252  | [会议室](https://leetcode-cn.com/problems/meeting-rooms/) 🔒  | [Java](./src/com/scuyjzh/leetcode/easy/No_0252_Meeting_Rooms/Solution.java) | 简单 | 数组 排序                                            |
| 253  | [会议室 II](https://leetcode-cn.com/problems/meeting-rooms-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0253_Meeting_Rooms_II/Solution.java) | 中等 | 贪心 数组 双指针 排序 堆（优先队列）                 |
| 352  | [将数据流变为多个不相交区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0352_Data_Stream_as_Disjoint_Intervals/SummaryRanges.java) | 困难 | 设计 二分查找 有序集合                               |
| 57   | [插入区间](https://leetcode-cn.com/problems/insert-interval/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0057_Insert_Interval/Solution.java) | 中等 | 数组                                                 |
| 56   | [合并区间](https://leetcode-cn.com/problems/merge-intervals/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0056_Merge_Intervals/Solution.java) | 中等 | 数组 排序                                            |
| 252  | [会议室](https://leetcode-cn.com/problems/meeting-rooms/) 🔒  | [Java](./src/com/scuyjzh/leetcode/easy/No_0252_Meeting_Rooms/Solution.java) | 简单 | 数组 排序                                            |
| 253  | [会议室 II](https://leetcode-cn.com/problems/meeting-rooms-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0253_Meeting_Rooms_II/Solution.java) | 中等 | 贪心 数组 双指针 排序 堆（优先队列）                 |
| 352  | [将数据流变为多个不相交区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0352_Data_Stream_as_Disjoint_Intervals/SummaryRanges.java) | 困难 | 设计 二分查找 有序集合                               |
| 239  | [滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0239_Sliding_Window_Maximum/Solution.java) | 困难 | 队列 数组 滑动窗口 单调队列 堆（优先队列）           |
| 295  | [数据流的中位数](https://leetcode-cn.com/problems/find-median-from-data-stream/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0295_Find_Median_from_Data_Stream/MedianFinder.java) | 困难 | 设计 双指针 数据流 排序 堆（优先队列）               |
| 53   | [最大子数组和](https://leetcode-cn.com/problems/maximum-subarray/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0053_Maximum_Subarray/Solution.java) | 简单 | 数组 分治 动态规划                                   |
| 325  | [和等于 k 的最长子数组长度 ](https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0325_Maximum_Size_Subarray_Sum_Equals_k/Solution.java) | 中等 | 数组 哈希表                                          |
| 209  | [长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0209_Minimum_Size_Subarray_Sum/Solution.java) | 中等 | 数组 二分查找 前缀和 滑动窗口                        |
| 238  | [除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0238_Product_of_Array_Except_Self/Solution.java) | 中等 | 数组 前缀和                                          |
| 152  | [乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0152_Maximum_Product_Subarray/Solution.java) | 中等 | 数组 动态规划                                        |
| 228  | [汇总区间](https://leetcode-cn.com/problems/summary-ranges/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0228_Summary_Ranges/Solution.java) | 简单 | 数组                                                 |
| 163  | [缺失的区间](https://leetcode-cn.com/problems/missing-ranges/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0163_Missing_Ranges/Solution.java) | 简单 | 数组                                                 |
| 88   | [合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0088_Merge_Sorted_Array/Solution.java) | 简单 | 数组 双指针 排序                                     |
| 75   | [颜色分类](https://leetcode-cn.com/problems/sort-colors/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0075_Sort_Colors/Solution.java) | 中等 | 数组 双指针 排序                                     |
| 283  | [移动零](https://leetcode-cn.com/problems/move-zeroes/)      | [Java](./src/com/scuyjzh/leetcode/easy/No_0283_Move_Zeroes/Solution.java) | 简单 | 数组 双指针                                          |
| 376  | [摆动序列](https://leetcode-cn.com/problems/wiggle-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0376_Wiggle_Subsequence/Solution.java) | 中等 | 贪心 数组 动态规划                                   |
| 280  | [摆动排序](https://leetcode-cn.com/problems/wiggle-sort/) 🔒  | [Java](./src/com/scuyjzh/leetcode/medium/No_0280_Wiggle_Sort/Solution.java) | 中等 | 贪心 数组 排序                                       |
| 324  | [摆动排序 II](https://leetcode-cn.com/problems/wiggle-sort-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0324_Wiggle_Sort_II/Solution.java) | 中等 | 数组 分治 快速排序 排序                              |
| 278  | [第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/) | [Java](https://leetcode-cn.com/problems/first-bad-version/solution/) | 简单 | 二分查找 交互                                        |
| 35   | [搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0035_Search_Insert_Position/Solution.java) | 简单 | 数组 二分查找                                        |
| 33   | [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0033_Search_in_Rotated_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |
| 81   | [搜索旋转排序数组 II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0081_Search_in_Rotated_Sorted_Array_II/Solution.java) | 中等 | 数组 二分查找                                        |
| 153  | [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0153_Find_Minimum_in_Rotated_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |
| 154  | [寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0154_Find_Minimum_in_Rotated_Sorted_Array_II/Solution.java) | 困难 | 数组 二分查找                                        |
| 162  | [寻找峰值](https://leetcode-cn.com/problems/find-peak-element/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0162_Find_Peak_Element/Solution.java) | 中等 | 数组 二分查找                                        |
| 374  | [猜数字大小](https://leetcode-cn.com/problems/guess-number-higher-or-lower/) | [Java](https://leetcode-cn.com/problems/guess-number-higher-or-lower/solution/cai-shu-zi-da-xiao-by-leetcode-solution-qdzu/) | 简单 | 二分查找 交互                                        |
| 34   | [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0034_Find_First_and_Last_Position_of_Element_in_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |
| 349  | [两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0349_Intersection_of_Two_Arrays/Solution.java) | 简单 | 数组 哈希表 双指针 二分查找 排序                     |
| 350  | [两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0350_Intersection_of_Two_Arrays_II/Solution.java) | 简单 | 数组 哈希表 双指针 二分查找 排序                     |
| 315  | [计算右侧小于当前元素的个数](https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0315_Count_of_Smaller_Numbers_After_Self/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |
| 300  | [最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0300_Longest_Increasing_Subsequence/Solution.java) | 中等 | 数组 二分查找 动态规划                               |
| 354  | [俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0354_Russian_Doll_Envelopes/Solution.java) | 困难 | 数组 二分查找 动态规划 排序                          |

### String

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                        |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------------- |
| 28   | [实现 strStr()](https://leetcode-cn.com/problems/implement-strstr/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0028_strStr/Solution.java) | 简单 | 双指针 字符串 字符串匹配                    |
| 14   | [最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0014_Longest_Common_Prefix/Solution.java) | 简单 | 字符串                                      |
| 58   | [最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0058_Length_of_Last_Word/Solution.java) | 简单 | 字符串                                      |
| 387  | [字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0387_First_Unique_Character_in_a_String/Solution.java) | 简单 | 队列 哈希表 字符串 计数                     |
| 383  | [赎金信](https://leetcode-cn.com/problems/ransom-note/)      | [Java](./src/com/scuyjzh/leetcode/easy/No_0383_Ransom_Note/Solution.java) | 简单 | 哈希表 字符串 计数                          |
| 344  | [反转字符串](https://leetcode-cn.com/problems/reverse-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0344_Reverse_String/Solution.java) | 简单 | 递归 双指针 字符串                          |
| 151  | [翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0151_Reverse_Words_in_a_String/Solution.java) | 中等 | 双指针 字符串                               |
| 186  | [翻转字符串里的单词 II](https://leetcode-cn.com/problems/reverse-words-in-a-string-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0186_Reverse_Words_in_a_String_II/Solution.java) | 中等 | 双指针 字符串                               |
| 345  | [反转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0345_Reverse_Vowels_of_a_String/Solution.java) | 简单 | 双指针 字符串                               |
| 205  | [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0205_Isomorphic_Strings/Solution.java) | 简单 | 哈希表 字符串                               |
| 293  | [翻转游戏](https://leetcode-cn.com/problems/flip-game/) 🔒    | [Java](./src/com/scuyjzh/leetcode/easy/No_0293_Flip_Game/Solution.java) | 简单 | 字符串                                      |
| 294  | [翻转游戏 II](https://leetcode-cn.com/problems/flip-game-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0294_Flip_Game_II/Solution.java) | 中等 | 记忆化搜索 数学 动态规划 回溯 博弈          |
| 290  | [单词规律](https://leetcode-cn.com/problems/word-pattern/)   | [Java](./src/com/scuyjzh/leetcode/easy/No_0290_Word_Pattern/Solution.java) | 简单 | 哈希表 字符串                               |
| 242  | [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0242_Valid_Anagram/Solution.java) | 简单 | 哈希表 字符串 排序                          |
| 49   | [字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0049_Group_Anagrams/Solution.java) | 中等 | 哈希表 字符串 排序                          |
| 249  | [移位字符串分组](https://leetcode-cn.com/problems/group-shifted-strings/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0249_Group_Shifted_Strings/Solution.java) | 中等 | 数组 哈希表 字符串                          |
| 87   | [扰乱字符串](https://leetcode-cn.com/problems/scramble-string/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0087_Scramble_String/Solution.java) | 困难 | 字符串 动态规划                             |
| 179  | [最大数](https://leetcode-cn.com/problems/largest-number/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0179_Largest_Number/Solution.java) | 中等 | 贪心 字符串 排序                            |
| 6    | [Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0006_ZigZag_Conversion/Solution.java) | 中等 | 字符串                                      |
| 161  | [相隔为 1 的编辑距离](https://leetcode-cn.com/problems/one-edit-distance/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0161_One_Edit_Distance/Solution.java) | 中等 | 双指针 字符串                               |
| 38   | [外观数列](https://leetcode-cn.com/problems/count-and-say/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0038_Count_and_Say/Solution.java) | 中等 | 字符串                                      |
| 358  | [K 距离间隔重排字符串](https://leetcode-cn.com/problems/rearrange-string-k-distance-apart/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0358_Rearrange_String_k_Distance_Apart/Solution.java) | 困难 | 贪心 哈希表 字符串 计数 排序 堆（优先队列） |
| 316  | [去除重复字母](https://leetcode-cn.com/problems/remove-duplicate-letters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0316_Remove_Duplicate_Letters/Solution.java) | 中等 | 栈 贪心 字符串 单调栈                       |
| 271  | [字符串的编码与解码](https://leetcode-cn.com/problems/encode-and-decode-strings/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0271_Encode_and_Decode_Strings/Codec.java) | 中等 | 设计 数组 字符串                            |
| 168  | [Excel表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0168_Excel_Sheet_Column_Title/Solution.java) | 简单 | 数学 字符串                                 |
| 171  | [Excel 表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0171_Excel_Sheet_Column_Number/Solution.java) | 简单 | 数学 字符串                                 |
| 13   | [罗马数字转整数](https://leetcode-cn.com/problems/roman-to-integer/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0013_Roman_to_Integer/Solution.java) | 简单 | 哈希表 数学 字符串                          |
| 12   | [整数转罗马数字](https://leetcode-cn.com/problems/integer-to-roman/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0012_Integer_to_Roman/Solution.java) | 中等 | 哈希表 数学 字符串                          |
| 273  | [整数转换英文表示](https://leetcode-cn.com/problems/integer-to-english-words/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0273_Integer_to_English_Words/Solution.java) | 困难 | 递归 数学 字符串                            |
| 246  | [中心对称数](https://leetcode-cn.com/problems/strobogrammatic-number/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0246_Strobogrammatic_Number/Solution.java) | 简单 | 哈希表 双指针 字符串                        |
| 247  | [中心对称数 II ](https://leetcode-cn.com/problems/strobogrammatic-number-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0247_Strobogrammatic_Number_II/Solution.java) | 中等 | 递归 数组 字符串                            |
| 248  | [中心对称数 III](https://leetcode-cn.com/problems/strobogrammatic-number-iii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0248_Strobogrammatic_Number_III/Solution.java) | 困难 | 递归 数组 字符串                            |
| 157  | [用 Read4 读取 N 个字符](https://leetcode-cn.com/problems/read-n-characters-given-read4/) 🔒 | [Java](https://leetcode-cn.com/problems/read-n-characters-given-read4/solution/) | 简单 | 字符串 交互 模拟                            |
| 158  | [用 Read4 读取 N 个字符 II](https://leetcode-cn.com/problems/read-n-characters-given-read4-ii-call-multiple-times/) 🔒 | [Java](https://leetcode-cn.com/problems/read-n-characters-given-read4-ii-call-multiple-times/solution/) | 困难 | 字符串 交互 模拟                            |
| 68   | [文本左右对齐](https://leetcode-cn.com/problems/text-justification/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0068_Text_Justification/Solution.java) | 困难 | 字符串 模拟                                 |
| 65   | [有效数字](https://leetcode-cn.com/problems/valid-number/)   | [Java](./src/com/scuyjzh/leetcode/hard/No_0065_Valid_Number/Solution.java) | 困难 | 字符串                                      |
| 76   | [最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0076_Minimum_Window_Substring/Solution.java) | 困难 | 哈希表 字符串 滑动窗口                      |
| 30   | [串联所有单词的子串](https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0030_Substring_with_Concatenation_of_All_Words/Solution.java) | 困难 | 哈希表 字符串 滑动窗口                      |
| 3    | [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0003_Longest_Substring_Without_Repeating_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口                      |
| 340  | [至多包含 K 个不同字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0340_Longest_Substring_with_At_Most_K_Distinct_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口                      |
| 395  | [至少有 K 个重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0395_Longest_Substring_with_At_Least_K_Repeating_Characters/Solution.java) | 中等 | 哈希表 字符串 分治 滑动窗口                 |
| 159  | [至多包含两个不同字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0159_Longest_Substring_with_At_Most_Two_Distinct_Characters/Solution.java) | 中等 | 哈希表 字符串 滑动窗口                      |
| 125  | [验证回文串](https://leetcode-cn.com/problems/valid-palindrome/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0125_Valid_Palindrome/Solution.java) | 简单 | 双指针 字符串                               |
| 266  | [回文排列](https://leetcode-cn.com/problems/palindrome-permutation/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0266_Palindrome_Permutation/Solution.java) | 简单 | 位运算 哈希表 字符串                        |
| 5    | [最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0005_Longest_Palindromic_Substring/Solution.java) | 中等 | 字符串 动态规划                             |
| 9    | [回文数](https://leetcode-cn.com/problems/palindrome-number/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0009_Palindrome_Number/Solution.java) | 简单 | 数学                                        |
| 214  | [最短回文串](https://leetcode-cn.com/problems/shortest-palindrome/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0214_Shortest_Palindrome/Solution.java) | 困难 | 字符串 字符串匹配 哈希函数 滚动哈希         |
| 336  | [回文对](https://leetcode-cn.com/problems/palindrome-pairs/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0336_Palindrome_Pairs/Solution.java) | 困难 | 字典树 数组 哈希表 字符串                   |
| 131  | [分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0131_Palindrome_Partitioning/Solution.java) | 中等 | 字符串 动态规划 回溯                        |
| 132  | [分割回文串 II](https://leetcode-cn.com/problems/palindrome-partitioning-ii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0132_Palindrome_Partitioning_II/Solution.java) | 困难 | 字符串 动态规划                             |
| 267  | [回文排列 II](https://leetcode-cn.com/problems/palindrome-permutation-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0267_Palindrome_Permutation_II/Solution.java) | 中等 | 哈希表 字符串 回溯                          |
| 20   | [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0020_Valid_Parentheses/Solution.java) | 简单 | 栈 字符串                                   |
| 22   | [括号生成](https://leetcode-cn.com/problems/generate-parentheses/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0022_Generate_Parentheses/Solution.java) | 中等 | 字符串 动态规划 回溯                        |
| 32   | [最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0032_Longest_Valid_Parentheses/Solution.java) | 困难 | 栈 字符串 动态规划                          |
| 241  | [为运算表达式设计优先级](https://leetcode-cn.com/problems/different-ways-to-add-parentheses/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0241_Different_Ways_to_Add_Parentheses/Solution.java) | 中等 | 递归 记忆化搜索 数学 字符串 动态规划        |
| 301  | [删除无效的括号](https://leetcode-cn.com/problems/remove-invalid-parentheses/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0301_Remove_Invalid_Parentheses/Solution.java) | 困难 | 广度优先搜索 字符串 回溯                    |
| 392  | [判断子序列](https://leetcode-cn.com/problems/is-subsequence/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0392_Is_Subsequence/Solution.java) | 简单 | 双指针 字符串 动态规划                      |
| 115  | [不同的子序列](https://leetcode-cn.com/problems/distinct-subsequences/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0115_Distinct_Subsequences/Solution.java) | 困难 | 字符串 动态规划                             |
| 187  | [重复的DNA序列](https://leetcode-cn.com/problems/repeated-dna-sequences/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0187_Repeated_DNA_Sequences/Solution.java) | 中等 | 位运算 哈希表 字符串 滑动窗口 滚动哈希      |

### Math

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ----------------------------------- |
| 7    | [整数反转](https://leetcode-cn.com/problems/reverse-integer/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0007_Reverse_Integer/Solution.java) | 简单 | 数学                                |
| 165  | [比较版本号](https://leetcode-cn.com/problems/compare-version-numbers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0165_Compare_Version_Numbers/Solution.java) | 中等 | 双指针 字符串                       |
| 66   | [加一](https://leetcode-cn.com/problems/plus-one/)           | [Java](./src/com/scuyjzh/leetcode/easy/No_0066_Plus_One/Solution.java) | 简单 | 数组 数学                           |
| 8    | [字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0008_String_to_Integer/Solution.java) | 中等 | 字符串                              |
| 258  | [各位相加](https://leetcode-cn.com/problems/add-digits/)     | [Java](./src/com/scuyjzh/leetcode/easy/No_0258_Add_Digits/Solution.java) | 简单 | 数学 数论 模拟                      |
| 67   | [二进制求和](https://leetcode-cn.com/problems/add-binary/)   | [Java](./src/com/scuyjzh/leetcode/easy/No_0067_Add_Binary/Solution.java) | 简单 | 位运算 数学 字符串 模拟             |
| 43   | [字符串相乘](https://leetcode-cn.com/problems/multiply-strings/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0043_Multiply_Strings/Solution.java) | 中等 | 数学 字符串 模拟                    |
| 29   | [两数相除](https://leetcode-cn.com/problems/divide-two-integers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0029_Divide_Two_Integers/Solution.java) | 中等 | 位运算 数学                         |
| 69   | [Sqrt(x)](https://leetcode-cn.com/problems/sqrtx/)           | [Java](./src/com/scuyjzh/leetcode/easy/No_0069_Sqrt/Solution.java) | 简单 | 数学 二分查找                       |
| 50   | [Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)        | [Java](./src/com/scuyjzh/leetcode/medium/No_0050_Pow/Solution.java) | 中等 | 递归 数学                           |
| 367  | [有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0367_Valid_Perfect_Square/Solution.java) | 简单 | 数学 二分查找                       |
| 365  | [水壶问题](https://leetcode-cn.com/problems/water-and-jug-problem/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0365_Water_and_Jug_Problem/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 数学      |
| 204  | [计数质数](https://leetcode-cn.com/problems/count-primes/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0204_Count_Primes/Solution.java) | 中等 | 数组 数学 枚举 数论                 |
| 1    | [两数之和](https://leetcode-cn.com/problems/two-sum/)        | [Java](./src/com/scuyjzh/leetcode/easy/No_0001_Two_Sum/Solution.java) | 简单 | 数组 哈希表                         |
| 167  | [两数之和 II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0167_Two_Sum_II/Solution.java) | 中等 | 数组 双指针 二分查找                |
| 15   | [三数之和](https://leetcode-cn.com/problems/3sum/)           | [Java](./src/com/scuyjzh/leetcode/medium/No_0015_3Sum/Solution.java) | 中等 | 数组 双指针 排序                    |
| 16   | [最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0016_3Sum_Closest/Solution.java) | 中等 | 数组 双指针 排序                    |
| 259  | [较小的三数之和](https://leetcode-cn.com/problems/3sum-smaller/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0259_3Sum_Smaller/Solution.java) | 中等 | 数组 双指针 二分查找 排序           |
| 18   | [四数之和](https://leetcode-cn.com/problems/4sum/)           | [Java](./src/com/scuyjzh/leetcode/medium/No_0018_4Sum/Solution.java) | 中等 | 数组 双指针 排序                    |
| 231  | [2 的幂](https://leetcode-cn.com/problems/power-of-two/)     | [Java](./src/com/scuyjzh/leetcode/easy/No_0231_Power_of_Two/Solution.java) | 简单 | 位运算 递归 数学                    |
| 326  | [3的幂](https://leetcode-cn.com/problems/power-of-three/)    | [Java](./src/com/scuyjzh/leetcode/easy/No_0326_Power_of_Three/Solution.java) | 简单 | 递归 数学                           |
| 342  | [4的幂](https://leetcode-cn.com/problems/power-of-four/)     | [Java](./src/com/scuyjzh/leetcode/easy/No_0342_Power_of_Four/Solution.java) | 简单 | 位运算 递归 数学                    |
| 372  | [超级次方](https://leetcode-cn.com/problems/super-pow/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0372_Super_Pow/Solution.java) | 中等 | 数学 分治                           |
| 233  | [数字 1 的个数](https://leetcode-cn.com/problems/number-of-digit-one/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0233_Number_of_Digit_One/Solution.java) | 困难 | 递归 数学 动态规划                  |
| 319  | [灯泡开关](https://leetcode-cn.com/problems/bulb-switcher/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0319_Bulb_Switcher/Solution.java) | 中等 | 脑筋急转弯 数学                     |
| 292  | [Nim 游戏](https://leetcode-cn.com/problems/nim-game/)       | [Java](./src/com/scuyjzh/leetcode/easy/No_0292_Nim_Game/Solution.java) | 简单 | 脑筋急转弯 数学 博弈                |
| 202  | [快乐数](https://leetcode-cn.com/problems/happy-number/)     | [Java](./src/com/scuyjzh/leetcode/easy/No_0202_Happy_Number/Solution.java) | 简单 | 哈希表 双指针 数学                  |
| 400  | [第 N 位数字](https://leetcode-cn.com/problems/nth-digit/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0400_Nth_Digit/Solution.java) | 中等 | 数学 二分查找                       |
| 263  | [丑数](https://leetcode-cn.com/problems/ugly-number/)        | [Java](./src/com/scuyjzh/leetcode/easy/No_0263_Ugly_Number/Solution.java) | 简单 | 数学                                |
| 264  | [丑数 II](https://leetcode-cn.com/problems/ugly-number-ii/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0264_Ugly_Number_II/Solution.java) | 中等 | 哈希表 数学 动态规划 堆（优先队列） |
| 306  | [累加数](https://leetcode-cn.com/problems/additive-number/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0306_Additive_Number/Solution.java) | 中等 | 字符串 回溯                         |
| 172  | [阶乘后的零](https://leetcode-cn.com/problems/factorial-trailing-zeroes/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0172_Factorial_Trailing_Zeroes/Solution.java) | 中等 | 数学                                |
| 396  | [旋转函数](https://leetcode-cn.com/problems/rotate-function/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0396_Rotate_Function/Solution.java) | 中等 | 数学 动态规划                       |
| 390  | [消除游戏](https://leetcode-cn.com/problems/elimination-game/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0390_Elimination_Game/Solution.java) | 中等 | 数学                                |
| 386  | [字典序排数](https://leetcode-cn.com/problems/lexicographical-numbers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0386_Lexicographical_Numbers/Solution.java) | 中等 | 深度优先搜索 字典树                 |
| 357  | [计算各个位数不同的数字个数](https://leetcode-cn.com/problems/count-numbers-with-unique-digits/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0357_Count_Numbers_with_Unique_Digits/Solution.java) | 中等 | 数学 动态规划 回溯                  |
| 360  | [有序转化数组](https://leetcode-cn.com/problems/sort-transformed-array/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0360_Sort_Transformed_Array/Solution.java) | 中等 | 数组 数学 双指针 排序               |
| 397  | [整数替换](https://leetcode-cn.com/problems/integer-replacement/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0397_Integer_Replacement/Solution.java) | 中等 | 贪心 位运算 记忆化搜索 动态规划     |
| 368  | [最大整除子集](https://leetcode-cn.com/problems/largest-divisible-subset/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0368_Largest_Divisible_Subset/Solution.java) | 中等 | 数组 数学 动态规划 排序             |

### Tree

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ----------------------------------- |
| 144 | [二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0144_Binary_Tree_Preorder_Traversal/Solution.java) | 简单 | 栈 树 深度优先搜索 二叉树 |
| 94 | [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0094_Binary_Tree_Inorder_Traversal/Solution.java) | 简单 | 栈 树 深度优先搜索 二叉树 |
| 145 | [二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0145_Binary_Tree_Postorder_Traversal/Solution.java) | 简单 | 栈 树 深度优先搜索 二叉树 |
| 102 | [二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0102_Binary_Tree_Level_Order_Traversal/Solution.java) | 中等 | 树 广度优先搜索 二叉树 |
| 100 | [相同的树](https://leetcode-cn.com/problems/same-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0100_Same_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树 |
| 101 | [对称二叉树](https://leetcode-cn.com/problems/symmetric-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0101_Symmetric_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树 |
| 226  | [翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0226_Invert_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树 |
| 257 | [二叉树的所有路径](https://leetcode-cn.com/problems/binary-tree-paths/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0226_Invert_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 字符串 二叉树 |
| 112 | [路径总和](https://leetcode-cn.com/problems/path-sum/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0112_Path_Sum/Solution.java) | 简单 | 树 深度优先搜索 二叉树 |
| 113 | [路径总和 II](https://leetcode-cn.com/problems/path-sum-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0113_Path_Sum_II/Solution.java) | 中等 | 树 深度优先搜索 回溯 二叉树 |
| 129 | [求根节点到叶节点数字之和](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0129_Sum_Root_to_Leaf_Numbers/Solution.java) | 中等 | 树 深度优先搜索 二叉树 |
| 298 | [二叉树最长连续序列](https://leetcode-cn.com/problems/binary-tree-longest-consecutive-sequence/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0298_Binary_Tree_Longest_Consecutive_Sequence/Solution.java) | 中等 | 树 深度优先搜索 二叉树 |
| 111 | [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0111_Minimum_Depth_of_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树 |
| 104 | [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0104_Maximum_Depth_of_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 广度优先搜索 二叉树 |
| 110 | [平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0110_Balanced_Binary_Tree/Solution.java) | 简单 | 树 深度优先搜索 二叉树 |
| 124 | [二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0124_Binary_Tree_Maximum_Path_Sum/Solution.java) | 困难 | 树 深度优先搜索 动态规划 二叉树 |
| 250 | [统计同值子树](https://leetcode-cn.com/problems/count-univalue-subtrees/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0250_Count_Univalue_Subtrees/Solution.java) | 中等 | 树 深度优先搜索 二叉树 |
| 366 | [寻找二叉树的叶子节点](https://leetcode-cn.com/problems/find-leaves-of-binary-tree/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0366_Find_Leaves_of_Binary_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉树 |
| 337 | [打家劫舍 III](https://leetcode-cn.com/problems/house-robber-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0337_House_Robber_III/Solution.java) | 中等 | 树 深度优先搜索 动态规划 二叉树 |
| 107 | [二叉树的层序遍历 II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0107_Binary_Tree_Level_Order_Traversal_II/Solution.java) | 中等 | 树 广度优先搜索 二叉树 |
| 103 | [二叉树的锯齿形层序遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0103_Binary_Tree_Zigzag_Level_Order_Traversal/Solution.java) | 中等 | 树 广度优先搜索 二叉树 |
| 199 | [二叉树的右视图](https://leetcode-cn.com/problems/binary-tree-right-side-view/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0199_Binary_Tree_Right_Side_View/Solution.java) | 中等 | 树 深度优先搜索 广度优先搜索 二叉树 |
| 98 | [验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0098_Validate_Binary_Search_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉搜索树 二叉树 |
| 235  | [二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree/Solution.java) | 简单 | 树 深度优先搜索 二叉搜索树 二叉树 |
| 236  | [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0236_Lowest_Common_Ancestor_of_a_Binary_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉树            |
| 108 | [将有序数组转换为二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0108_Convert_Sorted_Array_to_Binary_Search_Tree/Solution.java) | 简单 | 树 二叉搜索树 数组 分治 二叉树 |
| 109 | [有序链表转换二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0109_Convert_Sorted_List_to_Binary_Search_Tree/Solution.java) | 中等 | 树 二叉搜索树 链表 分治 二叉树 |
| 173 | [二叉搜索树迭代器](https://leetcode-cn.com/problems/binary-search-tree-iterator/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0173_Binary_Search_Tree_Iterator/BSTIterator.java) | 中等 | 栈 树 设计 二叉搜索树 二叉树 迭代器 |
| 230 | [二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0230_Kth_Smallest_Element_in_a_BST/Solution.java) | 中等 | 树 深度优先搜索 二叉搜索树 二叉树 |
| 297 | [二叉树的序列化与反序列化](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0297_Serialize_and_Deserialize_Binary_Tree/Codec.java) | 困难 | 树 深度优先搜索 二叉搜索树 设计 字符串 二叉树 |
| 285 | [二叉搜索树中的中序后继](https://leetcode-cn.com/problems/inorder-successor-in-bst/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0285_Inorder_Successor_in_BST/Solution.java) | 中等 | 树 深度优先搜索 二叉搜索树 二叉树 |
| 270 | [最接近的二叉搜索树值](https://leetcode-cn.com/problems/closest-binary-search-tree-value/) 🔒 | [Java](./src/com/scuyjzh/leetcode/easy/No_0270_Closest_Binary_Search_Tree_Value/Solution.java) | 简单 | 树 深度优先搜索 二叉搜索树 二分查找 二叉树 |
| 272 | [最接近的二叉搜索树值 II](https://leetcode-cn.com/problems/closest-binary-search-tree-value-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0272_Closest_Binary_Search_Tree_Value_II/Solution.java) | 困难 | 栈 树 深度优先搜索 二叉搜索树 双指针 二叉树 堆（优先队列） |
| 99 | [恢复二叉搜索树](https://leetcode-cn.com/problems/recover-binary-search-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0099_Recover_Binary_Search_Tree/Solution.java) | 中等 | 树 深度优先搜索 二叉搜索树 二叉树 |
| 156 | [上下翻转二叉树](https://leetcode-cn.com/problems/binary-tree-upside-down/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0156_Binary_Tree_Upside_Down/Solution.java) | 中等 | 树 深度优先搜索 二叉树 |
| 114 | [二叉树展开为链表](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0114_Flatten_Binary_Tree_to_Linked_List/Solution.java) | 中等 | 栈 树 深度优先搜索 链表 二叉树 |
| 255 | [验证前序遍历序列二叉搜索树](https://leetcode-cn.com/problems/verify-preorder-sequence-in-binary-search-tree/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0255_Verify_Preorder_Sequence_in_Binary_Search_Tree/Solution.java) | 中等 | 栈 树 二叉搜索树 递归 二叉树 单调栈 |
| 333 | [最大 BST 子树](https://leetcode-cn.com/problems/largest-bst-subtree/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0333_Largest_BST_Subtree/Solution.java) | 中等 | 树 深度优先搜索 二叉搜索树 动态规划 二叉树 |
| 222 | [完全二叉树的节点个数](https://leetcode-cn.com/problems/count-complete-tree-nodes/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0222_Count_Complete_Tree_Nodes/Solution.java) | 中等 | 树 深度优先搜索 二分查找 二叉树 |
| 105 | [从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal/Solution.java) | 中等 | 树 数组 哈希表 分治 二叉树 |
| 106 | [从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal/Solution.java) | 中等 | 树 数组 哈希表 分治 二叉树 |
| 116  | [填充每个节点的下一个右侧节点指针](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0116_Populating_Next_Right_Pointers_in_Each_Node/Solution.java) | 中等 | 树 深度优先搜索 广度优先搜索 二叉树 |
| 117  | [填充每个节点的下一个右侧节点指针 II](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0117_Populating_Next_Right_Pointers_in_Each_Node_II/Solution.java) | 中等 | 树 深度优先搜索 广度优先搜索 二叉树 |
| 314 | [二叉树的垂直遍历](https://leetcode-cn.com/problems/binary-tree-vertical-order-traversal/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0314_Binary_Tree_Vertical_Order_Traversal/Solution.java) | 中等 | 树 深度优先搜索 广度优先搜索 哈希表 二叉树 |
| 96 | [不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0096_Unique_Binary_Search_Trees/Solution.java) | 中等 | 树 二叉搜索树 数学 动态规划 二叉树 |
| 95 | [不同的二叉搜索树 II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0095_Unique_Binary_Search_Trees_II/Solution.java) | 中等 | 树 二叉搜索树 动态规划 回溯 二叉树 |
| 331 | [验证二叉树的前序序列化](https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0331_Verify_Preorder_Serialization_of_a_Binary_Tree/Solution.java) | 中等 | 栈 树 字符串 二叉树 |

### Backtracking

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                          |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | --------------------------------------------- |
| 78   | [子集](https://leetcode-cn.com/problems/subsets/)            | [Java](./src/com/scuyjzh/leetcode/medium/No_0078_Subsets/Solution.java) | 中等 | 位运算 数组 回溯                              |
| 90   | [子集 II](https://leetcode-cn.com/problems/subsets-ii/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0090_Subsets_II/Solution.java) | 中等 | 位运算 数组 回溯                              |
| 77   | [组合](https://leetcode-cn.com/problems/combinations/)       | [Java](./src/com/scuyjzh/leetcode/medium/No_0077_Combinations/Solution.java) | 中等 | 数组 回溯                                     |
| 39   | [组合总和](https://leetcode-cn.com/problems/combination-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0039_Combination_Sum/Solution.java) | 中等 | 数组 回溯                                     |
| 40   | [组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0040_Combination_Sum_II/Solution.java) | 中等 | 数组 回溯                                     |
| 216  | [组合总和 III](https://leetcode-cn.com/problems/combination-sum-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0216_Combination_Sum_III/Solution.java) | 中等 | 数组 回溯                                     |
| 377  | [组合总和 Ⅳ](https://leetcode-cn.com/problems/combination-sum-iv/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0377_Combination_Sum_IV/Solution.java) | 中等 | 数组 动态规划                                 |
| 254  | [因子的组合](https://leetcode-cn.com/problems/factor-combinations/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0254_Factor_Combinations/Solution.java) | 中等 | 数组 回溯                                     |
| 46   | [全排列](https://leetcode-cn.com/problems/permutations/)     | [Java](./src/com/scuyjzh/leetcode/medium/No_0046_Permutations/Solution.java) | 中等 | 数组 回溯                                     |
| 47   | [全排列 II](https://leetcode-cn.com/problems/permutations-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0047_Permutations_II/Solution.java) | 中等 | 数组 回溯                                     |
| 31   | [下一个排列](https://leetcode-cn.com/problems/next-permutation/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0031_Next_Permutation/Solution.java) | 中等 | 数组 双指针                                   |
| 60   | [排列序列](https://leetcode-cn.com/problems/permutation-sequence/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0060_Permutation_Sequence/Solution.java) | 困难 | 递归 数学                                     |
| 291  | [单词规律 II](https://leetcode-cn.com/problems/word-pattern-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0291_Word_Pattern_II/Solution.java) | 中等 | 哈希表 字符串 回溯                            |
| 17   | [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0017_Letter_Combinations_of_a_Phone_Number/Solution.java) | 中等 | 哈希表 字符串 回溯                            |
| 320  | [列举单词的全部缩写](https://leetcode-cn.com/problems/generalized-abbreviation/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0320_Generalized_Abbreviation/Solution.java) | 中等 | 位运算 字符串 回溯                            |
| 93   | [复原 IP 地址](https://leetcode-cn.com/problems/restore-ip-addresses/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0093_Restore_IP_Addresses/Solution.java) | 中等 | 字符串 回溯                                   |
| 282  | [给表达式添加运算符](https://leetcode-cn.com/problems/expression-add-operators/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0282_Expression_Add_Operators/Solution.java) | 困难 | 数学 字符串 回溯                              |
| 140  | [单词拆分 II](https://leetcode-cn.com/problems/word-break-ii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0140_Word_Break_II/Solution.java) | 困难 | 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯 |
| 351  | [安卓系统手势解锁](https://leetcode-cn.com/problems/android-unlock-patterns/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0351_Android_Unlock_Patterns/Solution.java) | 中等 | 动态规划 回溯                                 |

### Dynamic Programming

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                     |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------- |
| 70   | [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)  | [Java](./src/com/scuyjzh/leetcode/easy/No_0070_Climbing_Stairs/Solution.java) | 简单 | 记忆化搜索 数学 动态规划                 |
| 62   | [不同路径](https://leetcode-cn.com/problems/unique-paths/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0062_Unique_Paths/Solution.java) | 中等 | 数学 动态规划 组合数学                   |
| 63   | [不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0063_Unique_Paths_II/Solution.java) | 中等 | 数学 动态规划 矩阵                       |
| 120  | [三角形最小路径和](https://leetcode-cn.com/problems/triangle/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0120_Triangle/Solution.java) | 中等 | 数学 动态规划                            |
| 279  | [完全平方数](https://leetcode-cn.com/problems/perfect-squares/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0279_Perfect_Squares/Solution.java) | 中等 | 广度优先搜索 数学 动态规划               |
| 139  | [单词拆分](https://leetcode-cn.com/problems/word-break/)     | [Java](./src/com/scuyjzh/leetcode/medium/No_0139_Word_Break/Solution.java) | 中等 | 字典树 记忆化搜索 哈希表 字符串 动态规划 |
| 375  | [猜数字大小 II](https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0375_Guess_Number_Higher_or_Lower_II/Solution.java) | 中等 | 数学 动态规划 博弈                       |
| 312  | [戳气球](https://leetcode-cn.com/problems/burst-balloons/)   | [Java](./src/com/scuyjzh/leetcode/hard/No_0312_Burst_Balloons/Solution.java) | 困难 | 数组 动态规划                            |
| 322  | [零钱兑换](https://leetcode-cn.com/problems/coin-change/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0322_Coin_Change/Solution.java) | 中等 | 广度优先搜索 数组 动态规划               |
| 256  | [粉刷房子](https://leetcode-cn.com/problems/paint-house/) 🔒  | [Java](./src/com/scuyjzh/leetcode/medium/No_0256_Paint_House/Solution.java) | 中等 | 数组 动态规划                            |
| 265  | [粉刷房子 II](https://leetcode-cn.com/problems/paint-house-ii/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0265_Paint_House_II/Solution.java) | 困难 | 数组 动态规划                            |
| 64   | [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0064_Minimum_Path_Sum/Solution.java) | 中等 | 数组 动态规划 矩阵                       |
| 72   | [编辑距离](https://leetcode-cn.com/problems/edit-distance/)  | [Java](./src/com/scuyjzh/leetcode/hard/No_0072_Edit_Distance/Solution.java) | 困难 | 字符串 动态规划                          |
| 97   | [交错字符串](https://leetcode-cn.com/problems/interleaving-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0097_Interleaving_String/Solution.java) | 中等 | 字符串 动态规划                          |
| 174  | [地下城游戏](https://leetcode-cn.com/problems/dungeon-game/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0174_Dungeon_Game/Solution.java) | 困难 | 数组 动态规划 矩阵                       |
| 221  | [最大正方形](https://leetcode-cn.com/problems/maximal-square/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0221_Maximal_Square/Solution.java) | 中等 | 数组 动态规划 矩阵                       |
| 85   | [最大矩形](https://leetcode-cn.com/problems/maximal-rectangle/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0085_Maximal_Rectangle/Solution.java) | 困难 | 栈 数组 动态规划 矩阵 单调栈             |
| 363  | [矩形区域不超过 K 的最大数值和](https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0363_Max_Sum_of_Rectangle_No_Larger_Than_K/Solution.java) | 困难 | 数组 二分查找 动态规划 矩阵 有序集合     |
| 198  | [打家劫舍](https://leetcode-cn.com/problems/house-robber/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0198_House_Robber/Solution.java) | 中等 | 数组 动态规划                            |
| 213  | [打家劫舍 II](https://leetcode-cn.com/problems/house-robber-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0213_House_Robber_II/Solution.java) | 中等 | 数组 动态规划                            |
| 276  | [栅栏涂色](https://leetcode-cn.com/problems/paint-fence/) 🔒  | [Java](./src/com/scuyjzh/leetcode/medium/No_0276_Paint_Fence/Solution.java) | 中等 | 动态规划                                 |
| 91   | [解码方法](https://leetcode-cn.com/problems/decode-ways/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0091_Decode_Ways/Solution.java) | 中等 | 字符串 动态规划                          |
| 10   | [正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0010_Regular_Expression_Matching/Solution.java) | 困难 | 递归 字符串 动态规划                     |
| 44   | [通配符匹配](https://leetcode-cn.com/problems/wildcard-matching/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0044_Wildcard_Matching/Solution.java) | 困难 | 贪心 递归 字符串 动态规划                |

### LinkedList

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                              |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | --------------------------------- |
| 206  | [反转链表](https://leetcode-cn.com/problems/reverse-linked-list/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0206_Reverse_Linked_List/Solution.java) | 简单 | 递归 链表                         |
| 141  | [环形链表](https://leetcode-cn.com/problems/linked-list-cycle/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0141_Linked_List_Cycle/Solution.java) | 简单 | 哈希表 链表 双指针                |
| 24   | [两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0024_Swap_Nodes_in_Pairs/Solution.java) | 中等 | 递归 链表                         |
| 328  | [奇偶链表](https://leetcode-cn.com/problems/odd-even-linked-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0328_Odd_Even_Linked_List/Solution.java) | 中等 | 链表                              |
| 92   | [反转链表 II](https://leetcode-cn.com/problems/reverse-linked-list-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0092_Reverse_Linked_List_II/Solution.java) | 中等 | 链表                              |
| 237  | [删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0237_Delete_Node_in_a_Linked_List/Solution.java) | 简单 | 链表                              |
| 19   | [删除链表的倒数第 N 个节点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0019_Remove_Nth_Node_From_End_of_List/Solution.java) | 中等 | 链表 双指针                       |
| 83   | [删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0083_Remove_Duplicates_from_Sorted_List/Solution.java) | 简单 | 链表                              |
| 203  | [移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0203_Remove_Linked_List_Elements/Solution.java) | 简单 | 递归 链表                         |
| 82   | [删除排序链表中的重复元素 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0082_Remove_Duplicates_from_Sorted_List_II/Solution.java) | 中等 | 链表 双指针                       |
| 369  | [给单链表加一](https://leetcode-cn.com/problems/plus-one-linked-list/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0369_Plus_One_Linked_List/Solution.java) | 中等 | 链表 数学                         |
| 2    | [两数相加](https://leetcode-cn.com/problems/add-two-numbers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0002_Add_Two_Numbers/Solution.java) | 中等 | 递归 链表 数学                    |
| 160  | [相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0160_Intersection_of_Two_Linked_Lists/Solution.java) | 简单 | 哈希表 链表 双指针                |
| 21   | [合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0021_Merge_Two_Sorted_Lists/Solution.java) | 简单 | 递归 链表                         |
| 234  | [回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0234_Palindrome_Linked_List/Solution.java) | 简单 | 栈 递归 链表 双指针               |
| 143  | [重排链表](https://leetcode-cn.com/problems/reorder-list/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0143_Reorder_List/Solution.java) | 中等 | 栈 递归 链表 双指针               |
| 142  | [环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0142_Linked_List_Cycle_II/Solution.java) | 中等 | 哈希表 链表 双指针                |
| 148  | [排序链表](https://leetcode-cn.com/problems/sort-list/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0148_Sort_List/Solution.java) | 中等 | 链表 双指针 分治 排序 归并排序    |
| 25   | [K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0025_Reverse_Nodes_in_kGroup/Solution.java) | 困难 | 递归 链表                         |
| 61   | [旋转链表](https://leetcode-cn.com/problems/rotate-list/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0061_Rotate_List/Solution.java) | 中等 | 链表 双指针                       |
| 86   | [分隔链表](https://leetcode-cn.com/problems/partition-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0086_Partition_List/Solution.java) | 中等 | 链表 双指针                       |
| 23   | [合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0023_Merge_k_Sorted_Lists/Solution.java) | 困难 | 链表 分治 堆（优先队列） 归并排序 |
| 147  | [对链表进行插入排序](https://leetcode-cn.com/problems/insertion-sort-list/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0147_Insertion_Sort_List/Solution.java) | 中等 | 链表 排序                         |

### Binary Search

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------------------- |
| 278  | [第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/) | [Java](https://leetcode-cn.com/problems/first-bad-version/solution/) | 简单 | 二分查找 交互                                        |
| 35   | [搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0035_Search_Insert_Position/Solution.java) | 简单 | 数组 二分查找                                        |
| 81   | [搜索旋转排序数组 II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0081_Search_in_Rotated_Sorted_Array_II/Solution.java) | 中等 | 数组 二分查找                                        |
| 153  | [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0153_Find_Minimum_in_Rotated_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |
| 162  | [寻找峰值](https://leetcode-cn.com/problems/find-peak-element/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0162_Find_Peak_Element/Solution.java) | 中等 | 数组 二分查找                                        |
| 374  | [猜数字大小](https://leetcode-cn.com/problems/guess-number-higher-or-lower/) | [Java](https://leetcode-cn.com/problems/guess-number-higher-or-lower/solution/cai-shu-zi-da-xiao-by-leetcode-solution-qdzu/) | 简单 | 二分查找 交互                                        |
| 34   | [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0034_Find_First_and_Last_Position_of_Element_in_Sorted_Array/Solution.java) | 中等 | 数组 二分查找                                        |
| 349  | [两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0349_Intersection_of_Two_Arrays/Solution.java) | 简单 | 数组 哈希表 双指针 二分查找 排序                     |
| 350  | [两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0350_Intersection_of_Two_Arrays_II/Solution.java) | 简单 | 数组 哈希表 双指针 二分查找 排序                     |
| 315  | [计算右侧小于当前元素的个数](https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0315_Count_of_Smaller_Numbers_After_Self/Solution.java) | 困难 | 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 |
| 300  | [最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0300_Longest_Increasing_Subsequence/Solution.java) | 中等 | 数组 二分查找 动态规划                               |
| 354  | [俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0354_Russian_Doll_Envelopes/Solution.java) | 困难 | 数组 二分查找 动态规划 排序                          |

### Matrix

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                      |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | --------------------------------------------------------- |
| 48   | [旋转图像](https://leetcode-cn.com/problems/rotate-image/)   | [Java](./src/com/scuyjzh/leetcode/medium/No_0048_Rotate_Image/Solution.java) | 中等 | 数组 数学 矩阵                                            |
| 54   | [螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0054_Spiral_Matrix/Solution.java) | 中等 | 数组 矩阵 模拟                                            |
| 59   | [螺旋矩阵 II](https://leetcode-cn.com/problems/spiral-matrix-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0059_Spiral_Matrix_II/Solution.java) | 中等 | 数组 矩阵 模拟                                            |
| 73   | [矩阵置零](https://leetcode-cn.com/problems/set-matrix-zeroes/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0073_Set_Matrix_Zeroes/Solution.java) | 中等 | 数组 哈希表 矩阵                                          |
| 311  | [稀疏矩阵的乘法](https://leetcode-cn.com/problems/sparse-matrix-multiplication/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0311_Sparse_Matrix_Multiplication/Solution.java) | 中等 | 数组 哈希表 矩阵                                          |
| 329  | [矩阵中的最长递增路径](https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0329_Longest_Increasing_Path_in_a_Matrix/Solution.java) | 困难 | 深度优先搜索 广度优先搜索 图 拓扑排序 记忆化搜索 动态规划 |
| 378  | [有序矩阵中第 K 小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0378_Kth_Smallest_Element_in_a_Sorted_Matrix/Solution.java) | 中等 | 数组 二分查找 矩阵 排序 堆（优先队列）                    |
| 74   | [搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0074_Search_a_2D_Matrix/Solution.java) | 中等 | 数组 二分查找 矩阵                                        |
| 240  | [搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0240_Search_a_2D_Matrix_II/Solution.java) | 中等 | 数组 二分查找 分治 矩阵                                   |
| 370  | [区间加法](https://leetcode-cn.com/problems/range-addition/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0370_Range_Addition/Solution.java) | 中等 | 数组 前缀和                                               |
| 79   | [单词搜索](https://leetcode-cn.com/problems/word-search/)    | [Java](./src/com/scuyjzh/leetcode/medium/No_0079_Word_Search/Solution.java) | 中等 | 数组 回溯 矩阵                                            |
| 296  | [最佳的碰头地点](https://leetcode-cn.com/problems/best-meeting-point/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0296_Best_Meeting_Point/Solution.java) | 困难 | 数组 数学 矩阵 排序                                       |
| 361  | [轰炸敌人](https://leetcode-cn.com/problems/bomb-enemy/) 🔒   | [Java](./src/com/scuyjzh/leetcode/medium/No_0361_Bomb_Enemy/Solution.java) | 中等 | 数组 动态规划 矩阵                                        |
| 317  | [离建筑物最近的距离](https://leetcode-cn.com/problems/shortest-distance-from-all-buildings/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0317_Shortest_Distance_from_All_Buildings/Solution.java) | 困难 | 广度优先搜索 数组 矩阵                                    |
| 302  | [包含全部黑色像素的最小矩形](https://leetcode-cn.com/problems/smallest-rectangle-enclosing-black-pixels/) 🔒 | [Java](./src/com/scuyjzh/leetcode/hard/No_0302_Smallest_Rectangle_Enclosing_Black_Pixels/Solution.java) | 困难 | 深度优先搜索 广度优先搜索 数组 二分查找 矩阵              |
| 36   | [有效的数独](https://leetcode-cn.com/problems/valid-sudoku/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0036_Valid_Sudoku/Solution.java) | 中等 | 数组 哈希表 矩阵                                          |
| 37   | [解数独](https://leetcode-cn.com/problems/sudoku-solver/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0037_Sudoku_Solver/Solution.java) | 困难 | 数组 回溯 矩阵                                            |

### DFS & BFS

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                       |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------------ |
| 200  | [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0200_Number_of_Islands/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 并查集 数组 矩阵 |
| 286  | [墙与门](https://leetcode-cn.com/problems/walls-and-gates/) 🔒 | [Java](./src/com/scuyjzh/leetcode/medium/No_0286_Walls_and_Gates/Solution.java) | 中等 | 广度优先搜索 数组 矩阵                     |
| 130  | [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0130_Surrounded_Regions/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 并查集 数组 矩阵 |
| 339  | [嵌套列表权重和](https://leetcode-cn.com/problems/nested-list-weight-sum/) 🔒 | [Java](https://leetcode-cn.com/problems/nested-list-weight-sum/solution/qian-tao-lie-biao-quan-zhong-he-by-leetcode-2/) | 中等 | 深度优先搜索 广度优先搜索                  |
| 364  | [加权嵌套序列和 II](https://leetcode-cn.com/problems/nested-list-weight-sum-ii/) 🔒 | [Java](https://leetcode-cn.com/problems/nested-list-weight-sum-ii/solution/) | 中等 | 栈 深度优先搜索 广度优先搜索               |
| 127  | [单词接龙](https://leetcode-cn.com/problems/word-ladder/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0127_Word_Ladder/Solution.java) | 困难 | 广度优先搜索 哈希表 字符串                 |
| 126  | [单词接龙 II](https://leetcode-cn.com/problems/word-ladder-ii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0126_Word_Ladder_II/Solution.java) | 困难 | 广度优先搜索 哈希表 字符串 回溯            |
| 51   | [N 皇后](https://leetcode-cn.com/problems/n-queens/)         | [Java](./src/com/scuyjzh/leetcode/hard/No_0051_N_Queens/Solution.java) | 困难 | 数组 回溯                                  |
| 52   | [N皇后 II](https://leetcode-cn.com/problems/n-queens-ii/)    | [Java](./src/com/scuyjzh/leetcode/hard/No_0052_N_Queens_II/Solution.java) | 困难 | 回溯                                       |

### Stack & PriorityQueue

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                                      |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | --------------------------------------------------------- |
| 155  | [最小栈](https://leetcode-cn.com/problems/min-stack/)        | [Java](./src/com/scuyjzh/leetcode/easy/No_0155_Min_Stack/MinStack.java) | 简单 | 栈 设计                                                   |
| 232  | [用栈实现队列](https://leetcode-cn.com/problems/implement-queue-using-stacks/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0232_Implement_Queue_using_Stacks/MyQueue.java) | 简单 | 栈 设计 队列                                              |
| 225  | [用队列实现栈](https://leetcode-cn.com/problems/implement-stack-using-queues/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0225_Implement_Stack_using_Queues/MyStack.java) | 简单 | 栈 设计 队列                                              |
| 150  | [逆波兰表达式求值](https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0150_Evaluate_Reverse_Polish_Notation/Solution.java) | 中等 | 栈 数组 数学                                              |
| 71   | [简化路径](https://leetcode-cn.com/problems/simplify-path/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0071_Simplify_Path/Solution.java) | 中等 | 栈 字符串                                                 |
| 388  | [文件的最长绝对路径](https://leetcode-cn.com/problems/longest-absolute-file-path/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0388_Longest_Absolute_File_Path/Solution.java) | 中等 | 栈 深度优先搜索 字符串                                    |
| 394  | [字符串解码](https://leetcode-cn.com/problems/decode-string/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0394_Decode_String/Solution.java) | 中等 | 栈 递归 字符串                                            |
| 224  | [基本计算器](https://leetcode-cn.com/problems/basic-calculator/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0224_Basic_Calculator/Solution.java) | 困难 | 栈 递归 数学 字符串                                       |
| 227  | [基本计算器 II](https://leetcode-cn.com/problems/basic-calculator-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0227_Basic_Calculator_II/Solution.java) | 中等 | 栈 数学 字符串                                            |
| 385  | [迷你语法分析器](https://leetcode-cn.com/problems/mini-parser/) | [Java](https://leetcode-cn.com/problems/mini-parser/solution/) | 中等 | 栈 深度优先搜索 字符串                                    |
| 84   | [柱状图中最大的矩形](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0084_Largest_Rectangle_in_Histogram/Solution.java) | 困难 | 栈 数组 单调栈                                            |
| 215  | [数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0215_Kth_Largest_Element_in_an_Array/Solution.java) | 中等 | 数组 分治 快速排序 排序 堆（优先队列）                    |
| 347  | [前 K 个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0347_Top_K_Frequent_Elements/Solution.java) | 中等 | 数组 哈希表 分治 桶排序 计数 快速排序 排序 堆（优先队列） |
| 313  | [超级丑数](https://leetcode-cn.com/problems/super-ugly-number/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0313_Super_Ugly_Number/Solution.java) | 中等 | 数组 哈希表 数学 动态规划 堆（优先队列）                  |
| 373  | [查找和最小的K对数字](https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0373_Find_K_Pairs_with_Smallest_Sums/Solution.java) | 中等 | 数组 堆（优先队列）                                       |
| 218  | [天际线问题](https://leetcode-cn.com/problems/the-skyline-problem/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0218_The_Skyline_Problem/Solution.java) | 困难 | 树状数组 线段树 数组 分治 有序集合 扫描线 堆（优先队列）  |
| 332  | [重新安排行程](https://leetcode-cn.com/problems/reconstruct-itinerary/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0332_Reconstruct_Itinerary/Solution.java) | 困难 | 深度优先搜索 图 欧拉回路                                  |

### Bit Manipulation

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                         |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------- |
| 389  | [找不同](https://leetcode-cn.com/problems/find-the-difference/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0389_Find_the_Difference/Solution.java) | 简单 | 位运算 哈希表 字符串 排序    |
| 136  | [只出现一次的数字](https://leetcode-cn.com/problems/single-number/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0136_Single_Number/Solution.java) | 简单 | 位运算 数组                  |
| 318  | [最大单词长度乘积](https://leetcode-cn.com/problems/maximum-product-of-word-lengths/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0318_Maximum_Product_of_Word_Lengths/Solution.java) | 中等 | 位运算 数组 字符串           |
| 393  | [UTF-8 编码验证](https://leetcode-cn.com/problems/utf-8-validation/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0393_UTF8_Validation/Solution.java) | 中等 | 位运算 数组                  |
| 201  | [数字范围按位与](https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0201_Bitwise_AND_of_Numbers_Range/Solution.java) | 中等 | 位运算                       |
| 371  | [两整数之和](https://leetcode-cn.com/problems/sum-of-two-integers/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0371_Sum_of_Two_Integers/Solution.java) | 中等 | 位运算 数学                  |
| 338  | [比特位计数](https://leetcode-cn.com/problems/counting-bits/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0338_Counting_Bits/Solution.java) | 简单 | 位运算 动态规划              |
| 89   | [格雷编码](https://leetcode-cn.com/problems/gray-code/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0089_Gray_Code/Solution.java) | 中等 | 位运算 数学 回溯             |
| 268  | [丢失的数字](https://leetcode-cn.com/problems/missing-number/) | [Java](./src/com/scuyjzh/leetcode/easy/No_0268_Missing_Number/Solution.java) | 简单 | 位运算 数组 哈希表 数学 排序 |
| 191  | [位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/) | [Java](https://leetcode-cn.com/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode-solution-jnwf/) | 简单 | 位运算                       |
| 190  | [颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/) | [Java](https://leetcode-cn.com/problems/reverse-bits/solution/dian-dao-er-jin-zhi-wei-by-leetcode-solu-yhxz/) | 简单 | 位运算 分治                  |
| 137  | [只出现一次的数字 II](https://leetcode-cn.com/problems/single-number-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0137_Single_Number_II/Solution.java) | 中等 | 位运算 数组                  |
| 260  | [只出现一次的数字 III](https://leetcode-cn.com/problems/single-number-iii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0260_Single_Number_III/Solution.java) | 中等 | 位运算 数组                  |

### Graph

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                                            |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ----------------------------------------------- |
| 133  | [克隆图](https://leetcode-cn.com/problems/clone-graph/)      | [Java](./src/com/scuyjzh/leetcode/medium/No_0133_Clone_Graph/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 图 哈希表             |
| 399  | [除法求值](https://leetcode-cn.com/problems/evaluate-division/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0399_Evaluate_Division/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 并查集 图 数组 最短路 |
| 310  | [最小高度树](https://leetcode-cn.com/problems/minimum-height-trees/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0310_Minimum_Height_Trees/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 图 拓扑排序           |
| 207  | [课程表](https://leetcode-cn.com/problems/course-schedule/)  | [Java](./src/com/scuyjzh/leetcode/medium/No_0207_Course_Schedule/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 图 拓扑排序           |
| 210  | [课程表 II](https://leetcode-cn.com/problems/course-schedule-ii/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0210_Course_Schedule_II/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 图 拓扑排序           |
| 149  | [直线上最多的点数](https://leetcode-cn.com/problems/max-points-on-a-line/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0149_Max_Points_on_a_Line/Solution.java) | 困难 | 几何 哈希表 数学                                |
| 305  | [岛屿数量 II](https://leetcode-cn.com/problems/number-of-islands-ii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0305_Number_of_Islands_II/Solution.java) | 困难 | 并查集 数组                                     |
| 547  | [省份数量](https://leetcode-cn.com/problems/number-of-provinces/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0547_Number_of_Provinces/Solution.java) | 中等 | 深度优先搜索 广度优先搜索 图 并查集             |

### Trie

| #    | 题目                                                         | 题解                                                         | 难度 | 标签                            |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------- |
| 211  | [添加与搜索单词 - 数据结构设计](https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0211_Design_Add_and_Search_Words_Data_Structure/WordDictionary.java) | 中等 | 深度优先搜索 设计 字典树 字符串 |
| 208  | [实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0208_Implement_Trie/Trie.java) | 中等 | 设计 字典树 哈希表 字符串       |
| 212  | [单词搜索 II](https://leetcode-cn.com/problems/word-search-ii/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0212_Word_Search_II/Solution.java) | 困难 | 字典树 数组 字符串 回溯 矩阵    |

### Design

| #    | 题目                                                    | 题解                                                         | 难度 | 标签                      |
| ---- | ------------------------------------------------------- | ------------------------------------------------------------ | ---- | ------------------------- |
| 146  | [LRU 缓存](https://leetcode-cn.com/problems/lru-cache/) | [Java](./src/com/scuyjzh/leetcode/medium/No_0146_LRU_Cache/LRUCache.java) | 中等 | 设计 哈希表 链表 双向链表 |
| 460  | [LFU 缓存](https://leetcode-cn.com/problems/lfu-cache/) | [Java](./src/com/scuyjzh/leetcode/hard/No_0460_LFU_Cache/LFUCache.java) | 困难 | 设计 哈希表 链表 双向链表 |
