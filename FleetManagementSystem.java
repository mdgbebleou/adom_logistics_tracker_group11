package main;

import datastructures.*;
import model.*;
import io.FileManager;
import java.util.Scanner;

public class FleetManagementSystem {
    private static FileManager fileManager = new FileManager();
    private static MyBinarySearchTree vehicles = fileManager.loadVehicles("vehicles.txt");
    private static MyQueue drivers = fileManager.loadDrivers("drivers.txt");
    private static MyLinkedList deliveries = fileManager.loadDeliveries("deliveries.txt");
    private static MyMinHeap maintenanceHeap = new MyMinHeap(100);
    private static MyArray vehicleArray = new MyArray(100);
    private static Menu menu = new Menu();
    private static IDGenerator idGenerator = new IDGenerator();

    public static void main(String[] args) {
        loadHeapAndArray(vehicles, maintenanceHeap, vehicleArray);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            menu.displayMainMenu();
            System.out.print("Select an option: ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    vehicleMenu(scanner);
                    break;
                case 2:
                    driverMenu(scanner);
                    break;
                case 3:
                    deliveryMenu(scanner);
                    break;
                case 4:
                    maintenanceMenu(scanner);
                    break;
                case 5:
                    fuelMenu(scanner);
                    break;
                case 6:
                    fileManager.saveVehicles(vehicles, "vehicles.txt");
                    fileManager.saveDrivers(drivers, "drivers.txt");
                    fileManager.saveDeliveries(deliveries, "deliveries.txt");
                    fileManager.saveMaintenance(vehicles, "maintenance.txt");
                    System.out.println("Data saved. Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void vehicleMenu(Scanner scanner) {
        while (true) {
            menu.vehicleMenu(scanner);
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter type, mileage, fuelUsage, driverID: ");
                    String[] vehicleData = scanner.nextLine().split(",");
                    if (vehicleData.length == 4) {
                        try {
                            String regNum = idGenerator.generateVehicleId();
                            Vehicle vehicle = new Vehicle(regNum, vehicleData[0], Integer.parseInt(vehicleData[1]),
                                                         Double.parseDouble(vehicleData[2]), vehicleData[3]);
                            vehicles.insert(vehicle);
                            maintenanceHeap.insert(vehicle);
                            vehicleArray.add(vehicle);
                            fileManager.saveVehicles(vehicles, "vehicles.txt");
                            System.out.println("Vehicle added with ID: " + regNum);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid mileage or fuelUsage format.");
                        }
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;
                case 2:
                    System.out.print("Enter regNum to remove: ");
                    String regNumRemove = scanner.nextLine();
                    Vehicle removed = vehicles.remove(regNumRemove);
                    if (removed != null) {
                        fileManager.saveVehicles(vehicles, "vehicles.txt");
                        loadHeapAndArray(vehicles, maintenanceHeap, vehicleArray);
                        System.out.println("Vehicle removed.");
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter regNum to search vehicle: ");
                    String regNumSearch = scanner.nextLine();
                    Vehicle foundVehicle = vehicles.search(regNumSearch);
                    System.out.println(foundVehicle != null ? foundVehicle.toString() : "Vehicle not found.");
                    break;
                case 4:
                    System.out.println("All Vehicles:");
                    System.out.println(vehicles.toString());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void driverMenu(Scanner scanner) {
        while (true) {
            menu.driverMenu(scanner);
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter name, proximity, experience: ");
                    String[] driverData = scanner.nextLine().split(",");
                    if (driverData.length == 3) {
                        try {
                            String driverId = idGenerator.generateDriverId();
                            Driver driver = new Driver(driverId, driverData[0], Double.parseDouble(driverData[1]), Integer.parseInt(driverData[2]));
                            drivers.enqueue(driver);
                            fileManager.saveDrivers(drivers, "drivers.txt");
                            System.out.println("Driver added with ID: " + driverId);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid proximity or experience format.");
                        }
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;
                case 2:
                    System.out.print("Enter packageId to assign driver: ");
                    String packageIdAssign = scanner.nextLine();
                    Delivery deliveryAssign = (Delivery) deliveries.find(packageIdAssign);
                    if (deliveryAssign != null) {
                        Driver driver = findAvailableDriver(deliveryAssign.getOrigin());
                        if (driver != null) {
                            deliveryAssign.updateStatus("Assigned");
                            deliveryAssign.setDriverId(driver.getId());
                            Vehicle vehicle = vehicles.search(deliveryAssign.getVehicleRegNum());
                            if (vehicle != null) {
                                vehicle.setDriverID(driver.getId());
                                driver.setAvailable(false);
                                fileManager.saveDeliveries(deliveries, "deliveries.txt");
                                fileManager.saveVehicles(vehicles, "vehicles.txt");
                                fileManager.saveDrivers(drivers, "drivers.txt");
                                System.out.println("Driver " + driver.getName() + " assigned (proximity: " + driver.getProximity() + " km).");
                            } else {
                                System.out.println("Vehicle not found for delivery.");
                            }
                        } else {
                            System.out.println("No available drivers found.");
                        }
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    break;
                case 3:
                    System.out.println("Available Drivers:");
                    MyQueue tempQueue = new MyQueue();
                    boolean found = false;
                    while (!drivers.isEmpty()) {
                        Driver driver = (Driver) drivers.dequeue();
                        if (driver.isAvailable()) {
                            System.out.println(driver.toString());
                            found = true;
                        }
                        tempQueue.enqueue(driver);
                    }
                    while (!tempQueue.isEmpty()) {
                        drivers.enqueue(tempQueue.dequeue());
                    }
                    if (!found) {
                        System.out.println("No available drivers.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void deliveryMenu(Scanner scanner) {
        while (true) {
            menu.deliveryMenu(scanner);
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter origin, destination, vehicleRegNum, driverId, eta: ");
                    String[] deliveryData = scanner.nextLine().split(",");
                    if (deliveryData.length == 5) {
                        String packageId = idGenerator.generatePackageId();
                        Delivery newDelivery = new Delivery(packageId, deliveryData[0], deliveryData[1], deliveryData[2], deliveryData[3], deliveryData[4]);
                        deliveries.addLast(newDelivery);
                        if (!deliveryData[3].isEmpty() && !deliveryData[3].equals("None")) {
                            Driver driver = drivers.find(deliveryData[3]);
                            if (driver != null && driver.isAvailable()) {
                                driver.setAvailable(false);
                                fileManager.saveDrivers(drivers, "drivers.txt");
                            } else if (driver != null) {
                                System.out.println("Warning: Specified driver is unavailable.");
                            }
                        }
                        fileManager.saveDeliveries(deliveries, "deliveries.txt");
                        System.out.println("Delivery added with ID: " + packageId);
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;
                case 2:
                    System.out.print("Enter packageId, new status (e.g., Picked, En Route, Delivered): ");
                    String[] statusData = scanner.nextLine().split(",");
                    if (statusData.length == 2) {
                        Delivery statusDelivery = (Delivery) deliveries.find(statusData[0]);
                        if (statusDelivery != null) {
                            String oldStatus = statusDelivery.getStatus();
                            statusDelivery.updateStatus(statusData[1]);
                            String driverId = statusDelivery.getDriverId();
                            if (driverId != null && !driverId.isEmpty()) {
                                Driver driver = drivers.find(driverId);
                                if (driver != null) {
                                    if (statusData[1].equals("Delivered") || statusData[1].equals("Cancelled")) {
                                        driver.setAvailable(true);
                                    } else if (!oldStatus.equals("Delivered") && !oldStatus.equals("Cancelled")) {
                                        driver.setAvailable(false);
                                    }
                                    fileManager.saveDrivers(drivers, "drivers.txt");
                                }
                            }
                            fileManager.saveDeliveries(deliveries, "deliveries.txt");
                            System.out.println("Delivery status updated to: " + statusData[1]);
                        } else {
                            System.out.println("Delivery not found.");
                        }
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;
                case 3:
                    System.out.println("All Deliveries:");
                    System.out.println(deliveries.toString());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void maintenanceMenu(Scanner scanner) {
        while (true) {
            menu.maintenanceMenu(scanner);
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter regNum, date, parts, cost: ");
                    String[] maintenanceData = scanner.nextLine().split(",");
                    if (maintenanceData.length == 4) {
                        try {
                            Vehicle vehicle = vehicles.search(maintenanceData[0]);
                            if (vehicle != null) {
                                vehicle.addMaintenance(maintenanceData[1], maintenanceData[2], Double.parseDouble(maintenanceData[3]));
                                fileManager.saveMaintenance(vehicles, "maintenance.txt");
                                System.out.println("Maintenance record added.");
                            } else {
                                System.out.println("Vehicle not found.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid cost format.");
                        }
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;
                case 2:
                    System.out.println("Maintenance Schedule:");
                    while (!maintenanceHeap.isEmpty()) {
                        Vehicle v = maintenanceHeap.extractMin();
                        System.out.println(v.getRegNum() + ": " + v.getMileage() + " miles");
                    }
                    loadHeapAndArray(vehicles, maintenanceHeap, vehicleArray);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void fuelMenu(Scanner scanner) {
        while (true) {
            menu.fuelMenu(scanner);
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    vehicleArray.quickSortByFuelUsage();
                    System.out.println("Fuel Efficiency Report:");
                    System.out.println("Average Fuel Usage: " + vehicleArray.getAverageFuelUsage());
                    System.out.println("Outliers: \n" + vehicleArray.getFuelOutliers());
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static Driver findAvailableDriver(String pickupLocation) {
        MyQueue tempQueue = new MyQueue();
        Driver closestDriver = null;
        double minProximity = Double.MAX_VALUE;

        while (!drivers.isEmpty()) {
            Driver driver = (Driver) drivers.dequeue();
            if (driver.isAvailable() && driver.getProximity() < minProximity) {
                minProximity = driver.getProximity();
                closestDriver = driver;
            }
            tempQueue.enqueue(driver);
        }

        while (!tempQueue.isEmpty()) {
            drivers.enqueue(tempQueue.dequeue());
        }

        return closestDriver;
    }

    private static void loadHeapAndArray(MyBinarySearchTree vehicles, MyMinHeap heap, MyArray array) {
        while (!heap.isEmpty()) {
            heap.extractMin();
        }
        array = new MyArray(100);
        vehicles.inOrderTraversal(heap, array);
    }
}