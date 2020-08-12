package com.scuyjzh.leetcode.medium.No_146_LRU_Cache;

import java.util.*;

/**
 * @author scuyjzh
 * @data 2020/6/16 21:11
 */
class LRUCache {
    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    class DoubleList {
        /**
         * 头尾虚节点
         */
        private Node head, tail;
        /**
         * 链表元素数
         */
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        /**
         * 在链表头部添加节点 x
         */
        public void addFirst(Node x) {
            x.next = head.next;
            x.prev = head;
            head.next.prev = x;
            head.next = x;
            size++;
        }

        /**
         * 删除链表中的 x 节点（x 一定存在）
         */
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        /**
         * 删除链表中最后一个节点，并返回该节点
         */
        public Node removeLast() {
            if (tail.prev == head) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }

        /**
         * 返回链表长度
         */
        public int size() {
            return size;
        }
    }

    /**
     * key -> Node(key, val)
     */
    private HashMap<Integer, Node> map;
    /**
     * Node(k1, v1) <-> Node(k2, v2)...
     */
    private DoubleList cache;
    /**
     * 最大容量
     */
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<>();
        this.cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        // 先把新节点 x 做出来
        Node x = new Node(key, val);

        if (map.containsKey(key)) {
            // 删除旧的节点
            cache.remove(map.get(key));
        } else {
            if (cap == cache.size()) {
                // 删除链表最后一个节点
                Node last = cache.removeLast();
                // 同时删除 map 中映射到该节点的 key
                map.remove(last.key);
            }
        }
        // 新节点插到头部
        cache.addFirst(x);
        // 更新 map 中对应的数据
        map.put(key, x);
    }
}
