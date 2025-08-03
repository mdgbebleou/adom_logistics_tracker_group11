package jsjf;

import jsjf.exceptions.EmptyCollectionException;

/**
 * A binary search tree implementation for key-value pairs.
 * Used to store vehicles by registration number (String key).
 * 
 * @author Group 11 - Adom Logistics
 * @version 1.0
 * @param <K> the type of keys (e.g., String for reg number)
 * @param <V> the type of values (e.g., Vehicle)
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    private BinaryTreeNode<K, V> root;
    private int count;

    /**
     * Creates an empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
        count = 0;
    }

    /**
     * Adds a key-value pair to the tree.
     * @param key the key (e.g., registration number)
     * @param value the value (e.g., Vehicle object)
     */
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    /**
     * Recursively inserts a key-value pair into the tree.
     */
    private BinaryTreeNode<K, V> insert(BinaryTreeNode<K, V> node, K key, V value) {
        if (node == null) {
            count++;
            return new BinaryTreeNode<>(key, value);
        }

        int compare = key.compareTo(node.getKey());

        if (compare < 0) {
            node.setLeft(insert(node.getLeft(), key, value));
        } else if (compare > 0) {
            node.setRight(insert(node.getRight(), key, value));
        } else {
            // Duplicate key: overwrite value
            node.setValue(value);
        }

        return node;
    }

    /**
     * Retrieves the value associated with the given key.
     * @param key the key to search for
     * @return the value, or null if not found
     */
    public V get(K key) {
        BinaryTreeNode<K, V> node = findNode(root, key);
        return (node == null) ? null : node.getValue();
    }

    /**
     * Finds the node with the given key.
     */
    private BinaryTreeNode<K, V> findNode(BinaryTreeNode<K, V> node, K key) {
        if (node == null)
            return null;

        int compare = key.compareTo(node.getKey());

        if (compare == 0)
            return node;
        else if (compare < 0)
            return findNode(node.getLeft(), key);
        else
            return findNode(node.getRight(), key);
    }

    /**
     * Returns true if the tree contains the given key.
     * @param key the key to search for
     * @return true if the key exists
     */
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * Returns the number of elements in the tree.
     * @return the size of the tree
     */
    public int size() {
        return count;
    }

    /**
     * Returns true if the tree is empty.
     * @return true if empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns a string representation of the tree (in-order).
     * @return string of key-value pairs
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BST{");
        inorder(root, sb);
        sb.append("}");
        return sb.toString();
    }

    private void inorder(BinaryTreeNode<K, V> node, StringBuilder sb) {
        if (node != null) {
            inorder(node.getLeft(), sb);
            sb.append(node.getKey()).append("=").append(node.getValue().toString()).append(", ");
            inorder(node.getRight(), sb);
        }
    }
}