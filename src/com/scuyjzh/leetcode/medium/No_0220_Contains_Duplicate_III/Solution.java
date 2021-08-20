package com.scuyjzh.leetcode.medium.No_0220_Contains_Duplicate_III;

import java.util.*;

/**
 * 220. 存在重复元素 III
 * <p>
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 */
class Solution {
    /**
     * 方法一：滑动窗口 + 有序集合
     * 时间复杂度：O(n*log(min(n,k)))，其中 n 是给定数组的长度。
     * 空间复杂度：O(min(n,k))，其中 n 是给定数组的长度。有序集合中至多包含 min(n,k+1) 个元素。
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        /*
         * 思路及算法：
         * 对于序列中每一个元素 x 左侧的至多 k 个元素，如果这 k 个元素中存在一个元素落在区间 [x−t, x+t] 中，就找到了一对符合条件的元素。
         * 注意到对于两个相邻的元素，它们各自的左侧的 k 个元素中有 k−1 个是重合的。
         * 于是可以使用滑动窗口的思路，维护一个大小为 k 的滑动窗口，
         * 每次遍历到元素 x 时，滑动窗口中包含元素 x 前面的最多 k 个元素，检查窗口中是否存在元素落在区间 [x−t, x+t] 中即可。
         *
         * 如果使用队列维护滑动窗口内的元素，由于元素是无序的，只能对于每个元素都遍历一次队列来检查是否有元素符合条件。
         * 如果数组的长度为 n，则使用队列的时间复杂度为 O(nk)，会超出时间限制。
         *
         * 因此希望能够找到一个数据结构维护滑动窗口内的元素，该数据结构需要满足以下操作：
         *   • 支持添加和删除指定元素的操作，否则无法维护滑动窗口；
         *   • 内部元素有序，支持二分查找的操作，这样可以快速判断滑动窗口中是否存在元素满足条件。
         * 具体而言，对于元素 x，当希望判断滑动窗口中是否存在某个数 y 落在区间 [x−t, x+t] 中，
         * 只需要判断滑动窗口中所有大于等于 x−t 的元素中的最小元素是否小于等于 x+t 即可。
         *
         * 可以使用有序集合来支持这些操作。
         * 实现方面，在有序集合中查找大于等于 x−t 的最小的元素 y，如果 y 存在，且 y ≤ x+t，就找到了一对符合条件的元素。
         * 完成检查后，将 x 插入到有序集合中，如果有序集合中元素数量超过了 k，将有序集合中最早被插入的元素删除即可。
         *
         * 注意：
         * 如果当前有序集合中存在相同元素，那么此时程序将直接返回 true。因此本题中的有序集合无需处理相同元素的情况。
         * 为防止整型 int 溢出，既可以使用长整型 long，也可以对查找区间 [x−t, x+t] 进行限制，使其落在 int 范围内。
         */
        int n = nums.length;
        // 使用有序集合维护一个大小为 k 的滑动窗口
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            // The ceiling() method of java.util.TreeSet<E> class is used to
            // return the least element in this set greater than or equal to
            // the given element, or null if there is no such element.
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            // 在有序集合中如果存在大于等于 x−t 的最小的元素 y，且 y ≤ x+t，就找到了一对符合条件的元素
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            // 完成检查后，将 x 插入到有序集合中
            set.add((long) nums[i]);
            // 如果有序集合中元素数量超过了 k，将有序集合中最早被插入的元素删除
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 方法二：桶
     * 时间复杂度：O(n)，其中 n 是给定数组的长度。每个元素至多被插入哈希表和从哈希表中删除一次，每次操作的时间复杂度均为 O(1)。
     * 空间复杂度：O(min(n,k))，其中 n 是给定数组的长度。哈希表中至多包含 min(n,k+1) 个元素。
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        /*
         * 思路及算法：
         * 利用桶排序的思想解决本题，按照元素的大小进行分桶。
         * 对于元素 x，其影响的区间为 [x−t, x+t]。于是可以设定桶的大小为 t+1。
         * 如果两个元素同属一个桶，那么这两个元素必然符合条件。
         * 如果两个元素属于相邻桶，那么需要校验这两个元素是否差值不超过 t。
         * 如果两个元素既不属于同一个桶，也不属于相邻桶，那么这两个元素必然不符合条件。
         *
         * 具体地，遍历该序列，假设当前遍历到元素 x，那么首先检查 x 所属于的桶是否已经存在元素，
         * 如果存在，那么就找到了一对符合条件的元素，否则继续检查两个相邻的桶内是否存在符合条件的元素。
         *
         * 实现方面，将 int 范围内的每一个整数 x 表示为 x=(t+1)×a+b(0≤b≤t) 的形式，这样 x 即归属于编号为 a 的桶。
         * 因为一个桶内至多只会有一个元素，所以使用哈希表实现即可。
         */
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>(16);
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            // 获取桶编号，同一个编号的桶内元素满足 abs(nums[i] - nums[j]) <= t
            long id = getID(nums[i], w);
            // 对于当前下标 i，有效桶是下标为 [i-k, i] 的元素所属于的桶
            if (i - (k + 1) >= 0) {
                // 删除下标范围 [i-k, i] 之外的元素所属于的无效桶
                map.remove(getID(nums[i - (k + 1)], w));
            }
            // 如果两个元素同属一个桶，那么这两个元素必然符合条件
            if (map.containsKey(id)) {
                return true;
            }
            // 如果两个元素属于相邻桶，那么需要校验这两个元素是否差值不超过 t
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            // 将当前元素分桶
            map.put(id, (long) nums[i]);
        }
        return false;
    }

    private long getID(long x, long w) {
        // 如果值大于等于零，直接分桶
        if (x >= 0) {
            return x / w;
        }
        // 如果值小于零，偏移后再分桶
        return (x + 1) / w - 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().containsNearbyAlmostDuplicate1(new int[]{1, 5, 9, 1, 5, 9}, 3, 0));
        System.out.println(new Solution().containsNearbyAlmostDuplicate2(new int[]{1, 2, 3, 1}, 3, 0));
    }
}
