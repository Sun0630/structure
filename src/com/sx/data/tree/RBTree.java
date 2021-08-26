package com.sx.data.tree;

import java.util.Comparator;

/**
 * 红黑树
 * 平衡二叉搜索树
 *
 * @param <E>
 */
public class RBTree<E> extends BST<E> {

    public static final boolean RED = false;
    public static final boolean BLACK = true;


    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    public RBTree() {
    }


    /**
     * 着色
     *
     * @param node
     * @param color
     * @return
     */
    private Node<E> coloring(Node<E> node, boolean color) {
        if (node == null) return null;
        ((RBNode<E>) node).color = color;
        return node;
    }

    /**
     * 染成红色
     *
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node) {
        return coloring(node, RED);
    }

    /**
     * 染成黑色
     *
     * @param node
     * @return
     */
    private Node<E> black(Node<E> node) {
        return coloring(node, BLACK);
    }

    /**
     * 判断一个节点的颜色
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    /**
     * 判断节点是否为黑色
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是否红黑色
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private static class RBNode<E> extends Node<E> {

        boolean color;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }


    }

}
