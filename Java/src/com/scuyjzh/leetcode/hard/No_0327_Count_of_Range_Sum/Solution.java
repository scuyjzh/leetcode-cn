package com.scuyjzh.leetcode.hard.No_0327_Count_of_Range_Sum;

import java.util.*;

/**
 * 327. 区间和的个数
 * <p>
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 */
class Solution {
    /**
     * 方法一：归并排序
     * 时间复杂度：O(NlogN)，其中 N 为数组的长度。
     * 空间复杂度：O(N)。
     */
    public int countRangeSum1(int[] nums, int lower, int upper) {
        /*
         * 思路与算法：
         * 设前缀和数组为 preSum，则问题等价于求所有的下标对 (i,j)，满足
         *     preSum[j]−preSum[i]∈[lower,upper]
         *
         * 先考虑如下的问题：给定两个升序排列的数组 n1,n2，试找出所有的下标对 (i,j)，满足
         *     n2[j]-n1[i]∈[lower,upper]
         * 在已知两个数组均为升序的情况下，这一问题是相对简单的：在 n2 中维护两个指针 l,r。起初，它们都指向 n2 的起始位置。
         *
         * 随后，考察 n1 的第一个元素。
         * 首先，不断地将指针 l 向右移动，直到 n2[l]≥n1[0]+lower 为止，此时，l 及其右边的元素均大于或等于 n1[0]+lower；
         * 随后，再不断地将指针 r 向右移动，直到 n2[r]>n1[0]+upper 为止，则 r 左边的元素均小于或等于 n1[0]+upper。
         * 故区间 [l,r) 中的所有下标 j，都满足
         *     n2[j]−n1[0]∈[lower,upper]
         *
         * 接下来，考察 n1 的第二个元素。
         * 由于 n1 是递增的，不难发现 l,r 只可能向右移动。
         * 因此，不断地进行上述过程，并对于 n1 中的每一个下标，都记录相应的区间 [l,r) 的大小。
         * 最终，就统计得到了满足条件的下标对 (i,j) 的数量。
         *
         * 在解决这一问题后，原问题就迎刃而解了：
         * 采用归并排序的方式，能够得到左右两个数组排序后的形式，以及对应的下标对数量。
         * 对于原数组而言，若要找出全部的下标对数量，只需要再额外找出左端点在左侧数组，同时右端点在右侧数组的下标对数量，而这正是此前讨论的问题。
         */
        long s = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    private int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组
            long[] sorted = new long[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = sum[p1++];
                    } else {
                        sorted[p++] = sum[p2++];
                    }
                }
            }
            System.arraycopy(sorted, 0, sum, left, sorted.length);
            return ret;
        }
    }

    /**
     * Definition for a segment tree node.
     */
    class SegNode {
        int lo, hi, add;
        SegNode lchild, rchild;

        public SegNode(int left, int right) {
            lo = left;
            hi = right;
            add = 0;
            lchild = null;
            rchild = null;
        }
    }

    /**
     * 方法二：线段树
     * 时间复杂度：O(NlogN)。使用哈希离散化之后，线段树维护的区间大小为 O(N)，故其深度、单次查询或插入的时间复杂度均为 O(logN)。而离散化本身的复杂度也为 O(NlogN)。
     * 空间复杂度：O(N)。线段树的深度为 O(N)，而第 i 层拥有的节点数量为 2^{i-1}，故线段树总的节点数量为 2^{O(logN)}=O(N)。
     */
    public int countRangeSum2(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        Set<Long> allNumbers = new TreeSet<Long>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        SegNode root = build(0, values.size() - 1);
        int ret = 0;
        for (long x : preSum) {
            int left = values.get(x - upper), right = values.get(x - lower);
            ret += count(root, left, right);
            insert(root, values.get(x));
        }
        return ret;
    }

    private SegNode build(int left, int right) {
        SegNode node = new SegNode(left, right);
        if (left == right) {
            return node;
        }
        int mid = (left + right) / 2;
        node.lchild = build(left, mid);
        node.rchild = build(mid + 1, right);
        return node;
    }

    private int count(SegNode root, int left, int right) {
        if (left > root.hi || right < root.lo) {
            return 0;
        }
        if (left <= root.lo && root.hi <= right) {
            return root.add;
        }
        return count(root.lchild, left, right) + count(root.rchild, left, right);
    }

    private void insert(SegNode root, int val) {
        root.add++;
        if (root.lo == root.hi) {
            return;
        }
        int mid = (root.lo + root.hi) / 2;
        if (val <= mid) {
            insert(root.lchild, val);
        } else {
            insert(root.rchild, val);
        }
    }

    /**
     * Definition for a binary indexed tree.
     */
    class BIT {
        int[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        public int lowbit(int x) {
            return x & (-x);
        }

        public void update(int x, int d) {
            while (x <= n) {
                tree[x] += d;
                x += lowbit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while (x != 0) {
                ans += tree[x];
                x -= lowbit(x);
            }
            return ans;
        }
    }

    /**
     * 方法三：树状数组
     * 时间复杂度：O(NlogN)。离散化本身的复杂度为 O(NlogN)，而树状数组单次更新或查询的复杂度为 O(logN)。
     * 空间复杂度：O(N)。
     */
    public int countRangeSum3(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        Set<Long> allNumbers = new TreeSet<Long>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        BIT bit = new BIT(values.size());
        for (long x : preSum) {
            int left = values.get(x - upper), right = values.get(x - lower);
            ret += bit.query(right + 1) - bit.query(left);
            bit.update(values.get(x) + 1, 1);
        }
        return ret;
    }

    /**
     * Definition for a balanced binary search tree.
     */
    class BalancedTree {
        private class BalancedNode {
            long val;
            long seed;
            int count;
            int size;
            BalancedNode left;
            BalancedNode right;

            BalancedNode(long val, long seed) {
                this.val = val;
                this.seed = seed;
                this.count = 1;
                this.size = 1;
                this.left = null;
                this.right = null;
            }

            BalancedNode leftRotate() {
                int prevSize = size;
                int currSize = (left != null ? left.size : 0) + (right.left != null ? right.left.size : 0) + count;
                BalancedNode root = right;
                right = root.left;
                root.left = this;
                root.size = prevSize;
                size = currSize;
                return root;
            }

            BalancedNode rightRotate() {
                int prevSize = size;
                int currSize = (right != null ? right.size : 0) + (left.right != null ? left.right.size : 0) + count;
                BalancedNode root = left;
                left = root.right;
                root.right = this;
                root.size = prevSize;
                size = currSize;
                return root;
            }
        }

        private BalancedNode root;
        private int size;
        private Random rand;

        public BalancedTree() {
            this.root = null;
            this.size = 0;
            this.rand = new Random();
        }

        public long getSize() {
            return size;
        }

        public void insert(long x) {
            ++size;
            root = insert(root, x);
        }

        public long lowerBound(long x) {
            BalancedNode node = root;
            long ans = Long.MAX_VALUE;
            while (node != null) {
                if (x == node.val) {
                    return x;
                }
                if (x < node.val) {
                    ans = node.val;
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            return ans;
        }

        public long upperBound(long x) {
            BalancedNode node = root;
            long ans = Long.MAX_VALUE;
            while (node != null) {
                if (x < node.val) {
                    ans = node.val;
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            return ans;
        }

        public int[] rank(long x) {
            BalancedNode node = root;
            int ans = 0;
            while (node != null) {
                if (x < node.val) {
                    node = node.left;
                } else {
                    ans += (node.left != null ? node.left.size : 0) + node.count;
                    if (x == node.val) {
                        return new int[]{ans - node.count + 1, ans};
                    }
                    node = node.right;
                }
            }
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
        }

        private BalancedNode insert(BalancedNode node, long x) {
            if (node == null) {
                return new BalancedNode(x, rand.nextInt());
            }
            ++node.size;
            if (x < node.val) {
                node.left = insert(node.left, x);
                if (node.left.seed > node.seed) {
                    node = node.rightRotate();
                }
            } else if (x > node.val) {
                node.right = insert(node.right, x);
                if (node.right.seed > node.seed) {
                    node = node.leftRotate();
                }
            } else {
                ++node.count;
            }
            return node;
        }
    }

    /**
     * 方法四：平衡二叉搜索树
     * 时间复杂度：O(NlogN)。
     * 空间复杂度：O(N)。
     */
    public int countRangeSum4(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        BalancedTree treap = new BalancedTree();
        int ret = 0;
        for (long x : preSum) {
            long numLeft = treap.lowerBound(x - upper);
            int rankLeft = (numLeft == Long.MAX_VALUE ? (int) (treap.getSize() + 1) : treap.rank(numLeft)[0]);
            long numRight = treap.upperBound(x - lower);
            int rankRight = (numRight == Long.MAX_VALUE ? (int) treap.getSize() : treap.rank(numRight)[0] - 1);
            ret += rankRight - rankLeft + 1;
            treap.insert(x);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countRangeSum1(new int[]{-2, 5, -1}, -2, 2));
        System.out.println(new Solution().countRangeSum2(new int[]{-2, 5, -1}, -2, 2));
        System.out.println(new Solution().countRangeSum3(new int[]{-2, 5, -1}, -2, 2));
        System.out.println(new Solution().countRangeSum4(new int[]{-2, 5, -1}, -2, 2));
    }
}