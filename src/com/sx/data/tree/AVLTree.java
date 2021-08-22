package com.sx.data.tree;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;

import java.util.Comparator;

/**
 * 平衡二叉搜索树
 *
 * @param <E>
 */
public class AVLTree<E> extends BST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }


    @Override
    protected void afterNode(Node<E> node) {
        // 恢复平衡的逻辑
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                // 整棵树都恢复平衡
                break;
            }
        }
    }


    /**
     * 恢复平衡
     *
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance(Node<E> grand) {
        final Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        final Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                // g右旋转
                rotateRight(grand);
            } else { // LR
                // p先左旋转，g右旋转
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { //R
            if (node.isLeftChild()) { //RL
                // p先右旋转，g左旋转
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                // g左旋转
                rotateLeft(grand);
            }

        }
    }

    /**
     * 左旋转
     *
     * @param grand
     */
    private void rotateLeft(Node<E> grand) {
        // RR 的情况需要左旋转
        final Node<E> parent = grand.right;
        // p 的左子节点
        final Node<E> child = parent.left;

        // 1. 进行旋转
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转
     *
     * @param grand
     */
    private void rotateRight(Node<E> grand) {
        // LL
        final Node<E> parent = grand.left;
        final Node<E> child = parent.right;

        // 进行右旋转
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }


    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 2. 让parent成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            // grand是root节点
            root = parent;
        }

        //3. 更新child的parent
        if (child != null) child.parent = grand;

        // 4. 更新grand的parent
        grand.parent = parent;

        // 5. 更新g，p的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private static class AVLNode<E> extends Node<E> {
        // 新添加的节点默认是叶子节点，高度肯定为1
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 计算平衡因子
         *
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新节点的高度
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }


        /**
         * 返回高度最高的子树
         *
         * @return 如果两个子树高度相等，就根据自己是parent的左还是右来判断
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_P(" + parentString + ")" + "_H(" + height + ")";
        }
    }


}