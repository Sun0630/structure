package com.sx.data.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 线程同时执行
 */
public class ThreadSafeDemo {
    public int count = 0;
    public void add(){
        count++;
    }
    public static void main(String[] args) throws InterruptedException {
        int size = 3;
        ThreadSafeDemo threadSafeDemo = new ThreadSafeDemo();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    // 所有线程都在这类排队
                    countDownLatch.await();
                    System.out.println(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(100);
        // 主线程执行倒数
        countDownLatch.countDown();
    }
}
