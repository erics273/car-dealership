package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    Dealership dealership;

    public UserInterface() {

    }

    public void display(){
        init();  // Load the dealership from file
        Scanner scanner = new Scanner(System.in);
        boolean menuRunning = true;

        while (menuRunning) {
            // Show menu options
            System.out.println("\n=== Car Dealership Menu ===");
            System.out.println("1 - Find vehicles by price");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List all vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 - Quit");
            System.out.print("Enter option: ");

            // Read user input
            int option;
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                scanner.nextLine(); // discard invalid input
                option = -1;
            }

            // Route to the appropriate method
            switch (option) {
                case 1: processGetByPriceRequest(); break;
                case 2: processGetByMakeModelRequest(); break;
                case 3: processGetByYearRequest(); break;
                case 4: processGetByColorRequest(); break;
                case 5: processGetByMileageRequest(); break;
                case 6: processGetByVehicleTypeRequest(); break;
                case 7: processAllVehiclesRequest(); break;
                case 8: processAddVehicleRequest(); break;
                case 9: processRemoveVehicleRequest(); break;
                case 99:
                    System.out.println("Goodbye!");
                    menuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

    }

    private void init(){

        //create a dealership file manager
        DealershipFileManager dealership = new DealershipFileManager();
        this.dealership = dealership.getDealership();

    }

    // Search for vehicles within a price range
    public void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        BigDecimal min = scanner.nextBigDecimal();
        System.out.print("Enter maximum price: ");
        BigDecimal max = scanner.nextBigDecimal();

        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by make and model
    public void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(results);
    }

    // Search for vehicles by year range
    public void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> results = dealership.getVehiclesByYear(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by color
    public void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByColor(color);
        displayVehicles(results);
    }

    // Search for vehicles by mileage
    public void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();

        List<Vehicle> results = dealership.getVehiclesByMileage(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by type
    public void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByType(type);
        displayVehicles(results);
    }

    // Show all vehicles in the dealership
    public void processAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    // Add a new vehicle to the inventory
    public void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter mileage: ");
        int mileage = scanner.nextInt();

        System.out.print("Enter price: ");
        BigDecimal price = scanner.nextBigDecimal();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        dealership.addVehicle(newVehicle);

        DealershipFileManager dfm = new DealershipFileManager();
        dfm.saveDealership(dealership);

        System.out.println("Vehicle added successfully.");
    }

    // Remove a vehicle by VIN
    public void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();

        Vehicle vehicleToRemove = null;
        for (Vehicle currentVehicle : dealership.getAllVehicles()) {
            if (currentVehicle.getVin() == vin) {
                vehicleToRemove = currentVehicle;
                break;
            }
        }

        // Example of equivalent long form loop:
        // for (int index = 0; index < dealership.getAllVehicles().size(); index++) {
        //     Vehicle currentVehicle = dealership.getAllVehicles().get(index);
        //     if (currentVehicle.getVin() == vin) {
        //         vehicleToRemove = currentVehicle;
        //         break;
        //     }
        // }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            DealershipFileManager dfm = new DealershipFileManager();
            dfm.saveDealership(dealership);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle with that VIN not found.");
        }
    }


    public void processGetAllVehicleRequest(){

        displayVehicles(dealership.getAllVehicles());

    }

    private void displayVehicles(List<Vehicle> vehicles){

        //looper over the list and print it to the screen
        for(Vehicle vehicle : vehicles){
            //make this better, tables and such
            System.out.println(vehicle.toString());
        }

    }


}
