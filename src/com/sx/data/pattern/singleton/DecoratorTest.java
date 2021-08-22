package com.sx.data.pattern.singleton;

public class DecoratorTest {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.operation();
    }
}

interface Component {
    void operation();
}

class ConcreteComponent implements Component {

    @Override
    public void operation() {
        System.out.println("拍照.");
    }
}

// 装饰者
abstract class Decorator implements Component{
    Component component;

    public Decorator(Component component) {
        this.component = component;
    }
}

class ConcreteDecorator1 extends Decorator{

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("美颜.");
        component.operation();
    }
}

class ConcreteDecorator2 extends Decorator{

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("大眼.");
        component.operation();
    }
}
