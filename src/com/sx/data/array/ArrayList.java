package com.sx.data.array;

/**
 * 实现一个动态数组
 *
 * @param <E>
 */
public class ArrayList<E> extends AbstractList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 所有的元素
     */
    private E[] elements;


    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.elements = (E[]) new Object[capacity];
    }


    /**
     * 返回 index位置对应的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        rangeCheck(index);

        return elements[index];
    }

    /**
     * 设置 index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 往 index位置添加元素
     * 元素从后往后移动的范围是： size-1  ~ index
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangCheckForAdd(index);

        // 检查容量
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }


    /**
     * 删除 index位置对应的元素
     * 挪动范围  index +1 ~~~ size -1
     *
     * @param index 移除的下标
     * @return 被移除的元素
     */
    public E remove(int index) {
        rangeCheck(index);

        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
//        size--;
        // 将最后一个位置清空，不然如果删除一个元素之后该元素还会往前移动
        elements[--size] = null;

        trim();
        return old;
    }


    /**
     * 查看元素的位置
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                // 使用equals
                if (element.equals(elements[i])) return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 检查容量，确定是否需要扩容
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        final int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        // 右移1位表示除以2,新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        // 遍历复制到新数组中
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "扩容为：" + newCapacity);
    }

    private void trim() {
        // 30
        int oldCapacity = elements.length;
        // 15
        int newCapacity = oldCapacity >> 1;
        if (size > (newCapacity) || oldCapacity <= DEFAULT_CAPACITY) return;

        // 剩余空间还很多
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println(oldCapacity + "缩容为" + newCapacity);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = ")
                .append(size)
                .append(",[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(elements[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
