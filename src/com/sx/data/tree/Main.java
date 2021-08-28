package com.sx.data.tree;

import com.sx.data.tree.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        testRBTree();
    }

    public static void testAVLTree() {
        Integer[] data = {92, 91, 45, 84, 41, 20, 2, 87, 28, 64, 88, 67, 97, 94, 21, 76};
        BST<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }
        BinaryTrees.print(avlTree);
    }

    public static void testRBTree() {
        Integer[] data = {62, 77, 72, 4, 41, 67, 46, 5, 1};
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.print(rbTree);
    }

    /**
     * 前序遍历
     */
    public static void preOrderTraversal() {
        BST<Integer> bst = getIntegerBinarySearchTree();
//        bst.preOrderTraversal();
        bst.preOrder(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print("pre-" + integer + "-");
                return integer == 2;
            }
        });
    }

    /**
     * 中序遍历二叉搜索树，会有顺序
     */
    public static void inOrderTraversal() {
        final BST<Integer> bst = getIntegerBinarySearchTree();
//        bst.inOrderTraversal();
        bst.inOrder(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print("in-" + integer + "-");
                return integer == 2;
            }
        });
    }

    /**
     * 层序遍历二叉搜索树
     */
    public static void levelOrderTraversal() {
        final BST<Integer> bst = getIntegerBinarySearchTree();
//        bst.levelOrderTraversal();
    }

    /**
     * 层序遍历二叉搜索树
     */
    public static void levelOrderTraversal2() {
        final BST<Integer> bst = getIntegerBinarySearchTree();
        bst.levelOrder(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print("-" + integer + "-");
                return integer == 2;
            }
        });
        System.out.println();
        System.out.println(bst.toString());
        System.out.println("递归：二叉树高度为:" + bst.height());
        System.out.println("层序遍历：二叉树高度为:" + bst.height2());
        System.out.println("是否是完全二叉树:" + bst.isComplete());

    }


    private static BST<Integer> getIntegerBinarySearchTree() {
        Integer[] data = {7, 4, 9, 2, 1};
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.print(bst);
        System.out.println();
        return bst;
    }
}
