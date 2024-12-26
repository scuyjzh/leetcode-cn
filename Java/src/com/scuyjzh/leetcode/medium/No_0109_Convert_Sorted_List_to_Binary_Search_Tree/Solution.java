package com.scuyjzh.leetcode.medium.No_0109_Convert_Sorted_List_to_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.*;

/**
 * 109. 有序链表转换二叉搜索树
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索
 * 树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的
 * 高度差的绝对值不超过 1。
 */
class Solution {
    /**
     * 方法一：分治
     */
    public TreeNode sortedListToBST1(ListNode head) {
        /*
         * 将给定的有序链表转换为二叉搜索树的第一步是确定根节点。由于需要构造出平衡的二叉树，因此比较
         * 直观的想法是让根节点左子树中的节点个数与右子树中的节点个数尽可能接近。这样一来，左右子树的高度
         * 也会非常接近，可以达到高度差绝对值不超过 1 的题目要求。
         *
         * 如何找出这样的一个根节点呢？可以找出链表元素的中位数作为根节点的值。
         *   • 这里对于中位数的定义为：如果链表中的元素个数为奇数，那么唯一的中间值为中位数；如果元素个数
         *     为偶数，那么唯二的中间值都可以作为中位数，而不是常规定义中二者的平均值。
         *
         * 根据中位数的性质，链表中小于中位数的元素个数与大于中位数的元素个数要么相等，要么相差 1。此时，
         * 小于中位数的元素组成了左子树，大于中位数的元素组成了右子树，它们分别对应着有序链表中连续的一
         * 段。在这之后，使用分治的思想，继续递归地对左右子树进行构造，找出对应的中位数作为根节点，以
         * 此类推。
         *
         * 具体地，设当前链表的左端点为 left，右端点 right，包含关系为「左闭右开」，即 left 包含在链表中而 right
         * 不包含在链表中。希望快速地找出链表的中位数节点 mid。
         *   • 为什么要设定「左闭右开」的关系？由于题目中给定的链表为单向链表，访问后继元素十分容易，但无
         *     法直接访问前驱元素。因此在找出链表的中位数节点 mid 之后，如果设定「左闭右开」的关系，就
         *     可以直接用 (left,mid) 以及 (mid.next,right) 来表示左右子树对应的列表了。并且，初始的列表也可以用
         *     (head,null) 方便地进行表示，其中 null 表示空节点。
         *
         * 找出链表中位数节点的方法多种多样，其中较为简单的一种是「快慢指针法」。初始时，快指针 fast 和慢指
         * 针 slow 均指向链表的左端点 left。将快指针 fast 向右移动两次的同时，将慢指针 slow 向右移动一次，
         * 直到快指针到达边界（即快指针到达右端点或快指针的下一个节点是右端点）。此时，慢指针对应的元素就
         * 是中位数。
         *
         * 在找出了中位数节点之后，将其作为当前根节点的元素，并递归地构造其左侧部分的链表对应的左子
         * 树，以及右侧部分的链表对应的右子树。
         */
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    private ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * ptr 指针初始指向链表头节点
     */
    ListNode ptr;

    /**
     * 方法二：分治 + 中序遍历优化
     */
    public TreeNode sortedListToBST2(ListNode head) {
        /*
         * 方法一的时间复杂度的瓶颈在于寻找中位数节点。由于构造出的二叉搜索树的中序遍历结果就是链表本身，
         * 因此可以将分治和中序遍历结合起来，减少时间复杂度。
         *
         * 具体地，设当前链表的左端点编号为 left，右端点编号为 right，包含关系为「双闭」，即 left 和 right 均包含
         * 在链表中。链表节点的编号为 [0,n)。中序遍历的顺序是「左子树 - 根节点 - 右子树」，那么在分治的过程
         * 中，不用急着找出链表的中位数节点，而是使用一个占位节点，等到中序遍历到该节点时，再填充它的
         * 值。
         *
         * 可以通过计算编号范围来进行中序遍历：
         *   • 中位数节点对应的编号为 mid=(left+right)/2；
         *   • 左右子树对应的编号范围分别为 [left,mid−1] 和 [mid+1,right]。
         *
         * 如果 left>right，那么遍历到的位置对应着一个空节点，否则对应着二叉搜索树中的一个节点。
         *
         * 这样一来，其实已经知道了这棵二叉搜索树的结构，并且题目给定了它的中序遍历结果，那么只要
         * 对其进行中序遍历，就可以还原出整棵二叉搜索树了。
         */
        ptr = head;
        // 计算链表节点个数
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    private TreeNode buildTree(int left, int right) {
        // 递归出口，返回 null 节点
        if (left > right) {
            return null;
        }
        // 求 mid，只是为了分治，不是用它断开链表
        int mid = (left + right) / 2;
        // 先递归构建左子树
        TreeNode leftNode = buildTree(left, mid - 1);
        // 根据 ptr.val 构建当前 root 节点
        TreeNode root = new TreeNode(ptr.val);
        // ptr 指针步进
        ptr = ptr.next;
        // root 接上构建好的左子树
        root.left = leftNode;
        // 递归构建当前 root 的右子树，接上
        root.right = buildTree(mid + 1, right);
        return root;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            ++len;
            head = head.next;
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().sortedListToBST1(ListNode.initLinkedList("[-10,-3,0,5,9]")));
        System.out.println(new Solution().sortedListToBST2(ListNode.initLinkedList("[-10,-3,0,5,9]")));
    }
}
