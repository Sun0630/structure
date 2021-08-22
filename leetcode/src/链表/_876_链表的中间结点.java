package 链表;


/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class _876_链表的中间结点 {
    // 此题可用快慢指针解题，快指针每次走2步，慢指针每次走一步，当快指针走到头的时候慢指针正好走到中间
    public static ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        final ListNode listNode1 = middleNode(listNode);
        System.out.println(listNode1);
    }
}
