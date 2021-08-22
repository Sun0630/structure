package com.sx.data.geek;

import java.util.PriorityQueue;

/**
 * 703. 数据流中的第 K 大元素
 * <p>
 * 维护一个最小堆，堆的元素个数为常量 k，新加入一个元素和堆顶比较，如果比堆顶元素小，丢弃，否则删除堆顶元素，插入新元素。
 */
class KthLargest {

    final PriorityQueue<Integer> q;
    final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        q = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        q.offer(val);
        if (q.size() > k) {
            // 堆头元素弹出
            q.poll();
        }
        return q.peek();
    }
}