package com.scuyjzh.leetcode.medium.No_0142_Linked_List_Cycle_II;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 142. 环形链表 II
 * <p>
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
class Solution {
    /**
     * 方法：快慢指针
     * 时间复杂度：O(N)，其中 N 为链表中节点的数目。
     * 空间复杂度：O(1)。只使用了 slow,fast,ptr 三个指针。
     */
    public ListNode detectCycle(ListNode head) {
        /*
         * 思路与算法：
         * 使用两个指针，fast 与 slow。它们起始都位于链表的头部。
         * 随后，slow 指针每次向后移动一个位置，而 fast 指针向后移动两个位置。
         * 如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。
         *
         * 设链表中环外部分的长度为 a。slow 指针进入环后，又走了 b 的距离与 fast 相遇，从相遇位置继续走 c 步回到环的入口。
         * 在相遇位置，fast 指针已经走完了环的 n 圈，因此它走过的总距离为 a+n(b+c)+b = a+(n+1)b+nc。
         *
         * 根据题意，任意时刻，fast 指针走过的距离都为 slow 指针的 2 倍。因此，有
         *         a+(n+1)b+nc = 2(a+b) ⟹ a = c+(n−1)(b+c)
         * 有了 a = c+(n−1)(b+c) 的等量关系，会发现：
         * 从相遇点到入环点的距离加上 n−1 圈的环长，恰好等于从链表头部到入环点的距离。
         *
         * 因此，当发现 slow 与 fast 相遇时，再额外使用一个指针 ptr。
         * 起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。
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
                return ptr;
            }
        }

        return null;
    }
}
