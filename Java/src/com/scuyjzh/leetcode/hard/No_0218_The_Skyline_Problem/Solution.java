package com.scuyjzh.leetcode.hard.No_0218_The_Skyline_Problem;

import java.util.*;

/**
 * 218. 天际线问题
 *
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给
 * 你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
 *
 * https://leetcode-cn.com/problems/the-skyline-problem/
 */
class Solution {
    public List<List<Integer>> getSkyline1(int[][] buildings) {
        // 第 1 步：预处理，先把输入数组处理成点的样子，也就是处理成「(横坐标, 纵坐标)」的格式
        List<int[]> buildingPoints = new ArrayList<>();
        for (int[] building : buildings) {
            // 把左端点的纵坐标处理成负数，负号表示左边高度的转折点
            buildingPoints.add(new int[]{building[0], -building[2]});
            buildingPoints.add(new int[]{building[1], building[2]});
        }

        // 第 2 步：按照横坐标排序，横坐标相同的时候，高度高的在前面
        buildingPoints.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                // 注意：这里因为左端点传进去的时候，数值是负的，在比较的时候还按照升序排序
                return a[1] - b[1];
            }
        });

        // 第 3 步：扫描一遍动态计算出结果
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // 最开始的时候，需要产生高度差，所以需要加上一个高度为 0，宽度为 0 的矩形
        maxHeap.offer(0);
        // 为了计算高度差，需要保存之前最高的高度
        int preMax = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (int[] buildingPoint : buildingPoints) {
            int x = buildingPoint[0];
            int y = buildingPoint[1];
            if (y < 0) {
                // 如果是左端点，说明存在一条往右延伸的可记录的边，将高度存入优先队列
                maxHeap.offer(-y);
            } else {
                // 如果是右端点，说明这条边结束了，将当前高度从队列中移除
                maxHeap.remove(y);
            }

            // 取出最高高度，如果当前不与前一矩形“上边”延展而来的那些边重合，则可以被记录
            int curMax = maxHeap.peek();
            if (curMax != preMax) {
                res.add(Arrays.asList(x, curMax));
                preMax = curMax;
            }
        }

        return res;
    }

    public List<List<Integer>> getSkyline2(int[][] buildings) {
        // 第 1 步：预处理，先把输入数组处理成点的样子，也就是处理成「(横坐标, 纵坐标)」的格式
        List<int[]> buildingPoints = new ArrayList<>();
        for (int[] building : buildings) {
            // 把左端点的纵坐标处理成负数，负号表示左边高度的转折点
            buildingPoints.add(new int[]{building[0], -building[2]});
            buildingPoints.add(new int[]{building[1], building[2]});
        }

        // 第 2 步：按照横坐标排序，横坐标相同的时候，高度高的在前面
        buildingPoints.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                // 注意：这里因为左端点传进去的时候，数值是负的，在比较的时候还按照升序排序
                return a[1] - b[1];
            }
        });

        // 第 3 步：扫描一遍动态计算出结果
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
        Map<Integer, Integer> delayed = new HashMap<>();

        // 最开始的时候，需要产生高度差，所以需要加上一个高度为 0，宽度为 0 的矩形
        maxHeap.offer(0);
        // 为了计算高度差，需要保存之前最高的高度
        int preMax = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (int[] buildingPoint : buildingPoints) {
            int x = buildingPoint[0];
            int y = buildingPoint[1];
            if (y < 0) {
                // 说明此时是「从下到上」，纵坐标参与选拔最大值，请见「规则 1」
                maxHeap.offer(-y);
            } else {
                // 不是真的删除 y，把它放进 delayed，等到堆顶元素是 y 的时候，才真的删除
                delayed.put(y, delayed.getOrDefault(y, 0) + 1);
            }

            // 如果堆顶元素在延迟删除集合中，才真正删除，这一步可能执行多次，所以放在 while 中
            // while (true) 都是可以的，因为 maxHeap 一定不会为空
            while (!maxHeap.isEmpty()) {
                int curMax = maxHeap.peek();
                if (delayed.containsKey(curMax)) {
                    delayed.put(curMax, delayed.get(curMax) - 1);
                    if (delayed.get(curMax) == 0) {
                        delayed.remove(curMax);
                    }
                    maxHeap.poll();
                } else {
                    break;
                }
            }

            int curMax = maxHeap.peek();
            if (curMax != preMax) {
                res.add(Arrays.asList(x, curMax));
                preMax = curMax;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getSkyline1(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}));
        System.out.println(new Solution().getSkyline2(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}));
    }
}
