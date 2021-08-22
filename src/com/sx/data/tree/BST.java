package com.sx.data.tree;

import java.util.Comparator;

/**
 * 二叉搜索树
 * 存入的元素都必须是可比较的
 */
public class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BST() {
        this(null);
    }


    public boolean contains(E element) {
        return node(element) != null;
    }

    public void add(E element) {
        checkNodeNotNull(element);
        // 处理根节点
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterNode(root);
            return;
        }
        // 走到这里，说明添加的不是第一个节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int compare = 0;
        while (node != null) {
            compare = compare(element, node.element);
            parent = node;// 记录下父节点，node跳出循环的条件就是node==null
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {// 相等建议覆盖，因为考虑到自定义类型，如果是Person，年龄相同，姓名不同，需要覆盖
                node.element = element;
                return;
            }
        }
        // 构造新节点并寻找新节点插入的位置
        Node<E> newNode = createNode(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else if (compare < 0) {
            parent.left = newNode;
        }
        size++;
        afterNode(newNode);
    }

    /**
     * 新添加节点之后的处理
     *
     * @param node
     */
    protected void afterNode(Node<E> node) {
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;
        size--;
        // 先判断度为2
        if (node.hasTwoChild()) {
            // 找到后继节点
            final Node<E> successorNode = successor(node);
            // 用后继节点的值覆盖度为2的值
            node.element = successorNode.element;
            // 直接让node指向后继节点,删除后继节点
            node = successorNode;
        }
        // 删除node节点，现在node节点也就是后继节点了 ，node节点的度必然为1或0
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) {
            // 更改parent
            replacement.parent = node.parent;
            // 说明度为1
            if (node.parent == null) {
                // 说明是根节点
                root = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            } else {
                node.parent.left = replacement;
            }

        } else if (node.parent == null) {
            // 度为0，也就是叶子节点,并且是根节点
            root = null;
        } else {
            // 是叶子节点 但不是根节点
            if (node.parent.right == node) {
                node.parent.right = null;
            } else {
                node.parent.left = null;
            }

        }

    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            final int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) { // element > node.element
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }


    /**
     * 指定比较规则
     *
     * @return 0: e1==e2,  1: e1>e2  -1:e1<e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) comparator.compare(e1, e2);
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void checkNodeNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Node must be not null");
        }
    }

}
