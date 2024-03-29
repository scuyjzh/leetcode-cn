package com.scuyjzh.leetcode.medium.No_0215_Kth_Largest_Element_in_an_Array;

/**
 * 215. 数组中的第K个最大元素
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个
 * 不同的元素。
 */
class Solution {
    /**
     * 方法一：快速选择（基于快速排序的选择方法）
     */
    public int findKthLargest1(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        // 第 k 大元素的下标是 N - k
        int target = len - k;

        // 在 while (true) 循环中，通过 left 与 right 向中间靠拢的方式逐步缩小搜索区间
        while (true) {
            // 将数组分区，左边区域比基数小，右边区域比基数大，然后返回基数的下标
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
    }

    /**
     * 双指针分区算法
     */
    private int partition(int[] nums, int start, int end) {
        // 在区间随机选择一个元素作为标定点
        int randomIndex = start + (int) (Math.random() * (end - start + 1));
        swap(nums, start, randomIndex);

        // 定义 pivot = nums[start] ，剩下的区间 [start + 1..end] 被变量 left 、right 分成三个部分：
        // [start + 1..left) <= pivot；
        // [left..right] 是程序没有看到的部分；
        // (right..end] >= pivot。
        int pivot = nums[start];
        int left = start + 1;
        int right = end;
        while (left < right) {
            // 找到第一个大于基数的位置
            while (left < right && nums[left] <= pivot) {
                ++left;
            }
            // 找到第一个小于基数的位置
            while (left < right && nums[right] >= pivot) {
                --right;
            }
            // 交换这两个数，使得左边分区都小于或等于基数，右边分区大于或等于基数
            if (left < right) {
                swap(nums, left, right);
                ++left;
                --right;
            }
        }
        // 如果 left 和 right 相等，单独比较 nums[right] 和 pivot
        if (left == right && nums[right] > pivot) {
            --right;
        }
        // 将基数和轴交换
        swap(nums, start, right);
        return right;
    }

    /**
     * 方法二：基于堆排序的选择方法
     */
    public int findKthLargest2(int[] nums, int k) {
        // 思路：建立一个大根堆，做 k−1 次删除操作后堆顶元素就是要找的答案
        int heapSize = nums.length;
        // 构建初始大顶堆
        buildMaxHeap(nums);
        for (int i = nums.length - 1; i > nums.length - k; --i) {
            // 将最大值交换到数组最后，相当于删除堆顶元素
            swap(nums, 0, i);
            // 调整剩余数组，使其满足大顶堆
            maxHeapify(nums, 0, --heapSize);
        }
        // 返回做 k−1 次删除操作后的堆顶元素
        return nums[0];
    }

    /**
     * 构建初始大顶堆
     */
    private void buildMaxHeap(int[] nums) {
        // 从最后一个非叶子节点开始调整大顶堆，最后一个非叶子节点的下标就是 arr.length / 2 - 1
        for (int i = (nums.length >> 1) - 1; i >= 0; --i) {
            maxHeapify(nums, i, nums.length);
        }
    }

    /**
     * 调整大顶堆，第三个参数表示剩余未排序的数字的数量，也就是剩余堆的大小
     */
    private void maxHeapify(int[] nums, int i, int heapSize) {
        // 左子树节点下标
        int l = i * 2 + 1;
        // 右子树节点下标
        int r = l + 1;
        // 记录根节点、左子树节点、右子树节点三者中的最大值下标
        int largest = i;
        // 与左子树节点比较
        if (l < heapSize && nums[l] > nums[largest]) {
            largest = l;
        }
        // 与右子树节点比较
        if (r < heapSize && nums[r] > nums[largest]) {
            largest = r;
        }
        if (largest != i) {
            // 将最大值交换为根节点
            swap(nums, i, largest);
            // 再次调整交换数字后的大顶堆
            maxHeapify(nums, largest, heapSize);
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findKthLargest1(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(new Solution().findKthLargest2(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
