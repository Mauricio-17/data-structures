package DoubleLinkedList;

public class Node {
    String data;
    Node next;
    Node prev;
    public Node(String data) {
        this.data = data;
        this.next = this.prev = null;
    }
}
