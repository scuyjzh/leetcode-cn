package com.scuyjzh.leetcode.easy.No_0155_Min_Stack;

import java.util.*;

/**
 * 155. 最小栈
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素
 * 的栈。
 *   • push(x) —— 将元素 x 推入栈中。
 *   • pop() —— 删除栈顶的元素。
 *   • top() —— 获取栈顶元素。
 *   • getMin() —— 检索栈中的最小元素。
 */
class MinStack {
    Deque<Integer> xStack;
    /**
     * 使用一个辅助栈，与元素栈同步插入与删除，用于存储与每个元素对应的最小值
     */
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        // 当一个元素要入栈时，取当前辅助栈的栈顶存储的最小值，与当前元素比较得出最小值，将这个最小值插入辅助栈中
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        // 当一个元素要出栈时，把辅助栈的栈顶元素也一并弹出
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        // 在任意一个时刻，栈内元素的最小值就存储在辅助栈的栈顶元素中
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
