package datastructures;

import model.Driver;

public class MyQueue {
    public MyLinkedList list;

    public MyQueue() {
        list = new MyLinkedList();
    }

    public void enqueue(Object item) {
        list.addLast(item);
    }

    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        Object item = list.getFirst();
        list.removeFirst();
        return item;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Driver find(String id) {
        return (Driver) list.find(id);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}