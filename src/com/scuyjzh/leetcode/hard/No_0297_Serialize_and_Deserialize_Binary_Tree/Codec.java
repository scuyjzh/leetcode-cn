package com.scuyjzh.leetcode.hard.No_0297_Serialize_and_Deserialize_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 297. 二叉树的序列化与反序列化
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将
 * 转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一
 * 个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 /
 * 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符
 * 串并且将这个字符串反序列化为原始的树结构。
 */
class Codec {
    /**
     * 方法一：深度优先遍历
     */
    public String serialize1(TreeNode root) {
        return dfs(root);
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return "null";
        }
        return root.val + "," + dfs(root.left) + "," + dfs(root.right);
    }

    public TreeNode deserialize1(String data) {
        if ("null".equals(data)) {
            return null;
        }
        Deque<String> queue = new ArrayDeque<>(Arrays.asList(data.split(",")));
        return dfs(queue);
    }

    private TreeNode dfs(Queue<String> queue) {
        String val = queue.poll();
        if ("null".equals(val)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = dfs(queue);
        root.right = dfs(queue);
        return root;
    }

    /**
     * 方法二：广度优先遍历
     */
    public String serialize2(TreeNode root) {
        return bfs(root);
    }

    private String bfs(TreeNode root) {
        if (root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                sb.append(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                sb.append("null");
            }
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public TreeNode deserialize2(String data) {
        if ("null".equals(data)) {
            return null;
        }
        String[] dataList = data.split(",");
        return bfs(dataList);
    }

    private TreeNode bfs(String[] dataList) {
        TreeNode root = new TreeNode(Integer.parseInt(dataList[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!"null".equals(dataList[i])) {
                node.left = new TreeNode(Integer.parseInt(dataList[i]));
                queue.offer(node.left);
            }
            ++i;
            if (!"null".equals(dataList[i])) {
                node.right = new TreeNode(Integer.parseInt(dataList[i]));
                queue.offer(node.right);
            }
            ++i;
        }
        return root;
    }

    public static void main(String[] args) {
        Codec c = new Codec();
        String s1 = c.serialize1(TreeNode.initBinaryTree("[1,2,3,null,null,4,5]"));
        System.out.println(s1);
        TreeNode n1 = c.deserialize1(s1);
        System.out.println(n1);

        String s2 = c.serialize2(TreeNode.initBinaryTree("[1,2,3,null,null,4,5]"));
        System.out.println(s2);
        TreeNode n2 = c.deserialize2(s2);
        System.out.println(n2);
    }
}
