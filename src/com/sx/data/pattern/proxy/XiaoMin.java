package com.sx.data.pattern.proxy;

/**
 * 具体诉讼人 被代理类(Real Subject)，小民
 */
public class XiaoMin implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("申请仲裁");
    }

    @Override
    public void burden() {
        System.out.println("合同与工资流水");
    }

    @Override
    public void defend() {
        System.out.println("开始辩护");
    }

    @Override
    public void finish() {
        System.out.println("诉讼结束");
    }
}
