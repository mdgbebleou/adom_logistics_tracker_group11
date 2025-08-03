// jsjf/ArrayQueue.java
package jsjf;

import jsjf.exceptions.EmptyCollectionException;
import jsjf.exceptions.FullCollectionException;

/**
 * Array implementation of a queue as a circular array.
 * Based on the Java Foundations slide examples.
 * 
 * @author Group 11 - Adom Logistics
 * @version 1.0
 * @param <T> the type of elements stored in the queue
 */
public class ArrayQueue<T> implements QueueADT<T> {
    private final int DEFAULT_CAPACITY = 50;
    private int front, rear, count;
    private T[] queue;

    /**
     * Creates an empty queue using the default capacity.
     */
    public ArrayQueue() {
        front = rear = count = 0;
        queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty queue using the specified capacity.
     * @param initialCapacity the initial size of the queue
     */
    public ArrayQueue(int initialCapacity) {
        front = rear = count = 0;
        queue = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Adds the specified element to the rear of this queue.
     * @param element the element to add to the rear
     */
    @Override
    public void enqueue(T element) {
        if (size() == queue.length)
            expandCapacity();

        queue[rear] = element;
        rear = (rear + 1) % queue.length;
        count++;
    }

    /**
     * Creates a new array to store the contents of this queue with
     * twice the capacity of the old one.
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);

        for (int scan = 0; scan < count; scan++) {
            larger[scan] = queue[front];
            front = (front + 1) % queue.length;
        }

        front = 0;
        rear = count;
        queue = larger;
    }

    /**
     * Removes and returns the element at the front of this queue.
     * @return the element removed from the front
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty())
            throw new EmptyCollectionException("array queue");

        T result = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;

        return result;
    }

    /**
     * Returns without removing the element at the front of this queue.
     * @return the first element in the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() {
        if (isEmpty())
            throw new EmptyCollectionException("array queue");

        return queue[front];
    }

    /**
     * Returns true if this queue contains no elements.
     * @return true if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns the number of elements in this queue.
     * @return the size of the queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of this queue.
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FRONT --> ");
        int current = front;
        for (int i = 0; i < count; i++) {
            sb.append(queue[current]).append(" ");
            current = (current + 1) % queue.length;
        }
        return sb.toString();
    }
}