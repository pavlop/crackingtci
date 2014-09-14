package crackinginterview.datastructures.x2.linkedLists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlop on 2/27/14.
 */
public class MySinglyLinkedList<T> {
    public SingleNode<T> head;
    public int length;

    public void add(T value) {
        SingleNode<T> toAdd = new SingleNode<T>(value, null);
        add(toAdd);
    }

    public void add (SingleNode<T> node) {
        if(head == null) {
            head = node;
        } else {
            SingleNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
        length++;
    }

    public void addToHead(T value) {
        if(head == null) {
            head = new SingleNode<T>(value, null);
        } else {
            SingleNode newHead = new SingleNode<T>(value, head);
            head = newHead;
        }
    }

    public static <T> List<T> toJavaList(SingleNode<T> head) {
        List<T> list = new ArrayList<T>();
        while(head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    public static <T> SingleNode<T> getNodeAtPosition(SingleNode<T> head, int pos) {
        System.out.println("getNodeAtPosition head" + head.value + " pos"+pos);
        SingleNode<T> cur = head;
        for(int i  = 0; i < pos; i++) {
            cur = cur.next;
        }
        System.out.println("getNodeAtPosition returning " + cur.value);
        return cur;
    }

    public static <T> List<T> subList(SingleNode<T> head, int len) {
        List <T> res = new ArrayList<T>();
        SingleNode<T> cur = head;
        for(int i  = 0; cur != null &&  i < len; i++) {
            res.add(cur.value);
            cur = cur.next;
        }
        return res;
    }

    public void reverseList() {
        reverseList( this.head);
    }

    public static SingleNode reverseList(SingleNode head) {
        SingleNode cur = head;
        SingleNode previous = null;
        if (cur == null) return null;

        while (cur != null && cur.next != null) {
            SingleNode next = cur.next;
            SingleNode secondNext = next.next;

            cur.next = previous;
            next.next = cur;

            previous = next;
            cur = secondNext;
        }

        SingleNode newhead;
        if (cur != null) {
            cur.next = previous;
             newhead = cur;
        } else {
             newhead = previous;
        }
        return newhead;
    }


    public static <T> int getLength (SingleNode<T> head) {
        SingleNode cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        return length;
    }

}

