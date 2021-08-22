package 链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_移除链表元素 {

    /**
     * 搞一个前节点
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        // 前节点
        ListNode node = new ListNode(-1);
        // 前节点指向头结点
        node.next = head;
        // 临时节点，用于移动
        ListNode temp = node;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return node.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(4);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;
        final ListNode listNode1 = removeElements(listNode, 3);
        System.out.println(listNode1);
    }
}
