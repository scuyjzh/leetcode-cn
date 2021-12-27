package com.scuyjzh.leetcode.medium.No_0133_Clone_Graph;

import java.util.*;

/**
 * 133. 克隆图
 *
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *   class Node {
 *       public int val;
 *       public List<Node> neighbors;
 *   }
 */
class Solution {
    /**
     * Definition for a Node.
     */
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * 方法一：深度优先搜索
     */
    public Node cloneGraph1(Node node) {
        HashMap<Node, Node> visited = new HashMap<>();
        return dfs(node, visited);
    }

    private Node dfs(Node node, HashMap<Node, Node> visited) {
        if (node == null) {
            return node;
        }

        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 克隆节点，注意到为了深拷贝，不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList());
        // 哈希表存储
        visited.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(dfs(neighbor, visited));
        }
        return cloneNode;
    }

    /**
     * 方法二：广度优先搜索
     */
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }

        HashMap<Node, Node> visited = new HashMap();

        // 将题目给定的节点添加到队列
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, new Node(node.val, new ArrayList()));

        // 广度优先搜索
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            Node curNode = queue.poll();
            // 遍历该节点的邻居
            for (Node neighbor : curNode.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    // 将邻居节点加入队列中
                    queue.offer(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(curNode).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }
}
