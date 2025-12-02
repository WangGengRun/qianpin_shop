package com.itbuka.entity;

import java.util.*;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
        children = new ArrayList<>();
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

public class Main {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                currentLevel.add(current.val);

                if (current.children != null) {
                    for (Node child : current.children) {
                        queue.offer(child);
                    }
                }
            }
            result.add(currentLevel);
        }

        return result;
    }

    // 格式化输出层序遍历结果
    public static void printResult(List<List<Integer>> result) {
        System.out.println("层序遍历结果：");
        System.out.println("[");
        for (int i = 0; i < result.size(); i++) {
            List<Integer> level = result.get(i);
            System.out.print("  [");
            for (int j = 0; j < level.size(); j++) {
                System.out.print(level.get(j));
                if (j < level.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i < result.size() - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Main main = new Main();

        // 手动构造示例树
        //       1
        //     / | \
        //    3  2  4
        //   / \
        //  5   6
        Node root = new Node(1);

        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node4 = new Node(4);

        root.children.add(node3);
        root.children.add(node2);
        root.children.add(node4);

        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node3.children.add(node5);
        node3.children.add(node6);

        // 执行层序遍历并输出结果
        List<List<Integer>> result = main.levelOrder(root);
        printResult(result);
    }
}