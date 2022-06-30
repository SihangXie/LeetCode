package designLinkedList;

/**
 * @Author: Sihang Xie
 * @Description: 206. 反转链表 - 力扣（LeetCode）
 * @Date: 2022/6/18 21:38
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0206 {
    public static void main(String[] args) { //测试
        ListNode firstNode = new ListNode(1);
        firstNode.next = new ListNode(2);
        firstNode.next.next = new ListNode(3);
        show(firstNode);


        System.out.println();
        System.out.println("===============反转后===============");
        ListNode result = reverseList(firstNode);
        show(result);
    }

    //我的思路：不破坏原有链表，使用头插法
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
            return head;
        }
        ListNode originHead = new ListNode(); //对传入的节点设置头节点
        originHead.next = head; //设置的头节点与传入的链表第一个进行绑定
        ListNode newHead = new ListNode(); //设置新链表用于保存反转后的结果
        ListNode originTemp = originHead; //原链表的辅助节点(指针)
        while (true) { //开始遍历原链表
            if (originTemp.next == null) { //最后一个节点了，退出遍历
                break;
            }
            ListNode node = new ListNode(originTemp.next.val, newHead.next); //依次取出原链表的节点
            newHead.next = node;//取出的节点以头插法插入到新头节点和newHead.next之间
            originTemp = originTemp.next; //辅助节点后移一位
        }
        return newHead.next;
    }

    //carl哥思路一：双指针法
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
            return head;
        }
        ListNode pre = null; //pre初始化为空，类似于head头节点
        ListNode cur = head; //cur初始化为链表第一个节点
        ListNode temp = null; //temp保存反转前cur.next的节点，防止反转后丢失
        while (cur != null) {
            temp = cur.next;
            cur.next = pre; //反转
            pre = cur; //pre后移一位
            cur = temp; //cur后移一位
        }
        return pre;
    }

    //carl哥思路二：双指针的递归法
    public static ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    private static ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) { //如果传入的链表为空或仅有一个节点，则直接返回原链表
            return pre;
        }
        ListNode temp = null;
        temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }

    //打印整个链表
    public static void show(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode originHead = new ListNode();
        originHead.next = head;
        ListNode temp = originHead;
        while (temp.next != null) {
            System.out.print(temp.next.toString() + "-->");
            temp = temp.next;
        }
    }
}

//类：节点类
class ListNode {
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

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}