package com.sx.data.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 单例设计模式
 */
public class LazySingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

//        final EnumSingle firstInstance = EnumSingle.INSTANCE;
//        firstInstance.setObjName("firstName");
//        System.out.println("F:" + firstInstance.getObjName());
//        final EnumSingle secondInstance = EnumSingle.INSTANCE;
//        secondInstance.setObjName("secondName");
//        System.out.println("F:" + firstInstance.getObjName());
//        System.out.println("S:" + secondInstance.getObjName());
//
//        final EnumSingle[] enumConstants = EnumSingle.class.getEnumConstants();
//        System.out.println("enum.size:" + enumConstants.length);
//        for (EnumSingle enumConstant : enumConstants) {
//            System.out.println(enumConstant.getObjName());
//        }

        // 反射攻击
        final Constructor<InnerSingle> declaredConstructor = InnerSingle.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        final InnerSingle innerSingle = declaredConstructor.newInstance();

        final InnerSingle instance = InnerSingle.getInstance();

        System.out.println(innerSingle == instance);// false

    }
}

class LazySingleton {

    private volatile static LazySingleton instance;

    private LazySingleton() {
        
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}


class HungrySingle {
    private static HungrySingle instance = new HungrySingle();
    short s1 = 1;

    private HungrySingle() {
        s1 += 1;
        s1++;
    }

    public static HungrySingle getInstance() {
        return instance;
    }
}


// 枚举实现单例:可以防止反射攻击
enum EnumSingle {
    INSTANCE;

    private String objName;

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }
}


// 静态内部类
class InnerSingle {

    private InnerSingle() {
        // 防止反射攻击
        if (InnerSingleHolder.INSTANCE != null) {
            throw new RuntimeException("单例不允许多实例存在");
        }
    }

    private static class InnerSingleHolder {
        private static final InnerSingle INSTANCE = new InnerSingle();
    }

    public static InnerSingle getInstance() {
        return InnerSingleHolder.INSTANCE;
    }
}