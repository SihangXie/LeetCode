package designLinkedList;

/**
 * @Author: Sihang Xie
 * @Description: 203. 移除链表元素 - 力扣（LeetCode）
 * @Date: 2022/6/4 13:16
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0203 {
    public static void main(String[] args) {

    }

    //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //使用虚拟头节点
    public ListNode removeElement(ListNode head, int val) {
        //如果链表为空，则直接返回空链表
        if (head == null) {
            return head;
        }
        //创建虚拟头节点，其next指向传入的链表
        ListNode virHead = new ListNode(-1, head);
        //创建辅助节点temp，用于遍历链表
        ListNode temp = virHead;
        //开始循环遍历，定位到要删除节点的前一个节点
        while (temp.next != null) {//遍历到最后一个节点，退出循环
            if (temp.next.val == val) {
                //把当前next指向要移除节点的后一个节点
                temp.next = temp.next.next;
            } else {//注意这里一定要用else，因为是要移除所有val相同的节点
                //辅助节点后移一位
                temp = temp.next;
            }
        }
        //virHead.next才是新的头节点
        return virHead.next;
    }
}
