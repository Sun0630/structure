package 队列;

import java.util.Stack;

public class _232_用栈实现队列 {
    Stack<Integer> inputStack;// 输入栈
    Stack<Integer> outputStack;// 输出栈

    /** Initialize your data structure here. */
    public _232_用栈实现队列() {
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
        checkOutStack();
        return outputStack.pop();
    }

    private void checkOutStack() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
    }

    /** 获取队头元素. */
    public int peek() {
        checkOutStack();
        return outputStack.peek();
    }

    /** 判断是否为空. */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
}
