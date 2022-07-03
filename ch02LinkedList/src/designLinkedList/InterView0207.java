package designLinkedList;

import static designLinkedList.Exer0206.show;

/**
 * @Author: Sihang Xie
 * @Description: 面试题 02.07. 链表相交 - 力扣（LeetCode）
 * @Date: 2022/7/2 20:00
 * @Version: 0.0.1
 * @Modified By:
 */
public class InterView0207 {
    public static void main(String[] args) {

        //验证2个链表相交部分地址值是否相等
        ListNode n1 = new ListNode(0);
        ListNode n2 = new ListNode(9);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        show(n1);
        System.out.println();

        ListNode b1 = new ListNode(3);
        b1.next = n4; //注意此处相交部分是指向链表A的节点的
        show(b1);
        System.out.println();

        System.out.println("=========比较结果=========");
        ListNode temp1 = n1;
        ListNode temp2 = b1;
        temp1 = temp1.next.next.next; //链表A后移3位
        temp2 = temp2.next; //链表B后移1位
        System.out.println(temp1 == temp2); //判断指针是否相同

        System.out.println("=========寻找相交节点结果=========");
        show(getIntersectionNode(b1, n1));
    }

    //我写的版本
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) { //如果两个节点为空，则直接返回null
            return null;
        }
        ListNode vHeadA = new ListNode(0, headA); //创建虚拟头节点
        ListNode vHeadB = new ListNode(0, headB);
        ListNode temp1 = vHeadA; //辅助节点作为指针
        ListNode temp2 = vHeadB;
        int lenA = 0;
        int lenB = 0;
        while (temp1.next != null) { //统计链表A的长度
            temp1 = temp1.next;
            lenA++;
        }
        while (temp2.next != null) { //统计链表B的长度
            temp2 = temp2.next;
            lenB++;
        }
        int diff = Math.abs(lenA - lenB); //两链表长度之差
        temp1 = vHeadA.next;
        temp2 = vHeadB.next;
        if (diff != 0) { //如果两个链表长度相等，不用进来
            while (diff > 0) { //移动到两个链表尾端对齐的状态
                if (lenA >= lenB) { //确保较长的链表移动指针对齐
                    temp1 = temp1.next;
                } else {
                    temp2 = temp2.next;
                }
                diff--;
            }
        }
        while (temp1 != null) { //这里不要用temp1.next != null，否则最后一个节点无法比较地址
            if (temp1 == temp2) { //找到相交节点
                return temp1;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return null; //来到这里就是没找到相交节点
    }
}
