package com.scuyjzh.leetcode.hard.No_0315_Count_of_Smaller_Numbers_After_Self;

import java.util.*;

/**
 * 315. 计算右侧小于当前元素的个数
 * <p>
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 */
class Solution {
    /**
     * 方法一：归并排序
     * 时间复杂度：O(NlogN)，即归并排序的时间复杂度。
     * 空间复杂度：O(N)，这里归并排序的临时数组、下标映射数组以及答案数组的空间代价均为 O(N)。
     */
    private int[] index;
    private int[] helper;
    private int[] count;

    public List<Integer> countSmaller1(int[] nums) {
        List<Integer> res = new ArrayList<>(nums.length);

        index = new int[nums.length];
        helper = new int[nums.length];
        count = new int[nums.length];
        for (int i = 0; i < index.length; ++i) {
            index[i] = i;
        }

        merge(nums, 0, nums.length - 1);

        for (int i = 0; i < count.length; ++i) {
            res.add(i, count[i]);
        }
        return res;
    }

    private void merge(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;

        if (l < mid) {
            merge(nums, l, mid);
        }

        if (mid + 1 < r) {
            merge(nums, mid + 1, r);
        }

        int i = l, j = mid + 1;
        int cur = l;
        while (i <= mid && j <= r) {
            if (nums[index[i]] <= nums[index[j]]) {
                helper[cur++] = index[j++];
            } else {
                count[index[i]] += r - j + 1;
                helper[cur++] = index[i++];
            }
        }

        while (i <= mid) {
            helper[cur++] = index[i++];
        }

        while (j <= r) {
            helper[cur++] = index[j++];
        }

        for (int k = l; k <= r; ++k) {
            index[k] = helper[k];
        }
    }

    /**
     * 方法二：离散化树状数组
     * 时间复杂度：梳理一下这个算法的流程，这里离散化使用哈希表去重，然后再对去重的数组进行排序，时间代价为 O(NlogN)；初始化树状数组的时间代价是 O(N)；通过值获取离散化 id 的操作单次时间代价为 O(logN)；对于每个序列中的每个元素，都会做一次查询 id、单点修改和前缀和查询，总的时间代价为 O(NlogN)。故渐进时间复杂度为 O(NlogN)。
     * 空间复杂度：这里用到的离散化数组、树状数组、哈希表的空间代价都是 O(N)，故渐进空间复杂度为 O(N)。
     */
    private int[] C;
    private int[] N;

    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        discretization(nums);
        init(nums.length);
        for (int i = nums.length - 1; i >= 0; --i) {
            int id = getId(nums[i]);
            res.add(query(id - 1));
            update(id);
        }
        Collections.reverse(res);
        return res;
    }

    private void init(int length) {
        C = new int[length + 1];
        Arrays.fill(C, 0);
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private void update(int pos) {
        while (pos < C.length) {
            C[pos] += 1;
            pos += lowBit(pos);
        }
    }

    private int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += C[pos];
            pos -= lowBit(pos);
        }

        return ret;
    }

    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        N = new int[size];
        int index = 0;
        for (int num : set) {
            N[index++] = num;
        }
        Arrays.sort(N);
    }

    private int getId(int x) {
        return Arrays.binarySearch(N, x) + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countSmaller1(new int[]{5, 2, 6, 1}));
        System.out.println(new Solution().countSmaller2(new int[]{5, 2, 6, 1}));
    }
}