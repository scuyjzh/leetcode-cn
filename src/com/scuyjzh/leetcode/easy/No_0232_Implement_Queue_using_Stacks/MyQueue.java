package com.scuyjzh.leetcode.easy.No_0232_Implement_Queue_using_Stacks;

import java.util.*;

/**
 * 232. 用栈实现队列
 *
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操
 * 作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 *   • void push(int x) 将元素 x 推到队列的末尾
 *   • int pop() 从队列的开头移除并返回元素
 *   • int peek() 返回队列开头的元素
 *   • boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * 说明：
 *   • 你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop
 *     from top, size, 和 is empty 操作是合法的。
 *   • 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队
 *     列）来模拟一个栈，只要是标准的栈操作即可。
 *
 * 进阶：
 *   • 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执
 *     行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费
 *     较长时间。
 */
class MyQueue {
    /**
     * 将一个栈当作输入栈，用于压入 push 传入的数据
     */
    Deque<Integer> inStack;
    /**
     * 另一个栈当作输出栈，用于 pop 和 peek 操作
     */
    Deque<Integer> outStack;

    public MyQueue() {
        inStack = new ArrayDeque<Integer>();
        outStack = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        // 每次 pop 或 peek 时，若输出栈为空则将输入栈的全部数据依次弹出并压入输出栈
        // 这样输出栈从栈顶往栈底的顺序就是队列从队首往队尾的顺序
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }
}
