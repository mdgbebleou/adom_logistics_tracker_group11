// jsjf/exceptions/FullCollectionException.java
package jsjf.exceptions;

/**
 * Represents the situation in which a collection is full.
 * 
 * @author Group 11 - Adom Logistics
 * @version 1.0
 */
public class FullCollectionException extends RuntimeException {
    /**
     * Sets up this exception with an appropriate message.
     * @param collection the name of the collection
     */
    public FullCollectionException(String collection) {
        super("The " + collection + " is full.");
    }
}
