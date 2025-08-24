package main;

import java.util.Scanner;

public class Menu {
    public void displayMainMenu() {
        System.out.println("--- Adom LogisTics  ---");
        System.out.println("1. Vehicle Management");
        System.out.println("2. Driver Assignment");
        System.out.println("3. Delivery Tracking");
        System.out.println("4. Maintenance Scheduler");
        System.out.println("5. Fuel Efficiency Reports");
        System.out.println("6. Exit");
    }

    public void vehicleMenu(Scanner scanner) {
        System.out.println("--- Vehicle Menu ---");
        System.out.println("1. Add Vehicle");
        System.out.println("2. Remove Vehicle");
        System.out.println("3. Search Vehicle");
        System.out.println("4. List All Vehicles");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    public void driverMenu(Scanner scanner) {
        System.out.println("--- Driver Menu ---");
        System.out.println("1. Add Driver");
        System.out.println("2. Assign Driver");
        System.out.println("3. List Available Drivers");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    public void deliveryMenu(Scanner scanner) {
        System.out.println("--- Delivery Menu ---");
        System.out.println("1. Add Delivery");
        System.out.println("2. Update Delivery Status");
        System.out.println("3. List Deliveries");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    public void maintenanceMenu(Scanner scanner) {
        System.out.println("--- Maintenance Menu ---");
        System.out.println("1. Schedule Maintenance");
        System.out.println("2. List Maintenance Records");
        System.out.println("3. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    public void fuelMenu(Scanner scanner) {
        System.out.println("--- Fuel Efficiency Menu ---");
        System.out.println("1. Show Fuel Efficiency Report");
        System.out.println("2. Back to Main Menu");
        System.out.print("Select an option: ");
    }
}