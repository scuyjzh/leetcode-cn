package com.scuyjzh.leetcode.medium.No_0881_Boats_to_Save_People;

import java.util.*;

/**
 * 881. 救生艇
 *
 * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，
 * 每艘船可以承载的最大重量为 limit。
 *
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 *
 * 返回 承载所有人所需的最小船数 。
 */
class Solution {
    /**
     * 方法：贪心 + 双指针
     */
    public int numRescueBoats(int[] people, int limit) {
        /*
         * 要使需要的船数尽可能地少，应当使载两人的船尽可能地多。
         *
         * 设 people 的长度为 n。考虑体重最轻的人：
         *   • 若他不能与体重最重的人同乘一艘船，那么体重最重的人无法与任何人同乘一艘船，此时应单独分配一
         *     艘船给体重最重的人。从 people 中去掉体重最重的人后，缩小了问题的规模，变成求解剩余 n-1
         *     个人所需的最小船数，将其加一即为原问题的答案。
         *   • 若他能与体重最重的人同乘一艘船，那么他能与其余任何人同乘一艘船，为了尽可能地利用船的承载重
         *     量，选择与体重最重的人同乘一艘船是最优的。从 people 中去掉体重最轻和体重最重的人后，缩
         *     小了问题的规模，变成求解剩余 n−2 个人所需的最小船数，将其加一即为原问题的答案。
         *
         * 在代码实现时，可以先对 people 排序，然后用两个指针分别指向体重最轻和体重最重的人，按照上述规
         * 则来移动指针，并统计答案。
         */
        int ans = 0;
        Arrays.sort(people);
        int light = 0, heavy = people.length - 1;
        while (light <= heavy) {
            if (people[light] + people[heavy] <= limit) {
                ++light;
            }
            --heavy;
            ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numRescueBoats(new int[]{3, 2, 2, 1}, 3));
        System.out.println(new Solution().numRescueBoats(new int[]{3, 5, 3, 4}, 5));
    }
}
