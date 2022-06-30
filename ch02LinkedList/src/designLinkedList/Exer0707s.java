package designLinkedList;

/**
 * @Author: Sihang Xie
 * @Description: 707. 设计链表 - 力扣（LeetCode）优化设计
 * @Date: 2022/6/4 17:56
 * @Version: 0.0.1
 * @Modified By:
 */
public class Exer0707s {
    public static void main(String[] args) {
        MyLinkedList2 linkedList = new MyLinkedList2();

    }
}

class MyLinkedList2 {
    //属性
    Node head, tail;//头节点和尾节点
    int size;//链表长度

    //双向节点内部类
    class Node {
        int val;
        Node prev, next;

        public Node() {
        }

        public Node(Node prev, int val, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    //空参构造器
    public MyLinkedList2() {
        size = 0;//初始化链表长度
        //初始化头尾节点并让其相连
        head = new Node();//实例化头节点
        tail = new Node();//实例化尾节点
        //头、尾节点相连
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        return -1;
    }

    public void addAtHead(int val) {

    }

    public void addAtTail(int val) {

    }

    public void addAtIndex(int index, int val) {

    }

    public void deleteAtIndex(int index) {

    }

    //遍历链表并打印
    public void show() {
        
    }
}
