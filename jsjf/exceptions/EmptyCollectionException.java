// jsjf/exceptions/EmptyCollectionException.java
package jsjf.exceptions;

/**
 * Represents the situation in which a collection is empty.
 * 
 * @author Group 11 - Adom Logistics
 * @version 1.0
 */
public class EmptyCollectionException extends RuntimeException {
    /**
     * Sets up this exception with an appropriate message.
     * @param collection the name of the collection
     */
    public EmptyCollectionException(String collection) {
        super("The " + collection + " is empty.");
    }
}