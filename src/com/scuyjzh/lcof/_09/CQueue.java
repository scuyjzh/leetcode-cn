package com.scuyjzh.lcof._09;

import java.util.LinkedList;

/**
 * 用两个栈实现队列
 *
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 *
 * @author scuyjzh
 * @date 2020/6/30 10:35
 */
class CQueue {
    LinkedList<Integer> A, B;

    public CQueue() {
        A = new LinkedList<>();
        B = new LinkedList<>();
    }

    public void appendTail(int value) {
        A.addLast(value);
    }

    public int deleteHead() {
        if (!B.isEmpty()) {
            return B.removeLast();
        }
        if (A.isEmpty()) {
            return -1;
        }
        while (!A.isEmpty()) {
            B.addLast(A.removeLast());
        }
        return B.removeLast();
    }
}
