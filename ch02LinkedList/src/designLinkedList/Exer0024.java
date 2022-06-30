package designLinkedList;

import static designLinkedList.Exer0206.show;

/**
 * @Author: Sihang Xie
 * @Description: 24. 两两交换链表中的节点 - 力扣（LeetCode）
 * @Date: 2022/6/21 10:10
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0024 {
    public static void main(String[] args) { //测试
        ListNode firstNode = new ListNode(1);
        firstNode.next = new ListNode(2);
        firstNode.next.next = new ListNode(3);
        firstNode.next.next.next = new ListNode(4);
        show(firstNode);


        System.out.println();
        System.out.println("===============交换后===============");
        ListNode swapResult = swapPairs(firstNode);
        show(swapResult);
    }

    //我的思路：双指针法
    public static ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
            return head;
        }
        ListNode cur = head; //cur当前指针
        ListNode virHead = new ListNode(); //虚拟头节点
        virHead.next = head;
        ListNode pre = virHead; //pre前指针
        ListNode temp = null; //辅助节点temp
        while (cur != null) {
            if (cur.next == null) { //解决节点个数为奇数时，没法两两交换时直接退出
                break;
            }
            temp = cur.next; //保存交换前的cur.next节点信息，以免丢失
            pre.next = temp; //第一个箭头
            if (temp.next == null) {
                cur.next = null;
            } else {
                cur.next = temp.next; //第二个箭头
            }
            //双指针后移，temp.next没改，要在下一次循环中改
            pre = cur; //pre后移
            temp.next = cur; //第三个箭头
            cur = cur.next; //cur后移
        }
        return virHead.next;
    }

    //Carl哥的思路：
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
            return head;
        }
        ListNode virHead = new ListNode(0, head); //创建虚拟头节点
        ListNode pre = virHead; //前指针
        ListNode temp = null; //创建辅助节点
        while (pre.next != null && pre.next.next != null) { //这样的循环条件可以一举解决奇偶节点数量问题
            temp = head.next.next; //缓存head指针后移位置
            pre.next = head.next; //步骤①
            head.next.next = head; //步骤②
            head.next = temp; //步骤③
            //指针后移
            pre = head; //pre后移2位
            head = head.next; //head后移1位
        }
        return virHead.next;
    }
}
