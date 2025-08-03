// jsjf/QueueADT.java
package jsjf;

/**
 * Defines the interface to a queue collection.
 * Based on Java Foundations style.
 * 
 * @author Group 11 - Adom Logistics
 * @version 1.0
 * @param <T> the type of elements stored in the queue
 */
public interface QueueADT<T> {
    /**
     * Adds the specified element to the rear of this queue.
     * @param element the element to add to the rear
     */
    public void enqueue(T element);

    /**
     * Removes and returns the element at the front of this queue.
     * @return the element removed from the front
     * @throws EmptyCollectionException if the queue is empty
     */
    public T dequeue();

    /**
     * Returns without removing the element at the front of this queue.
     * @return the element at the front
     * @throws EmptyCollectionException if the queue is empty
     */
    public T first();

    /**
     * Returns true if this queue contains no elements.
     * @return true if the queue is empty
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this queue.
     * @return the size of the queue
     */
    public int size();

    /**
     * Returns a string representation of this queue.
     * @return a string representation of the queue
     */
    public String toString();
}