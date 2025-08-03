// jsjf/LinkedStack.java
package jsjf;

import jsjf.exceptions.EmptyCollectionException;

/**
 * Represents a linked implementation of a stack.
 * Based on Java Foundations style from course slides.
 * 
 * @author Group 11 - Adom Logistics
 * @version 1.0
 * @param <T> the type of elements stored in the stack
 */
public class LinkedStack<T> implements StackADT<T> {
    private LinearNode<T> top;
    private int count;

    /**
     * Creates an empty stack.
     */
    public LinkedStack() {
        top = null;
        count = 0;
    }

    /**
     * Adds the specified element to the top of the stack.
     * @param element the element to be pushed onto the stack
     */
    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        newNode.setNext(top);
        top = newNode;
        count++;
    }

    /**
     * Removes and returns the top element from the stack.
     * @return the element removed from the top
     * @throws EmptyCollectionException if the stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyCollectionException("stack");

        T result = top.getElement();
        top = top.getNext();
        count--;
        return result;
    }

    /**
     * Returns without removing the top element of the stack.
     * @return the element on top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyCollectionException("stack");

        return top.getElement();
    }

    /**
     * Returns true if the stack is empty.
     * @return true if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    /**
     * Returns the number of elements in the stack.
     * @return the size of the stack
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of the stack from top to bottom.
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TOP --> ");
        LinearNode<T> current = top;
        while (current != null) {
            sb.append(current.getElement().toString()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }
}