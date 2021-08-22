package com.sx.data.stack;

import com.sx.data.array.ArrayList;
import com.sx.data.array.List;


/**
 * 1. 使用继承ArrayList实现栈，这种方式不能屏蔽父类中的方法，不合适
 * 2. 使用组合ArrayList的方式实现栈，合理
 *
 * @param <E>
 */
public class Stack<E> /*extends ArrayList<E>*/ {

    private List<E> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }


    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size() - 1);
    }

    public void clear() {
        list.clear();
    }

}
