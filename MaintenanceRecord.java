package model;

public class MaintenanceRecord {
    private String date;
    private String parts;
    private double cost;

    public MaintenanceRecord(String date, String parts, double cost) {
        this.date = date;
        this.parts = parts;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return date + "," + parts + "," + cost;
    }
}