package com.sx.data.array;

import org.w3c.dom.Node;

/**
 * 链表结构
 *
 * @param <E>
 */
public class LinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        final Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);
        if (index == 0) {
            first = new Node<>(element, first);
        } else {
            // 先拿到前一个节点
            final Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        } else {
            // 先拿到前一个节点
            final Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                // 使用equals
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    /**
     * 根据索引获取节点，就是从first节点一路循环找
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = ")
                .append(size)
                .append(",[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(node);
            node = node.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }
}
