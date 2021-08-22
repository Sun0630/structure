package com.sx.data.pattern.bridge;

// 苹果品牌
public class Apple implements Brand {
    @Override
    public void info() {
        System.out.print("苹果");
    }
}
