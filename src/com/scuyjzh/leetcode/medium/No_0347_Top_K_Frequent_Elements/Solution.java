package com.scuyjzh.leetcode.medium.No_0347_Top_K_Frequent_Elements;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高
 * 的元素。你可以按 任意顺序 返回答案。
 */
class Solution {
    Map<Integer, Integer> map;

    /**
     * 方法一：堆排序
     */
    public int[] topKFrequent1(int[] nums, int k) {
        /*
         * 首先遍历整个数组，并使用哈希表记录每个数字出现的次数，并形成一个「出现次数数组」。找出原数组的
         * 前 k 个高频元素，就相当于找出「出现次数数组」的前 k 大的值。
         *
         * 最简单的做法是给「出现次数数组」排序。但由于可能有 O(N) 个不同的出现次数（其中 N 为原数组长
         * 度），故总的算法复杂度会达到 O(NlogN)，不满足题目的要求。
         *
         * 在这里，可以利用堆的思想：建立一个小顶堆，然后遍历「出现次数数组」：
         *   • 如果堆的元素个数小于 k，就可以直接插入堆中。
         *   • 如果堆的元素个数等于 k，则检查堆顶与当前出现次数的大小。如果堆顶更大，说明至少有 k 个数字的
         *     出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。
         *
         * 遍历完成后，堆中的元素就代表了「出现次数数组」中前 k 大的值。
         */
        map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] heap = new int[k];
        Iterator<Integer> it = map.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i < k) {
                heap[i] = it.next();
                ++i;
                if (i == k) {
                    for (int j = k / 2 - 1; j >= 0; --j) {
                        minHeapify(heap, j);
                    }
                }
            } else {
                int key = it.next();
                if (map.get(key) > map.get(heap[0])) {
                    heap[0] = key;
                    minHeapify(heap, 0);
                }
            }
        }
        return heap;
    }

    /**
     * 维护小顶堆
     */
    private void minHeapify(int[] heap, int i) {
        // value 用来记录初始根节点值
        int value = heap[i];
        // 初始化子树节点下标，先定位到左子树节点
        int j = 2 * i + 1;
        while (j < heap.length) {
            // 若右子树节点值小于左子树，则定位到右子树节点
            if (j + 1 < heap.length && map.get(heap[j + 1]) < map.get(heap[j])) {
                ++j;
            }
            // 若根节点值最小，则直接跳出循环
            if (map.get(value) < map.get(heap[j])) {
                break;
            }
            // 将最小值赋给根节点（单向赋值，无须交换）
            heap[i] = heap[j];
            // 更新根节点和子树节点下标
            i = j;
            j = 2 * j + 1;
        }
        heap[i] = value;
    }

    /**
     * 方法二：快速排序
     */
    public int[] topKFrequent2(int[] nums, int k) {
        map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<int[]> numCnt = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            numCnt.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // 使用基于快速排序（降序）的方法，求出「出现次数数组」的前 k 大的值
        List<int[]> topKs = findTopK(numCnt, k, 0, map.size() - 1);
        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = topKs.get(i)[0];
        }
        return res;
    }

    private List<int[]> findTopK(List<int[]> numCnt, int k, int start, int end) {
        // 在 while (true) 循环中，通过 left 与 right 向中间靠拢的方式逐步缩小搜索区间
        while (true) {
            int index = partition(numCnt, start, end);
            if (index == k - 1) {
                return numCnt.subList(0, k);
            } else if (index > k - 1) {
                end = index - 1;
            } else {
                start = index + 1;
            }
        }
    }

    /**
     * 双指针分区算法
     */
    private int partition(List<int[]> numCnt, int start, int end) {
        /*
         * 从 left 开始，遇到比基数小的数，记录其下标；
         * 再从 right 往前遍历，找到第一个比基数大的数，记录其下标；
         * 然后交换这两个数。继续遍历，直到 left 和 right 相遇。
         *
         * 然后交换基数和中间值，并返回中间值的下标。
         * 需要在退出循环后，单独比较 left 和 right 的值。
         */
        int pivotIndex = start + (int) (Math.random() * (end - start + 1));
        // 随机初始化 pivot 元素
        Collections.swap(numCnt, start, pivotIndex);
        // 取第一个数为基数
        int pivot = numCnt.get(start)[1];
        // 从第二个数开始分区
        int left = start + 1;
        // 右边界
        int right = end;
        while (left < right) {
            // 找到第一个小于基数的位置
            while (left < right && numCnt.get(left)[1] >= pivot) {
                ++left;
            }
            // 找到第一个大于基数的位置
            while (left < right && numCnt.get(right)[1] <= pivot) {
                --right;
            }
            // 交换这两个数，使得左边分区都大于或等于基数，右边分区小于或等于基数
            if (left < right) {
                Collections.swap(numCnt, left, right);
                ++left;
                --right;
            }
        }
        // 如果 left 和 right 相等，单独比较 arr[right] 和 pivot
        if (left == right && numCnt.get(right)[1] < pivot) {
            --right;
        }
        // 将基数和轴交换
        Collections.swap(numCnt, start, right);
        return right;
    }

    /**
     * 方法三：桶排序
     */
    public int[] topKFrequent3(int[] nums, int k) {
        map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] list = new List[nums.length + 1];
        for (int num : map.keySet()) {
            int cnt = map.get(num);
            if (list[cnt] == null) {
                list[cnt] = new ArrayList<>();
            }
            list[cnt].add(num);
        }

        int[] res = new int[k];
        int d = 0;
        for (int i = list.length - 1; i >= 0 && d < k; --i) {
            if (list[i] != null) {
                for (int j = 0; j < list[i].size(); ++j) {
                    if (d < k) {
                        res[d++] = list[i].get(j);
                    } else {
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().topKFrequent1(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(new Solution().topKFrequent2(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(new Solution().topKFrequent3(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
}
