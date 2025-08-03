package jsjf;

/**
 * A node in a binary search tree that holds a key-value pair.
 * 
 * @author Group 11 - Adom Logistics
 * @version 1.0
 * @param <K> the key type
 * @param <V> the value type
 */
public class BinaryTreeNode<K, V> {
    private K key;
    private V value;
    private BinaryTreeNode<K, V> left;
    private BinaryTreeNode<K, V> right;

    public BinaryTreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // --- Getters ---
    public K getKey() { return key; }
    public V getValue() { return value; }
    public BinaryTreeNode<K, V> getLeft() { return left; }
    public BinaryTreeNode<K, V> getRight() { return right; }

    // --- Setters ---
    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    public void setLeft(BinaryTreeNode<K, V> left) { this.left = left; }
    public void setRight(BinaryTreeNode<K, V> right) { this.right = right; }
}