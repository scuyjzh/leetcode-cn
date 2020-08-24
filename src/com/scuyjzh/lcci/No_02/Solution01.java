package com.scuyjzh.lcci.No_02;

import com.scuyjzh.lcci.No_00.ListNode;

import java.util.*;

/**
 * 移除重复节点
 *
 * @author scuyjzh
 * @date 2020/8/24 10:18
 */
class Solution01 {
    /**
     * 方法一：哈希表
     * 时间复杂度：O(N)，其中 N 是给定链表中节点的数目
     * 空间复杂度：O(N)。在最坏情况下，给定链表中每个节点都不相同，哈希表中需要存储所有的 N 个值
     */
    public ListNode removeDuplicateNodes1(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> occurred = new HashSet<>();
        occurred.add(head.val);
        ListNode pre = head;
        // 枚举前驱节点
        while (pre.next != null) {
            // 当前待删除节点
            ListNode cur = pre.next;
            if (occurred.add(cur.val)) {
                pre = pre.next;
            } else {
                // 在一些语言（例如 C++）中，并没有较好的内存回收机制
                // 因此如果在面试中遇到了本题，可以和面试官确认是否需要释放被移除的节点占用的内存空间
                pre.next = pre.next.next;
            }
        }
        pre.next = null;
        return head;
    }

    /**
     * 方法二：两重循环
     * 时间复杂度：O(N^2)，其中 N 是给定链表中节点的数目
     * 空间复杂度：O(1)
     */
    public ListNode removeDuplicateNodes2(ListNode head) {
        ListNode ob = head;
        // 第一重循环从链表的头节点开始，枚举一个保留的节点，这是因为我们保留的是「最开始出现的节点」
        while (ob != null) {
            ListNode oc = ob;
            // 第二重循环从枚举的保留节点开始，到链表的末尾结束，将所有与保留节点相同的节点全部移除
            while (oc.next != null) {
                if (oc.next.val == ob.val) {
                    oc.next = oc.next.next;
                } else {
                    oc = oc.next;
                }
            }
            // 继续枚举保留节点
            ob = ob.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Solution01 solution = new Solution01();
        System.out.println(ListNode.toString(solution.removeDuplicateNodes1(ListNode.initList("[1,2,3,3,4,5]"))));
        System.out.println(ListNode.toString(solution.removeDuplicateNodes2(ListNode.initList("[1,2,3,4,4,5]"))));
    }
}