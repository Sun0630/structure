package com.sx.data.geek;

import java.util.Stack;

public class ListTest {
    public static void main(String[] args) {
//        ListNode listNode = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);
//
//        listNode5.next = listNode4;
//        listNode4.next = listNode3;
//        listNode3.next = listNode2;
//        listNode2.next = listNode;
//        listNode.next = listNode5;
//        printListNode(listNode5);
        // 反转链表
//        ListNode reverseNode = reverseList(listNode5);
        // 两两交换链表相邻两个节点
//        ListNode swapNode = swapPairs(listNode5);
//        System.out.println();
//        printListNode(swapNode);
//        System.out.println(hasCycle(listNode5));

        // 判断有效括号
        String s = "()[]{}";
        final boolean valid = isValid(s);
        System.out.println(valid);
    }

    public static void printListNode(ListNode node) {
        while (node != null) {
            System.out.print(node.value);
            node = node.next;
        }
    }

    /**
     * 反转链表
     * 遍历链表，双指针移动法。保持pre一直在cur前面
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;// 前指针
        ListNode cur = head;// 当前指针
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev; // 断开当前节点与下一个节点的连接
            prev = cur;// 前指针后移
            cur = tmp;// 当前指针后移
        }
        return prev;
    }

    /**
     * 24. 两两交换链表中的节点
     * 解法一：使用Stack数据结构，每次拿出两个放入Stack，然后再从Stack中取出，
     * 再把这两个节点串联起来，重复这个逻辑遍历完整个链表，就可以做到两两反转的效果了。
     * <b>注意边界条件的判断<b/>
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        // 用Stack每次保存两个节点
        Stack<ListNode> stack = new Stack<>();
        ListNode p = new ListNode(-1);
        ListNode cur = head;
        //head指向新的p节点，函数结束时返回head.next即可
        head = p;
        // 迭代链表
        while (cur != null && cur.next != null) {
            // 将两个节点放入Stack
            stack.push(cur);
            stack.push(cur.next);
            //当前节点往前走两步
            cur = cur.next.next;
            //从stack中弹出两个节点，然后用p节点指向新弹出的两个节点
            p.next = stack.pop();
            p = p.next;
            p.next = stack.pop();
            p = p.next;
        }
        // 注意边界条件，当链表长度是奇数时，cur就不为空
        if (cur != null) {
            p.next = cur;
        } else {
            p.next = null;
        }

        return head.next;
    }

    /**
     * 解法二：迭代
     * 这里我们需要三个指针，a，b，tmp
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        // 增加一个特殊节点
        ListNode p = new ListNode(-1);
        p.next = head;
        //创建a，b两个指针，这里还需要一个tmp指针
        ListNode a = p;
        ListNode b = p;
        ListNode tmp = p;// tmp指针用来处理边界条件的

        while (b != null && b.next != null && b.next.next != null) {
            // a 前进一位，b 前进两位
            a = a.next;
            b = b.next.next;
            //这步很关键，tmp指针用来处理边界条件的
            //假设链表是1->2->3->4，a指向1，b指向2
            //改变a和b的指向，于是就变成2->1，但是1指向谁呢？
            //1是不能指向2的next，1应该指向4，而循环迭代的时候一次处理2个节点
            //1和2的关系弄清楚了，3和4的关系也能弄清楚，但需要一个指针来处理
            //2->1，4->3的关系，tmp指针就是干这个用的
            tmp.next = b;
            a.next = b.next;
            b.next = a;
        }
        return p.next;
    }


    /**
     * 141：判断链表是否有环
     * 快慢指针法
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    /**
     * 20. 有效的括号
     * <p>
     * 输入：s = "()[]{}"
     * 输出：true
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }
}
