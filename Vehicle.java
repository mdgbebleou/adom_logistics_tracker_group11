package model;

import datastructures.MyHashMap;

public class Vehicle {
    private String regNum;
    private String type;
    private int mileage;
    private double fuelUsage;
    private String driverID;
    private MyHashMap maintenanceRecords;

    public Vehicle(String regNum, String type, int mileage, double fuelUsage, String driverID) {
        this.regNum = regNum;
        this.type = type;
        this.mileage = mileage;
        this.fuelUsage = fuelUsage;
        this.driverID = driverID;
        this.maintenanceRecords = new MyHashMap();
    }

    public String getRegNum() { return regNum; }
    public String getType() { return type; }
    public int getMileage() { return mileage; }
    public double getFuelUsage() { return fuelUsage; }
    public String getDriverID() { return driverID; }

    public void setDriverID(String driverID) { this.driverID = driverID; }

    public void addMaintenance(String date, String parts, double cost) {
        MaintenanceRecord record = new MaintenanceRecord(date, parts, cost);
        maintenanceRecords.put(date, record);
    }

    public String getMaintenanceRecords() {
        StringBuilder sb = new StringBuilder();
        for (Object record : maintenanceRecords.values()) {
            sb.append(regNum).append(",").append(record.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return regNum + "," + type + "," + mileage + "," + fuelUsage + "," + driverID + "\n";
    }
}