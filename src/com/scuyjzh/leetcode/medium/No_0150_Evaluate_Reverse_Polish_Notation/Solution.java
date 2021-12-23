package com.scuyjzh.leetcode.medium.No_0150_Evaluate_Reverse_Polish_Notation;

import java.util.*;

/**
 * 150. 逆波兰表达式求值
 *
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另
 * 一个逆波兰表达式。
 *
 * 说明：
 *   • 整数除法只保留整数部分。
 *   • 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数
 *     且不存在除数为 0 的情况。
 *
 * 逆波兰表达式：
 * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 *   • 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 )
 *     。
 *   • 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 * 逆波兰表达式主要有以下两个优点：
 *   • 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依
 *     据次序计算出正确结果。
 *   • 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字
 *     进行计算，并将结果压入栈中。
 */
class Solution {
    /**
     * 方法一：栈
     */
    public int evalRPN1(String[] tokens) {
        /*
         * 逆波兰表达式严格遵循「从左到右」的运算。计算逆波兰表达式的值时，使用一个栈存储操作数，从左到右
         * 遍历逆波兰表达式，进行如下操作：
         *   • 如果遇到操作数，则将操作数入栈；
         *   • 如果遇到运算符，则将两个操作数出栈，其中先出栈的是右操作数，后出栈的是左操作数，使用运算符
         *     对两个操作数进行运算，将运算得到的新操作数入栈。
         *
         * 整个逆波兰表达式遍历完毕之后，栈内只有一个元素，该元素即为逆波兰表达式的值。
         */
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    /**
     * 方法二：数组模拟栈
     */
    public int evalRPN2(String[] tokens) {
        /*
         * 方法一使用栈存储操作数。也可以使用一个数组模拟栈操作。
         *
         * 最坏情况下，(n+1)/2 个操作数都在表达式的前面，(n-1)/2 个运算符都在表达式的后面，此时栈内元素最多为 (n+1)/2
         * 个。在其余情况下，栈内元素总是少于 (n+1)/2 个。因此，在任何情况下，栈内元素最多可能有 (n+1)/2 个，将数
         * 组的长度定义为 (n+1)/2 即可。
         *
         * 具体实现方面，创建数组 stack 模拟栈，数组下标 0 的位置对应栈底，定义 index 表示栈顶元素的下标位
         * 置，初始时栈为空，index=−1。当遇到操作数和运算符时，进行如下操作：
         *   • 如果遇到操作数，则将 index 的值加 1，然后将操作数赋给 stack[index]；
         *   • 如果遇到运算符，则将 index 的值减 1，此时 stack[index] 和 stack[index+1] 的元素分别是左操作数和
         *     右操作数，使用运算符对两个操作数进行运算，将运算得到的新操作数赋给 stack[index]。
         *
         * 整个逆波兰表达式遍历完毕之后，栈内只有一个元素，因此 index=0，此时 stack[index] 即为逆波兰表达式
         * 的值。
         */
        int[] stack = new int[(tokens.length + 1) / 2];
        int index = -1;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    --index;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    --index;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    --index;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    --index;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    ++index;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().evalRPN1(new String[] { "2", "1", "+", "3", "*" }));
        System.out.println(new Solution().evalRPN2(new String[] { "2", "1", "+", "3", "*" }));
    }
}
