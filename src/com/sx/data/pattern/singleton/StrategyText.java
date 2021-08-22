package com.sx.data.pattern.singleton;

public class StrategyText {
    public static void main(String[] args) {
        Zombie zombie = new FlagZombie();
        zombie.display();
        zombie.attack();
        zombie.move();
        zombie.setMovement(new FastMovement());
        zombie.move();
    }
}

// 僵尸
abstract class Zombie{

    Movement movement;
    Attackment attackment;

    public Zombie(Movement movement, Attackment attackment) {
        this.movement = movement;
        this.attackment = attackment;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public void setAttackment(Attackment attackment) {
        this.attackment = attackment;
    }

    // 形象
    abstract void display();
    abstract void attack();
    abstract void move();

}

// 行为：移动方式
interface Movement{
    void move();
}

//行为：攻击方式
interface Attackment{
    void attack();
}


// 具体走路策略
class StepByStep implements Movement{

    @Override
    public void move() {
        System.out.println("一步一步似爪牙");
    }
}

class FastMovement implements Movement{
    @Override
    public void move() {
        System.out.println("跑起来");
    }
}

// 具体攻击策略
class BiteAttack implements  Attackment{
    @Override
    public void attack() {
        System.out.println("咬死你");
    }
}


class FlagZombie extends Zombie{

    public FlagZombie(){
        super(new StepByStep(),new BiteAttack());
    }

    public FlagZombie(Movement movement, Attackment attackment) {
        super(movement, attackment);
    }

    @Override
    void display() {
        System.out.println("旗子僵尸...");
    }

    @Override
    void attack() {
        attackment.attack();
    }

    @Override
    void move() {
        movement.move();
    }
}