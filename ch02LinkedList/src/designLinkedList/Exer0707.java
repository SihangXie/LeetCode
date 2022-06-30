package designLinkedList;

/**
 * @Author: Sihang Xie
 * @Description: 707. 设计链表 - 力扣（LeetCode）
 * @Date: 2022/6/4 14:45
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0707 {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.addAtHead(7);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(1);
//        linkedList.show();
//        linkedList.addAtIndex(3, 0);
//        linkedList.show();


//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1, 2);
//        linkedList.show();

        linkedList.addAtIndex(1, 0);
        linkedList.show();
//        System.out.println(linkedList.get(0));
    }
}

//我的思路，本题我使用双向链表
class MyLinkedList {
    //属性
    Node head = new Node();

    //节点内部类
    class Node {
        int val;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(Node prev, int val, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    //空参构造器
    public MyLinkedList() {
    }

    public void show() {
        if (head.next == null) {
            return;
        }
        Node temp = head.next;
        while (true) {
            if (temp.next == null) {
                System.out.println(temp);
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //获取链表中第 index 个节点的值。如果索引无效，则返回-1。
    public int get(int index) {
        if (index < 0) {
            return -1;
        }
        Node temp = head;
        while (index >= 0) {
            if (temp.next == null) {
                return -1;
            }
            temp = temp.next;
            index--;
        }
        return temp.val;
    }

    //在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
    public void addAtHead(int val) {
        if (head.next == null) {
            head.next = new Node(head, val, null);
            return;
        }
        Node newNode = new Node(head, val, head.next);
        head.next.prev = newNode;
        head.next = newNode;
    }

    //将值为 val 的节点追加到链表的最后一个元素。
    public void addAtTail(int val) {
        if (head.next == null) {
            head.next = new Node(head, val, null);
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(temp, val, null);
    }

    //在指定索引index前一个位置插入元素val
    //注意这个index是可以超出链表长度一个的，即左闭右开的
    public void addAtIndex(int index, int val) {
        if (head.next == null && index > 0) {
            return;
        }
        if (head.next == null && index == 0) {
            head.next = new Node(head, val, null);
        }
        if (index < 0) {
            addAtHead(val);
        }
        Node temp = head;
        //此题定位到index所指的节点本身
        while (index >= 0) {
            if (temp.next == null) {
                temp.next = new Node(temp, val, null);
                return;
            }
            temp = temp.next;
            index--;
        }
        Node newNode = new Node(temp.prev, val, temp);
        temp.prev.next = newNode;
        temp.prev = newNode;
    }

    //如果索引 index 有效，则删除链表中的第 index 个节点。
    public void deleteAtIndex(int index) {
        if (index < 0) {
            return;
        }
        if (head.next == null) {
            return;
        }
        Node temp = head;
        while (index >= 0) {
            if (temp.next == null) {
                return;
            }
            temp = temp.next;
            index--;
        }
        if (temp.next == null) {
            temp.prev.next = null;
            return;
        }
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
    }
}