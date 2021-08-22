package 链表;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * 思路： 用被删掉的当前节点的下一个节点的element覆盖当前节点的element，然后将当前节点指向下一个节点的下一个节点
 */
public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
