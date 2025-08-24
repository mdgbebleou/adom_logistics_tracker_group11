package datastructures;

import model.Vehicle;

public class MyMinHeap {
    private Vehicle[] heap;
    private int size;
    private int capacity;

    public MyMinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new Vehicle[capacity];
        this.size = 0;
    }

    public void insert(Vehicle vehicle) {
        if (size >= capacity) return;
        heap[size] = vehicle;
        int current = size++;
        while (current > 0 && heap[current].getMileage() < heap[parent(current)].getMileage()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Vehicle extractMin() {
        if (size == 0) return null;
        Vehicle min = heap[0];
        heap[0] = heap[--size];
        heapify(0);
        return min;
    }

    private void heapify(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        int smallest = index;
        if (left < size && heap[left].getMileage() < heap[smallest].getMileage()) {
            smallest = left;
        }
        if (right < size && heap[right].getMileage() < heap[smallest].getMileage()) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void swap(int i, int j) {
        Vehicle temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}