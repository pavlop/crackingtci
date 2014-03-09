package interview.datastructures.linkedLists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlop on 2/27/14.
 */
public class MySinglyLinkedList<T> {
    public Node<T> head;

    public void add(T value) {
        if(head == null) {
            head = new Node<T>(value, null);
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node<T>(value, null);
        }
    }

    public static <T> List<T> toJavaList(Node<T> head) {
        List<T> list = new ArrayList<T>();
        while(head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    public static <T> Node<T> getNodeAtPosition(Node<T> head, int pos) {
        System.out.println("getNodeAtPosition head" + head.value + " pos"+pos);
        Node<T> cur = head;
        for(int i  = 0; i < pos; i++) {
            cur = cur.next;
        }
        System.out.println("getNodeAtPosition returning " + cur.value);
        return cur;
    }

    public static <T> List<T> subList(Node<T> head, int len) {
        List <T> res = new ArrayList<T>();
        Node<T> cur = head;
        for(int i  = 0; i < len; i++) {
            res.add(cur.value);
            cur = cur.next;
        }
        return res;
    }

}

class Node<T> {
    public T value;
    public Node<T> next;

    Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public String toString(){
        return value+"";
    }
}
