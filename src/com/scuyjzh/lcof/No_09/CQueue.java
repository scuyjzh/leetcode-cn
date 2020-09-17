package com.scuyjzh.lcof.No_09;

import java.util.*;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。
 *
 * @author scuyjzh
 * @date 2020/6/30 10:35
 */
class CQueue {
    Deque<Integer> A, B;

    public CQueue() {
        A = new ArrayDeque<>();
        B = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        A.push(value);
    }

    public int deleteHead() {
        if (!B.isEmpty()) {
            return B.pop();
        }
        if (A.isEmpty()) {
            return -1;
        }
        while (!A.isEmpty()) {
            B.push(A.pop());
        }
        return B.pop();
    }

    public static void main(String[] args) {
        CQueue q = new CQueue();
        q.appendTail(1);
        q.appendTail(2);
        q.appendTail(3);
        System.out.println(q.deleteHead());
        System.out.println(q.deleteHead());
        System.out.println(q.deleteHead());
    }
}
