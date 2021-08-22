package com.sx.data;

public class Main {

    /**
     *             0 1 2 3 4 5
     * 斐波那契数列  0 1 1 2 3 5 8 13 ...
     */

    /**
     * 方法一： 递归
     * 问题：太慢,当n=64的时候，计算不出来了就
     * 时间复杂度为:O(2^n)
     */
    private static int fib1(int n) {
        if (n <= 1) return n;
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 方法二：使用循环
     * 时间复杂度为：O(n)
     */
    private static int fib2(int n){
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

    public static void main(String[] args) {
        System.out.println(fib2(64));
    }


}
