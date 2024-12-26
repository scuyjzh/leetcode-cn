package com.scuyjzh.leetcode.medium.No_0331_Verify_Preorder_Serialization_of_a_Binary_Tree;

import java.util.*;

/**
 * 331. 验证二叉树的前序序列化
 *
 * 序列化二叉树的一种方法是使用前序遍历。当遇到一个非空节点时，可以
 * 记录下这个节点的值。如果它是一个空节点，可以使用一个标记值记录，例如
 * #。
 *        _9_
 *       /   \
 *      3     2
 *     / \   / \
 *    4   1  #  6
 *   / \ / \   / \
 *   # # # #   # #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中
 * # 代表一个空节点。
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个
 * 在不重构树的条件下的可行算法。
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比
 * 如"1,,3" 。
 */
class Solution {
    boolean isValid = true;

    public boolean isValidSerialization1(String preorder) {
        String[] nodes = preorder.split(",");
        // 从树的根节点处开始遍历，获取能够遍历得到的节点个数
        int len = dfs(0, nodes);
        return isValid && len == nodes.length;
    }

    private int dfs(int index, String[] nodes) {
        // 判断是否越界
        if (index >= nodes.length) {
            isValid = false;
            return 0;
        }
        // 如果当前节点是 #，说明它没有子树，直接返回这个节点自身长度 1
        if ("#".equals(nodes[index])) {
            return 1;
        }
        // 当前节点不为 #，那么需要递归获取它的左子树长度（即左子树节点个数）
        // 由前序遍历可知，当前节点下标 +1，就是它的左子节点的下标
        int leftLen = dfs(index + 1, nodes);
        // 获取右子树长度，同样由前序遍历可知，当前节点下标 + 1 + 左子树长度，就是右子节点的下标
        int rightLen = dfs(index + 1 + leftLen, nodes);
        // 最后返回左子树长度 + 右子树长度 + 当前根节点长度（1），就是整棵树的长度
        return 1 + leftLen + rightLen;
    }

    public boolean isValidSerialization2(String preorder) {
        /*
         * 具体操作流程示例如下：
         * 如输入： "9,3,4,#,#,1,#,#,2,#,6,#,#" ，当遇到 x,#,# 的时候，就把它变为 #。
         * 模拟一遍过程：
         *   1.[9,3,4,#,#] => [9,3,#]，继续
         *   2.[9,3,#,1,#,#] => [9,3,#,#] => [9,#] ，继续
         *   3.[9,#2,#,6,#,#] => [9,#,2,#,#] => [9,#,#] => [#]，结束
         */
        LinkedList<String> stack = new LinkedList<>();
        for (String node : preorder.split(",")) {
            stack.push(node);
            while (stack.size() >= 3
                    && "#".equals(stack.get(0))
                    && "#".equals(stack.get(1))
                    && !"#".equals(stack.get(2))) {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push("#");
            }
        }
        return stack.size() == 1 && "#".equals(stack.pop());
    }

    public boolean isValidSerialization3(String preorder) {
        /*
         * 在树（甚至图）中，所有节点的入度之和等于出度之和。可以根据这个特点判断输入序列是否有效。
         * 在一棵二叉树中：
         *   • 每个空节点（ "#" ）会提供 0 个出度和 1 个入度。
         *   • 每个非空节点会提供 2 个出度和 1 个入度（根节点的入度是 0）。
         *
         * 只要把字符串遍历一次，每个节点都累加 diff = 出度 - 入度 。在遍历到任何一个节点的时候，要求
         * diff >= 0，原因是还没遍历到该节点的子节点，所以此时的出度应该大于等于入度。当所有节点遍历完成
         * 之后，整棵树的 diff == 0 。
         *
         * 将 diff 初始化为 1 的原因是，当遍历到一个非空节点时，都会对 diff 先减去 1（入度），再加上 2
         * （出度）。但是由于根节点没有父节点，所以其入度为 0，出度为 2。因此 diff 初始化为 1，是为了在
         * 遍历根节点时，diff 先减去 1（入度），再加上 2（出度），此时 diff 正好等于 2。
         */
        int diff = 1;
        for (String node : preorder.split(",")) {
            --diff;
            if (diff < 0) {
                return false;
            }
            if (!"#".equals(node)) {
                diff += 2;
            }
        }
        return diff == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValidSerialization1("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(new Solution().isValidSerialization2("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(new Solution().isValidSerialization3("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }
}
