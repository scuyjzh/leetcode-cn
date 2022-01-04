package com.scuyjzh.leetcode.medium.No_0406_Queue_Reconstruction_by_Height;

import java.util.*;

/**
 * 406. 根据身高重建队列
 *
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人
 * 的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的
 * 身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 *
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格
 * 式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属
 * 性（queue[0] 是排在队列前面的人）。
 */
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }

        return list.toArray(new int[list.size()][2]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}})));
    }
}
