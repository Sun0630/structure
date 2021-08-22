package com.sx.data.geek;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 */
class MyQueue {

    Stack<Integer> inputStack;// 输入栈
    Stack<Integer> outputStack;// 输出栈

    /** Initialize your data structure here. */
    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        inputStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 如果输出栈为空，则将输入栈的内容全部压入输出栈中，然后输出栈 outputStack.pop()
        if (outputStack.isEmpty()){
            while (!inputStack.isEmpty()){
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (outputStack.isEmpty()){
            while (!inputStack.isEmpty()){
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */