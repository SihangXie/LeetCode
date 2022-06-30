package designLinkedList;

import static designLinkedList.Exer0206.show;

/**
 * @Author: Sihang Xie
 * @Description: 19. 删除链表的倒数第 N 个结点 - 力扣（LeetCode）
 * @Date: 2022/6/25 21:20
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0019 {
    public static void main(String[] args) { //测试
        ListNode firstNode = new ListNode(1);
        firstNode.next = new ListNode(2);
        firstNode.next.next = new ListNode(3);
        firstNode.next.next.next = new ListNode(4);
        show(firstNode);

        System.out.println();
        System.out.println("===============删除后===============");
        ListNode swapResult = removeNthFromEnd(firstNode, 4);
        show(swapResult);
    }


    //我的思路：先反转，再删除
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null || head.next == null) { //如果链表为空或只有一个节点，直接返回空链表
            return null;
        }
        ListNode newHead = reverseList(head);
        //反转完毕，下面开始搜索n并删除
        ListNode virHead = new ListNode(0, newHead); //创建虚拟头节点
        ListNode temp = virHead; //创建辅助节点
        int counter = n; //计数器
        temp = virHead; //辅助节点指向反转后的虚拟头节点
        while (counter > 1) { //定位到要删除节点的前一个节点
            temp = temp.next; //辅助节点后移一位
            counter--;
        }
        //定位完毕，开始删除
        temp.next = temp.next.next; //删除节点
        return reverseList(virHead.next); //再反转回开始顺序的链表，返回
    }

    //把反转链表的功能抽象出来
    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode temp = null;
        while (head != null) { //双指针法反转链表
            temp = head.next;
            head.next = pre;
            pre = head; // pre后移一位
            head = temp; //head后移一位
        }
        return pre;
    }

    //Carl哥思路：快慢指针，快针先跑n，再快慢同时移动
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) { //如果链表为空或只有一个节点，直接返回空链表
            return null;
        }
        ListNode virHead = new ListNode(0, head); //创建虚拟头节点
        ListNode fast = virHead; //快指针
        ListNode slow = virHead; //慢指针
        while (fast != null) {
            if (n > -1) { //这里是-1是为了让slow定位到要删除节点的上一个节点
                fast = fast.next; //快指针先移动n步
                n--;
            } else { //快慢指针同时后移
                fast = fast.next;
                slow = slow.next;
            }
        }
        slow.next = slow.next.next; //删除倒数第n个节点
        return virHead.next;
    }
}
