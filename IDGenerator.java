package main;

import datastructures.MyHashMap;
import java.io.*;

public class IDGenerator {
    private MyHashMap usedIds;
    private int counter;

    public IDGenerator() {
        usedIds = new MyHashMap();
        counter = 1;
        loadUsedIds();
    }

    public String generateDriverId() {
        return generateUniqueId("DR");
    }

    public String generateVehicleId() {
        return generateUniqueId("VE");
    }

    public String generatePackageId() {
        return generateUniqueId("PCKGE");
    }

    public String generateDeliveryId() {
        return generateUniqueId("DEL");
    }

    private String generateUniqueId(String prefix) {
        String id;
        do {
            id = String.format("%s%04d", prefix, counter++);
        } while (usedIds.containsKey(id));
        usedIds.put(id, "1");
        saveUsedIds();
        return id;
    }

    private void loadUsedIds() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/ids.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String id = line.trim();
                usedIds.put(id, "1");
                String numericPart = id.replaceAll("[^0-9]", "");
                if (!numericPart.isEmpty()) {
                    try {
                        int num = Integer.parseInt(numericPart);
                        if (num >= counter) {
                            counter = num + 1;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    private void saveUsedIds() {
        try (FileWriter writer = new FileWriter("data/ids.txt")) {
            for (String id : usedIds.keys()) {
                writer.write(id + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving IDs: " + e.getMessage());
        }
    }
}