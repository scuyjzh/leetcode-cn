package com.scuyjzh.leetcode.medium.No_0142_Linked_List_Cycle_II;

import com.scuyjzh.leetcode.structure.ListNode;

import java.util.*;

/**
 * 142. 环形链表 II
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返
 * 回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中
 * 存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链
 * 表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链
 * 表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实
 * 际情况。
 * 不允许修改 链表。
 * 进阶：你是否可以使用 O(1) 空间解决此题？
 */
class Solution {
    /**
     * 方法一：哈希表
     *
     * • 时间复杂度：O(N)，其中 N 为链表中节点的数目。恰好需要访问链表中的每一个节点。
     * • 空间复杂度：O(N)，其中 N 为链表中节点的数目。需要将链表中的每个节点都保存在哈希表当
     *   中。
     */
    public ListNode detectCycle1(ListNode head) {
        /*
         * 一个非常直观的思路是：遍历链表中的每个节点，并将它记录下来；一旦遇到了此前遍历过的节点，就
         * 可以判定链表中存在环。借助哈希表可以很方便地实现。
         */
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }

    /**
     * 方法二：快慢指针
     *
     * • 时间复杂度：O(N)，其中 N 为链表中节点的数目。在最初判断快慢指针是否相遇时，slow 指针走过
     *   的距离不会超过链表的总长度；随后寻找入环点时，走过的距离也不会超过链表的总长度。因此，总的
     *   执行时间为 O(N)+O(N)=O(N)。
     * • 空间复杂度：O(1)。只使用了 slow,fast,ptr 三个指针。
     */
    public ListNode detectCycle2(ListNode head) {
        /*
         * 使用两个指针，fast 与 slow。它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，而
         * fast 指针向后移动两个位置。如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。
         *
         * 设链表中环外部分的长度为 a。slow 指针进入环后，又走了 b 的距离与 fast 相遇。此时，fast
         * 指针已经走完了环的 n 圈，因此它走过的总距离为 a+n(b+c)+b = a+(n+1)b+nc。
         *
         * 根据题意，任意时刻，fast 指针走过的距离都为 slow 指针的 2 倍。因此，有
         *         a+(n+1)b+nc=2(a+b)   ⟹   a=c+(n−1)(b+c)
         * 有了 a=c+(n−1)(b+c) 的等量关系，会发现：从相遇点到入环点的距离加上 n−1 圈的环长，恰好
         * 等于从链表头部到入环点的距离。
         *
         * 因此，当发现 slow 与 fast 相遇时，再额外使用一个指针 ptr。起始，它指向链表头部；随后，它和 slow
         * 每次向后移动一个位置。最终，它们会在入环点相遇。
         */
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // slow 与 fast 相遇
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                // 在入环点相遇
                return ptr;
            }
        }

        return null;
    }
}
