package com.sx.data.tree;

import java.util.Comparator;

/**
 * 平衡二叉搜索树
 *
 * @param <E>
 */
public class AVLTree<E> extends BBST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }


    @Override
    protected void afterAdd(Node<E> node) {
        // 恢复平衡的逻辑
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                reblance(node);
                // 整棵树都恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // 恢复平衡的逻辑
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                reblance(node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        // 5. 更新g，p的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /**
     * 恢复平衡
     *
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance2(Node<E> grand) {
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
     * 统一旋转操作。
     *
     * @param grand
     */
    private void reblance(Node<E> grand) {
        final Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        final Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else { // LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else { //R
            if (node.isLeftChild()) { //RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else { // RR
                rotate(grand, grand, parent.left, parent, node, node.left);
            }

        }
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
