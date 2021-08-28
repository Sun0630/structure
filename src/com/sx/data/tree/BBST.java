package com.sx.data.tree;

import java.util.Comparator;

/**
 * 平衡二叉搜索树
 */
public class BBST<E> extends BST<E>{

    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }


    /**
     * 统一旋转：不管是左旋转还是右旋转，最后旋转完成后的结果都是一样的，最后发现规律
     * d:会变成最终子树的根节点。  a , b ,c ,d ,e ,f ,g 按照顺序排列
     *
     * @param r 子树的根节点
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     */
    protected void rotate(Node<E> r,
                        Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f) {
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }


        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

    /**
     * 左旋转
     *
     * @param grand
     */
    protected void rotateLeft(Node<E> grand) {
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
    protected void rotateRight(Node<E> grand) {
        // LL
        final Node<E> parent = grand.left;
        final Node<E> child = parent.right;

        // 进行右旋转
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }


    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
    }
}
