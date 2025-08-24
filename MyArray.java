package datastructures;

import model.Vehicle;

public class MyArray {
    private Vehicle[] array;
    private int size;
    private int capacity;

    public MyArray(int capacity) {
        this.capacity = capacity;
        this.array = new Vehicle[capacity];
        this.size = 0;
    }

    public void add(Vehicle vehicle) {
        if (size < capacity) {
            array[size++] = vehicle;
        }
    }

    public void quickSortByFuelUsage() {
        quickSort(0, size - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = array[high].getFuelUsage();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].getFuelUsage() <= pivot) {
                i++;
                Vehicle temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Vehicle temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public double getAverageFuelUsage() {
        if (size == 0) return 0.0;
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            sum += array[i].getFuelUsage();
        }
        return sum / size;
    }

    public String getFuelOutliers() {
        if (size == 0) return "";
        double mean = getAverageFuelUsage();
        double sumSquaredDiff = 0.0;
        for (int i = 0; i < size; i++) {
            double diff = array[i].getFuelUsage() - mean;
            sumSquaredDiff += diff * diff;
        }
        double stdDev = Math.sqrt(sumSquaredDiff / size);
        StringBuilder outliers = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (Math.abs(array[i].getFuelUsage() - mean) > 2 * stdDev) {
                outliers.append(array[i].getRegNum()).append(": ").append(array[i].getFuelUsage()).append("\n");
            }
        }
        return outliers.toString();
    }
}