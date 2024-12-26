package com.scuyjzh.leetcode.medium.No_0146_LRU_Cache;

import java.util.*;

/**
 * 146. LRU 缓存
 *
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 *
 * 实现 LRUCache 类：
 *   • LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化
 *     LRU 缓存
 *   • int get(int key) 如果关键字 key 存在于缓存中，则返回关键字
 *     的值，否则返回 -1 。
 *   • void put(int key, int value) 如果关键字 key 已经存在，则变
 *     更其数据值 value ；如果不存在，则向缓存中插入该组 key-
 *     value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐
 *     出 最久未使用的关键字。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
class LRUCache {
    /**
     * 双向链表节点
     */
    class Node {
        int key, val;
        Node next, prev;

        Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * 双向链表
     */
    class DoubleList {
        /**
         * 虚拟头尾节点
         */
        Node head, tail;
        /**
         * 链表元素数
         */
        int size;

        DoubleList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 在链表头部添加节点 x，时间复杂度 O(1)
         */
        void addFirst(Node x) {
            x.next = head.next;
            x.prev = head;
            head.next.prev = x;
            head.next = x;
            ++size;
        }

        /**
         * 删除链表中的 x 节点（x 一定存在），时间复杂度 O(1)
         */
        void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            --size;
        }

        /**
         * 删除链表中最后一个节点，并返回该节点，时间复杂度 O(1)
         */
        Node removeLast() {
            if (tail.prev == head) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }

        /**
         * 返回链表长度，时间复杂度 O(1)
         */
        int size() {
            return size;
        }
    }

    /**
     * 缓存容量
     */
    private int capacity;
    /**
     * key 映射到 Node(key, val)
     */
    private HashMap<Integer, Node> map;
    /**
     * Node(k1, v1) <-> Node(k2, v2)...
     */
    private DoubleList cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
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
        // key 已存在
        if (map.containsKey(key)) {
            // 删除旧的节点
            cache.remove(map.get(key));
        } else {
            // cache 已满
            if (capacity == cache.size()) {
                // 删除链表最后一个节点
                Node last = cache.removeLast();
                // 同时删除 map 中映射到该节点的 key
                map.remove(last.key);
            }
        }
        // 新节点 x 插到头部
        cache.addFirst(x);
        // 更新 map 中对应的数据
        map.put(key, x);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
