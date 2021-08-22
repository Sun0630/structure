package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_反转链表 {

    /**
     * 使用递归方式: 搞清楚方法的作用
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    /**
     * 使用迭代，头插法反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}
