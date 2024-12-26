package com.scuyjzh.leetcode.hard.No_0460_LFU_Cache;

import java.util.*;

/**
 * 460. LFU 缓存
 *
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *
 * 实现 LFUCache 类：
 *   • LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对
 *     象
 *   • int get(int key) - 如果键 key 存在于缓存中，则获取键的值，
 *     否则返回 -1 。
 *   • void put(int key, int value) - 如果键 key 已存在，则变更其
 *     值；如果键不存在，请插入键值对。当缓存达到其容量 capacity
 *     时，则应该在插入新项之前，移除最不经常使用的项。在此问题
 *     中，当存在平局（即两个或更多个键具有相同使用频率）时，应该
 *     去除 最近最久未使用 的键。
 *
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。
 * 使用计数最小的键是最久未使用的键。
 *
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操
 * 作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
class LFUCache {
    /**
     * 双向链表节点
     */
    class Node {
        int key, val;
        int freq;

        Node prev, next;

        Node() {
        }

        Node(int key, int val) {
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
     * key 映射到 Node(key, val, freq)
     */
    Map<Integer, Node> map;
    /**
     * freq 映射到 DoubleList
     */
    Map<Integer, DoubleList> freqMap;
    /**
     * 缓存容量
     */
    int capacity;
    /**
     * 最少使用频率
     */
    int minFreq;

    public LFUCache(int capacity) {
        map = new HashMap<>();
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        updateFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!map.containsKey(key)) {
            if (map.size() == capacity) {
                Node deletedNode = freqMap.get(minFreq).removeLast();
                map.remove(deletedNode.key);
                if (freqMap.get(minFreq).size() == 0) {
                    freqMap.remove(minFreq);
                }
            }
            Node node = new Node(key, value);
            node.freq = 1;
            map.put(key, node);
            DoubleList list = freqMap.getOrDefault(1, new DoubleList());
            list.addFirst(node);
            freqMap.put(1, list);
            minFreq = 1;
        } else {
            Node node = map.get(key);
            node.val = value;
            updateFreq(node);
        }
    }

    private void updateFreq(Node node) {
        int freq = node.freq;
        freqMap.get(freq).remove(node);
        if (freqMap.get(freq).size() == 0) {
            freqMap.remove(freq);
            if (minFreq == freq) {
                ++minFreq;
            }
        }
        ++node.freq;
        DoubleList list = freqMap.getOrDefault(freq + 1, new DoubleList());
        list.addFirst(node);
        freqMap.put(freq + 1, list);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
