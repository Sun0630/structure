package com.sx.data.fibonacci;


/**
 * 两种算法计算斐波那契数列
 *n 0 1 2 3 4 5 6 7
 *  0 1 1 2 3 5 8 13 ...
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(fib1(10));
        System.out.println(fib2(46));

        final Class<Main> mainClass = Main.class;
    }

    /**
     * 递归法
     *
     * @param n
     */
    public static int fib1(int n) {
        if (n <= 1) return n;
        return fib1(n - 2) + fib1((n - 1));
    }

    /**
     * 使用for循环
     *
     * @param n
     * @return
     */
    public static int fib2(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

}
