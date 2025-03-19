package com.danielcaita.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeTraversal {

    static class TreeNode {
        int value;
        TreeNode left, right;
        
        TreeNode(int value) {
            this.value = value;
            this.left = this.right = null;
        }
    }

    // In-order Traversal (Left, Root, Right)
    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.value + " ");
            inOrder(root.right);
        }
    }

    // Pre-order Traversal (Root, Left, Right)
    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // Post-order Traversal (Left, Right, Root)
    public static void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.value + " ");
        }
    }

    // Level-order Traversal (Breadth-First Search)
    public static void levelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.value + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
}