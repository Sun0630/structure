package com.sx.data.array;

public interface List<E> {

    /**
     * 元素未找到标记
     */
    int ELEMENT_NOT_FOUND = -1;

    /**
     * 元素的数量
     *
     * @return
     */
    int size();

    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 添加元素到最后面
     *
     * @param element
     */
    void add(E element);

    /**
     * 返回 index位置对应的元素
     *
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 设置 index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素
     */
    E set(int index, E element);

    /**
     * 往 index位置添加元素
     * 元素从后往后移动的范围是： size-1  ~ index
     *
     * @param index
     * @param element
     */
    void add(int index, E element);


    /**
     * 删除 index位置对应的元素
     * 挪动范围  index +1 ~~~ size -1
     *
     * @param index 移除的下标
     * @return 被移除的元素
     */
    E remove(int index);


    /**
     * 查看元素的位置
     *
     * @param element
     * @return
     */
    int indexOf(E element);

    /**
     * 清除所有元素
     */
    void clear();
}
