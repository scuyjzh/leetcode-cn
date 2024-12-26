package com.scuyjzh.leetcode.easy.No_0225_Implement_Stack_using_Queues;

import java.util.*;

/**
 * 225. 用队列实现栈
 *
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部
 * 四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *   • void push(int x) 将元素 x 压入栈顶。
 *   • int pop() 移除并返回栈顶元素。
 *   • int top() 返回栈顶元素。
 *   • boolean empty() 如果栈是空的，返回 true ；否则，返回 false
 *     。
 *
 * 注意：
 *   • 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop
 *     from front、size 和 is empty 这些操作。
 *   • 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者
 *     deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *
 * 进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执
 * 行 n 个操作的总时间复杂度 O(n) ，尽管其中某个操作可能需要比其他操
 * 作更长的时间。你可以使用两个以上的队列。
 */
class MyStack {
    /** queue1 用于存储栈内的元素 */
    Queue<Integer> queue1;
    /** queue2 作为入栈操作的辅助队列 */
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new ArrayDeque<Integer>();
        queue2 = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        /*
         * 入栈操作时，首先将元素入队到 queue2，然后将 queue1 的全部元素依次出队并入队到 queue2，此时 queue2
         * 的前端的元素即为新入栈的元素，再将 queue1 和 queue2 互换，则 queue1 的元素即为栈内的元素，queue1 的
         * 前端和后端分别对应栈顶和栈底。
         */
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        // 出栈操作只需要移除 queue1 的前端元素并返回即可
        return queue1.poll();
    }

    public int top() {
        // 获得栈顶元素操作只需要获得 queue1 的前端元素并返回即可（不移除元素）
        return queue1.peek();
    }

    public boolean empty() {
        // 由于 queue1 用于存储栈内的元素，判断栈是否为空时，只需要判断 queue1 是否为空即可
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
    }
}
