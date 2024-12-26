package com.scuyjzh.leetcode.hard.No_0332_Reconstruct_Itinerary;

import java.util.*;

/**
 * 332. 重新安排行程
 *
 * 给你一份航线列表 tickets ，其中 tickets[i] = [from_i, to_i] 表示飞机
 * 出发和降落的机场地点。请你对该行程进行重新规划排序。
 *
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该
 * 行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最
 * 小的行程组合。
 *     • 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序
 *       更靠前。
 *
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只
 * 能用一次。
 */
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        /*
         * Hierholzer 算法用于在连通图中寻找欧拉路径，其流程如下：
         *   1.从起点出发，进行深度优先搜索。
         *   2.每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
         *   3.如果没有可移动的路径，则将所在节点加入到栈中，并返回。
         */
        List<String> ans = new LinkedList<>();
        if (tickets == null || tickets.size() == 0) {
            return ans;
        }

        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!graph.containsKey(src)) {
                graph.put(src, new PriorityQueue<>());
            }
            graph.get(src).offer(dst);
        }

        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            PriorityQueue<String> nbr;
            while ((nbr = graph.get(stack.peek())) != null && nbr.size() > 0) {
                stack.push(nbr.poll());
            }
            ans.add(0, stack.pop());
        }

        return ans;
    }

    public static void main(String[] args) {
        List<String> ticket1 = new ArrayList<>();
        ticket1.add("JFK");
        ticket1.add("SFO");
        List<String> ticket2 = new ArrayList<>();
        ticket2.add("JFK");
        ticket2.add("ATL");
        List<String> ticket3 = new ArrayList<>();
        ticket3.add("SFO");
        ticket3.add("ATL");
        List<String> ticket4 = new ArrayList<>();
        ticket4.add("ATL");
        ticket4.add("JFK");
        List<String> ticket5 = new ArrayList<>();
        ticket5.add("ATL");
        ticket5.add("SFO");

        List<List<String>> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);

        System.out.println(new Solution().findItinerary(tickets));
    }
}
