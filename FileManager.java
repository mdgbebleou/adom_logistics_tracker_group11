package io;

import datastructures.*;
import model.*;

import java.io.*;

public class FileManager {
    public MyBinarySearchTree loadVehicles(String fileName) {
        MyBinarySearchTree vehicles = new MyBinarySearchTree();
        try (BufferedReader reader = new BufferedReader(new FileReader(getResourcePath(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Vehicle vehicle = new Vehicle(parts[0], parts[1], Integer.parseInt(parts[2]),
                                                 Double.parseDouble(parts[3]), parts[4]);
                    vehicles.insert(vehicle);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading vehicles: " + e.getMessage());
        }
        return vehicles;
    }

    public MyQueue loadDrivers(String fileName) {
        MyQueue drivers = new MyQueue();
        try (BufferedReader reader = new BufferedReader(new FileReader(getResourcePath(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Driver driver = new Driver(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
                    if (parts.length > 4) driver.incrementDelays(); // parts[4] for delays
                    if (parts.length > 5) driver.incrementInfractions(); // parts[5] for infractions
                    if (parts.length > 6) driver.setAvailable(Boolean.parseBoolean(parts[6])); // parts[6] for isAvailable
                    drivers.enqueue(driver);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading drivers: " + e.getMessage());
        }
        return drivers;
    }

    public MyLinkedList loadDeliveries(String fileName) {
        MyLinkedList deliveries = new MyLinkedList();
        try (BufferedReader reader = new BufferedReader(new FileReader(getResourcePath(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    Delivery delivery = new Delivery(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    delivery.updateStatus(parts[6]);
                    deliveries.addLast(delivery);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading deliveries: " + e.getMessage());
        }
        return deliveries;
    }

    public void saveVehicles(MyBinarySearchTree vehicles, String fileName) {
        try (FileWriter writer = new FileWriter(getResourcePath(fileName))) {
            writer.write(vehicles.toString());
        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }

    public void saveDrivers(MyQueue drivers, String fileName) {
        try (FileWriter writer = new FileWriter(getResourcePath(fileName))) {
            writer.write(drivers.toString());
        } catch (IOException e) {
            System.out.println("Error saving drivers: " + e.getMessage());
        }
    }

    public void saveDeliveries(MyLinkedList deliveries, String fileName) {
        try (FileWriter writer = new FileWriter(getResourcePath(fileName))) {
            writer.write(deliveries.toString());
        } catch (IOException e) {
            System.out.println("Error saving deliveries: " + e.getMessage());
        }
    }

    public void saveMaintenance(MyBinarySearchTree vehicles, String fileName) {
        try (FileWriter writer = new FileWriter(getResourcePath(fileName))) {
            StringBuilder maintenanceRecords = new StringBuilder();
            vehicles.collectMaintenanceRecords(maintenanceRecords);
            writer.write(maintenanceRecords.toString());
        } catch (IOException e) {
            System.out.println("Error saving maintenance: " + e.getMessage());
        }
    }

    private String getResourcePath(String fileName) {
        File file = new File("data/" + fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        return file.getPath();
    }
}