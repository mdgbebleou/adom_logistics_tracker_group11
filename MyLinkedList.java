package datastructures;

import model.Driver;

public class MyLinkedList {
    public class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;

    public MyLinkedList() {
        head = null;
    }

    public void addLast(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void addFirst(Object data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
        }
    }

    public Object getFirst() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Object find(String id) {
        Node current = head;
        while (current != null) {
            if (current.data instanceof Driver) {
                Driver driver = (Driver) current.data;
                if (driver.getId().equals(id)) {
                    return driver;
                }
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data.toString());
            current = current.next;
        }
        return sb.toString();
    }
}