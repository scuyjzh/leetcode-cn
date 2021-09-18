package com.scuyjzh.leetcode.medium.No_0075_Sort_Colors;

import java.util.*;

/**
 * 75. 颜色分类
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 */
class Solution {
    /**
     * 方法一：单指针
     * 时间复杂度：O(N)，其中 N 是数组 nums 的长度。
     * 空间复杂度：O(1)。
     */
    public void sortColors1(int[] nums) {
        /*
         * 本题是经典的「荷兰国旗问题」，由计算机科学家 Edsger W. Dijkstra 首先提出。
         *
         * 思路与算法：
         * 可以考虑对数组进行两次遍历。
         * 在第一次遍历中，将数组中所有的 0 交换到数组的头部。
         * 在第二次遍历中，将数组中所有的 1 交换到头部的 0 之后。
         * 此时，所有的 2 都出现在数组的尾部，这样就完成了排序。
         *
         * 具体地，使用一个指针 ptr 表示「头部」的范围，ptr 中存储了一个整数，表示数组 nums 从位置 0 到位置 ptr−1 都属于「头部」。
         * ptr 的初始值为 0，表示还没有数处于「头部」。
         *
         * 在第一次遍历中，从左向右遍历整个数组，如果找到了 0，那么就需要将 0 与「头部」位置的元素进行交换，并将「头部」向后扩充一个位置。
         * 在遍历结束之后，所有的 0 都被交换到「头部」的范围，并且「头部」只包含 0。
         *
         * 在第二次遍历中，从「头部」开始，从左向右遍历整个数组，如果找到了 1，那么就需要将 1 与「头部」位置的元素进行交换，并将「头部」向后扩充一个位置。
         * 在遍历结束之后，所有的 1 都被交换到「头部」的范围，并且都在 0 之后，此时 2 只出现在「头部」之外的位置，因此排序完成。
         */
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                swap(nums, i, ptr++);
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                swap(nums, i, ptr++);
            }
        }
    }

    /**
     * 方法二：双指针
     * 时间复杂度：O(N)，其中 N 是数组 nums 的长度。
     * 空间复杂度：O(1)。
     */
    public void sortColors2(int[] nums) {
        /*
         * 思路与算法：
         * 方法一需要进行两次遍历，那么是否可以仅使用一次遍历呢？可以额外使用一个指针，即使用两个指针分别用来交换 0 和 1。
         *
         * 具体地，用指针 p0 来交换 0，p1 来交换 1，初始值都为 0。当从左向右遍历整个数组时：
         *   • 如果找到了 1，那么将其与 nums[p1] 进行交换，并将 p1 向后移动一个位置，这与方法一是相同的；
         *   • 如果找到了 0，那么将其与 nums[p0] 进行交换，并将 p0 向后移动一个位置。
         *
         * 这样做是正确的吗？
         * 可以注意到，对于第二种情况，因为连续的 0 之后是连续的 1，因此如果将 0 与 nums[p0] 进行交换，那么可能会把一个 1 交换出去。
         * 当 p0 < p1 时，已经将一些 1 连续地放在头部，此时一定会把一个 1 交换出去，导致答案错误。
         * 因此，如果 p0 < p1，那么需要再将 nums[i] 与 nums[p1] 进行交换，其中 i 是当前遍历到的位置，
         * 在进行了第一次交换后，nums[i] 的值为 1，需要将这个 1 放到「头部」的末端。
         * 在最后，无论是否有 p0 < p1，需要将 p0 和 p1 均向后移动一个位置，而不是仅将 p0 向后移动一个位置。
         */
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                swap(nums, i, p1++);
            } else if (nums[i] == 0) {
                swap(nums, i, p0);
                if (p0 < p1) {
                    swap(nums, i, p1);
                }
                p0++;
                p1++;
            }
        }
    }

    /**
     * 方法三：双指针
     * 时间复杂度：O(N)，其中 N 是数组 nums 的长度。
     * 空间复杂度：O(1)。
     */
    public void sortColors3(int[] nums) {
        /*
         * 思路与算法：
         * 与方法二类似，也可以考虑使用指针 p0 来交换 0，p2 来交换 2。
         * 此时，p0 的初始值仍然为 0，而 p2 的初始值为 n−1。
         * 在遍历的过程中，需要找出所有的 0 交换至数组的头部，并且找出所有的 2 交换至数组的尾部。
         *
         * 由于此时其中一个指针 p2 是从右向左移动的，因此当在从左向右遍历整个数组时，如果遍历到的位置超过了 p2，那么就可以直接停止遍历了。
         *
         * 具体地，从左向右遍历整个数组，设当前遍历到的位置为 i，对应的元素为 nums[i]：
         *   • 如果找到了 0，那么与前面两种方法类似，将其与 nums[p0] 进行交换，并将 p0 向后移动一个位置；
         *   • 如果找到了 2，那么将其与 nums[p2] 进行交换，并将 p2 向前移动一个位置。
         *
         * 这样做是正确的吗？
         * 可以发现，对于第二种情况，当将 nums[i] 与 nums[p2] 进行交换之后，新的 nums[i] 可能仍然是 2，也可能是 0。
         * 然而此时已经结束了交换，开始遍历下一个元素 nums[i+1]，不会再考虑 nums[i] 了，这样就会得到错误的答案。
         *
         * 因此，当找到 2 时，需要不断地将其与 nums[p2] 进行交换，直到新的 nums[i] 不为 2。
         * 此时，如果 nums[i] 为 0，那么对应着第一种情况；如果 nums[i] 为 1，那么就不需要进行任何后续的操作。
         */
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                swap(nums, i, p2--);
            }
            if (nums[i] == 0) {
                swap(nums, i, p0++);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        int[] nums;
        Solution solution = new Solution();

        nums = new int[]{2, 0, 2, 1, 1, 0};
        solution.sortColors1(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{2, 0, 2, 1, 1, 0};
        solution.sortColors2(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{2, 0, 2, 1, 1, 0};
        solution.sortColors3(nums);
        System.out.println(Arrays.toString(nums));
    }
}