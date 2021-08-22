package com.sx.data.thread;

/**
 * 三个线程依次执行
 */
public class OrderThread1 {
    // 关键在于Volatile关键字的使用，能够使ticket在被改变之后其他线程能够立刻感知
    static volatile int ticket = 1;

    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            while (true) {
                if (ticket == 1) {
                    try {
                        Thread.sleep(100);
                        for (int i = 0; i < 5; i++) {
                            System.out.println("a:" + i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket = 2;
                    return;
                }
            }
        });

        final Thread thread2 = new Thread(() -> {
            while (true) {
                if (ticket == 2) {
                    try {
                        Thread.sleep(100);
                        for (int i = 0; i < 5; i++) {
                            System.out.println("b:" + i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket = 3;
                    return;
                }
            }
        });

        final Thread thread3 = new Thread(() -> {
            while (true) {
                if (ticket == 3) {
                    try {
                        Thread.sleep(100);
                        for (int i = 0; i < 5; i++) {
                            System.out.println("c:" + i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
