package com.sx.data.pattern.bridge;

public class Test {
    public static void main(String[] args) {
        // 组装一个苹果台式机
        Computer computer = new Desktop(new Apple());
        computer.info();

        // 组装一个联想笔记本
        Computer computer1 = new Laptop(new Lenovo());
        computer1.info();
    }
}
