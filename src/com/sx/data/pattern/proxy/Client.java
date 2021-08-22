package com.sx.data.pattern.proxy;


import com.sx.data.pattern.proxy.dynamic.DynamicProxy;

import java.lang.reflect.Proxy;
import java.security.acl.LastOwnerException;

public class Client {
    public static void main(String[] args) {

    }


    public void staticProxy() {
        XiaoMin xiaoMin = new XiaoMin();
        Lawyer lawyer = new Lawyer(xiaoMin);
        lawyer.submit();
        lawyer.burden();
        lawyer.defend();
        lawyer.finish();
    }

    public void dynamicProxy() {
        XiaoMin xiaoMin = new XiaoMin();
        ILawsuit lawsuit = (ILawsuit) Proxy.newProxyInstance(
                xiaoMin.getClass().getClassLoader(),
                new Class[]{ILawsuit.class},
                new DynamicProxy(xiaoMin));

        lawsuit.submit();
        lawsuit.burden();
        lawsuit.defend();
        lawsuit.finish();

    }
}
