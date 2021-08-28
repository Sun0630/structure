package com.sx.data.tree;

import com.sun.org.apache.xml.internal.serializer.ToStream;
import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;

import java.util.Comparator;

/**
 * 红黑树
 * 自平衡的二叉搜索树
 *
 * @param <E>
 */
public class RBTree<E> extends BBST<E> {

    public static final boolean RED = false;
    public static final boolean BLACK = true;


    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    public RBTree() {
    }


    @Override
    protected void afterAdd(Node<E> node) {
        final Node<E> parent = node.parent;
        if (parent == null) {
            // 1. 如果添加的是根节点，直接染黑
            black(node);
            return;
        }
        //2.  如果添加在黑色节点下，不做处理
        if (isBlack(parent)) {
            return;
        }

        // 叔父节点
        final Node<E> uncle = node.uncle();
        // 祖父节点
        final Node<E> grand = parent.parent;
        // 3. 叔父节点是红色
        if (isRed(uncle)) {
            // 上溢，染色
            black(uncle);
            black(parent);
            // 祖父节点当成是新添加的节点，向上合并。递归调用
            afterAdd(red(grand));
            return;
        }

        // 4. 叔父节点不是红色，LL,RR,LR,RL 需要旋转
        if (parent.isLeftChild()) { // L
            red(grand);
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            // grand进行右旋转
            rotateRight(grand);
        } else { // R
            red(grand);
            if (node.isLeftChild()) { //RL
                black(node);
                rotateRight(parent);
            } else {//RR
                black(parent);
            }
            // grand进行左旋转
            rotateLeft(grand);
        }

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
     *
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    /**
     * 判断节点是否为黑色
     *
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是否红黑色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {

        // 默认是红色
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }

}
