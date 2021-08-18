package com.scuyjzh.leetcode.medium.No_0189_Rotate_Array;

import java.util.*;

/**
 * 189. 旋转数组
 * <p>
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 进阶：
 * • 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * • 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 */
class Solution {
    /**
     * 方法一：使用额外的数组
     * 时间复杂度：O(n)，其中 n 为数组的长度。
     * 空间复杂度：O(n)。
     */
    public void rotate1(int[] nums, int k) {
        int len = nums.length;
        // 使用额外的数组来将每个元素放至正确的位置
        int[] newArr = new int[len];
        for (int i = 0; i < len; ++i) {
            // 遍历原数组，将原数组下标为 i 的元素放至新数组下标为 (i+k) mod n 的位置
            newArr[(i + k) % len] = nums[i];
        }
        // 将新数组拷贝至原数组
        System.arraycopy(newArr, 0, nums, 0, len);
    }

    /**
     * 方法二：环状替换
     * 时间复杂度：O(n)，其中 n 为数组的长度。每个元素只会被遍历一次。
     * 空间复杂度：O(1)。只需常数空间存放若干变量。
     */
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        // 若k大于nums.length
        k %= len;
        // 使用单独的变量 count 跟踪当前已经访问的元素数量，当 count=n 时，结束遍历过程
        int count = 0;
        for (int start = 0; count < len; start++) {
            int cur = start;
            int pre = nums[cur];
            do {
                int next = (cur + k) % len;
                // 如果直接将每个数字放至它最后的位置，这样被放置位置的元素会被覆盖从而丢失
                // 因此，从另一个角度，可以将被替换的元素保存在变量 temp 中，从而避免了额外数组的开销
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            } while (start != cur);
        }
    }

    /**
     * 方法三：数组翻转
     * 时间复杂度：O(n)，其中 n 为数组的长度。每个元素被翻转两次，一共 n 个元素，因此总时间复杂度为 O(2n)=O(n)。
     * 空间复杂度：O(1)。
     */
    public void rotate3(int[] nums, int k) {
        /*
          visualization:
          nums = "----->-->"; k =3
          result = "-->----->";

          reverse "----->-->" we can get "<--<-----"
          reverse "<--" we can get "--><-----"
          reverse "<-----" we can get "-->----->"
         */
        // 若k大于nums.length
        k %= nums.length;
        // 先将所有元素翻转，这样尾部的 k mod n 个元素就被移至数组头部
        reverse(nums, 0, nums.length - 1);
        // 然后再翻转 [0, (k mod n)−1] 区间的元素
        reverse(nums, 0, k - 1);
        // 和 [k mod n, n−1] 区间的元素
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        solution.rotate1(nums, 3);
        System.out.println(Arrays.toString(nums));
        solution.rotate2(nums, 3);
        System.out.println(Arrays.toString(nums));
        solution.rotate3(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
