package com.sx.data.pattern.proxy;

/**
 * Proxy Subject 代理律师，持有被代理者的一个引用
 */
public class Lawyer implements ILawsuit {
    private ILawsuit lawsuit;

    public Lawyer(ILawsuit lawsuit) {
        this.lawsuit = lawsuit;
    }

    @Override
    public void submit() {
        lawsuit.submit();
    }

    @Override
    public void burden() {
        lawsuit.burden();
    }

    @Override
    public void defend() {
        lawsuit.defend();
    }

    @Override
    public void finish() {
        lawsuit.finish();
    }
}
