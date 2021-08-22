package com.sx.data.pattern.bridge;

// 抽象电脑类型接口
public abstract class Computer {
    protected Brand brand;

    // 通过组合而非继承的方式将品牌集中到电脑抽象类中
    public Computer(Brand brand) {
        this.brand = brand;
    }

    // 电脑自带品牌
    public void info() {
        brand.info();
    }
}

// 台式机
class Desktop extends Computer {

    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.print("台式机");
    }
}

// 笔记本
class Laptop extends Computer {

    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.print("笔记本");
    }
}
