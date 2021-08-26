package com.sx.data.tree;

import com.sx.data.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 */
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    // 根节点
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 前序遍历
     *
     * @param visitor
     */
    public void preOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrder(root, visitor);
    }

    private void preOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }

    /**
     * 中序遍历
     *
     * @param visitor
     */
    public void inOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        inOrder(root, visitor);
    }

    private void inOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inOrder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inOrder(node.right, visitor);
    }


    /**
     * 后序遍历
     *
     * @param visitor
     */
    public void postOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        postOrder(root, visitor);
    }

    private void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历
     *
     * @param visitor
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        // 头结点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 队头出队访问
            final Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;
            if (node.left != null) { // 入队
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }


    /**
     * 获取二叉树的高度
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 使用递归的方式，获取某个节点的高度
     * 思路：某一节点的高度等于1+它的左右节点的最大的那个高度
     *
     * @param node
     * @return
     */
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 使用层序遍历的方式获取高度，记录层数
     *
     * @return
     */
    public int height2() {
        if (root == null) return 0;
        Queue<Node<E>> queue = new LinkedList<>();
        int height = 0;// 定义树的高度
        // 定义每层的节点个数，其实就是一次遍历之后queue.size
        // 每次从队列中取出元素之后--，如果它=0，说明一层遍历完毕，即将进入下一层
        int levelSize = 1;
        queue.offer(root); // 添加根节点
        while (!queue.isEmpty()) {
            // 取出队头元素
            Node<E> node = queue.poll();
            // 取出一个元素 levelSize 会--
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                // 表示即将进入下一层
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }


    /**
     * 判断二叉树是否是完全二叉树,使用层序遍历
     * node.right == null,那么往下遍历的节点都必须是叶子节点
     *
     * @return
     */
    public boolean isComplete() {
        if (root == null) return false;
        boolean leaf = false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 应该是叶子节点但又不是
            if (leaf && !node.isLeaf()) return false;
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                // node.left == null && node.right != null
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // node.right == null ，表示接下来遍历的节点都必须是叶子节点
                leaf = true;
            }
        }
        return true;
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    /**
     * 找到一个节点的前驱节点
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.left;
        // 首先找左子节点，然后一直right.right 直到right为空
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 向上找parent
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        // node.parent == null  或者 node == node.parent.right
        return node.parent;
    }

    /**
     * 找到一个节点的后继节点
     *
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.right;
        // 首先找左子节点，然后一直right.right 知道right为空
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 向上找parent
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        // node.parent == null  或者 node == node.parent.right
        return node.parent;
    }


    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        /**
         * 是否是叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChild() {
            return left != null && right != null;
        }

        // 是否是左子树
        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        // 是否是右子树
        public boolean isRightChild() {
            return parent != null && parent.right == this;
        }

        /**
         * 获取兄弟节点
         * @return
         */
        public Node<E> sibling(){
            if (isLeftChild()){
                return parent.right;
            }
            if (isRightChild()){
                return parent.left;
            }
            return null;
        }

        /**
         * 获取叔父节点
         * @return
         */
        public Node<E> uncle(){
            return parent.sibling();
        }

    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }

    public static abstract class Visitor<E> {
        boolean stop;

        /**
         * @param e
         * @return 如果返回true，就停止遍历
         */
        boolean visit(E e) {
            return false;
        }
    }

}
