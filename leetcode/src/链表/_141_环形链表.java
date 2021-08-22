package 链表;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class _141_环形链表 {

    /**
     * 判断一个链表是否有环，使用快慢指针
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        // 循环条件是fast不为null 或者 fast.next不为null
        while (fast != null || fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
