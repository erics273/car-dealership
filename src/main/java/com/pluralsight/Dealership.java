package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        //create the dealership with no inventory
        this.inventory = new ArrayList<>();
    }

    //add vehicles to the inventory
    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    //get all the vehicles
    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    // Removes a vehicle based on VIN match
    public void removeVehicle(Vehicle vehicle) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vehicle.getVin()) {
                inventory.remove(i); // stop after removing first match
                break;
            }
        }
    }

    // Returns vehicles between the given price range
    public ArrayList<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice().compareTo(min) >= 0 && vehicle.getPrice().compareTo(max) <= 0) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Returns vehicles that match both make and model
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Returns vehicles within the year range provided
    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Returns vehicles matching the specified color
    public List<Vehicle> getVehiclesByColor(String color) {
//        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
//        for (Vehicle vehicle : inventory) {
//            if (vehicle.getColor().equalsIgnoreCase(color)) {
//                results.add(vehicle);
//            }
//        }

        List<Vehicle> results = inventory.stream()
                .filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color))
                .toList();

        return results;
    }

    // Returns vehicles within the specified mileage range
    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
//        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
//        for (Vehicle vehicle : inventory) {
//            if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) {
//                results.add(vehicle);
//            }
//        }
        return inventory.stream()
                .filter(vehicle -> vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage)
                .toList();
    }

    // Returns vehicles that match the vehicle type
    public List<Vehicle> getVehiclesByType(String type) {
//        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
//        for (Vehicle vehicle : inventory) {
//            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
//                results.add(vehicle);
//            }
//        }
        return inventory.stream()
                .filter(vehicle -> vehicle.getVehicleType().equalsIgnoreCase(type))
                .toList();
    }

    // Getters for dealership name, address, and phone
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

}
